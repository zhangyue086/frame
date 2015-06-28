<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!-->
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>物流系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="/media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />
<link href="/media/css/bootstrap-responsive.min.css" rel="stylesheet"
	type="text/css" />
<link href="/media/css/font-awesome.min.css" rel="stylesheet"
	type="text/css" />
<link href="/media/css/style-metro.css" rel="stylesheet" type="text/css" />
<link href="/media/css/style.css" rel="stylesheet" type="text/css" />
<link href="/media/css/style-responsive.css" rel="stylesheet"
	type="text/css" />
<link href="/media/css/default.css" rel="stylesheet" type="text/css"
	id="style_color" />
<link href="/media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />
<link href="/media/css/login.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="/media/image/favicon.ico" />
</head>
<body class="login">
	<div class="logo">
	</div>
	<div class="content">
		<form class="form-vertical login-form" id='loginForm' method="post" action="/login.do">
			<h3 class="form-title">用户登录</h3>
			<div class="alert alert-error hide">
				<button class="close" data-dismiss="alert"></button>
				<span>请输入用户名和密码</span>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">用户名</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-user"></i> <input class="m-wrap placeholder-no-fix"
							type="text" placeholder="用户名" name="userName" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label visible-ie8 visible-ie9">密码</label>
				<div class="controls">
					<div class="input-icon left">
						<i class="icon-lock"></i> <input class="m-wrap placeholder-no-fix"
							type="password" placeholder="密码" name="passWord" />
					</div>
				</div>
			</div>
			<div class="form-actions">
				<button type="submit" class="btn green pull-right">
					登录 <i class="m-icon-swapright m-icon-white"></i>
				</button>
			</div>
		</form>
	</div>
	<script src="/media/js/jquery-1.11.2.js" type="text/javascript"></script>
	<script src="/media/js/jquery-migrate-1.2.1.min.js"
		type="text/javascript"></script>
	<script src="/media/js/jquery-ui-1.10.1.custom.min.js"
		type="text/javascript"></script>
	<script src="/media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>

	<script src="/media/js/excanvas.min.js"></script>

	<script src="/media/js/respond.min.js"></script>  

	<![endif]-->

	<script src="/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="/media/js/jquery.blockui.min.js" type="text/javascript"></script>
	<script src="/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="/media/js/jquery.uniform.min.js" type="text/javascript"></script>
	<script src="/media/js/jquery.validate.min.js" type="text/javascript"></script>
	<script src="/media/js/app.js" type="text/javascript"></script>
	<script src="/media/js/login.js" type="text/javascript"></script>
	<script>
		jQuery(document).ready(function() {
			Login.init();
		});
	</script>
</body>
</html>