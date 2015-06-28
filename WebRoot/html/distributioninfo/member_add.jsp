<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
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
					<li><a href="#">信息中心</a> <i class="icon-angle-right"></i></li>
					<li><a href="#" onclick="loadHtml('initList_member.do')">配送员信息</a>
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
						<form id="memberAddForm" class="form-horizontal" action="">
							<input type="hidden" name="member.distributionMemberId"
								value="${member.distributionMemberId}">
							<div class="alert alert-error hide">
								<button class="close" data-dismiss="alert"></button>
								表单数据验证失败,请重新输入！
							</div>
							<div class="alert alert-success hide">
								<button class="close" data-dismiss="alert"></button>
								表单验证成功！
							</div>
							<c:if test="${member.workNumber gt 0}">
							<div class="control-group">
								<label class="control-label">工号<span class="required">*</span></label>
								<div class="controls">
									<input name="member.workNumber" type="text"
										class="span6 m-wrap" value="${member.workNumber}"
										disabled />
								</div>
							</div>
							</c:if> 
							
							<div class="control-group">
								<label class="control-label">姓名<span class="required">*</span></label>
								<div class="controls">
									<input name="member.distributionMemberName" type="text"
										class="span6 m-wrap" value="${member.distributionMemberName}"
										<c:if test="${member.distributionMemberId gt 0}">disabled</c:if> />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">城市<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" name="member.cityId"
										id="modalCity">
										<option></option>
										<c:forEach items="${cityList}" var="city">
											<option value="${city.cityId }"
												<c:if test="${city.cityId == member.cityId}">selected</c:if>>${city.cityName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">物流点<span class="required">*</span></label>
								<div class="controls">
									<select class="span6 m-wrap" id="distributionStation"
										name="member.distributionStationId">
										<c:choose>
											<c:when test="${member.distributionMemberId gt 0}">
												<option value="${member.distributionStationId}">
													<c:forEach items="${stationList}" var="d">
															<c:if test="${d.distributionStationId == member.distributionStationId}">${d.distributionStationName}</c:if>
													</c:forEach>
												</option>
											</c:when>
											<c:otherwise>
												<option></option>
											</c:otherwise>
										</c:choose>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机号<span class="required">*</span></label>
								<div class="controls">
									<input name="member.mobile" type="text" class="span6 m-wrap"
										value="${member.mobile}" />
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">密码<span class="required">*</span></label>
								<div class="controls">
									<input name="member.password" type="password"
										class="span6 m-wrap"
										 />
									<span class="help-inline">配送终端密码（6-8位），不要将!@#$%^&*做为密码</span>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">员工属性<span class="required">*</span></label>
								<div class="controls">
									<select class="m-wrap span6 " name="member.status">
										<option value="1"
											<c:if test="${member.status == 1}">selected</c:if>>全职</option>
										<option value="2"
											<c:if test="${member.status == 2}">selected</c:if>>兼职</option>
										<option value="3"
											<c:if test="${member.status == 3}">selected</c:if>>离职</option>
									</select>
								</div>
							</div>
						</form>
						<!-- END FORM-->
						<div class="form-actions" style="">
							<span class="span2">&nbsp;</span>
							<button type="submit" class="btn blue" onclick="memberAdd()">保存</button>
							<button type="button" class="btn"
								onclick="loadHtml('initList_member.do')">返回</button>
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

	<script type="text/javascript" src="${path}/js/distribution.member.form.js"></script>
	<script>
		jQuery(document).ready(function() {
			// initiate layout and plugins
			DistribuitonMemberAdd.init();
			$("#modalCity").select2({
				placeholder: "选择城市",
			});
			$("#distributionStation").select2({
				placeholder: "选择物流点",
			});
			$("#modalCity").change(function() {
				var cityId = $("#modalCity").find("option:selected").val();
				$.post('stationForCity_station.do',{cityId:cityId},function(data){
					$('#distributionStation').empty();
					$('#distributionStation').append("<option value=''></option>");
					var html;
					$.each(JSON.parse(data),function(i,n){
						html+="<option value='"+n.distributionStationId+"'>"+n.distributionStationName+"</option>";
					});
					$('#distributionStation').append(html);
				});
				//$('#distributionStation').select2Remote({  
				//    blankMsg:'请输入物流点名称',//这里填写空选项时显示的文字  
				//    url:'stationForCity_station.do?cityId='+cityId,//远程加载的url  
				//    initUrl:'stationForCity_station.do?cityId='+cityId,//初始化url  
				//    valueField:'distributionStationId',//value名   在vo中对应id的属性名  
				//    textField: 'distributionStationName'//显示的text  
				//}); 
			});
		});
		function memberAdd() {
			$("#memberAddForm").submit();
		}
	</script>
</body>
</html>