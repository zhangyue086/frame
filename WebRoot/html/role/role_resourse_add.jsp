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



<style>
div.tree_add{background:url(treeline_2.gif) no-repeat left;padding-left:24px;padding-top:3px;color:blue;cursor:hand}
div.tree_1{background:url(ico_top1.gif) no-repeat left middle;padding-top:3px;font-size:15px;padding-left:10px;cursor:hand;color:blue}
div.tree_2{padding-left:80px;background:url(treeline_1.gif) 100px repeat-y;font-size:14px}
div.tree_3{background:url(treeline_2.gif) no-repeat left;padding-left:24px;padding-top:3px}


div.tree_add{background:url(treeline_2.gif) no-repeat left;padding-left:24px;padding-top:3px;color:blue;cursor:hand}
div.wrap-page{background:url(ico_top1.gif) no-repeat left middle;padding-top:3px;font-size:15px;padding-left:10px;cursor:hand;color:blue}
div.wrap-tree{padding-left:80px;background:url(treeline_1.gif) 100px repeat-y;font-size:14px}
div.tree_3{background:url(treeline_2.gif) no-repeat left;padding-left:24px;padding-top:3px}


.tree {
    min-height:20px;
    padding:19px;
    margin-bottom:20px;
    background-color:#fbfbfb;
    border:1px solid #999;
    -webkit-border-radius:4px;
    -moz-border-radius:4px;
    border-radius:4px;
    -webkit-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    -moz-box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05);
    box-shadow:inset 0 1px 1px rgba(0, 0, 0, 0.05)
}
.tree li {
    list-style-type:none;
    margin:0;
    padding:10px 5px 0 5px;
    position:relative
}
.tree li::before, .tree li::after {
    content:'';
    left:-20px;
    position:absolute;
    right:auto
}
.tree li::before {
    border-left:1px solid #999;
    bottom:50px;
    height:100%;
    top:0;
    width:1px
}
.tree li::after {
    border-top:1px solid #999;
    height:20px;
    top:25px;
    width:25px
}
.tree li span {
    -moz-border-radius:5px;
    -webkit-border-radius:5px;
    border:1px solid #999;
    border-radius:5px;
    display:inline-block;
    padding:3px 8px;
    text-decoration:none
}
.tree li.parent_li>span {
    cursor:pointer
}
.tree>ul>li::before, .tree>ul>li::after {
    border:0
}
.tree li:last-child::before {
    height:30px
}
.tree li.parent_li>span:hover, .tree li.parent_li>span:hover+ul li span {
    background:#eee;
    border:1px solid #94a0b4;
    color:#000
}

</style>
<script>
function line(obj){
	obj.style.textDecoration='underline';
}
function delline(obj){
	obj.style.textDecoration='none';
}
function showtree(str){
	var eval1="span_"+str+".innerHTML=span_"+str+".innerHTML=='+'?'-':'+'";
	var eval2=str+"_value.style.display="+str+"_value.style.display=='none'?'':'none'";
	eval(eval1);
	eval(eval2);
}
</script>

<script type="text/javascript">
$(function(){
    $('.tree li:has(ul)').addClass('parent_li').find(' > span').attr('title', 'Collapse this branch');
    $('.tree li.parent_li > span').on('click', function (e) {
        var children = $(this).parent('li.parent_li').find(' > ul > li');
        if (children.is(":visible")) {
            children.hide('fast');
            $(this).attr('title', 'Expand this branch').find(' > i').addClass('icon-plus-sign').removeClass('icon-minus-sign');
        } else {
            children.show('fast');
            $(this).attr('title', 'Collapse this branch').find(' > i').addClass('icon-minus-sign').removeClass('icon-plus-sign');
        }
        e.stopPropagation();
    });
});
</script>

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
						<form id="addOrUpadateResourseForm" class="form-horizontal">
							<div class="control-group">
								<label class="control-label">角色<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" name="role.roleId"
										id="roleName">
										<option></option>
										<c:forEach items="${roleList}" var="roleEntity">
											<option value="${role.roleId }"
												<c:if test="${role.roleId == roleEntity.roleId}">selected</c:if>>${roleEntity.roleName }</option>
										</c:forEach>
									</select>
								</div>
							</div>

<div class="tree well">
<ul>
	<c:forEach items="${menuViewList}" var="menuView">
		<li>
		<span><i class="icon-minus-sign"></i>${menuView.fatherMenu.menuName}&nbsp;&nbsp;
			<c:if test="${menuView.fatherMenu.selected == 1}">
				<input type="checkbox" value="${menuView.fatherMenu.menuId}" checked="checked" name="selectedMenuId"/>
			</c:if>
			<c:if test="${menuView.fatherMenu.selected != 1}">
				<input type="checkbox" value="${menuView.fatherMenu.menuId}" name="selectedMenuId"  name="selectedMenuId"/>
			</c:if>
		</span>
			
			<ul>
			<c:forEach items="${menuView.childMenuList}" var="childMenu">
			<li>
				<span><i class="icon-minus-sign"></i>${childMenu.menuName}&nbsp;&nbsp;
					<c:if test="${childMenu.selected == 1}">
						<input  type="checkbox" value="${childMenu.menuId}" checked="checked" name="selectedMenuId"/>
					</c:if>
					<c:if test="${childMenu.selected  != 1}">
						<input type="checkbox" value="${childMenu.menuId}" name="selectedMenuId"/>
					</c:if>
				</span>
			</li>
			</c:forEach>
			</ul>
			
		</li>
	</c:forEach>
</ul>
</div>
						</form>
						<div class="form-actions" style="">
							<span class="span2">&nbsp;</span>
							<button type="submit" class="btn blue" onclick="addOrUpadateResourse()">保存</button>
							<button type="button" class="btn"
								onclick="loadHtml('/role/initList_role.do')">返回</button>
						</div>
					</div>
				</div>
				<!-- END SAMPLE FORM PORTLET-->
			</div>
		</div>
		<!-- END PAGE CONTENT-->
	</div>
	
	
	
	
</body>
<script type="text/javascript" src="${path}/js/addResource.form.js"></script>
<script type="text/javascript">
$(function(){
	ShiftAdd.init();

});
function addOrUpadateResourse(){

	$('#addOrUpadateResourseForm').submit();
}




	$("#cityName").change(
		function() {
			var cityId = $("#cityName").find("option:selected").val();
			$.post('/schedule/stationForCity_station.do', {
				cityId : cityId
			}, function(data) {
				$('#distributionStation').empty();
				$('#distributionStation').append(
						"<option value=''></option>");
				var html;
				$.each(JSON.parse(data), function(i, n) {
					html += "<option value='"+n.distributionStationId+"'>"
							+ n.distributionStationName + "</option>";
				});
				$('#distributionStation').append(html);
			});
		});


</script>
</html>