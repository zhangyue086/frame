<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE HTML>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<!-- BEGIN PAGE CONTAINER-->
<div class="container-fluid">
	<!-- BEGIN PAGE HEADER-->
	<div class="row-fluid">
		<div class="span12">
			<h3 class="page-title">
			</h3>
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="index.do">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">工作台</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">配送员考勤</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
			<div class="tabbable tabbable-custom">
					<ul class="nav nav-tabs">
						<li class="active"><a data-toggle="tab" href="#tab_1_1">打卡(排班)</a></li>
						<li><a data-toggle="tab" href="#tab_1_2">打卡(加班)</a></li>
						<li><a data-toggle="tab" href="#tab_1_3">请假</a></li>
					</ul>
					<div class="tab-content">
											<!-- BEGIN TAB_1_1 -->   
						<div id="tab_1_1" class="tab-pane active">
								<div class="row-fluid">
										<div class="span12">
											<div class="portlet box">
												<div class="portlet-body">
													<div style="background-color:rgb(229,234,238);padding:10px" class="portlet">
														<div class="row-fluid">
															<div class="input-append">  
																<input type="text" style="background-color:white;" class="input-xlarge m-wrap medium" size="12" placeholder="输入配送员工号">
																<button class="btn blue">搜索
																<i class="m-icon-swapright m-icon-white"></i>
																</button>
															</div>
															<input type="hidden" value="1,2" id="stationStatus">
													</div>
												</div>
													<table id="tab_1_1" class="table table-striped table-bordered table-hover table-full-width">
														<thead>
															<tr>
																<th>工号</th>
																<th>姓名</th>
															    <th>城市</th>
															    <th>物流点</th>
															    <th>班次</th>
															    <th>操作</th>
															</tr>
															<tr>
																<td>123</td>
																<td>陈悦</td>
																<td>南京</td>
																<td>新街口</td>
																<td>11:00 ~ 12:00</td>
																<td><a class="btn btn-primary btn-large" href="#" >上班</a></td>
															</tr>
														</thead>
													</table>
												</div>
											</div>
										</div>
									</div>
						</div>
							<!-- END TAB_1_1-->			
							<!-- BEGIN TAB_1_2-->			
						<div id="tab_1_2" class="tab-pane">
								<div class="row-fluid">
									<div class="span12">
										<div class="portlet box">
											<div class="portlet-body">
												<div style="background-color:rgb(229,234,238);padding:10px" class="portlet">
													<div class="row-fluid">
														<div class="input-append">  
															<input type="text" style="background-color:white;" class="input-xlarge m-wrap medium" size="12" placeholder="输入配送员工号">
															<button class="btn blue">搜索
															<i class="m-icon-swapright m-icon-white"></i>
															</button>
														</div>
														<input type="hidden" value="1,2" id="stationStatus">
												</div>
											</div>
												<table id="tab_1_2" class="table table-striped table-bordered table-hover table-full-width">
													<thead>
														<tr>
															<th>工号</th>
															<th>姓名</th>
														    <th>城市</th>
														    <th>物流点</th>
														    <th>操作</th>
														</tr>
														<tr>
															<td>123</td>
															<td>陈悦</td>
															<td>南京</td>
															<td>新街口</td>
															<td><a class="btn btn-primary btn-large" href="#">上班(加班)</a></td>
														</tr>
													</thead>
												</table>
											</div>
										</div>
									</div>
								</div>
						</div>
							<!-- END TAB_1_2-->			

						<div id="tab_1_3" class="tab-pane">
									<div class="portlet box">
										<div class="portlet-body form">
											<!-- BEGIN FORM-->
											<form class="form-horizontal" action="#">
												<div class="row-fluid">
													<div class="span4">
														<div class="control-group">
															<label for="firstName" class="control-label">配送员工号</label>
															<div class="controls">
																	<input type="text" placeholder="请输入配送员工号" class="m-wrap span12" id="firstName">
															</div>
														</div>
													</div>
												</div>
												<!--/row-->
												
												<div class="row-fluid">
													<div class="span4">
														<div class="control-group">
															<label class="control-label">选择一个日期</label>
															<div class="controls">
																<div data-date-viewmode="years" data-date-format="dd-mm-yyyy" data-date="12-02-2012" class="input-append date date-picker">
																	<input id="leaveDate" placeholder="选择一个日期" type="text" value="" size="16" readonly="" class="m-wrap m-ctrl-medium date-picker"><span class="add-on"><i class="icon-calendar"></i></span>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!--/row-->        
												<div class="row-fluid">
													<div class="span4">
														<div class="control-group">
															<label class="control-label">选择请假时常</label>
															<div class="controls">
																<select tabindex="-1" >
																	<option value="0.5">0.5</option>
																	<option value="1">1</option>
																	<option value="1.5">1.5</option>
																	<option value="2">2</option>
																</select>
															</div>
														</div>
													</div>
												</div>
												<div class="form-actions">
													<button class="btn blue" type="button"><i class="icon-ok"></i>请假</button>
												</div>
											</form>
											<!-- END FORM--> 
										</div>
									</div>
						</div>
			</div>
		</div>
	</div>
	<!-- END PAGE CONTENT-->
</div>
<!-- END PAGE CONTAINER--> 
</div>
</body>
<script>
	$(function(){
		$("#leaveDate").datepicker();
	});
</script>
</html>