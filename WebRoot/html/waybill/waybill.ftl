<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/workplace/waybill.js"></script>
<title>运单查询</title>
</head>
<body>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN STYLE CUSTOMIZER -->
			
			<!-- END BEGIN STYLE CUSTOMIZER --> 
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">
			</h3>
			<ul class="breadcrumb" style="margin-bottom: 5px;">
				<li>
					<i class="icon-home"></i>
					<a href="index.do">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">工作台</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">运单查询</a></li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN PAGE CONTENT-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN SAMPLE FORM PORTLET-->   
			<div class="portlet box">
				<div class="portlet-body">
					<div class="portlet" style="background-color:rgb(229,234,238);padding:10px">
						<div class="row-fluid" >
							<div class="input-append span3">  
								<input type="text" id="waybill_list_order_code" value="${keyword!''}" onkeydown="keydownOnPassword(this);" placeholder="输入运单号或运单号后5位" size="12" class="input-xlarge m-wrap medium" required style="background-color:white;">
								<a class="btn blue" id="waybill_list_search">搜索
								<i class="m-icon-swapright m-icon-white"></i>
								</a>
							</div>
							<!-- <div id="warning" class="alert alert-warning span3" >
								<a href="#" class="close" data-dismiss="alert">
								</a>
								<strong>警告！</strong>请输入订单号或者订单号后5位。
							</div> -->
					</div>
				</div>
					<table class="table table-striped table-bordered table-hover table-full-width" id="waybill_list_table1">
						<thead>
							<tr>
								<th>运单id</th>
								<th>配送日期</th>
							    <th>运单号</th>
							    <th>配送员</th>
							    <th>商家名称</th>
							    <th>运单状态</th>
							    <th>运单金额  </th>
							    <th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<!-- END SAMPLE FORM PORTLET-->			
		</div>
	</div>
	<!-- END PAGE CONTENT-->
</div>
<!-- END PAGE CONTAINER--> 
</div>
</body>
<script>
		$(function(){
		//详情页回显
				var keyword = $.trim($("#waybill_list_order_code").val());
				if(keyword !=null && keyword !=''){
					$("#waybill_list_search").click();
				}
		});
	function keydownOnPassword(e){
	    var theEvent = window.event || arguments.callee.caller.arguments[0];
		var key = theEvent.which||theEvent.keyCode;
		if(key == 13){
		   $("#waybill_list_search").click();
		}
	}
</script>
</html>