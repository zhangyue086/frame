<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid" id='headdiv'>
			<div class="span12">
				<h3 class="page-title"></h3>
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">工作台</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">考勤</a></li>
				</ul>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="tabbable tabbable-custom">
					<ul class="nav nav-tabs">
						<li class="active"><a href="#tab_1_1" data-toggle="tab">打卡(排班)</a></li>
						<li><a href="#tab_1_2" data-toggle="tab">打卡(加班)</a></li>
						<li><a href="#tab_1_3" data-toggle="tab">请假</a></li>
					</ul>
					<div class="tab-content">
						<div class="tab-pane active" id="tab_1_1">
							<div class="portlet  box">
								<div class="portlet-body">
									<div class="portlet"
										style="background-color: rgb(229, 234, 238); padding: 10px">
										<div class="row-fluid">
											<div class="span3 ">
												<div class="controls">
													<input type="text" class="m-wrap span12 "
														id="queryKeyWord1" style="background-color: white"
														placeholder="输入配送员工号" /> <input id="hiddenClockNum"
														type="hidden" />
												</div>
											</div>
											<div class="span3 ">
												<div class="controls">
													<button class="btn blue" id="search1">
														搜索 <i class="m-icon-swapright m-icon-white"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<table
										class="table table-striped table-bordered table-hover table-full-width"
										id="memberClock">
										<thead>
											<tr>
												<th>排班id</th>
												<th>工号</th>
												<th>姓名</th>
												<th>城市</th>
												<th>物流点</th>
												<th>班次</th>
												<th>操作</th>
											</tr>
										</thead>
									</table>

								</div>
							</div>
						</div>
						<div class="tab-pane" id="tab_1_2">

							<div class="portlet  box">
								<div class="portlet-body">
									<div class="portlet"
										style="background-color: rgb(229, 234, 238); padding: 10px">
										<div class="row-fluid">
											<div class="span3 ">
												<div class="controls">
													<input type="text" class="m-wrap span12 "
														id="queryKeyWord2" style="background-color: white"
														placeholder="输入配送员工号" /> <input id="hiddenAddClockNum"
														type="hidden" />
												</div>
											</div>
											<div class="span3 ">
												<div class="controls">
													<button class="btn blue" id="search2">
														搜索 <i class="m-icon-swapright m-icon-white"></i>
													</button>
												</div>
											</div>
										</div>
									</div>
									<table
										class="table table-striped table-bordered table-hover table-full-width"
										id="memberAddClock">
										<thead>
											<tr>
												<th>排班ID</th>
												<th>工号</th>
												<th>姓名</th>
												<th>城市</th>
												<th>物流点</th>
												<th>时间</th>
												<th>操作</th>
											</tr>
										</thead>
									</table>

								</div>
							</div>
						</div>
						<div class="tab-pane" id="tab_1_3">
							<div class="portlet box">
								<div class="portlet-body form">
									<!-- BEGIN FORM   addMemberLeave_memberClock -->
									<form class="form-horizontal" id='memberLeaveForm'>
										<div class="alert alert-error hide">
											<button class="close" data-dismiss="alert"></button>
											表单数据验证失败,请重新输入！
										</div>
										<div class="alert alert-success hide">
											<button class="close" data-dismiss="alert"></button>
											表单验证成功！
										</div>
										<div class="row-fluid">
											<div class="span4">
												<div class="control-group">
													<label for="firstName" class="control-label">配送员工号</label>
													<div class="controls">
														<input type="text" placeholder="请输入配送员工号"
															class="m-wrap m-ctrl-small" name="firstName" id="firstName">
													</div>
												</div>
											</div>
										</div>
										<!--/row-->

										<div class="row-fluid">
											<div class="span4">
												<div class="control-group">
													<label class="control-label">请假日期</label>
													<div class="controls">
														<input id="leaveDate" name='leaveDate' placeholder="请选择请假日期"
															class="m-wrap m-ctrl-small date-picker" readonly
															type="text" value="" />
													</div>
												</div>
											</div>
										</div>
										<!--/row-->
										<div class="row-fluid">
											<div class="span4">
												<div class="control-group">
													<label class="control-label">请假时长</label>
													<div class="controls">
														<select tabindex="-1" id='leaveTime' name="leaveTime">
															<option value="4">0.5天</option>
															<option value="8">1天</option>
														</select>
													</div>
												</div>
											</div>
										</div>
										<div class="form-actions">
											<button class="btn blue" type="button"
												onclick="memberLeaveAdd();">请假</button>
										</div>
									</form>
									<!-- END FORM-->
								</div>
							</div>



						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="${path}/js/memberclock.form.js"></script>
<script type="text/javascript">
	$(function() {
		memberClock();
		$("#leaveDate").datepicker();
		MemberclockAdd.init();
	});

	$('#search1').click(
			function() {
				$("#hiddenClockNum").val($("#queryKeyWord1").val());
				memberClockTable.fnSettings().ajax = {
					"url" : "/memberClock/listClock_memberClock.do?now="
							+ new Date().getTime(),
					"type" : "POST",
					"data" : {
						"workNum" : $("#hiddenClockNum").val()
					}
				};
				memberClockTable.fnDraw();
			});
	$('#search2').click(
			function() {
				$("#hiddenAddClockNum").val($("#queryKeyWord2").val());
				memberAddClockTable.fnSettings().ajax = {
					"url" : "/memberClock/listAddClock_memberClock.do?now="
							+ new Date().getTime(),
					"type" : "POST",
					"data" : {
						"workNum" : $("#hiddenAddClockNum").val()
					}
				};
				memberAddClockTable.fnDraw();
			});

	function memberClock() {
		memberClockTable = $('#memberClock')
				.dataTable(
						{
							"dom" : '<"top">rt<"bottom">',
							"bPaginate" : false,
							"processing" : true,
							"serverSide" : true,
							"bLengthChange" : false,
							"bFilter" : false,
							"bSort" : false,
							"ajax" : {
								"url" : "/memberClock/listClock_memberClock.do?now="
										+ new Date().getTime(),
								"type" : "POST",
								"data" : {
									"workNum" : 0
								}
							},
							"columns" : [ {
								"data" : "memberScheduleId",
								"bVisible" : false
							}, {
								"data" : "memberWorkNum"
							}, {
								"data" : "memberName"
							}, {
								"data" : "cityName"
							}, {
								"data" : "stationName"
							}, {
								"data" : "scheduleName"
							} ],
							"columnDefs" : [ {
								"targets" : [ 6 ],
								"data" : "clockStatus",
								"render" : function(data, type, full) {
									var html = "";
									if (data == 1) {
										html = "<a class='label label-info' href='javascript:void(0)' onclick='changeMemberClock(1,"
												+ full.memberScheduleId
												+ ","
												+ full.memberClockId
												+ ","
												+ full.memberId + ")'>上班</a>";
									}
									if (data == 2) {
										html = "<a class='label label-info' href='javascript:void(0)' onclick='changeMemberClock(2,"
												+ full.memberScheduleId
												+ ","
												+ full.memberClockId
												+ ","
												+ full.memberId + ")'>下班</a>";
									}

									return html;
								}
							} ]
						});
		memberAddClockTable = $('#memberAddClock')
				.dataTable(
						{
							"dom" : '<"top">rt<"bottom">',
							"bPaginate" : false,
							"processing" : true,
							"serverSide" : true,
							"bLengthChange" : false,
							"bFilter" : false,
							"bSort" : false,
							"ajax" : {
								"url" : "/memberClock/listAddClock_memberClock.do?now="
										+ new Date().getTime(),
								"type" : "POST",
								"data" : {
									"workNum" : 0
								}
							},
							"columns" : [ {
								"data" : "memberScheduleId",
								"bVisible" : false
							}, {
								"data" : "memberWorkNum"
							}, {
								"data" : "memberName"
							}, {
								"data" : "cityName"
							}, {
								"data" : "stationName"
							}, {
								"data" : "scheduleName"
							} ],
							"columnDefs" : [ {
								"targets" : [ 6 ],
								"data" : "clockStatus",
								"render" : function(data, type, full) {
									var html = "";
									if (data == 1) {
										html = "<a class='label label-info' href='javascript:void(0)' onclick='changeMemberAddClock(1,"
												+ full.memberScheduleId
												+ ","
												+ full.memberClockId
												+ ","
												+ full.memberId + ")'>上班</a>";
									}
									if (data == 2) {
										html = "<a class='label label-info' href='javascript:void(0)' onclick='changeMemberAddClock(2,"
												+ full.memberScheduleId
												+ ","
												+ full.memberClockId
												+ ","
												+ full.memberId + ")'>下班</a>";
									}

									return html;
								}
							} ]
						});

	}
	function changeMemberClock(clockStatus, memberScheduleId, memberClockId,
			memberId) {
		$.post("/memberClock/memberClock_memberClock.do?now="
				+ new Date().getTime(), {
			"clockStatus" : clockStatus,
			"memberScheduleId" : memberScheduleId,
			"memberClockId" : memberClockId,
			"memberId" : memberId
		}, function(data) {
			$('#search1').click();
		});
	}
	function changeMemberAddClock(clockStatus, memberScheduleId, memberClockId,
			memberId) {
		$.post("/memberClock/memberAddClock_memberClock.do?now="
				+ new Date().getTime(), {
			"clockStatus" : clockStatus,
			"memberScheduleId" : memberScheduleId,
			"memberClockId" : memberClockId,
			"memberId" : memberId
		}, function(data) {
			$('#search2').click();
		});
	}
	function memberLeaveAdd() {
		$("#memberLeaveForm").submit();
	}
</script>
</html>