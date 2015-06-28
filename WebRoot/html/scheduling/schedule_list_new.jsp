<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>排班</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row-fluid" id='headdiv'>
			<div class="span12">
				<h3 class="page-title"></h3>
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">信息中心</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">物流点排班</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<div class="row-fluid">
			<div class="span8">
				<div class="portlet">
					<div class="row-fluid"
						style="background-color: rgb(229, 234, 238); padding: 10px 0 0 10px">
						<div class="span3">
							<div class="control-group">
								<label class="control-label">城市：</label>
								<div class="controls">
									<select name="" id="cityName" class="span12" placeholder="选择城市"
										style="display: inline">
										<option value=""></option>
										<c:forEach items="${cityList}" var="city">
											<option value="${city.cityId }">${city.cityName }</option>
										</c:forEach>
									</select>
								</div>
								<input type="hidden" id="hiddenCity" />
							</div>
						</div>
						<div class="span3 ">
							<div class="control-group">
								<label class="control-label">物流点:</label>
								<div class="controls">
									<select id="distributionStation" class="m-wrap span12">
										<option value=""></option>
									</select>
								</div>
								<input type="hidden" id="hiddenStation" />
							</div>
						</div>

						<div class="span4 ">
							<div class="control-group">
								<label class="control-label">日期:</label>
								<div class="controls">
									<input id="leaveDate" style="width: 150px"
										class="m-wrap m-ctrl-small date-picker" readonly type="text"
										value="" />
								</div>
								<input type="hidden" id="hiddenLeaveDate" />
							</div>
						</div>
						<div class="span2">
							<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<button class="btn blue" id="search">
										搜索 <i class="m-icon-swapright m-icon-white"></i>
									</button>
								</div>
							</div>
						</div>
					</div>
					<div class="row-fluid" style="padding: 100px 5px 0">
						<div class="span12">
							<div class="portlet box grey">
								<div class="portlet-title">
									<div class="caption">
										<i class="icon-reorder"></i>排班<span id="clickOverDate"
											class=""></span>
									</div>

								</div>
								<div class="portlet-body light-grey">
									<table
										class="table table-striped table-bordered table-hover table-full-width"
										id="mySchedule">
										<thead>
											<tr>
												<th>班次</th>
												<th>配送员</th>
												<th>操作</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>

						</div>

					</div>
				</div>
			</div>
			<div class="span4">
				<div class="portlet">
					<div class="portlet-title">
						<a data-url="initAdd_schedule.do" class="btn green"
							data-toggle='modal' href='#myModal' id="addScheduleInit"><i
							class="icon-pencil"></i>添加班次</a>
					</div>
					<div class="portlet-body light-grey">
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="mydemo">
							<thead>
								<tr>
									<th>编号</th>
									<th>班次</th>
									<th>操作</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="row-fluid" style="padding: 10px 0">
					<div class="span12">
						<div class="portlet">
							<div class="portlet-body light-grey">
								<table
									class="table table-striped table-bordered table-hover table-full-width"
									id="dateSchedule">
									<thead>
										<tr>
											<th>日期</th>
											<th>排班情况</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>

					</div>

				</div>

			</div>

		</div>
</body>
<script>
	var otable;
	var otable2;
	var otable3;
	var today = '${today}';
	$(function() {
		$('.date-picker').datepicker();
		tableInit(0);
	});
	$('#search').click(
			function() {
				var distributionStation = $("#distributionStation").find(
						"option:selected").val();
				var leaveDate = $("#leaveDate").val();
				if (distributionStation == null
						|| distributionStation.length == 0) {
					messageStation();
					return false;
				}
				if (leaveDate == null || leaveDate.length == 0) {
					messageLeaveDate();
					return false;
				}

				$("#hiddenStation").val(distributionStation);
				$("#hiddenLeaveDate").val(leaveDate);

				$.post("/schedule/memberScheduleCount_schedule.do?now="
						+ new Date().getTime(),{
							"shift.scheduleDate" : $("#hiddenLeaveDate").val(),
							"shift.distributionStationId" : $("#hiddenStation")
									.val()
				},function(data){
					var memberScheduleCount = eval("("+data+")");
					var s=" 排班人数:"+memberScheduleCount.countMemberSchuduleCount+"  总人数:"+memberScheduleCount.countMemberCount;
					$("#clickOverDate").text(
							$("#distributionStation").find("option:selected")
									.html()
									+ "(" + $("#hiddenLeaveDate").val() + ")"+s);
				});
				
				
				

				///////////////////////

				otable.fnSettings().ajax = {
					"url" : "/schedule/list_schedule.do?now="
							+ new Date().getTime(),
					"type" : "POST",
					"data" : {
						"schedule.distributionStationId" : $("#hiddenStation")
								.val()
					}
				};
				otable.fnDraw();

				////
				otable2.fnSettings().ajax = {
					"url" : "/schedule/shiftList_schedule.do?now="
							+ new Date().getTime(),
					"type" : "POST",
					"data" : {
						"shift.scheduleDate" : $("#hiddenLeaveDate").val(),
						"shift.distributionStationId" : $("#hiddenStation")
								.val()
					}
				};
				otable2.fnDraw();

				///
				otable3.fnSettings().ajax = {
					"url" : "/schedule/dateSchedule_schedule.do?now="
							+ new Date().getTime(),
					"type" : "POST",
					"data" : {
						"schedule.distributionStationId" : $("#hiddenStation")
								.val()
					}
				};
				otable3.fnDraw();

			});

	$("#addScheduleInit").click(
			function() {
				if ($("#hiddenStation").val() == null
						|| $("#hiddenStation").val().length == 0) {
					messageSerchFrist();
					return false;
				}
				var totalRecords = 3;

				$.ajax({
					type : 'POST',
					async:false,
					url : "/schedule/list_schedule.do?now="
							+ new Date().getTime(),
					data : {
						"schedule.distributionStationId" : $("#hiddenStation")
								.val()
					},
					success : function(data) {
						totalRecords = eval("(" + data + ")").recordsTotal;
					}

				});
				if (totalRecords >= 3) {
					messageMaxCount();
					return false;
				} else {
					$("#myModal").load($("#addScheduleInit").attr("data-url"));
				}
			});
	$("#editScheduleInit").die().live('click', function() {
		$("#myModal").load($(this).attr("data-url"));
	});
	$("#deleteScheduleInit").die().live('click', function() {
		$("#myModal").load($(this).attr("data-url"));
	});
	$("#addShiftInit").die().live(
			'click',
			function() {

				if ($("#hiddenStation").val() == null
						|| $("#hiddenStation").val().length == 0) {
					messageSerchFrist();
					return false;
				}
				if ($("#hiddenLeaveDate").val() == null
						|| $("#hiddenLeaveDate").val().length == 0) {
					messageSerchFrist();
					return false;
				}
				loadHtml($(this).attr("data-url") + "&now="
					+ new Date().getTime());
			});

	$("#cityName").select2({
		placeholder : "选择城市",
		allowClear : true
	});
	$('#distributionStation').select2({
		placeholder : "选择物流点",
		allowClear : true
	});
	$("#cityName").change(
			function() {
				var cityId = $("#cityName").find("option:selected").val();
				$.post('/schedule/stationForCity_station.do', {
					cityId : cityId
				}, function(data) {
					$('#distributionStation').empty();
					$('#distributionStation').append(
							"<option value=''></option>");
					var html;
					$.each(JSON.parse(data), function(i, n) {
						html += "<option value='"+n.distributionStationId+"'>"
								+ n.distributionStationName + "</option>";
					});
					$('#distributionStation').append(html);
				});
			});
	function tableInit(stationId) {
		otable = $('#mydemo')
				.dataTable(
						{
							"dom" : '<"top">rt<"bottom">',
							"bPaginate" : false,
							"serverSide" : true,
							"bFilter" : false,
							"bSort" : false,
							"ajax" : {
								"url" : "/schedule/list_schedule.do?now="
										+ new Date().getTime(),
								"type" : "POST",
								"data" : {
									"schedule.distributionStationId" : stationId,
								}
							},
							"columns" : [ {
								"data" : "scheduleId",
								"bVisible" : false
							}, {
								"data" : "scheduleName"
							} ],
							"columnDefs" : [ {
								"targets" : [ 2 ],
								"data" : "scheduleId",
								"render" : function(data, type, full) {
									var html = "<a class='btn mini purple' id='editScheduleInit' data-toggle='modal' href='#myModal' data-url='/schedule/initEdit_schedule.do?schedule.scheduleId="
											+ data
											+ "'><i class='icon-edit'></i>修改</a>&nbsp;"
											+ "<a class='btn mini black' id='deleteScheduleInit' data-toggle='modal' href='#myModal' data-url='/schedule/initDelete_schedule.do?schedule.scheduleId="
											+ data
											+ "'><i class='icon-trash'></i>删除</a>";
									return html;
								}
							} ]
						});
		otable2 = $('#mySchedule')
				.dataTable(
						{
							"dom" : '<"top">rt<"bottom">',
							"bPaginate" : false,
							"serverSide" : true,
							"bFilter" : false,
							"bSort" : false,
							"ajax" : {
								"url" : "/schedule/shiftList_schedule.do?now="
										+ new Date().getTime(),
								"type" : "POST",
								"data" : {
									"shift.scheduleDate" : new Date()
											.Format("yyyy-MM-dd"),
									"shift.distributionStationId" : stationId,
								}
							},
							"columns" : [ {
								"data" : "scheduleName"
							}, {
								"data" : "distributionMemberNames"
							} ],
							"columnDefs" : [ {
								"targets" : [ 2 ],
								"data" : "shiftScheduleId",
								"render" : function(data, type, full) {
									var html = '';
									if (full.scheduleDate > today) {
										if(!data)
											data='';
										html = "<a class='btn mini blue' id='addShiftInit' data-toggle='modal' href='#' data-url='/schedule/initAddShiftNew_schedule.do?shift.shiftScheduleId="
												+ data+"&shift.distributionStationId="+full.distributionStationId+"&shift.scheduleId="+full.scheduleId+"&shift.scheduleName="+full.scheduleName
												+"&shift.scheduleDate="+full.scheduleDate
												+ "'><i class='icon-edit'></i>排班</a>";
									}
									return html;
								}
							} ]
						});

		otable3 = $('#dateSchedule').dataTable(
				{
					"dom" : '<"top">rt<"bottom">',
					"bPaginate" : false,
					"serverSide" : true,
					"bFilter" : false,
					"bSort" : false,
					"ajax" : {
						"url" : "/schedule/dateSchedule_schedule.do?now="
								+ new Date().getTime(),
						"type" : "POST",
						"data" : {
							"schedule.distributionStationId" : stationId,
						}
					},
					"columns" : [ {
						"data" : "scheduleDate"
					} ],
					"columnDefs" : [ {
						"targets" : [ 1 ],
						"data" : "scheduleDateStatu",
						"render" : function(data, type, full) {
							var html = "";
							if (data >= 1) {
								html = '已排';
							} else {
								html = "<font color='#F00'>未排</font>";
							}
							return html;
						}
					} ]
				});
	}
	//////////////////////////////////////////////
	function messageStation() {
		myalert("请选择物流点");
	}
	function messageLeaveDate() {
		myalert("请选择时间");
	}
	function messageLeaveDateSmall() {
		myalert("时间必须大于今天");
	}
	function messageSerchFrist() {
		myalert("请先查询");
	}
	function messageMaxCount() {
		myalert("最多三个排班");
	}
	function myalert(message)
	{
		
	var html = "<div class='alert'>";
		html += "<a class='close' data-dismiss='alert'></a>";
		html += "<strong>错误!</strong> " + message + "</div>";
		$("#headdiv").before(html);
	}
</script>
</html>