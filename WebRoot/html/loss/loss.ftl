<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="/js/workplace/loss.js"></script>
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
					<li><a href="#">异常处理(报损)</a></li>
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
								<div class="span3">
									<div class="control-group">
										<label class="control-label">城市：</label>
										<div class="controls">
											<select name="" id="city" class="select2-container span12 select2_category">
												<option value=""></option>
												<#list cityList as city >
													<option value="#{(city.cityId)!0}">${(city.cityName)!'未知城市'}</option>
												</#list>
											</select>
										</div>
									</div>
								</div>
								<div class="span3">
									<div class="control-group">
										<label class="control-label">物流点：</label>
										<div class="controls">
											<select name="" id="loss_station" class="span12 select2-container">
												<option value=""></option>
											</select>
										</div>
									</div>
								</div>
								<div class="span4">
									<div class="control-group">
										<label class="control-label">日期:</label>
										<div class="controls">
											<div data-date-viewmode="years" data-date-format="dd-mm-yyyy" data-date="12-02-2012" class="input-append date date-picker">
												<input id="loss_search_date" placeholder="选择一个日期" type="text" value="${.now?string('yyyy-MM-dd')}"  readonly="" class="m-wrap m-ctrl-medium date-picker"><span class="add-on"><i class="icon-calendar"></i></span>
											</div>
										</div>
									</div>
								</div>
								<div class="span2">
									<div class="control-group">
										<label class="control-label">&nbsp;</label>
										<div class="controls">
											<button class="btn blue" id="loss_search">
												查询 <i class="m-icon-swapright m-icon-white"></i>
											</button>
										</div>
									</div>
								</div>

							</div>
							<div class="row-fluid">
								<div class="span3 ">
									<div class="control-group">
										<label class="control-label">关键词：</label>
										<div class="controls">
											<select name="" id="loss_search_keyword_condition" class="span12">
												<option value="1">姓名</option>
												<option value="2">工号</option>
											</select>
										</div>
									</div>
								</div>
								<div class="span7">
									<div class="control-group">
										<label class="control-label">姓名或者工号:</label>
										<div class="controls">
											<input type="text" class="m-wrap span5" id="queryKeyWord" style="background-color: white" placeholder="姓名或者工号" onkeydown="keydownOnPassword('loss_search');" />
										</div>
									</div>
								</div>
								<div class="span2">
									<div class="control-group">
										<label class="control-label">&nbsp;</label>
										<div class="controls">
											<button class="btn blue" id="loss_import">
												导出 <i class="icon-share m-icon-white"></i>
											</button>
										</div>
									</div>
								</div>

							</div>
						</div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="loss_table1">
							<thead>
								<tr>
									<th>配送日期</th>
									<th>城市</th>
									<th>物流点</th>
									<th>工号</th>
									<th>配送员</th>
									<th>运单号</th>
									<th>应收</th>
									<th>实收</th>
									<th>损失</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
							<#--	<tr>
									<td>2015-01-01</td>
									<td>南京</td>
									<td>奥特曼</td>
									<td>3</td>
									<td>陈悦</td>
									<td>20150989qinxl09567</td>
									<td>100</td>
									<td>90</td>
									<td>10</td>
									<td><a id="dealLossDetail" data-url="/loss/initLossDetail_loss.do?status=0" href="#myModal" data-toggle="modal"  class="btn btn-primary btn-large">处理</a></td>
								</tr>
								<tr>
									<td>2015-01-01</td>
									<td>南京</td>
									<td>猎户座</td>
									<td>3</td>
									<td>陈悦</td>
									<td>20150989qinxl09567</td>
									<td>1100</td>
									<td>900</td>
									<td>100</td>
									<td><a id="toLossDetail" data-url="/loss/initLossDetail_loss.do?status=1"   href="#myModal" data-toggle="modal"  class="btn btn-primary btn-large">详情</a></td>
								</tr> -->
							</tbody>
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
	$("#dealLossDetail").off("click").on("click",function(){
		$("#myModal").load($(this).attr("data-url"));		
	});
	$("#toLossDetail").off("click").on("click",function(){
		$("#myModal").load($(this).attr("data-url"));		
	});
	function keydownOnPassword(id){
	    var theEvent = window.event || arguments.callee.caller.arguments[0];
		var key = theEvent.which||theEvent.keyCode;
		if(key == 13){
		   $("#"+id).click();
		}
	}
</script>
</html>