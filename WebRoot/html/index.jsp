<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<meta charset="utf-8" />
	<title>零号线物流系统</title>
	<meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
	<!-- BEGIN GLOBAL MANDATORY STYLES -->
	<link href="${path}/media/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/style-metro.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/style.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/style-responsive.css" rel="stylesheet" type="text/css"/>
	<link href="${path}/media/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>
	<link href="${path}/media/css/uniform.default.css" rel="stylesheet" type="text/css"/>
	<!-- END GLOBAL MANDATORY STYLES -->

	<!-- BEGIN PAGE LEVEL STYLES --> 
	<link rel="stylesheet" type="text/css" href="${path}/media/css/select2_metro.css" />
	<!-- 表格begin -->
	<link rel="stylesheet" href="${path}/media/css/DT_bootstrap.css" />
	<!-- 表格end -->
	<!-- form -->
	<link rel="stylesheet" type="text/css" href="${path}/media/css/chosen.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/bootstrap-fileupload.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/jquery.gritter.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/select2_metro.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/jquery.tagsinput.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/clockface.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/bootstrap-wysihtml5.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/datepicker.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/timepicker.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/colorpicker.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/bootstrap-toggle-buttons.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/daterangepicker.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/datetimepicker.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/multi-select-metro.css" />
	<link rel="stylesheet" type="text/css" href="${path}/media/css/bootstrap-modal.css" />
	<!-- form -->
	<!-- UI -->
	<link href="${path}/media/css/jquery.gritter.css" rel="stylesheet" type="text/css"/>
	<!-- UI -->
	<!-- 日历 -->
	<link href='${path}/media/css/fullcalendar.css' rel='stylesheet' />
	<link href='${path}/media/css/fullcalendar.print.css' rel='stylesheet' media='print' />
	<link href='${path}/media/css/jquery-ui.min.css' rel='stylesheet' media='print' />
	<!-- 日历 -->
	<!-- END PAGE LEVEL STYLES -->
	<link rel="shortcut icon" href="media/image/favicon.ico" />
</head>
<body class="page-header-fixed">
	<!-- BEGIN HEADER -->
	<div class="header navbar navbar-inverse navbar-fixed-top">
		<!-- BEGIN TOP NAVIGATION BAR -->
		<div class="navbar-inner">
			<div class="container-fluid">
				<!-- BEGIN LOGO -->
				<a class="brand" href="index.do" style="margin-top:-5px">
					<img src="${path}/media/image/logo.png" alt="logo" />
				</a>
				<!-- END LOGO -->
				<!-- BEGIN RESPONSIVE MENU TOGGLER -->
				<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
				<img src="media/image/menu-toggler.png" alt="" />
				</a>          
				<!-- END RESPONSIVE MENU TOGGLER --> 
				<!-- BEGIN TOP NAVIGATION MENU -->              
				<ul class="nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown user">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<img alt="" src="${path}/media/image/avatar.png" />
						<span class="username">${userName} </span>
						<i class="icon-angle-down"></i>
						</a>
						<ul class="dropdown-menu">
							<li><a href="/loginOut.do"><i class="icon-key"></i>退出</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
				<!-- END TOP NAVIGATION MENU --> 
			</div>
		</div>
		<!-- END TOP NAVIGATION BAR -->
	</div>
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->  
	<div class="page-container  row-fluid">
		<!-- BEGIN SIDEBAR -->
		<div class="page-sidebar nav-collapse collapse">
			<!-- BEGIN SIDEBAR MENU -->        
			<ul class="page-sidebar-menu">
				<li>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
					<div class="sidebar-toggler hidden-phone"></div>
					<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
				</li>
				<li>
					&nbsp;
				</li>
				<li class="start active ">
					<a href="index.do">
					<i class="icon-home"></i> 
					<span class="title">首页</span>
					<span class="selected"></span>
					</a>
				</li>
				<!-- <li class="">
					<a href="javascript:;">
					<i class="icon-file-text"></i> 
					<span class="title">信息</span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
						<li>
							<a href="#" onclick="loadHtml('initList_member.do')" class="ajaxify">
							配送员信息</a>
						</li>
						<li >
							<a href="#" onclick="loadHtml('initList_station.do')" class="ajaxify">
							物流点</a>
						</li>
						<li >
							<a href="#" onclick="loadHtml('initList_schedule.do')" class="ajaxify">
							排班信息</a>
						</li>
						<li >
							<a href="#" onclick="loadHtml('initList_attendance.do')" class="ajaxify">
							配送员考勤</a>
						</li>
					</ul>
				</li> -->
				<s:iterator value="#request.menuList" id="menu" status="st">
				<li class="">
					<a href="javascript:void(0);">
					<i class="icon-file-text"></i> 
					<span class="title"><s:property  value="fatherMenu.menuName" /></span>
					<span class="arrow "></span>
					</a>
					<ul class="sub-menu">
				   <s:iterator value="#menu.childMenuList" id="submenu" status="sutst">
				   <li >
							<a href="javascript:void(0);" onclick="loadHtml('<s:property  value="menuUrl" />')" class="ajaxify">
							<s:property  value="menuName" /></a>
						</li>
				   </s:iterator> 
				
				</ul>
				</li>
				</s:iterator>
				
				
				
				
				
			</ul>
			<!-- END SIDEBAR MENU -->
		</div>
		<!-- END SIDEBAR -->
		<!-- BEGIN PAGE -->
		<div class="page-content">
			<!-- BEGIN PAGE CONTAINER-->
			<div class="container-fluid">
				<!-- BEGIN PAGE HEADER-->
				<div class="row-fluid">
					<div class="span12">
						<!-- BEGIN STYLE CUSTOMIZER -->
						<div class="color-panel hidden-phone">
							<div class="color-mode-icons icon-color"></div>
							<div class="color-mode-icons icon-color-close"></div>
							<div class="color-mode">
								<p>主题设置</p>
								<ul class="inline">
									<li class="color-black current color-default" data-style="default"></li>
									<li class="color-blue" data-style="blue"></li>
									<li class="color-brown" data-style="brown"></li>
									<li class="color-purple" data-style="purple"></li>
									<li class="color-grey" data-style="grey"></li>
									<li class="color-white color-light" data-style="light"></li>
								</ul>
								<label>
									<span>布局</span>
									<select class="layout-option m-wrap small">
										<option value="fluid" selected>满屏</option>
										<option value="boxed">居中</option>
									</select>
								</label>
								<label>
									<span>状态栏</span>
									<select class="header-option m-wrap small">
										<option value="fixed" selected>固定</option>
										<option value="default">默认</option>
									</select>
								</label>
								<label>
									<span>导航栏</span>
									<select class="sidebar-option m-wrap small">
										<option value="fixed">固定</option>
										<option value="default" selected>默认</option>
									</select>
								</label>
								<label>
									<span>底边栏</span>
									<select class="footer-option m-wrap small">
										<option value="fixed">固定</option>
										<option value="default" selected>默认</option>
									</select>
								</label>
							</div>
						</div>
						<!-- END BEGIN STYLE CUSTOMIZER --> 
						<!-- BEGIN PAGE TITLE & BREADCRUMB-->
						<h3 class="page-title">
							首页<small>工作信息</small>
						</h3>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home"></i>
								<a href="index.do">首页</a> 
							</li>
						</ul>
						<!-- END PAGE TITLE & BREADCRUMB-->
					</div>
				</div>
				<!-- END PAGE HEADER-->
				<!-- BEGIN PAGE CONTENT-->
				<div class="row-fluid">
					<div class="span12">
					</div>
				</div>
				<!-- END PAGE CONTENT-->
			</div>
			<!-- END PAGE CONTAINER--> 
		</div>
		<!-- END PAGE --> 
	</div>
	<!-- END CONTAINER -->
	<!-- BEGIN FOOTER -->
	<div class="footer">
		<div class="footer-inner">
		</div>
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
	</div>
	<div id="myModal" tabindex="-1" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
	<div id="bigModal" tabindex="-1" class="modal container hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"></div>
	
	<!-- END FOOTER -->
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
	<!-- BEGIN CORE PLUGINS -->
	<script src="${path}/media/js/jquery-1.11.2.js" type="text/javascript"></script>
	<script src="${path}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
	<script src="${path}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      
	<script src="${path}/media/js/bootstrap.min.js" type="text/javascript"></script>
	<!--[if lt IE 9]>
	<script src="${path}/media/js/excanvas.min.js"></script>
	<script src="${path}/media/js/respond.min.js"></script>  
	<![endif]-->   
	<script src="${path}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>
	<script src="${path}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  
	<script src="${path}/media/js/jquery.cookie.min.js" type="text/javascript"></script>
	<script src="${path}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	<!-- END CORE PLUGINS -->
	<!-- BEGIN PAGE LEVEL PLUGINS -->
	<!-- 表格 --> 
	<script type="text/javascript" src="${path}/media/js/jquery.dataTables.js"></script>
	<script type="text/javascript" src="${path}/media/js/DT_bootstrap.js"></script>     
	<!-- 表格 -->      
	<!-- form -->
	<script type="text/javascript" src="${path}/media/js/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/additional-methods.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/select2.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/jquery-validate-cn.js"></script>
	<script type="text/javascript" src="${path}/media/js/ckeditor.js"></script>  

	<script type="text/javascript" src="${path}/media/js/bootstrap-fileupload.js"></script>
	<script type="text/javascript" src="${path}/media/js/chosen.jquery.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/wysihtml5-0.3.0.js"></script> 
	<script type="text/javascript" src="${path}/media/js/bootstrap-wysihtml5.js"></script>
	
	<script type="text/javascript" src="${path}/media/js/jquery.toggle.buttons.js"></script>
	<script type="text/javascript" src="${path}/media/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="${path}/media/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="${path}/media/js/clockface.js"></script>
	<script type="text/javascript" src="${path}/media/js/date.js"></script>
	<script type="text/javascript" src="${path}/media/js/daterangepicker.js"></script> 
	<script type="text/javascript" src="${path}/media/js/bootstrap-colorpicker.js"></script>  
	<script type="text/javascript" src="${path}/media/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript" src="${path}/media/js/jquery.inputmask.bundle.min.js"></script>   
	<script type="text/javascript" src="${path}/media/js/jquery.input-ip-address-control-1.0.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/jquery.multi-select.js"></script>   
	<script type="text/javascript" src="${path}/media/js/bootstrap-modal.js" ></script>
	<script type="text/javascript" src="${path}/media/js/bootstrap-modalmanager.js" ></script> 
	<!-- form -->
	<!-- UI -->
	<script type="text/javascript" src="${path}/media/js/jquery.gritter.js"></script>
	<script type="text/javascript" src="${path}/media/js/jquery.pulsate.min.js"></script>
	<script type="text/javascript" src="${path}/media/js/jquery.bootpag.min.js"></script>
	<!-- UI -->
	<!-- 日历 -->
	<script src='${path}/media/js/moment.min.js'></script>
	<script src='${path}/media/js/fullcalendar.min.js'></script>
	<script src='${path}/media/js/lang-all.js'></script>
	<script src='${path}/media/js/lang-all.js'></script>
	<!-- 日历 -->
	<!-- END PAGE LEVEL SCRIPTS -->  
	<!-- BEGIN PAGE LEVEL STYLES -->
	<script src="${path}/media/js/app.js"></script>
	<script src="${path}/media/js/ui-general.js"></script>
	<script src="${path}/media/js/form-components.js"></script>
	<script src="${path}/media/js/calendar.js"></script>
	<!-- END PAGE LEVEL STYLES -->   
	<script src="${path}/js/common.js" type="text/javascript"></script> 
	<script>
		jQuery(document).ready(function() {    
			// initiate layout and plugins
			   App.init();
			   $('#myModal').on('hide.bs.modal',function(e){$(this).removeData();});
			   $('.page-sidebar .ajaxify.start').click();
			   //log the dataTable warning in console instead of alert it
			   $.fn.dataTable.ext.errMode = $.fn.dataTable.ext.errMode = function ( settings, helpPage, message ) { console.log(message); };
		});
		// $.datepicker.formatDate(pattern, date)
		function loadHtml(url, data){
			var nav_inner_a = $('.page-sidebar-menu').children('li');
			var nav_inner_b = $('.sub-menu').children('li');
			nav_inner_a.click(function () {
				$.each(nav_inner_a,function(i,n){
					$(n).removeClass('active');
				});
				$(this).addClass('active');
			});
			nav_inner_b.click(function (i,n) {
				$.each(nav_inner_b,function(i,n){
					$(n).removeClass('active');
				});
				$(this).addClass('active');
			});
			$(".page-content").html("");
			//loading图标
			$(".page-content").load(url, data, function() {
			});
		}
	</script>
	<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=I4nRakhXFGygVW41lBTTdNrv" >
</script>
</body>
</html>