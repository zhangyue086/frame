<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
				<li><a href="#">角色-菜单管理</a></li>
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
					<div style="background-color:rgb(229,234,238);padding:10px">
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
								<a href="#" data-url="initAdd_roleMenu.do" class="btn green" id="addRoleMenuInit"><i class="icon-pencil"></i>配置菜单</a>
								</div>
								</div>
							</div>
						</div>
					</div>
					
					<table class="table table-striped table-bordered table-hover table-full-width" id="mydemo">
						<thead>
							<tr>
								<th>ID</th>
								<th>角色ID</th>
								<th>角色名</th>
							    <th>菜单ID</th>
							    <th>菜单名</th>
							    <th>创建人</th>
							    <th>创建时间</th>
							    <th>最后更新人</th>
							    <th>最后更新时间</th>
							    <th>是否删除</th>
							    <th>操作</th>
							</tr>
						</thead>
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
<script>
var otable;
$('#search').click(function(){
	otable.fnSettings().ajax={
		"url": "list_roleMenu.do?now=" + new Date().getTime(),
        "type": "POST",
        "data":{
	        /*
        	 "station.cityId": $('#cityName').find("option:selected").val()
       	 */
	}};
	otable.fnDraw();
});

jQuery(document).ready(function() { 
	App.initUniform();
	otable = $('#mydemo').dataTable({
        "processing": true,
        "serverSide": true,
        "bLengthChange" : false,
        "bFilter": false,
        "bSort": false,
        "ajax": {
            "url": "list_roleMenu.do?now=" + new Date().getTime(),
            "type": "POST",
            "data":{
                /**
            	 "station.cityId": $('#cityName').find("option:selected").val()
           	 */
            }
        },
        "columns": [
                    {"data":"id","bVisible":false},
                    {"data": "roleId"},
                    {"data": "roleName"},
                    {"data": "menuId"},
                    {"data": "menuName"},
                    {"data": "createId"},
                    {"data": "createTime"},
                    {"data": "lastUpdateId"},
                    {"data": "lastUpdateTime"},
                    {"data": "isDeleted"}
                  ],
        "columnDefs": [
                       {
                           "targets": [10],
                           "data": "id",
                           "render": function(data, type, full) {
                        	 var html = "<a class='btn mini purple' id='editRoleMenuInit'  href='#' data-url='initEdit_roleMenu.do?roleMenu.id="+data+"'><i class='icon-edit'></i>修改</a>&nbsp;";
                        	 html+= "<a class='btn mini red' id='deleteRoleMenuInit' data-toggle='modal' href='#myModal' data-url='initDelete_roleMenu.do?roleMenu.id="+data+"'><i class='icon-trash'></i>删除</a>&nbsp;";
                             return html;
                           }
                         }
                     ]
    });


    
	$("#addRoleMenuInit").click(function(){
	    loadHtml($(this).attr("data-url"));
	});

	$("#editRoleMenuInit").die().live('click',function(event){
	    loadHtml($(this).attr("data-url"));
	});
	
	$("#deleteRoleMenuInit").die().live('click',function(){
	    $("#myModal").load($(this).attr("data-url"));
	});
	
});
</script>
</html>