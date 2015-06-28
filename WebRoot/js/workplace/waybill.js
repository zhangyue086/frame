/**
 * Description: 运单搜索页面js
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-8
 * 
 * @version 1.0
 */
var waybill ={};
var waybill_list_table1;
$(function(){
		waybill_list_table1 = $('#waybill_list_table1').dataTable({
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
	                    {"data":"logisticsOrderId","bVisible":false},
	                    {"data": "shippingTime"},
	                    {"data": "logisticsOrderCode"},
	                    {"data": "dmName"},
	                    {"data": "shopName"},
	                    {"data": "logisticsOrderStatus"},
	                    {"data": "orderPrice"},
	                    {"data": "logisticsOrderId"},
	                  ],
	        "columnDefs": [
	                       {
		                         "targets": [1],
		                         "data": "shippingTime",
		                         "render": function(dateObj) {
		                        	 return dateObj.replace("T"," ");
		                         }
	                       },
	                       {
	                         "targets": [5],
	                         "data": "logisticsOrderStatus",
	                         "render": function(data, type, full) {
	                           if(data == 0){
	                        	  return "初始状态"; 
	                           }else if(data == 1){
	                        	   return "揽货中"; 
	                           }else if(data == 2){
	                        	   return "配送中"; 
	                           }else if(data == 3){
	                        	   return "已送达"; 
	                           }else if(data == 4){
	                        	   return "已取消"; 
	                           }
	                         }
	                       },
	                       {
	                           "targets": [7],
	                           "data": "logisticsOrderId",
	                           "render": function(data, type, full) {
	                        	 var html = "<a id='waybill_list_detail_info' data-url='/waybill/initDetail_waybill.do?logisticsOrderId="+data+"'    class='btn green btn-large'>查看详情</a>";
	                             return html;
	                           }
	                         }
	                     ]
			
		});
		
		
		$("#waybill_list_detail_info").die().live('click', function() {
			var keyword = $("#waybill_list_order_code").val();
			
			loadHtml($(this).attr("data-url")+"&keyword="+keyword);
		});
		$("#waybill_list_search").die().live("click",function(){
			var orderCode = $.trim($("#waybill_list_order_code").val());
			$("#waybill_list_order_code").val(orderCode);
			if(orderCode.length < 5 ){
				alert("请输入正确的运单号!");
				return;
			}
			 var reg = new RegExp("^[0-9]5$");
			if(orderCode.length == 5 && !reg.test(orderCode) ){
				alert("请输入正确的运单号后5位!");
				return;
			}
			waybill_list_table1.fnSettings().ajax={
				"url": "/waybill/queryList_waybill.do?now=" + new Date().getTime(),
		        "type": "POST",
		        "data":{"logisticsOrderCode":orderCode}
			};
			waybill_list_table1.fnDraw();
		});
});
