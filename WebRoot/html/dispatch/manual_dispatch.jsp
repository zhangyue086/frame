<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<head>
	<meta charset="UTF-8">
	<title>调度管理</title>
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<link rel="stylesheet" href="${path}/css/baiduMap.css" />
	<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
	<link rel="stylesheet" href="http://api.map.baidu.com/res/12/bmap.css">
	<!--  <link href="${path}/js/js-autocomplete/jquery.autocomplete.css" rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" href="${path}/jeasyui/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${path}/jeasyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${path}/css/index.css">
	-->
	<script src="http://api.map.baidu.com/api?v=1.5&ak=759f225777418e45843e0018a74c55bf"></script>
	<script src="http://api.map.baidu.com/api?v=1.2"></script>
	<script src="http://api.map.baidu.com/getscript?v=1.2&ak=&services=&t=20121127154638"></script>
	<script src="http://api.map.baidu.com/library/MarkerTool/1.2/src/MarkerTool_min.js"></script>
	<script src="${path}/media/js/jquery-1.11.2.js" type="text/javascript"></script>
</head>
<body class="">
	<div class="mapLeft clearfix">
		<div class="mapLeftIn">
			<h3 class="mapLeftHead">　</h3>
			<div class="WLbox">
				<p class="SelectBox">
					<!-- 
					<input type="text" id="marketCircleId" name="marketCircleId" />
					-->
					
					<label for="cityId">城市：</label>
					<input type="hidden" id="cityIdForDispatchMap" name="cityId" style="width:112px"/>
					<select name="" id="cityName">
						<option value="">--</option>
						<c:forEach items="${cityList}" var="city">
							<option value="${city.cityId }">${city.cityName }</option>
						</c:forEach>
					</select>
					<br/><br/>
					<label for="WLSelect">站点：</label>
					<div class="SelectBoxMultiple" id="marketCircleDiv">
					<!-- 
					<#if resultList??>
						<#list resultList as mc>
							<p><input type="checkbox" onclick="showShopArea();" name="marketCircleIds"  value='${mc.marketCircleId}'/><span id='${mc.marketCircleId}_p'>${mc.marketCircleName}</span></p>
						</#list>
					</#if>
					--->
					</div>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<br/>
					<a href="#" class="select_button" onclick="searchDispathSo();">查询订单</a>
				</p>
				<p id="uncheckSoNums" class="mt10"></p>
				<p id="uncheckSoNumsBtn" style="display:none">
					<a href="#" class="select_button_D" onclick="searchUncheckSo();">显示</a>
				</p>
				<p id="msg" class="mt10"></p>
				<table id="WLTabel" class="">
					<tbody>
						<tr id="head_tr">
							<th width="5%"></th>
							<th width="17%">订单号</th>
							<th width="23%">商家</th>
							<th width="23%">站点</th>
							<th width="15%">取货时间</th>
							<!--  <th width="17%">倒计时</th>-->
						</tr>
					</tbody>
				</table>
				<p id="orderCount"></p>
				<p id="soundP"></p>
			</div>
		</div>
		<div class="mapLeftSidebar mapLeftSidebarL" title="点击展开/收缩左侧栏"></div>
	</div>
	<div class="mapRight" >
		<div class="mapRightIn">
			<div class="mapRightTop">
				<div class="search">
					<label for="cityIdForDm">城市：</label>
					<select name="" id="cityNameR">
						<option value="">--</option>
						<c:forEach items="${cityList}" var="city">
							<option value="${city.cityId }">${city.cityName }</option>
						</c:forEach>
					</select>
					<!-- <input type="text" id="cityIdForDm" name="cityIdForDm" style="width:112px"/> -->
					<label>站点：</label>
					<div class="SelectBoxMultipleR" id="marketCircleDivR">
					<!-- 
					<#if resultList??>
						<#list resultList as mc>
							<p><input type="checkbox" name="marketCircleIdsR"  value='${mc.marketCircleId}'/>${mc.marketCircleName}</p>
						</#list>
					</#if>
					-->
					</div>
					<label style="margin-left: 110px;">配送员：</label>
					<select name="numType" id="numType" />
						<option value="">---请选择---</option>
						<option value="1">0单</option>
						<option value="2">1单</option>
						<option value="3">2单</option>
						<option value="4">3单及以上</option>
					</select>
					<div class="suggestList">
						<ul id="dmul">
						</ul>
					</div>
					<input type="button" class="search_button" value="搜索配送员" onclick="searchALLDM();"/>
				</div>
			</div>
			<div class="mapRightMain">
				<div id="container"></div>
			</div>
		</div>
	</div>
	<span id="windowParent"><div id="myWindow"></div><div id="mySubWindow"></div></span>
	<div id="sodiv">
	<div class="mapLeftInMask"><img class="mapLeftInLoading" src="${path}/image/loading.gif" alt="" /></div>
<script src="${path}/js/dispatch_map.js"></script>
<script>
	
	//$("#marketCircleId").combobox({url:'/mcircle/list4jsonMarketCircle.do?searchDto',valueField:'marketCircleId',textField:'marketCircleName',editable:true});

	$('.SelectBoxMultiple').hover(
		function () {
			$(this).height('auto');
		},
		function () {
			$(this).height(20);
		}
	);
	
	$('.SelectBoxMultipleR').hover(
		function () {
			$(this).height('auto');
		},
		function () {
			$(this).height(20);
		}
	);
					
	var map = new BMap.Map("container");   // 创建地图实例
	map.addControl(new BMap.NavigationControl());   //缩放地图的控件，默认在左上角
	map.addControl(new BMap.ScaleControl());   //地图显示比例的控件，默认在左下方；
	map.addControl(new BMap.OverviewMapControl());   //地图的缩略图的控件，默认在右下方
	map.addControl(new BMap.MapTypeControl());
	map.enableScrollWheelZoom();//支持鼠标滑轮缩放地图
		//如果没有所属城市默认南京
		map.setCurrentCity("南京");
		var point = new BMap.Point(118.784, 32.040);   // 创建点坐标
		map.centerAndZoom(point, 16);	// 初始化地图，设置中心点坐标和地图级别
		
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
										var html;
										$("#marketCircleDiv").empty();
										$.each(JSON.parse(data),
														function(
																i,
																n) {
											                mapFocus(n.lngPoint,n.latPoint);
															html += "<option value='"+n.distributionStationId+"'>"
																	+ n.distributionStationName
																	+ "</option>";
																	$("#marketCircleDiv").append("<p><input type='checkbox' name='stationId' value='"+n.distributionStationId+"'/><span id='"+n.distributionStationId+"_p'>"+n.distributionStationName+"</span></p>");   
														
														
														});
									});
				
				});
		
		$("#cityNameR")
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
										var html;
										$("#marketCircleDivR").empty();
										$.each(JSON.parse(data),
														function(
																i,
																n) {
															html += "<option value='"+n.distributionStationId+"'>"
																	+ n.distributionStationName
																	+ "</option>";
																	$("#marketCircleDivR").append("<p><input type='checkbox' name='stationId' value='"+n.distributionStationId+"'/><span id='"+n.distributionStationId+"_p'>"+n.distributionStationName+"</span></p>");   
														
														
														});
									});
				});
	
</script>
</body>
</html>