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
					<a href="#">信息中心</a>
					<i class="icon-angle-right"></i>
				</li>
				<li><a href="#">物流点</a></li>
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
										<select name="" id="cityName" class="span12" placeholder="选择城市" style="display:inline">
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
										<input type="hidden" id="stationStatus" value="1,2" />
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
								<a href="#" data-url="initAdd_station.do" class="btn green" id="addDistributionInit"><i class="icon-pencil"></i>添加物流点</a>
								</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label" style="display: none;">&nbsp;</label>
										<div class="controls">
											<label class="checkbox">
											<input type="checkbox" checked="checked" id='qiyongcheckbox' value="1" /> 启用
											</label>
											<label class="checkbox">
											<input type="checkbox" checked="checked" id='tingyongcheckbox' value="2" /> 停用
											</label>
					                    </div>
					  </div>
					
					<table class="table table-striped table-bordered table-hover table-full-width" id="mydemo">
						<thead>
							<tr>
								<th>物流点ID</th>
								<th>城市</th>
							    <th>物流点</th>
							    <th>地址</th>
							    <th>电话(物流点)</th>
							    <th>站长</th>
							    <th>手机(站长)</th>
							    <th>状态</th>
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
</div>
</body>
<script>
var otable;
$('#search').click(function(){
	otable.fnSettings().ajax={
		"url": "list_station.do?now=" + new Date().getTime(),
        "type": "POST",
        "data":{
        	 "station.cityId": $('#cityName').find("option:selected").val(),
             "station.distributionStationId": $('#distributionStation').val(),
             "stationStatus": $('#stationStatus').val()
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
            "url": "list_station.do?now=" + new Date().getTime(),
            "type": "POST",
            "data":{
            	 "station.cityId": $('#cityName').find("option:selected").val(),
            	 "station.distributionStationId": $('#distributionStation').val(),
            	 "stationStatus": $('#stationStatus').val()
            }
        },
        "columns": [
                    {"data":"distributionStationId","bVisible":false},
                    {"data": "cityName"},
                    {"data": "distributionStationName"},
                    {"data": "distributionStationAddress"},
                    {"data": "distributionStationMobile"},
                    {"data": "stationManagerName"},
                    {"data": "stationManagerMobile"},
                  ],
        "columnDefs": [
                       {
                         "targets": [7],
                         "data": "status",
                         "render": function(data, type, full) {
                           if(data == 1){
                        	  return "<span class='label label-info' onclick='changeStatus(2,"+full.distributionStationId+")'>启用</span>"; 
                           }
                           if(data == 2){
                        	   return "<span class='label label-danger' onclick='changeStatus(1,"+full.distributionStationId+")'>停用</span>"; 
                           }
                         }
                       },
                       {
                           "targets": [8],
                           "data": "distributionStationId",
                           "render": function(data, type, full) {
                        	 var html = "<a class='btn mini purple' id='editDistributionInit'  href='#' data-url='initEdit_station.do?station.distributionStationId="+data+"'><i class='icon-edit'></i>修改</a>&nbsp;";
                        	 html+= "<a class='btn mini red' id='deleteDistributionInit' data-toggle='modal' href='#myModal' data-url='initDelete_station.do?station.distributionStationId="+data+"'><i class='icon-trash'></i>删除</a>";
                             return html;
                           }
                         }
                     ]
    });
	$("#addDistributionInit").click(function(){
	    loadHtml($(this).attr("data-url"));
	});

	$("#editDistributionInit").die().live('click',function(event){
	    loadHtml($(this).attr("data-url"));
	});
	$("#deleteDistributionInit").die().live('click',function(){
	    $("#myModal").load($(this).attr("data-url"));
	});
	$("#cityName").select2({
		placeholder: "选择城市",
        allowClear: true
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
	
	$("#qiyongcheckbox,#tingyongcheckbox").change(function(){
		var qiyongcheckbox = 0;
		if($("#qiyongcheckbox").is(':checked'))
		{
			qiyongcheckbox = $("#qiyongcheckbox").val();
		}
		var tingyongcheckbox = 0;
		if($("#tingyongcheckbox").is(':checked'))
		{
			tingyongcheckbox = $("#tingyongcheckbox").val();
		}
		
		$('#stationStatus').val(''+qiyongcheckbox+','+tingyongcheckbox);
		$('#search').click();
	});		
	
});


function changeStatus(status,distributionId)
{
	$.post('distribution/updateStatus_station.do', {
			stationStatu : status,
			stationId : distributionId
		}, function(data) {
			$('#search').click();
		});

	}
</script>
</html>