<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path}/js/shift.form.js"></script>
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
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">信息</a> <i class="icon-angle-right"></i></li>
					<li><a href="#" onclick="loadHtml('initList_schedule.do')">角色管理</a>
						<i class="icon-angle-right"></i></li>
					<li><a href="#">角色资源配置</a></li>
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
					<div class="portlet-title">
						<div class="caption"></div>
						<div class="tools"></div>
					</div>
					<div class="portlet-body">
						<form id="shiftAddForm" class="form-horizontal">
						
						<input type="hidden" name ="shift.scheduleId" value="${shift.scheduleId}" />
						<input type="hidden" name ="shift.distributionStationId"  value="${shift.distributionStationId}" />
						<input type="hidden" name ="shift.shiftScheduleId" value="${shift.shiftScheduleId}" />
						<input type="hidden" name ="shift.scheduleDate" value="${shift.scheduleDate}" />
							<div class="control-group">
								<label class="control-label">日期</label>
								<label class="control-label">
								${shift.scheduleDate }
								</label>
							</div>
							<div class="control-group">
								<label class="control-label">班次</label>
								<label class="control-label">
									${shift.scheduleName }
								</label>
							</div>
							<div class="control-group">
								<label class="control-label">资源分配</label>
								<div class="controls">
									<select multiple="multiple" name="shift.distributionMemberIds" id="distributionMemberIds" style="width:250px">
										<c:forEach items="${menuList}" var="menu">
											<option value="${menu.menuId}">${menu.menuName}
											</option>
										</c:forEach>
										<c:forEach items="${listRight}" var="mem">
											<option selected="selected"  value="${mem.distributionMemberId }">${mem.distributionMemberName }
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</form>
						<div class="form-actions" style="">
							<span class="span2">&nbsp;</span>
							<button type="submit" class="btn blue" onclick="shiftAdd()">保存</button>
							<button type="button" class="btn"
								onclick="loadHtml('/scheduleNew/initList_scheduleNew.do')">返回</button>
						</div>
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
<script type="text/javascript">
$(function(){
	ShiftAdd.init();
	$('#distributionMemberIds').multiSelect();
});
function shiftAdd(){

	$('#shiftAddForm').submit();
}
</script>
</html>