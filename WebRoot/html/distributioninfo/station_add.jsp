<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path}/js/distribution.station.form.js"></script>
<style type="text/css">

#div_show
{
    height: 400px;
    left: 80px;
    position: fixed;
    top: 88px;
    width: 1180px;
    z-index: -100;
}
.div_show_title
{
 filter:progid:DXImageTransform.Microsoft.gradient(startColorstr='#12a3f4', endColorstr='#0e86c9', GradientType=0);
    clear: both;
    display: block;
    background: rgb(134, 134, 134);
    color: #FFFFFF;
    font-weight: 700;
    position: relative;
    height: 30px;
    width: 100%;
}
.div_show_font
{
    color: #FFFFFF;
    float: left;
    font-weight: 700;
    margin: 0.4em 0 0.4em 10px;
}
.div_show_btn
{
    float: right;
    margin: 0.4em 10px 0.4em 0;
}
#div_hotelmap
{
    height: 500px;
    border: 5px solid white;
}
</style>
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
			<ul class="breadcrumb">
				<li>
					<i class="icon-home"></i>
					<a href="index.do">首页</a> 
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">信息</a>
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#" onclick="loadHtml('initList_station.do')">物流点</a>
					<i class="icon-angle-right"></i>
				</li>
				<li>
					<a href="#">添加/编辑</a>
				</li>
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
				<div class="portlet-title">
					<div class="caption"></div>
					<div class="tools">
					</div>
				</div>
				<div class="portlet-body">
					<!-- BEGIN FORM-->
						<form id="stationAddForm" class="form-horizontal">
							<input type="hidden" name="station.distributionStationId" value="${station.distributionStationId }">
							<div class="alert alert-error hide">
								<button class="close" data-dismiss="alert"></button>
								表单数据验证失败,请重新输入！
							</div>
							<div class="alert alert-success hide">
								<button class="close" data-dismiss="alert"></button>
								表单验证成功！
							</div>							
							<div class="control-group">
								<label class="control-label">城市<span class="required">*</span></label>
								<div class="controls">
									<select id="cityName" class="span6 m-wrap" name="station.cityId" <c:if test="${station.distributionStationId gt 0}">disabled</c:if>>
										<option></option>
										<c:forEach items="${cityList}" var="city">
											<option value="${city.cityId }" <c:if test="${city.cityId == station.cityId}">selected</c:if>>${city.cityName }</option>
										</c:forEach>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">物流点<span class="required">*</span></label>
								<div class="controls">
									<input name="station.distributionStationName" type="text" class="span6 m-wrap" value="${station.distributionStationName}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">电话<span class="required">*</span></label>
								<div class="controls">
									<input name="station.distributionStationMobile" type="text" class="span6 m-wrap" value="${station.distributionStationMobile}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">地址<span class="required">*</span></label>
								<div class="controls">
									<input name="station.distributionStationAddress" id='distributionStationAddress' type="text" class="span6 m-wrap" value="${station.distributionStationAddress}"/>
							
                                       <a class='btn green' id='baiduMapInit' >地图定位</a>
                              	<div id="div_body">   </div>   
					<div id="div_show"> </div>			
								
								</div>
								
							</div>
							<div class="control-group">
								<label class="control-label">经度<span class="required">*</span></label>
								<div class="controls">
									<input name="station.lngPoint" id='stationLng' type="text" class="span6 m-wrap" value="${station.lngPoint}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">纬度<span class="required">*</span></label>
								<div class="controls">
									<input name="station.latPoint" id='stationLat' type="text" class="span6 m-wrap" value="${station.latPoint}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">站长<span class="required">*</span></label>
								<div class="controls">
									<input name="station.stationManagerName" type="text" class="span6 m-wrap" value="${station.stationManagerName}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">手机号(站长)<span class="required">*</span></label>
								<div class="controls">
									<input name="station.stationManagerMobile" type="text" class="span6 m-wrap" value="${station.stationManagerMobile}"/>
								</div>
							</div>
							<div class="control-group">
								<label class="control-label">物流点状态<span class="required">*</span></label>
								<div class="controls">
									<div class="basic-toggle-button">
										<input id="changeStatus" type="checkbox" class="toggle" <c:if test="${station.status == 1}">checked="checked"</c:if> />
									</div>
									<input type="hidden" name="station.status">
								</div>
							</div>
						</form>
						<!-- END FORM-->
						<div class="form-actions" style="">
							<span class="span2">&nbsp;</span>
							<button type="submit" class="btn blue" onclick="stationAdd()">保存</button>
							<button type="button" class="btn" onclick="loadHtml('initList_station.do')">返回</button>
						</div>
				</div>
			</div>
			<!-- END SAMPLE FORM PORTLET-->			
		</div>
		
	</div>
	<!-- END PAGE CONTENT-->
</div>
<!-- END PAGE CONTAINER--> 
<script>
jQuery(document).ready(function() {    
	// initiate layout and plugins
	DistribuitonStationAdd.init();
	FormComponents.init();
	$("#cityName").select2({
		placeholder: "选择城市"
	});
});

$("#baiduMapInit").click(function()
		{
	divtoshow();
	
		});
function stationAdd(){
	$("#stationAddForm").submit();
}


//显示百度地图弹出层
    function divtoshow() {
    	 var cityName = '';
         var distributionStationAddress=$("#distributionStationAddress").val();
	    $("#cityName").find("option").each(function() {
			if ($(this).attr("selected")) {
				cityName = $(this).html();
			}
		});
		if (cityName.length == 0 || distributionStationAddress.length == 0) {
			alert("请选择城市，并且填写地址");
			return false;
		}
		div_show = document.getElementById("div_show");
		div_show.innerHTML = "<div class=\"div_show_title\"> <span class=\"div_show_font\">地图</span> <input type=\"button\" value=\"关闭\" onclick=\"close_show();\" class=\"div_show_btn\" /> </div><div id=\"div_hotelmap\" ></div>";
		getshade();
		getpopup();
		///// cityName  distributionStationAddress

		getbaidumap(distributionStationAddress, cityName);
		div_show.style.zIndex = "1000";
		div_show.style.display = "block";
	}

	//创建遮罩层
	function getshade() {
		var div = document.createElement("div");
		div.style.width = Math.max(document.documentElement.scrollWidth,
				document.documentElement.clientWidth)
				+ "px";
		div.style.height = Math.max(document.documentElement.scrollHeight,
				document.documentElement.clientHeight)
				+ "px";
		div.style.backgroundColor = "#000000";
		div.style.position = "absolute";
		div.style.opacity = 0.5;
		div.style.left = 0;
		div.style.top = 0;
		div.id = "tohotelshade";
		div.style.zIndex = 100;
		document.getElementById("div_body").appendChild(div);
	}

	//创建用于显示百度地图的DIV
	function getpopup() {
		var div = document.createElement("div");
		div.style.width = "100%";
		div.style.height = "100%";
		div.id = "tohotelmap";
		document.getElementById("div_hotelmap").appendChild(div);
	}

	//清除弹出层和遮罩层
	function close_show() {
		var tohotelshade = document.getElementById("tohotelshade");
		var tohotelmap = top.document.getElementById("tohotelmap");
		var div_show = document.getElementById("div_show");
		var div_hotelmap = document.getElementById("div_hotelmap");
		div_show.style.zIndex = "-100";
		tohotelshade.parentNode.removeChild(tohotelshade);
		tohotelmap.parentNode.removeChild(tohotelmap);
		div_show.innerHTML = "";
	}

	//通过经纬度显示百度地图
	function getbaidumap(address, city) {
		console.log(address, city);
		var map = new BMap.Map("tohotelmap");
		map.centerAndZoom(new BMap.Point(116.309965, 40.058333), 10);
		map.enableScrollWheelZoom();//启动鼠标滚轮缩放地图
		map.enableKeyboard();//启动键盘操作地图
		map.addControl(new BMap.NavigationControl());
		map.addControl(new BMap.ScaleControl());
		map.addControl(new BMap.OverviewMapControl());
		map.addControl(new BMap.MapTypeControl());
		/////////////
		var myGeo = new BMap.Geocoder();
		// 将地址解析结果显示在地图上,并调整地图视野
		myGeo.getPoint(address, function(point) {
			if (point) {
				map.centerAndZoom(point, 16);
				var marker = new BMap.Marker(point);
				map.addOverlay(marker);
				marker.enableDragging();
				marker.addEventListener("dblclick", function(result) {
					$('#stationLng').val(result.point.lng);
					$('#stationLat').val(result.point.lat);
					close_show();
				});
			} else {
				alert("您选择地址没有解析到结果!");
			}
		}, city);

	}
</script>  
</body>
</html>