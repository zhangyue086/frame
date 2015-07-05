<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
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
					<li><a href="#">系统管理</a> <i class="icon-angle-right"></i></li>
					<li><a href="#" onclick="loadHtml('initList_role.do')">角色-菜单管理</a>
						<i class="icon-angle-right"></i></li>
					<li><a href="#">添加/编辑</a></li>
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
						<div class="tools">
						</div>
					</div>
					<div class="portlet-body">
						<!-- BEGIN FORM-->
						<form id="roleMenuAddForm" class="form-horizontal" action="">
							<input type="hidden" name="roleMenu.id"
								value="${roleMenu.id}">
							<div class="alert alert-error hide">
								<button class="close" data-dismiss="alert"></button>
								表单数据验证失败,请重新输入！
							</div>
							<div class="alert alert-success hide">
								<button class="close" data-dismiss="alert"></button>
								表单验证成功！
							</div>
							<c:if test="${roleMenu.id gt 0}">
							<div class="control-group">
								<label class="control-label">角色菜单ID<span class="required">*</span></label>
								<div class="controls">
									<input name="role.roleId" type="text"
										class="span6 m-wrap" value="${roleMenu.id}"
										disabled />
								</div>
							</div>
							</c:if> 
							
							<div class="control-group">
								<label class="control-label">角色ID<span class="required">*</span></label>
								<div class="controls">
									<input name="roleMenu.roleId" type="text"
										class="span6 m-wrap" value="${roleMenu.roleId}"/>
								</div>
							</div>
							
							<div class="control-group">
								<label class="control-label">菜单ID<span class="required">*</span></label>
								<div class="controls">
									<input name="roleMenu.menuId" type="text" class="span6 m-wrap"
										value="${roleMenu.menuId}" />
								</div>
							</div>
							
							
						</form>
						<!-- END FORM-->
						<div class="form-actions" style="">
							<span class="span2">&nbsp;</span>
							<button type="submit" class="btn blue" onclick="roleMenuAdd()">保存</button>
							<button type="button" class="btn"
								onclick="loadHtml('initList_roleMenu.do')">返回</button>
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

	<script type="text/javascript" src="${path}/js/roleMenu.form.js"></script>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			RoleMenuAdd.init();
		});
		
		function roleMenuAdd() {
			$("#roleMenuAddForm").submit();
		}
	</script>
</body>
</html>