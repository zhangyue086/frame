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
					<li><a href="#" onclick="loadHtml('initList_schedule.do')">排版信息</a>
						<i class="icon-angle-right"></i></li>
					<li><a href="#">添加/编辑排班</a></li>
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
							<input type="hidden" value="${shift.scheduleDate}" name="shift.scheduleDate"/>
							<input type="hidden" value="" name="shift.distributionMemberNames" id="memberNames"/>
							<input type="hidden" value="" name="shift.scheduleName" id="scheduleName"/>
							<input type="hidden" value="${station.distributionStationName}" name="shift.distributionStationName" />
							<input type="hidden" value="${station.distributionStationId}" name="shift.distributionStationId" />
							<div class="control-group">
								<label class="control-label">班次</label>
								<div class="controls">
									<select class="span6 m-wrap" name="shift.scheduleId" id="scheduleId">
										<c:forEach items="${scheduleList}" var="sche">
											<option value="${sche.scheduleId }">${sche.scheduleName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">配送员</label>
								<div class="controls">
									<select multiple="multiple" name="shift.distributionMemberIds" id="distributionMemberIds" style="width:250px">
										<c:forEach items="${memberList}" var="mem">
											<option value="${mem.distributionMemberId }">${mem.distributionMemberName }
											${mem.haveShifts }
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
								onclick="loadHtml('initList_schedule.do')">返回</button>
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
	var distributionMemberNames='';
    $('#distributionMemberIds option:selected').each(function() {
         distributionMemberNames+=$(this).text()+";";
    });
    $('#memberNames').val(distributionMemberNames.replace(/[\r\n\d:-]/g,"").replace(/\ +/g,""));
    $('#scheduleName').val($('#scheduleId').find('option:selected').text());
	$('#shiftAddForm').submit();
}
</script>
</html>