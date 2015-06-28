/**
 * Description: 配送员结算 js
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-10
 * 
 * @version 1.0
 */
var balance_member_list_table1;

$(function(){
	balance_member_list_table1 = $('#balance_member_list_table1').dataTable({
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
	                    {"data": "balanceDate"},
	                    {"data": "member.workNumber"},
	                    {"data": "member.distributionMemberName"},
	                    {"data": "member.cityName"},
	                    {"data": "member.distributionStationName"},
	                    {"data": "needPayTotal"},
	                    {"data": "member.workNumber"}
	                  ],
	        "columnDefs": [
	                       {
	                           "targets": [6],
	                           "data": "member.workNumber",
	                           "render": function(data, type, full) {
	                        	 var html = "<a data-url='/balance/initDetail_memberBalance.do?dmWorkNumber="+data+"&searchDate="+full.balanceDate+"'  id='balance_member_list_detail_info' class='btn purple  btn-large'>查看详情</a>";
	                             return html;
	                           }
	                         }
	                     ]
			
		});
		//日历选项
		$('#balance_member_list_search_date').datepicker({ 
			format: 'yyyy-mm-dd',	//时间格式
			autoclose:true,        //选择后立刻关闭
			clearBtn:true,        //清除按钮
			todayHighlight:true   //高亮今天
		});
		//搜索田间
		$("#balance_member_list_search_condition").select2();
		
		$("#balance_member_list_detail_info").die().live("click",function(){
			loadHtml($(this).attr("data-url"));		
		});
		$("#balance_member_list_search_button").die().live("click",function(){
			var type = $.trim($("#balance_member_list_search_condition").val());
			var keyword =$.trim($("#balance_member_list_keyword").val());
			var searchDate = $.trim($("#balance_member_list_search_date").val());
			var data={};
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
			data = {"keyword":keyword,"searchDate":searchDate};
			
			balance_member_list_table1.fnSettings().ajax={
				"url": "/balance/queryTotalInfo_memberBalance.do?now=" + new Date().getTime(),
		        "type": "POST",
		        "data":data
			};
			balance_member_list_table1.fnDraw();
		});
});
