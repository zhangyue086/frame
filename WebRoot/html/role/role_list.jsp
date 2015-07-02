<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
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
			<!-- BEGIN STYLE CUSTOMIZER -->
			
			<!-- END BEGIN STYLE CUSTOMIZER --> 
			<!-- BEGIN PAGE TITLE & BREADCRUMB-->
			<h3 class="page-title">
			</h3>
			<ul class="breadcrumb" style="margin-bottom: 5px;">
				<li>
					<i class="icon-home"></i>
					<a href="index.do">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">系统管理</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">菜单管理</a></li>
			</ul>
			<!-- END PAGE TITLE & BREADCRUMB-->
		</div>
	</div>
	<!-- END PAGE HEADER-->
	<!-- BEGIN PAGE CONTENT-->
	<div class="row-fluid">
		<div class="span12">
			<!-- BEGIN SAMPLE FORM PORTLET-->   
			<div class="portlet box">
				<div class="portlet-body">
					<div class="portlet" style="background-color:rgb(229,234,238);padding:10px">
						<div class="row-fluid" >
							<div class="span2">
								<div class="control-group">
									<label class="control-label">城市：</label>
									<div class="controls">
										<select name="cityName" id="cityName" class="span12" placeholder="选择城市" style="display:inline">
											<option value=""></option>
											<c:forEach items="${cityList}" var="city">
												<option value="${city.cityId }">${city.cityName }</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</div>
						   
							<div class="span2">
								<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
									<button class="btn blue" id="search">
									搜索 <i class="m-icon-swapright m-icon-white"></i></button>
								</div>
								</div>
							</div>
							<div class="span2">
								<div class="control-group">
								<label class="control-label">&nbsp;</label>
								<div class="controls">
								<a class='btn green' id='addMenuInit'  data-toggle='modal' href='#myModal' data-url='initAdd_menu.do'><i class='icon-edit'></i>添加菜单</a>
								<!--  
								<a href="#" data-url="initAdd_menu.do" class="btn green" data-toggle='modal' href='#myModal' id="addMenuInit"><i class="icon-pencil"></i>添加菜单</a>
								-->
								</div>
								</div>
							</div>
						</div>
					</div>
					
					
					<table class="table table-striped table-bordered table-hover table-full-width" id="mydemo">
						<thead>
							<tr>
								<th>菜单ID</th>
								<th>菜单名字</th>
								<th>菜单URL</th>
							    <th>父ID</th>
							    <th>菜单排序ID</th>
							    <th>创建时间</th>
							    <th>最后更新时间</th>
							    <th>操作</th>
							</tr>
						</thead>
						
						<c:forEach items="${menuList}" var="menu">
						<tr>
							<td>${menu.menuId}</td>
							<td>${menu.menuName}</td>
							<td>${menu.menuUrl}</td>
							<td>${menu.menuFather}</td>
							<td>${menu.menuOrder}</td>
							<td>${menu.createTime}</td>
							<td>${menu.lastUpdateTime}</td>
							<td>
							<a class='btn mini purple' id='editMenuInit'  data-toggle='modal' href='#myModal' data-url='initEdit_menu.do?menu.menuId=${menu.menuId}'><i class='icon-edit'></i>修改</a>
							<a class='btn mini red' id='deleteMenuInit' data-toggle='modal' href='#myModal' data-url='initDelete_menu.do?menu.menuId=${menu.menuId}'><i class='icon-trash'></i>删除</a>
							</td>
						</tr>
						</c:forEach>
						
					</table>
					
					
				</div>
			</div>
			<!-- END SAMPLE FORM PORTLET-->			
		</div>
	</div>
	<!-- END PAGE CONTENT-->
</div>
<!-- END PAGE CONTAINER--> 
</body>

<SCRIPT type="text/javascript">

	
	$("#addMenuInit").die().live('click',function(event){
		   $("#myModal").load($(this).attr("data-url"));
		});

	
	$("#editMenuInit").die().live('click',function(event){
	   $("#myModal").load($(this).attr("data-url"));
	});
	
	$("#deleteMenuInit").die().live('click',function(){
	    $("#myModal").load($(this).attr("data-url"));
	});


</SCRIPT>


</html>