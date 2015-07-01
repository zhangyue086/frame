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
<script type="text/javascript" src="${path}/js/menu.form.js"></script>
</head>
<body>
<div class="modal-header">

	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>

	<h3 id="myModalLabel3">菜单修改</h3>

</div>

<div class="modal-body">
	<form id="menuAddForm" class="form-horizontal">
		<div class="alert alert-error hide">
			<button class="close" data-dismiss="alert"></button>
			表单数据验证失败,请重新输入！
		</div>
		<div class="alert alert-success hide">
			<button class="close" data-dismiss="alert"></button>
			表单验证成功！
		</div>
		<div class="control-group">
			<label class="control-label">菜单ID<span class="required">*</span></label>
			<div class="controls">
				<input name="menu.menuId" value="${menu.menuId}" type="text" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单名称<span class="required">*</span></label>
			<div class="controls">
				<input name="menu.menuName" value="${menu.menuName}" type="text"    />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单URL<span class="required">*</span></label>
			<div class="controls">
				<input name="menu.menuUrl" value="${menu.menuUrl}" type="text" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">父ID<span class="required">*</span></label>
			<div class="controls">
				<input name="menu.menuFather" value="${menu.menuFather}" type="text"    />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">菜单排序<span class="required">*</span></label>
			<div class="controls">
				<input name="menu.menuOrder" value="${menu.menuOrder}" type="text"    />
			</div>
		</div>
	</form>
	<!-- END FORM-->
	
</div>

<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<button type="submit" class="btn blue" onclick="menuAdd()">保存</button>
</div>
</body>
<script>

/**
jQuery(document).ready(function() {    
	ScheduleAdd.init();
	$("#scheduleStationId").val($("#hiddenStation").val());
	$("#scheduleStart").inputmask("mask", {"mask": "99:99"});
	$("#scheduleEnd").inputmask("mask", {"mask": "99:99"});
});
*/

function menuAdd(){
	$("#menuAddForm").submit();
}
</script>
</html>