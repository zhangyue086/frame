/**
 * Description: 零号线结算
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-12
 * 
 * @version 1.0
 */
var balance_line0_table1;
$(function(){
	balance_line0_table1 = $('#balance_line0_table1').dataTable({
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
	                    {"data":"balanceDate"},
	                    {"data": "city.cityName"},
	                    {"data": "station.distributionStationName"},
	                    {"data": "waitBalanceTotal"},
	                    {"data": "hasRecordToday"},
	                    {"data": "balanceDate"}
	                  ],
	        "columnDefs": [
	                       {
		                         "targets": [4],
		                         "data": "hasRecordToday",
		                         "render": function(data, type, full) {
		                             return data?"<font color='green'>已结算</font>":"<font color='red'>未结算</font>";
		                           }
	                       },
	                       {
		                         "targets": [5],
		                         "data": "balanceDate",
		                         "render": function(data, type, full) {
		                        	 var params ="cityId="+full.city.cityId+"&stationId="+full.station.distributionStationId+"&searchDate="+full.balanceDate;
		                        	 var html ="";
		                        	 if(full.hasRecordToday){
		                        		  html = "<a class='btn purple btn-large disabled'>结算</a>";
		                        	 }else{
		                        		 html = "<a data-url='/balance/balanceLine0_line0Balance.do?"+params+"'   id='goto_balance_line0' class='btn purple btn-large'>结算</a>";
		                        	 }
		                             return html;
		                           }
	                       }
	                     ]
			
		});
		$("#city").select2({placeholder:"请选择一个城市"});
		$("#line0_station").select2({placeholder:"请选择一个站点"});
		$("#city").die().live("change",function(){
			$('#line0_station').html("<option value=''></option>");
			$("#line0_station").select2({placeholder:"请选择一个站点"});
			var cityId = $("#city option:selected").val();
			if(cityId != null && cityId !=''){
				$.ajax({
		            type: "POST",
		            cache:true,
		            data:{"cityId":cityId},
		            url: "/balance/queryStation_line0Balance.do",
		            success: function(data){
		            	$('#line0_station').html("<option value=''></option>");
		    			var html ="";
		    			$.each(data,function(i,n){
		    				html+="<option value='"+n.distributionStationId+"'>"+n.distributionStationName+"</option>";
		    			});
		    			$('#line0_station').append(html);
		            },
		            error:function(data){
		            	alert(data);
		            }
		        });
			}else{
				alert("异常");
			}
		})
		//日历选项
		$('#balance_line0_search_date').datepicker({ 
			format: 'yyyy-mm-dd',	//时间格式
			autoclose:true,        //选择后立刻关闭
			clearBtn:true,        //清除按钮
			todayHighlight:true   //高亮今天
		});
		$("#line0_search_button").die().live("click",function(){
			var cityId = $("#city option:selected").val();
			var stationId = $("#line0_station option:selected").val();
			var searchDate = $.trim($("#balance_line0_search_date").val());
			var dateReg = /^(\d{4})-(\d{2})-(\d{2})$/
			if (!dateReg.test(searchDate)) { 
				alert("日期格式不正确!") 
				return ; 
			} 
			if(cityId == null || cityId ==""){
				alert("请选择一个城市");
				return; 
			}
			if(stationId == null || stationId ==""){
				alert("请选择一个物流站点");
				return; 
			}
			
			balance_line0_table1.fnSettings().ajax={
				"url": "/balance/queryStationBalance_line0Balance.do?now=" + new Date().getTime(),
		        "type": "POST",
		        "data":{"cityId":cityId,"balancedStationId":stationId,"searchDate":searchDate,}
			};
			balance_line0_table1.fnDraw();
		});
		$("#goto_balance_line0").die().live("click",function(){
			$.post($(this).attr("data-url"),null,function(data){
				if(data != null && data !=''){
					if(data.isSuccess){
						$("#line0_search_button").click();
					}else{
						alert("结算异常:"+data.info);
					}	
				}
			});
			
		});
});
