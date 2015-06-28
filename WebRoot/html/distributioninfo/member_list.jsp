<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String rc = request.getContextPath();
	request.setAttribute("path", rc);
%>
<!DOCTYPE HTML>
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
				<h3 class="page-title"></h3>
				<ul class="breadcrumb" style="margin-bottom: 5px;">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">信息中心</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">配送员信息</a></li>
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
					<div class="portlet-body" >
						<div class="portlet"
							style="background-color: rgb(229, 234, 238); padding: 10px">
							<div class="row-fluid">
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">城市：</label>
										<div class="controls">
											<select name="" id="cityName" class="span12">
												<option value=""></option>
												<c:forEach items="${cityList}" var="city">
													<option value="${city.cityId }">${city.cityName }</option>
												</c:forEach>
											</select>
										</div>
									</div>
								</div>
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">物流点:</label>
										<div class="controls">
											<select id="distributionStation" class="m-wrap span12">
												<option value=""></option>
											</select>
										</div>
									</div>
								</div>

								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">配送员</label>
										<div class="controls">
											<input type="text" class="m-wrap span12 " id="queryKeyWord"
												style="background-color: white" placeholder="姓名或者工号" />
										</div>
									</div>
								</div>
								<input type="hidden" id="memberStatus" value="1,2,3" />
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">&nbsp;</label>
										<div class="controls">
											<button class="btn blue" id="search">
												搜索 <i class="m-icon-swapright m-icon-white"></i>
											</button>
										</div>
									</div>
								</div>
								<div class="span2 ">
									<div class="control-group">
										<label class="control-label">&nbsp;</label>
										<div class="controls">
										<a data-url="initAdd_member.do" class="btn green"  href="#" id="addMemberInit"><i class="icon-pencil"></i>添加配送员</a>
										</div>
									</div>
								</div>
								

							</div>
						</div>
						<div class="control-group">
						<label class="control-label" style="display: none;">&nbsp;</label>
										<div class="controls">
											<label class="checkbox">
											<input type="checkbox" checked="checked" id='quzhicheckbox' value="1" /> 全职
											</label>
											<label class="checkbox">
											<input type="checkbox" checked="checked" id='jianzhicheckbox' value="2" /> 兼职
											</label>
											<label class="checkbox">
											<input type="checkbox" checked="checked" id='lizhicheckbox' value="3" /> 离职
											</label>
					                    </div>
					  </div>
						<table
							class="table table-striped table-bordered table-hover table-full-width"
							id="mydemo">
							<thead>
								<tr>
									<th>配送员ID</th>
									<th>工号</th>
									<th>姓名</th>
									<th>城市</th>
									<th>物流点</th>
									<th>手机号</th>
									<th>员工属性</th>
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
	$('#search').click(
			function() {
				otable.fnSettings().ajax = {
					"url" : "list_member.do?now=" + new Date().getTime(),
					"type" : "POST",
					"data" : {
						"member.cityId" : $('#cityName')
								.find("option:selected").val(),
						"member.distributionStationId" : $(
								'#distributionStation').find("option:selected")
								.val(),
						"queryKeyWord" : $('#queryKeyWord')
								.val().trim(),
						"memberStatus" : $('#memberStatus')
								.val()
					}
				};
				otable.fnDraw();
			});
	jQuery(document)
			.ready(
					function() {
						App.initUniform();
						otable = $('#mydemo')
								.dataTable(
										{
											"processing" : true,
											"serverSide" : true,
											"bLengthChange" : false,
											"bFilter" : false,
											"bSort": false,
											"ajax" : {
												"url" : "list_member.do?now="
														+ new Date().getTime(),
												"type" : "POST",
												"data" : {
													"member.cityId" : $(
															'#cityName').find(
															"option:selected")
															.val(),
													"member.distributionStationId" : $(
															'#distributionStation')
															.find(
																	"option:selected")
															.val(),
													"queryKeyWord" : $(
															'#queryKeyWord')
															.val().trim(),
													"memberStatus" : $('#memberStatus')
															.val()
												}
											},
											"columns" : [
													{
														"data" : "distributionMemberId",
														"bVisible" : false
													},
													{
														"data" : "workNumber"
													}
													,
													{
														"data" : "distributionMemberName"
													},
													{
														"data" : "cityName"
													},
													{
														"data" : "distributionStationName"
													}, {
														"data" : "mobile"
													}, ],
											"columnDefs" : [
													{
														"targets" : [ 6 ],
														"data" : "status",
														"render" : function(
																data, type,
																full) {
															if (data == 1) {
																return "全职";
															}
															if (data == 2) {
																return "兼职";
															}
															if (data == 3) {
																return "离职";
															}
														}
													},
													{
														"targets" : [ 7 ],
														"data" : "distributionMemberId",
														"render" : function(
																data, type,
																full) {
															var html = "<a class='btn mini purple' id='editMemberInit'  href='#' data-url='initEdit_member.do?member.distributionMemberId="
																	+ data
																	+ "'><i class='icon-edit'></i>修改</a>&nbsp;";
															html += "<a class='btn mini black' id='deleteMemberInit' data-toggle='modal' href='#myModal' data-url='initDelete_member.do?member.distributionMemberId="
																	+ data
																	+ "'><i class='icon-trash'></i>删除</a>";
															return html;
														}
													} ]
										});
						$("#addMemberInit").click(function() {
							loadHtml($(this).attr("data-url"));
						});

						$("#editMemberInit").die().live('click', function() {
							loadHtml($(this).attr("data-url"));
						});
						$("#deleteMemberInit").die().live('click', function() {
							$("#myModal").load($(this).attr("data-url"));
						});
						$("#cityName").select2({
							placeholder : "选择城市",
							allowClear : true
						});
						$('#distributionStation').select2({
							placeholder : "选择物流点",
							allowClear : true
						});
						$("#cityName")
								.change(
										function() {
											var cityId = $("#cityName").find(
													"option:selected").val();
											$
													.post(
															'stationForCity_station.do',
															{
																cityId : cityId
															},
															function(data) {
																$(
																		'#distributionStation')
																		.empty();
																$(
																		'#distributionStation')
																		.append(
																				"<option value=''></option>");
																var html;
																$
																		.each(
																				JSON
																						.parse(data),
																				function(
																						i,
																						n) {
																					html += "<option value='"+n.distributionStationId+"'>"
																							+ n.distributionStationName
																							+ "</option>";
																				});
																$(
																		'#distributionStation')
																		.append(
																				html);
															});
										});
						//FormComponents.init();
				$("#quzhicheckbox,#jianzhicheckbox,#lizhicheckbox").change(function(){
					var quzhi = 0;
					if($("#quzhicheckbox").is(':checked'))
					{
						quzhi = $("#quzhicheckbox").val();
					}
					var jianzhi = 0;
					if($("#jianzhicheckbox").is(':checked'))
					{
						jianzhi = $("#jianzhicheckbox").val();
					}
					var lizhi = 0;
					if($("#lizhicheckbox").is(':checked'))
					{
						lizhi = $("#lizhicheckbox").val();
					}
					$('#memberStatus').val(''+quzhi+','+jianzhi+','+lizhi);
					$('#search').click();
				});		
						
						
						
						
						
						
						
						
						
						
						
					});
</script>
</html>