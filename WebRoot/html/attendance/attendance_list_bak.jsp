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
					<a href="#">信息</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">配送员考勤</a></li>
			</ul>
		</div>
	</div>
	<div class="row-fluid">
		<div class="span4">
			<div class="portlet  box">
				<div class="portlet-title">
					<div class="caption"></div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body" style="background:rgb(156,222,156)">
					<div class="row-fluid">
						<div class="span6">
							<div class="control-group">
								<label class="control-label">配送员：</label>
								<select name="" id="memberList" class="span12 m-wrap">
									<option value=""></option>
									<c:forEach items="${memberList}" var="member">
										<option value="${member.distributionMemberId }">${member.distributionMemberName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="span6">
							<div class="control-group">
								<label class="control-label">班次：</label>
								<select name="" id="scheduleList" class="span12 m-wrap">
									<option value=""></option>
								
								</select>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<br><br>
						<a href="#" class="btn" id="pushCard">打卡</a>
					</div>
				</div>
			</div>
		</div>
		<div class="span3">
			<div class="portlet  box">
				<div class="portlet-title">
					<div class="caption"></div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body" style="background:rgb(156,222,156)">
					<div class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label">配送员：</label>
								<select name="" id="memberList2" class="span12 m-wrap">
									<option value=""></option>
									<c:forEach items="${memberList}" var="member">
										<option value="${member.distributionMemberId }">${member.distributionMemberName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="row-fluid">
						<br><br>
						<a href="#" class="btn" id="overTimeStart">加班上班卡</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="#" class="btn" id="overTimeEnd">加班下班卡</a>
					</div>	
				</div>
			</div>
		</div>
		<div class="span5">
			<div class="portlet  box">
				<div class="portlet-title">
					<div class="caption"></div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body" style="background:rgb(156,222,156)">
					<div class="row-fluid">
						<div class="span7">
							<div class="control-group">
								<label class="control-label">日期：</label>
								<input id="leaveDate" class="m-wrap m-ctrl-small date-picker" readonly size="16" type="text" value="" />
							</div>
						</div>
						<div class="span5">
							<div class="control-group">
								<label class="control-label">配送员：</label>
								<select name="" id="memberList3" class="span12 m-wrap">
									<option value=""></option>
									<c:forEach items="${memberList}" var="member">
										<option value="${member.distributionMemberId }">${member.distributionMemberName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div  class="row-fluid">
						<div class="span12">
							<div class="control-group">
								<label class="control-label">备注：</label>
								<input class="span6 m-wrap" id="leaveMask">
								<span class="help-inline">例：09:00-10:00</span>
							</div>
						</div>
					</div>	
					<div  class="row-fluid">
						<a href="#" class="btn" id="leaveInfo">请假</a>
					</div>		
				</div>
			</div>
		</div>
	</div>
	<div class="row-fluid">
		<div class="portlet">
			<div class="portlet-title">
				<div class="caption">考勤记录</div>
				<div class="tools">
				</div>
			</div>
			<div class="portlet-body">
				<div class="portlet" style="background-color:rgb(229,234,238);padding:10px">
						<div class="row-fluid" >
							<div class="span4 ">
								<div class="control-group">
									<label class="control-label">日期：</label>
									<input id="infoDate" class="m-wrap m-ctrl-small date-picker" readonly size="16" type="text" value="" />
								</div>
							</div>
							<div class="span3 ">
								<div class="control-group">
									<label class="control-label">配送员：</label>
									<select name="" id="memberList4" class="span12 m-wrap">
										<option value=""></option>
										<c:forEach items="${memberList}" var="member">
											<option value="${member.distributionMemberId }">${member.distributionMemberName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="span4 ">
								<div class="control-group">
									<label class="control-label">&nbsp;</label>
									<div class="controls">
										<button class="btn blue" id="search">
											搜索 <i class="m-icon-swapright m-icon-white"></i></button>
									</div>
								</div>
							</div>
						</div>
					</div>
					<table class="table table-striped table-bordered table-hover table-full-width" id="mydemo">
						<thead>
							<tr>
								<th>日期</th>
								<th>物流点</th>
							    <th>配送员</th>
							    <th>班次</th>
							    <th>上班卡时间</th>
							    <th>下班卡时间</th>
							    <th>迟到</th>
							    <th>早退</th>
							    <th>加班</th>
							    <th>请假</th>
							</tr>
						</thead>
					</table>
			</div>
		</div>
	
	</div>
	<!-- END PAGE CONTENT-->
</div>
<!-- END PAGE CONTAINER--> 
</div>
</body>
<script>
var otable;
$('#search').click(function(){
	otable.fnSettings().ajax={
		"url": "list_attendance.do?now=" + new Date().getTime(),
        "type": "POST",
        "data":{
             "attendanceInfo.attendanceDate": $('#listDate').val(),
             "attendanceInfo.distributionMemberId": $('#memberList4').val()
	}};
	otable.fnDraw();
});
jQuery(document).ready(function() {       
	$('.date-picker').datepicker();
	$("#leaveMask").inputmask("mask", {"mask": "99:99-99:99"});
	$("#memberList").select2({
		placeholder: "选择配送员",
        allowClear: true
	});
	$("#memberList2").select2({
		placeholder: "选择配送员",
        allowClear: true
	});
	$("#memberList3").select2({
		placeholder: "选择配送员",
        allowClear: true
	});
	$("#memberList4").select2({
		placeholder: "选择配送员",
        allowClear: true
	});
	$("#scheduleList").select2({
		placeholder: "选择班次",
        allowClear: true
	});
	$("#memberList").change(function(){
		var distributionMemberId = $("#memberList").find("option:selected").val();
		$.post('queryScheduleByMember_attendance.do',{distributionMemberId:distributionMemberId},function(data){
			$('#scheduleList').empty();
			$('#scheduleList').append("<option value=''></option>");
			var html;
			$.each(JSON.parse(data),function(i,n){
				html+="<option value='"+n.scheduleId+"'>"+n.scheduleName+"</option>";
			});
			$('#scheduleList').append(html);
		});
	});
	$("#pushCard").click(function(){
		var distributionMemberId = $('#memberList option:selected').val();
		var scheduleId = $('#scheduleList option:selected').val();
		if(distributionMemberId == null || distributionMemberId == ""){
			alert("请选择配送员");
			return;
		}
		if(scheduleId == null || scheduleId == ""){
			alert("请选择班次");
			return;
		}
		var data = {
			"attendance.distributionMemberId":distributionMemberId,	
			"attendance.scheduleId":scheduleId,	
		};
		$.post('pushCard_attendance.do',data,function(result){
			alert(JSON.parse(result).info);
		});
	});
	$("#overTimeStart").click(function(){
		var distributionMemberId = $('#memberList2 option:selected').val();
		if(distributionMemberId == null || distributionMemberId == ""){
			alert("请选择配送员");
			return;
		}
		var data = {
			"attendance.distributionMemberId":distributionMemberId,
			"workType":1
		};
		$.post('overTime_attendance.do',data,function(result){
			alert(JSON.parse(result).info);
		});
	});
	$("#overTimeEnd").click(function(){
		var distributionMemberId = $('#memberList2 option:selected').val();
		if(distributionMemberId == null || distributionMemberId == ""){
			alert("请选择配送员");
			return;
		}
		var data = {
			"attendance.distributionMemberId":distributionMemberId,
			"workType":2
		};
		$.post('overTime_attendance.do',data,function(result){
			alert(JSON.parse(result).info);
		});
	});
	$("#leaveInfo").click(function(){
		var distributionMemberId = $('#memberList3 option:selected').val();
		var leaveInfoMask = $('#leaveMask').val();
		var leaveInfoDate = $('#leaveDate').val();
		if(distributionMemberId == null || distributionMemberId == ""){
			alert("请选择配送员");
			return;
		}
		if(leaveInfoMask == null || leaveInfoMask == ""){
			alert("请选择请假日期");
			return;
		}
		if(leaveInfoDate == null || leaveInfoDate == ""){
			alert("请填写备注");
			return;
		}
		var data = {
			"leaveInfo.distributionMemberId":distributionMemberId,	
			"leaveInfo.leaveInfoMask":leaveInfoMask,	
			"leaveInfo.leaveInfoDate":leaveInfoDate	
		};
		$.post('leaveAdd_attendance.do',data,function(result){
			if(JSON.parse(result).isSuccess){
				alert("请假成功");
			}
		});
	});
	otable = $('#mydemo').dataTable({
        //"dom": '<"top">rt<"bottom"flip><"clear">',
        "processing": true,
        "serverSide": true,
        "bFilter": false,
        "ajax": {
        	"url": "list_attendance.do?now=" + new Date().getTime(),
            "type": "POST",
            "data":{
                 "attendanceInfo.attendanceDate": $('#listDate').val(),
                 "attendanceInfo.distributionMemberId": $('#memberList4').val()
            }
        },
        "columns": [
                    {"data": "attendanceDate"},
                    {"data": "distributionStationName"},
                    {"data": "distributionMemberName"},
                    {"data": "scheduleName"},
                    {"data": "startWorkTime"},
                    {"data": "endWorkTime"},
                    {"data": "lateTime"},
                    {"data": "earlyTime"},
                    {"data": "overTime"},
                    {"data": "leaveInfoMask"}
                  ]
    });
});
</script>
</html>