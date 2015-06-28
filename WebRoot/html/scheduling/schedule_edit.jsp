<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path}/js/schedule.form.js"></script>
</head>
<body>
<div class="modal-header">

	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

	<h3 id="myModalLabel3">班次修改</h3>

</div>

<div class="modal-body">
	<form id="scheduleAddForm" class="form-horizontal">
		<div class="alert alert-error hide">
			<button class="close" data-dismiss="alert"></button>
			表单数据验证失败,请重新输入！
		</div>
		<div class="alert alert-success hide">
			<button class="close" data-dismiss="alert"></button>
			表单验证成功！
		</div>
		<div class="control-group">
			<label class="control-label">开始时间<span class="required">*</span></label>
			<div class="controls">
				<input name="schedule.scheduleStart" type="text" id="scheduleStart" value="${schedule.scheduleStart}" />
				<input type="hidden" name="schedule.scheduleId" value="${schedule.scheduleId}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">结束时间<span class="required">*</span></label>
			<div class="controls">
				<input name="schedule.scheduleEnd" type="text" id="scheduleEnd"  value="${schedule.scheduleEnd}" />
			</div>
		</div>
	</form>
	<!-- END FORM-->
</div>

<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<button type="submit" class="btn blue" onclick="scheduleAdd()">保存</button>
</div>
</body>
<script>
jQuery(document).ready(function() {    
	ScheduleEdit.init();
	$("#scheduleStart").inputmask("mask", {"mask": "99:99"});
	$("#scheduleEnd").inputmask("mask", {"mask": "99:99"});
});
function scheduleAdd(){
	$("#scheduleAddForm").submit();
}
</script>
</html>