<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/workplace/balance_member.js"></script>
<title>配送员结算</title>
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
				<h3 class="page-title"></h3>
				<ul class="breadcrumb" style="margin-bottom: 5px;">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">工作台</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">结算(配送员)</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div class="row-fluid">
			<div class="span12">
				<!-- BEGIN SAMPLE FORM PORTLET-->
				<div class="portlet  box">
					<div class="portlet-body" >
						<div class="portlet"
							style="background-color: rgb(229, 234, 238); padding: 10px">
							<div class="row-fluid">
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">关键词：</label>
										<div class="controls">
											<select name="" id="balance_member_list_search_condition" class="span12">
												<option value="1">姓名</option>
												<option value="2">工号</option>
											</select>
										</div>
									</div>
								</div>
								<div class="span2">
									<div class="control-group">
										<label class="control-label">姓名或者工号:</label>
										<div class="controls">
											<input type="text" class="m-wrap span12 " id="balance_member_list_keyword" onkeydown="keydownOnPassword('balance_member_list_search_button');" style="background-color: white" placeholder="姓名或者工号"  value=""/>
										</div>
									</div>
								</div>

								<div class="span3">
									<div class="control-group">
										<label class="control-label">日期:</label>
										<div class="controls">
											<div data-date-viewmode="years" data-date-format="yyyy-MM-dd"  class="input-append date date-picker">
												<input id="balance_member_list_search_date" onkeydown="keydownOnPassword('balance_member_list_search_button');" placeholder="选择一个日期" type="text" value="${.now?string('yyyy-MM-dd')}"  size="12" readonly="" class="m-wrap m-ctrl-medium date-picker"><span class="add-on"><i class="icon-calendar"></i></span>
											</div>
										</div>
									</div>
								</div>
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">&nbsp;</label>
										<div class="controls">
											<button class="btn blue" id="balance_member_list_search_button">
												搜索 <i class="m-icon-swapright m-icon-white"></i>
											</button>
										</div>
									</div>
								</div>

							</div>
						</div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="balance_member_list_table1">
							<thead>
								<tr>
									<th>结算日期</th>
									<th>工号</th>
									<th>姓名</th>
									<th>城市</th>
									<th>物流点</th>
									<th>待结算金额</th>
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
</body>
<script>
	function keydownOnPassword(id){
	    var theEvent = window.event || arguments.callee.caller.arguments[0];
		var key = theEvent.which||theEvent.keyCode;
		if(key == 13){
		   $("#"+id).click();
		}
	}
</script>
</html>