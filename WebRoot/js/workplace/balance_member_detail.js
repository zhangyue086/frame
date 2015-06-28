/**
 * Description: 配送员结算详细js
 * 
 * @author zhangzhitao
 * 
 * @date 2015-6-11
 * 
 * @version 1.0
 */
var balance_member_detail_list_table1;
var balance_member_detail_list_table2;

$(function(){
		//初始化 待结算
		balance_member_detail_list_table1 = $('#balance_member_detail_list_table1').dataTable({
			"processing": true,
	        "bLengthChange" : true,
	        "bFilter": false,
	        "bSort":false
		});
		//初始化 结算明细
		balance_member_detail_list_table2 = $('#balance_member_detail_list_table2').dataTable({
			"processing": true,
			"bLengthChange" : true,
			"bFilter": false,
			"bSort":false
		});
		//全部结算
		$("#balance_member_detail_list_balance_all").die().live("click",function(){
			$.post($(this).attr("data-url"),null,function(result){
				if(result){
					if(!result.isSuccess){
						alert(result.info);
					}else{
						alert("全部结算成功!");
						loadHtml('/balance/initBalance_memberBalance.do');
					}
				}else{
					alert("结算异常! 无返回结果!");
				}
			});
		});
		//结算单个
		$("#balance_member_detail_list_balance_one").die().live("click",function(){
			$.post($(this).attr("data-url"),null,function(result){
				if(result){
					if(!result.isSuccess){
						alert(result.info);
					}else{
						$("#balance_refresh").click();
					}
				}else{
					alert("结算异常! 无返回结果!");
				}
			});
		});
});
