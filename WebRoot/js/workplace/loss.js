/**
 * Description: 报损
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-12
 * 
 * @version 1.0
 */
var loss_table1;
$(function(){
	loss_table1 = $('#loss_table1').dataTable({
			"processing": true,
	        "serverSide": true,
	        "bLengthChange" : false,
	        "bFilter": false,
	        "bSort":false,
	        "ajax":{
				"url": "",
		        "type": "POST",
		        "data":{}
			},
	        "columns": [
	                    {"data":"lossId","bVisible":false},
	                    {"data": "city.cityName"},
	                    {"data": "station.distributionStationName"},
	                    {"data": "dmWorkNumber"},
	                    {"data": "dmName"},
	                    {"data": "order.logisticsOrderCode"},
	                    {"data": "needPay"},
	                    {"data": "needPayReceive"},
	                    {"data": "lossAccount"},
	                    {"data": "lossDesc"},
	                    {"data": "start","bVisible":false},
	                    {"data": "length","bVisible":false}
	                  ],
	        "columnDefs": [
	                       {
		                         "targets": [9],
		                         "data": "lossId",
		                         "render": function(data, type, full) {
		                        	 if(data == null || data == ""){
		                    			   var html = "<a data-url='/loss/initLossDetail_loss.do?lossId="+full.lossId+"' href='#myModal' data-toggle='modal' id='dealWithLoss' class='btn purple'>处理</a>";
		                    			   return html;
		                    		   }else{
		                    			   var html = "<a data-url='/loss/initLossDetail_loss.do?lossId="+full.lossId+"' href='#myModal' data-toggle='modal' id='dealWithLoss' class='btn green'>详情</a>";
		                    			   return html;
		                    		   }
		                           }
	                       },
	                       {
	                    	   "targets": [10],
	                    	   "data": "start",
	                    	   "render": function(data, type, full) {
	                    	   }
	                       },
	                       {
	                    	   "targets": [11],
	                    	   "data": "length",
	                    	   "render": function(data, type, full) {
	                    	   }
	                       }
	                     ]
			
		});
		$("#city").select2({placeholder:"请选择一个城市"});
		$("#loss_station").select2({placeholder:"请选择一个站点"});
		$("#loss_search_keyword_condition").select2();
		$("#city").die().live("change",function(){
			$('#loss_station').html("<option value=''></option>");
			$("#loss_station").select2({placeholder:"请选择一个站点"});
			var cityId = $("#city option:selected").val();
			if(cityId != null && cityId !=''){
				$.ajax({
		            type: "POST",
		            cache:true,
		            data:{"cityId":cityId},
		            url: "/balance/queryStation_memberBalance.do",
		            success: function(data){
		            	$('#loss_station').html("<option value=''></option>");
		    			var html ="";
		    			$.each(data,function(i,n){
		    				html+="<option value='"+n.distributionStationId+"'>"+n.distributionStationName+"</option>";
		    			});
		    			$('#loss_station').append(html);
		            },
		            error:function(data){
		            	alert(data.statusText);
		            }
		        });
			}else{
				alert("异常");
			}
		})
		//日历选项
		$('#loss_search_date').datepicker({ 
			format: 'yyyy-mm-dd',	//时间格式
			autoclose:true,        //选择后立刻关闭
			clearBtn:true,        //清除按钮
			todayHighlight:true   //高亮今天
		});
		$("#loss_search").die().live("click",function(){
			var cityId = $("#city option:selected").val();
			var stationId = $("#loss_station option:selected").val();
			var searchDate = $.trim($("#loss_search_date").val());
			var type = $.trim($("#loss_search_keyword_condition").val());
			var keyword =$.trim($("#queryKeyWord").val());
			if(cityId == null || cityId ==""){
				alert("请选择一个城市");
				return; 
			}
			if(stationId == null || stationId ==""){
				alert("请选择一个物流站点");
				return; 
			}
			var dateReg = /^(\d{4})-(\d{2})-(\d{2})$/
			if (!dateReg.test(searchDate)) { 
				alert("日期格式不正确!") 
				return ; 
			} 
			//0:空 1:姓名:2:工号
			if(type == ""){
				alert("请选择一个查询条件");
				return; 
			}else if(type == "1"){
				var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
				var isCn = keyword.match(reg) != null;
				if (!isCn){
					alert("请输入正确的姓名!");
					return;
				}
			}else if(type == "2"){
			    if(!/^[0-9]+$/.test(keyword)){
			        alert("请输入正确的工号!");
			        return;
			    }
			}else{
				alert("异常!");
				return;
			}
			var data={"cityId":cityId,"stationId":stationId,"searchDate":searchDate,"searchType":type,"keyword":keyword};
			loss_table1.fnSettings().ajax={
				"url": "/loss/queryList_loss.do?now=" + new Date().getTime(),
		        "type": "POST",
		        "data":data
			};
			loss_table1.fnDraw();
		});
		//导出
		$("#loss_import").die().live("click",function(){
			var cityId = $("#city option:selected").val();
			var stationId = $("#loss_station option:selected").val();
			var searchDate = $.trim($("#loss_search_date").val());
			var type = $.trim($("#loss_search_keyword_condition").val());
			var keyword =$.trim($("#queryKeyWord").val());
			if(cityId == null || cityId ==""){
				alert("请选择一个城市");
				return; 
			}
			if(stationId == null || stationId ==""){
				alert("请选择一个物流站点");
				return; 
			}
			var dateReg = /^(\d{4})-(\d{2})-(\d{2})$/
				if (!dateReg.test(searchDate)) { 
					alert("日期格式不正确!") 
					return ; 
				} 
			//0:空 1:姓名:2:工号
			if(type == ""){
				alert("请选择一个查询条件");
				return; 
			}else if(type == "1"){
				var reg = /^[\u4E00-\u9FA5\uF900-\uFA2D]+$/;
				var isCn = keyword.match(reg) != null;
				if (!isCn){
					alert("请输入正确的姓名!");
					return;
				}
			}else if(type == "2"){
				if(!/^[0-9]+$/.test(keyword)){
					alert("请输入正确的工号!");
					return;
				}
			}else{
				alert("异常!");
				return;
			}
			var params = "cityId="+cityId+"&stationId="+stationId+"&searchDate="+searchDate+"&searchType="+type+"&keyword="+keyword;
			 window.open("/loss/importLossFile_loss.do?"+params);
		});
		$("#dealWithLoss").die().live("click",function(){
			$("#myModal").load($(this).attr("data-url"));
		});
});
