$(document).ready(function () {
	/**
	 * TODO:mapRightMain高度
	 */
	function windowSize() {
		var windowWidth = $(window).width();
		var mapLeftWidth =  $('.mapLeft').width();
		var mapRightWidth = windowWidth - mapLeftWidth-5;

		var windowHeight = $(window).height();
		var mapRightTopHeight = $('.mapRightTop').height();
		var mapRightMainHeight = windowHeight - mapRightTopHeight;

		return {
			w : mapRightWidth,
			h : mapRightMainHeight
		};
	}
	$('.mapRight').width(windowSize().w);
	$('.mapRightMain').height(windowSize().h);
	$('.mapLeftInLoading').css({
		top : ( $(window).height() - $('.mapLeftInLoading').height() ) / 2 + $(window).scrollTop() + 'px',
		left : ( $(window).width() - $('.mapLeftInLoading').width() ) / 2 + $(window).scrollLeft() + 'px'
	});
	$(window).resize(function () {
		$('.mapRight').width(windowSize().w);
		$('.mapRightMain').height(windowSize().h);
		$('.mapLeftInLoading').css({
			top : ( $(window).height() - $('.mapLeftInLoading').height() ) / 2 + $(window).scrollTop() + 'px',
			left : ( $(window).width() - $('.mapLeftInLoading').width() ) / 2 + $(window).scrollLeft() + 'px'
		});
	});

	/**
	 * TODO:mapLeftSidebar点击隐藏左侧栏
	 */
//	var window_width = $(window).width();
//	var mapLeftSidebar_width = $('.mapLeftSidebar').width();
//	var mapLeft_width = $('.mapLeft').width();
//	var mapRight_width_type1 = window_width - mapLeftSidebar_width;
//	var mapRight_width_type2 = window_width - mapLeft_width;
//	$('.mapLeftSidebar').toggle(
//		function () {
//			$('.mapLeftIn').hide();
//			$('.mapLeft').animate({ width : '10px' }, 500);
//			$('.mapRight').animate({ width : mapRight_width_type1 }, 500);
//			$(this).removeClass('mapLeftSidebarL').addClass('mapLeftSidebarR');
//		},
//		function () {
//			$('.mapLeft').animate({ width : '390px' }, 500 ,function () {
//				$('.mapLeftIn').show();
//			});
//			$('.mapRight').animate({ width : mapRight_width_type2 }, 500);
//			$(this).removeClass('mapLeftSidebarR').addClass('mapLeftSidebarL');
//		}
//	);

	$('#distributionMemberName').focus(function () {

		var val = $('#distributionMemberName').val();
		var len = val.length;
		//console.log(len);
		if (len != 0) {
			$('.suggestList').show();
		} else {
			hidesuggest();
		}
	});
	$('#distributionMemberName').blur(function () {
	});

	/**
	 * 输入框KeyUp效果
	 */
	$('.search_input').keyup(function () {
		var val = $(this).val();
		var len = val.length;
		//console.log(len);
		$('#dmul').html("");
		if (len != 0) {
			$.post('/so/serarchDmByNameWithLike.do?dmName=' + val, null,
					function(data) {
						data = eval('(' + data + ')');
						var dmList = data;
						if(data!=null && data.length > 0 ){
							var html = '';
							for(var i = 0; i < dmList.length; i++) {
								var dmName = dmList[i]['distributionMemberName']+"";
								html += '<li><a href="#" onclick="setDmNameByClick(\''+dmName+'\')">'+dmName+'</a></li>';
							}
							$('#dmul').append(html);
							$('.suggestList').show();
						}
						
					}, 'json'
				);
		} else {
			hidesuggest();
		}
	});
});
function hidesuggest(){
	$('.suggestList').hide();
}
function setDmNameByClick(distributionMemberName){
	$('#distributionMemberName').val(distributionMemberName);
	hidesuggest();
}

function hideShopArea(){
	var marketCircleIdList = document.getElementsByName("marketCircleIds");
	for(var i = 0; i < marketCircleIdList.length; i++) {
		var marketCircleId = marketCircleIdList[i].value;
		var shopAreaList = document.getElementsByName(marketCircleId);
		if(shopAreaList != null){
			for(var j = 0; j < shopAreaList.length; j++) {
				shopAreaList[j].style.display ="none";
				shopAreaList[j].firstChild.checked  = false;
			}
		}
	}
}

/**
 * 更换城市的时候，级联改变商圈(for 订单查询)
 */
function changeMarketCircleByCityForOrder(cityId,marketCircleId){
	var cityId = $('#'+cityId).combobox('getValue');
	$("#"+marketCircleId).html("");
	$.post('/dstation/getMcListByCityIdDstation.do?cityId='+cityId, null,
			function(data) {
				if(data!=null && data.length > 0 ){
					for(var i = 0; i < data.length; i++) {
						if( i == 0 && data[i].city != null && data[i].city.longPoint != null){
							mapFocus(data[i].city.longPoint,data[i].city.letPoint);
						}
						var marketCircleName = data[i].marketCircleName;
						
						$("#"+marketCircleId).append("<p><input type='checkbox' onclick='showShopArea();' name='marketCircleIds'  value='"+data[i].marketCircleId+"'/><span id='"+data[i].marketCircleId+"_p'>"+data[i].marketCircleName+"</span></p>");   
					}
				}
				hideShopArea();
				
			}, 'json'
		);
}

//地图聚焦
function mapFocus(longPoint,letPoint){
	var point = new BMap.Point(longPoint, letPoint);   // 创建点坐标
	map.centerAndZoom(point, 16);	// 初始化地图，设置中心点坐标和地图级别
}

/**
 * 更换城市的时候，级联改变商圈(for 配送员查询)
 */
function changeMarketCircleByCityForDm(cityId,marketCircleId){
	var cityId = $('#'+cityId).combobox('getValue');
	$("#"+marketCircleId).html("");
	$.post('/dstation/getMcListByCityIdDstation.do?cityId='+cityId, null,
			function(data) {
				if(data!=null && data.length > 0 ){
					for(var i = 0; i < data.length; i++) {
						if( i == 0 && data[i].city != null && data[i].city.longPoint != null){
							mapFocus(data[i].city.longPoint,data[i].city.letPoint);
						}
						var marketCircleName = data[i].marketCircleName;
						$("#"+marketCircleId).append("<p><input type='checkbox' name='marketCircleIdsR'  value='"+data[i].marketCircleId+"'/>"+data[i].marketCircleName+"</p>");   
					}
				}
				hideShopArea();
			}, 'json'
		);
}

function searchByAddress(){
	 map.removeOverlay(marker);//移除所有标注
	 var keyword = document.getElementById("addr").value; 
     localSearch.search(keyword); 
     localSearch.setSearchCompleteCallback(function(searchResult){   
         var poi = searchResult.getPoi(0); 
         var marker = new BMap.Marker(poi.point);//创建标注
         marker.enableDragging(); //标注可拖拽
         var gc = new BMap.Geocoder();    
         marker.addEventListener("dragend", 
	         function(e){
	        	 var pt = e.point;
	      	     gc.getLocation(pt, function(rs){
	      	        var addComp = rs.addressComponents;
	      	        var address = document.getElementById("addr");
	      	        address.value = "";
	      	        address.value = addComp.city + addComp.district + addComp.street + addComp.streetNumber;
	     });   
	  })
      map.centerAndZoom(poi.point, 16); 
      map.addOverlay(marker);
      map.setZoom(20);
     }); 
 }

function pressEnter(e) {
    var code;
    if (!e) var  e = window.event;
    if (e.keyCode) code = e.keyCode;
    else if (e.which) code = e.which;
    if(code==13){
    	searchByAddress();
    }
}


function clearData() {
	var WLTabel = $('#WLTabel');
	var tr = WLTabel.children().children('tr');
	var tr_len = tr.length;
	for (var i = 1; i < tr_len; i++) {
		tr.eq(i).remove();
	}
	$('#orderCount').html("总计: 0单");
}

var expRecTimePeriodArry=["0:01-00:30","0:30-1:00","1:00-1:30","1:30-2:00","2:00-2:30","2:30-3:00","3:00-3:30","3:30-4:00","4:00-4:30","4:30-5:00","5:00-5:30","5:30-6:00","6:00-6:30","6:30-7:00","7:00-7:30","7:30-8:00","8:00-8:30","8:30-9:00","9:00-9:30","9:30-10:00","10:00-10:30","10:30-11:00","11:00-11:30","11:30-12:00","12:00-12:30","12:30-13:00","13:00-13:30","13:30-14:00","14:00-14:30","14:30-15:00","15:00-15:30","15:30-16:00","16:00-16:30","16:30-17:00","17:00-17:30","17:30-18:00","18:00-18:30","18:30-19:00","19:00-19:30","19:30-20:00","20:00-20:30","20:30-21:00","21:00-21:30","21:30-22:00","22:00-22:30","22:30-23:00","23:00-23:30","23:30-23:59"];
var soundplayFlag = false;
function searchDispathSo(){
	var stationIds=null;
	$("input[name='stationId']").each(function(i){
		 if($(this).is(':checked')){
			 if(stationIds==null){
				 stationIds=$(this).attr("value");
			 }else{
				 stationIds=stationIds+","+$(this).attr("value");
			 }
		 }  
	 });
//	alert(stationIds);
	$.ajax({
		   type: "POST",
		   url: "/dispatch/getUnResolvedOrder_Dispatch.do",
		   data: "stationIds="+stationIds,
		   success: function(msg){
//			   alert( "Data Saved: " + msg );
			   if(msg!=null){
			     var resJson= $.parseJSON(msg);
			     	showOrderOnMap(resJson)
			   }
			   
		   }
		});
	
//	soundplayFlag = false;
//	var marketCircleIdList = document.getElementsByName("marketCircleIds");
//	var marketCircleIds = "";
//	for(var i = 0; i < marketCircleIdList.length; i++) {
//		if(marketCircleIdList[i].checked == true) {
//			var marketCircleId = marketCircleIdList[i].value;
//			//if($('#'+marketCircleId+'_p').html() == "江宁" || $('#'+marketCircleId+'_p').html() == "仙林"){
//			//	soundplayFlag = true;
//			//	setTimeout("searchDispathSo()",60000);//1分钟查询一次
//			//}
//			if(marketCircleIds == "") {
//				marketCircleIds = marketCircleId;
//			}else {
//				marketCircleIds = marketCircleIds + ";" + marketCircleId;
//			}
//		}
//	}
//	
//	$('.mapLeftInMask').show();
//	$('#uncheckSoNumsBtn').hide();
//	clearData();
//	$("#msg").html('');
//	$("#uncheckSoNums").html('');
//	$('#orderCount').html('');
//	map.clearOverlays();    //清除地图上所有覆盖物
//	var cityId = "";
//	var shopAreaIdList = document.getElementsByName("shopAreaIds");
//	var shopAreaIds = "";
//	for(var j = 0; j < shopAreaIdList.length; j++) {
//		if(shopAreaIdList[j].checked == true) {
//			var shopAreaId = shopAreaIdList[j].value;
//			if(shopAreaIds == "") {
//				shopAreaIds = shopAreaId;
//			}else {
//				shopAreaIds = shopAreaIds + ";" + shopAreaId;
//			}
//		}
//	}
//	
//	var container = $("#container");
//	$.post('/so/searchDispathSo.do?soDto.marketCircleIds=' + marketCircleIds+'&soDto.shopAreaIds='+shopAreaIds+'&soDto.receiverCity='+cityId, null,
//		function(data) {
//			$('.mapLeftInMask').hide();
//			if(data=="用户已经退出，请重新登录！"){
//				$("#msg").html(data);
//				return;
//			}
//			data = eval('(' + data + ')');
//			if(data.success == false){
//				$("#msg").html(data.message);
//				return;
//			}
//			if(data.unCheckSoNums != null){
//				$("#uncheckSoNumsBtn").show();
//				$("#uncheckSoNums").html("客服正在处理的订单数："+data.unCheckSoNums+"单");
//			}
//			var soList = data.soList;
//			var outDmList;
//			if(data.dmMap != null){
//				outDmList = data.dmMap['outstation'];
//			}
//			if(soList != "" && soList != null && soList.length>0){
//				showOrderOnMap(soList);
//			}
//		}, 'json'
//	);
}

function searchUncheckSo(){
	$('.mapLeftInMask').show();
	var marketCircleIdList = document.getElementsByName("marketCircleIds");
	var marketCircleIds = "";
	for(var i = 0; i < marketCircleIdList.length; i++) {
		if(marketCircleIdList[i].checked == true) {
			var marketCircleId = marketCircleIdList[i].value;
			if(marketCircleIds == "") {
				marketCircleIds = marketCircleId;
			}else {
				marketCircleIds = marketCircleIds + ";" + marketCircleId;
			}
		}
	}
	$.post('/so/searchUncheckSo.do?soDto.marketCircleIds=' + marketCircleIds, null,
			function(data) {
				$('.mapLeftInMask').hide();
				if(data=="用户已经退出，请重新登录！"){
					$("#msg").html(data);
					return;
				}
				data = eval('(' + data + ')');
				if(data.success == false){
					$("#msg").html(data.message);
					return;
				}
				var soList = data.soList;
				if(soList != "" && soList != null && soList.length>0){
					showUncheckOrderOnMap(soList);
				}
			}, 'json'
		);
	
}


//在地图上显示订单
function showUncheckOrderOnMap(soList){
	var html = '';
	for (var i = 0; i < soList.length; i++) {
		var orderId = soList[i]['orderId'];
		var parentSoCode = "";
		if(soList[i]['parentSoCode']!=null && soList[i]['parentSoCode'] != ""){
			parentSoCode = soList[i]['parentSoCode'];
		}
		var lonPoint = soList[i]['receiveLng'];
		var latPoint = soList[i]['receiveLat'];
		//终点
		var p1 = new BMap.Point(lonPoint,latPoint);
		var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+soList[i]['orderCode']+"</td></tr><tr><td>商家名称：</td><td>"+soList[i]['shop'].shopName+"</td></tr><tr><td>收货地址：</td><td>"+soList[i]['receiverAddress']+"</td></tr></table>");
		addUncheckOrderMarker(p1,infoWindow,orderId,parentSoCode);
	}
}

//订单标注
function addUncheckOrderMarker(point,infoWindow,orderId,parentSoCode) {
	var marker = new BMap.Marker(point);
	//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
	var size = new BMap.Size(34,34);
    var icon = new BMap.Icon('/all_img/note7.png',size);
	marker.setIcon(icon);
	marker.addEventListener("mouseover", function () {
		this.openInfoWindow(infoWindow);
	});
	marker.addEventListener("mouseout", function () {
		this.closeInfoWindow();
	});
	map.addOverlay(marker);
}

//地图上显示大本营
function showHomeOnMap(stationName,lonPoint,latPoint,inDmList,distributionStationCode){
	var p1 = new BMap.Point(lonPoint,latPoint);
	var info = '站点：'+stationName+'<br/>';
	if(inDmList != null && inDmList.length > 0){
		info +='<div style="height:400px;overflow:hidden;overflow-y:auto;"><table border="1px"><tr><th>配送员名称</th><th>可接单时间</th><th>操作</th></tr>'
		for(var i = 0; i < inDmList.length; i++) {
			info +='<tr>';
			info +='<td>';
			var distributionMemberName = inDmList[i]['distributionMemberName'];
			var distributionMemberId = inDmList[i]['distributionMemberId'];
			var num = 0;
			if(inDmList[i]['acceptableOrderNum'] !=null){
				num = inDmList[i]['acceptableOrderNum'];
			}
			var soNum = 0
			if(inDmList[i]['soList'] !=null){
				soNum = inDmList[i]['soList'].length;
			}
			if(num!=0){
				info += '<input type="hidden" value="'+distributionMemberName+':'+distributionStationCode+'" id="'+distributionMemberId+'" /> <a href="#" onclick="showSimpleDmOnMap('+distributionMemberId+');" >'+'【'+num+'】'+distributionMemberName+'</a>';
			}else{
				info += '<input type="hidden" value="'+distributionMemberName+':'+distributionStationCode+'" id="'+distributionMemberId+'" /> <a href="#" onclick="showSimpleDmOnMap('+distributionMemberId+');" >'+distributionMemberName+'('+soNum+'单)</a>';
			}
			
			info +='</td>';
			info +='<td>';
			var acceptableOrderTime = inDmList[i]['acceptableOrderTime'];
			if(acceptableOrderTime != null){
				acceptableOrderTime = acceptableOrderTime.replace('T', ' ').substr(0,16);
				info += acceptableOrderTime;
			}
			info +='</td>';
			info +='<td>';
			info +='<input type="button" value ="新加订单" onclick="addOrder('+inDmList[i]['distributionMemberId']+')"/>';
			info +='</td>';
			info +='</tr>';
		}
		info +='</table></div>'
		var infoWindow = new BMap.InfoWindow(info);
		addHomeMarker(p1,infoWindow);
	}else{
		var infoWindow = new BMap.InfoWindow(info);
		addHomeMarker(p1,infoWindow);
	}
	
	
}
//点击配送员在地图上显示配送员
function showSimpleDmOnMap(distributionMemberId){
	$('.mapLeftInMask').show();
	var para = $("#"+distributionMemberId).val();
	var distributionMemberName = para.split(":")[0];
	var distributionStationCode =  para.split(":")[1];
	$.post('/so/getDMByName.do?soDto.distributionMemberName='+distributionMemberName, null,function(data){
		$('.mapLeftInMask').hide();
		data = eval('(' + data + ')');
		var dmList;
		if(data.dmMap != null){
			dmList = data.dmMap[distributionStationCode];
		}
		if(dmList != "" && dmList != null && dmList.length>0){
			writeSimpleDmOnMap(dmList);
		}
		
		}, 'json'
	);
}


function writeSimpleDmOnMap(dmList){
	if(dmList != null){
		//空闲配送员排序
		var p = 1;
		for (var i = 0; i < dmList.length; i++) {
			var point = new BMap.Point(dmList[i]['lonPoint'], dmList[i]['latPoint']);
			var distributionMemberName = dmList[i]['distributionMemberName'];
			var mobile = dmList[i]['mobile'];
			//创建信息窗口
			var context1 = "";
			var orderC = "";
			var status = "";
			if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 0){
				status = "空闲";
			}else if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 1){
				status = "配送中";
			}
			var dmSoList = dmList[i]['soList'];
			var infoWindow;
			if(dmSoList != null && dmSoList !="" && dmSoList.length>0){
				//获取所有订单ID
				var arrayObj = new Array([dmSoList.length]);　//创建一个数组并指定长度，注意不是上限，是长度
				for(var j= 0;j<dmSoList.length;j++){
					arrayObj[j]= dmSoList[j]['orderId'];
				}
				for(var k= 0;k<dmSoList.length;k++){
					var soStatus = "";
					if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 2){
						soStatus = "已调度";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 3){
						soStatus = "揽货中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 4){
						soStatus = "配送中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 5){
						soStatus = "送达";
					}
					
					orderC += "<tr><td><a href='#' onclick='showOrderLocus("+dmSoList[k]['orderId']+")' />"+dmSoList[k]['orderCode']+"</a></td><td>"+soStatus+"</td><td>"+dmSoList[k]['lastUpdateTime'].replace('T', '').substr(10,5)+"</td><td>"+dmSoList[k]['distance']+"M</td><td><input type='button' value ='移除' onclick='removeOrder("+dmSoList[k]['orderId']+","+ dmList[i]['distributionMemberId'] + ")'/></td></tr>";
				}
				context1 = "<h4>携带订单信息列表</h4><table border='1px'><tr><td><b>配送员：</b></td><td Colspan='4'>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td><b>订单号</b></td><td><b>状态</b></td><td><b>最后更新时间</b></td><td><b>配送距离</b></td><td><b>操作</b></td></tr>"+orderC+"<tr><td colspan='5'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='显示当前订单线路' onclick='showDmOrderLocus("+arrayObj+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}else{
				context1 = "<h4>空闲中</h4><table border='1px'><tr><td width=100px><b>配送员：</b></td><td>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td colspan='2'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}
			//定义显示的Label
			if(dmSoList == null || dmSoList.length <= 0){
				var myLabel = new BMap.Label("<font color='green' >"+dmList[i]['distributionMemberName']+"：0单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
			}else if(dmSoList.length > 3){
				var myLabel = new BMap.Label("<font color='red' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
					
			}else{
				var myLabel = new BMap.Label("<font color='#FFC125' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
			}
			addSimpleDistributionMarker(point,infoWindow,myLabel);
		}
	}
}

function addSimpleDistributionMarker(point, infoWindow, myLabel) {
	var marker = new BMap.Marker(point);
	var icon = BMapLib.MarkerTool.SYS_ICONS[10];
	marker.setIcon(icon);
	marker.addEventListener("click", function () {
		this.openInfoWindow(infoWindow);
	});
	try{
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click",true,true);
		myLabel.addEventListener("click", function () {
			marker.dispatchEvent(evt);
		});
	}catch(err){}
	map.addOverlay(marker);
	map.addOverlay(myLabel);
	try{
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click",true,true);
		marker.dispatchEvent(evt);
	}catch(err){}
}


//订单标注
function addHomeMarker(point,infoWindow) {
	var marker = new BMap.Marker(point);
	//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
	var size = new BMap.Size(53,43);
    var icon = new BMap.Icon('/all_img/house.png',size);
	marker.setIcon(icon);
	marker.addEventListener("click", function () {
		this.openInfoWindow(infoWindow);
	});
	map.addOverlay(marker);
}

//在地图上显示订单
//父订单和子订单
function showOrderOnMap(soList){
	clearData();
	var html = '';
	for (var i = 0; i < soList.length; i++) {
		html += '<tr>';
		var orderId = soList[i]['logisticsOrderId'];
		var parentSoCode = "";
		if(soList[i]['parentSoCode']!=null && soList[i]['parentSoCode'] != ""){
			parentSoCode = soList[i]['parentSoCode'];
		}
		html += '<td ><input type="checkbox" id="'+orderId+'" onclick="clicklistCheckbox('+orderId+');" name="soIdList" value="'+soList[i]['orderId']+'" /></td>';
		html += '<td >'+"<a  href='#'  onclick='showOrderLocus("+soList[i]['receiveLng']+","+soList[i]['receiveLat']+","+soList[i]['shopLng']+","+soList[i]['shopLat']+")' >"+soList[i]['oldOrderCode'].substr(soList[i]['oldOrderCode'].length-5,soList[i]['oldOrderCode'].length)+'</a></td>';
		
		if(soList[i]['shopName'] != null){
		html += '<td>'+soList[i]['shopName']+'</td>';
	    }else{
	  	html += '<td></td>';
     	}
		html += '<td>'+soList[i]['distributionStation'].distributionStationName+'</td>';
		if(soList[i]['requirePickUpTime']==null || soList[i]['requirePickUpTime'] == ""){
		html += '<td></td>';
	    }else{
		html += '<td>'+soList[i]['requirePickUpTime'].replace('T', '').substr(10,5)+'</td>';
	    }
		
//		var downmoniute=GetDateDiff(soList[i]['requirePickUpTime'].format("yyyy-MM-dd HH:mm:ss"),new Date().format("yyyy-MM-dd HH:mm:ss"),"minute")
		//		html += '<td ><input type="checkbox" id="'+orderId+'" onclick="clicklistCheckbox('+orderId+');" name="soIdList" value="'+soList[i]['orderId']+'" /></td>';
//		html += '<td>';
//		if(soList[i]['orderChangedInfo'] != null){
//			html += '<font color="red">(变)</font>';
//		}
//		html += '<a id="'+orderId+'_a" href="#" onclick="showOrderLocus('+orderId+');" >'+soList[i]['orderCode'].substr(soList[i]['orderCode'].length-5,soList[i]['orderCode'].length)+'</a>';
//		html +=  '<a id="'+orderId+'_a" href="#" onclick="gotoDetail('+orderId+');" ><font color="blue">【详】</font></a>';
//		if(soList[i]['parentSoCode']!=null && soList[i]['parentSoCode'] != ""){
//			html += '<br/><input value="'+orderId+'" type="hidden" name="'+soList[i]['parentSoCode']+'_hidden" /><font color="red">M:'+soList[i]['parentSoCode'].substr(soList[i]['parentSoCode'].length-5,soList[i]['parentSoCode'].length)+'</font>';
//		}
//		if(soList[i]['expectedReceiveTimePeriod'] != null && soList[i]['expectedReceiveDate'] != null){
//			html += '<font color="red">(预)</font>';
//		}
//		if(soList[i]['isCommunicated'] != null && soList[i]['isCommunicated'] == 1){
//			html += '<font color="red">(丢)</font>';
//		}
//		if(soList[i]['orderCount'] != null && soList[i]['orderCount'] > 7){
//			html += '<font color="red">(多)</font>';
//		}
//		if(soList[i]['ifLossOrder'] != null && soList[i]['ifLossOrder'] > 0){
//			html += '<font color="red">(补)</font>';
//		}
//		html += '</td>';
//		if(soList[i]['shop'] != null){
//			html += '<td>'+soList[i]['shop'].shopName+'</td>';
//		}else{
//			html += '<td></td>';
//		}
//		if(soList[i]['shop'] != null && soList[i]['shop'].shopArea != null){
//			html += '<td>'+soList[i]['shop'].shopArea.shopAreaName+'</td>';
//		}else{
//			html += '<td></td>';
//		}
//		if(soList[i]['requirePickUpTime']==null || soList[i]['requirePickUpTime'] == ""){
//			html += '<td></td>';
//		}else{
//			html += '<td>'+soList[i]['requirePickUpTime'].replace('T', '').substr(10,5)+'</td>';
//		}
//		if(soList[i]['dispatchCountdownMin'] != null){
//			if(soList[i]['dispatchCountdownMin'] < 45){
//				html += '<td><font color="red">'+soList[i]['dispatchCountdownMin']+'</font></td>';
//			}else{
//				html += '<td>'+soList[i]['dispatchCountdownMin']+'</td>';
//			}
//		}else{
//			html += '<td></td>';
//		}
////		if(soList[i]['orderEstimatedArrivedTime']==null || soList[i]['orderEstimatedArrivedTime'] == ""){
////			//如果是预约单，预计送达时间为预约时间
////			if(soList[i]['expectedReceiveDate']!=null && soList[i]['expectedReceiveTimePeriod']!=null){
////				html += '<td>'+soList[i]['expectedReceiveDate'].substr(0,10)+' '+expRecTimePeriodArry[soList[i]['expectedReceiveTimePeriod']-1]+'</td>';
////			}else{
////				html += '<td></td>';
////			}
////		}else{
////			html += '<td>'+soList[i]['orderEstimatedArrivedTime'].replace('T', ' ').substr(0,16)+'</td>';
////		}
		html += '</tr>';
		var lonPoint = soList[i]['receiveLng'];
		var latPoint = soList[i]['receiveLat'];
		//终点
		var p1 = new BMap.Point(lonPoint,latPoint);
		var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+soList[i]['oldOrderCode']+"</td></tr><tr><td>商家名称：</td><td>"+soList[i]['shopName']+"</td></tr><tr><td>收货地址：</td><td>"+soList[i]['receiverAddress']+"</td></tr></table>");
		addOrderMarker(p1,infoWindow,orderId,parentSoCode);
	}
	$('#WLTabel').children().append(html);
	if(soundplayFlag){
		$('#soundP').html("<embed src='/js/PlaySound.swf' class='alert' style='position:absolute;margin-top:-1;width:1px;height:1px;z-index:0;' flashvars='audiofile=/js/order.mp3'></embed>");
	}
	$('#orderCount').html("总计:"+soList.length+"单");
}

function GetDateDiff(startTime, endTime, diffType) {
    //将xxxx-xx-xx的时间格式，转换为 xxxx/xx/xx的格式 
    startTime = startTime.replace(/\-/g, "/");
    endTime = endTime.replace(/\-/g, "/");
    //将计算间隔类性字符转换为小写
    diffType = diffType.toLowerCase();
    var sTime = new Date(startTime);      //开始时间
    var eTime = new Date(endTime);  //结束时间
    //作为除数的数字
    var divNum = 1;
    switch (diffType) {
        case "second":
            divNum = 1000;
            break;
        case "minute":
            divNum = 1000 * 60;
            break;
        case "hour":
            divNum = 1000 * 3600;
            break;
        case "day":
            divNum = 1000 * 3600 * 24;
            break;
        default:
            break;
    }
    return parseInt((eTime.getTime() - sTime.getTime()) / parseInt(divNum));
}

function gotoDetail(orderId){
	gotoEditSo(orderId,'详情');
}

//在地图上显示订单
function showOrderOnMapWithoutList(soList){
	for (var i = 0; i < soList.length; i++) {
		var orderId = soList[i]['orderId'];
		var parentSoCode = "";
		if(soList[i]['parentSoCode']!=null && soList[i]['parentSoCode'] != ""){
			parentSoCode = soList[i]['parentSoCode'];
		}
		var lonPoint = soList[i]['receiveLng'];
		var latPoint = soList[i]['receiveLat'];
		//终点
		var p1 = new BMap.Point(lonPoint,latPoint);
		var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+soList[i]['orderCode']+"</td></tr><tr><td>商家名称：</td><td>"+soList[i]['shop'].shopName+"</td></tr><tr><td>收货地址：</td><td>"+soList[i]['receiverAddress']+"</td></tr></table>");
		addOrderMarker(p1,infoWindow,orderId,parentSoCode);
	}
}

//点击列表的checkbox
function clicklistCheckbox(orderId){
	$.post('/so/searchOrderLocus.do?soDto.orderId=' + orderId, null,function(data){
		data = eval('(' + data + ')');
		var parentSoCode = "";
		if(data.parentSoCode!=null && data.parentSoCode != ""){
			parentSoCode = data.parentSoCode;
		}
		if(parentSoCode != ""){
			var hds = document.getElementsByName(parentSoCode+"_hidden");
			for(var i = 0; i < hds.length; i++) {
				 if($("#"+orderId).attr("checked")=="checked"){
					 $("#"+hds[i].value).attr("checked",true);;
				 }
				 //如果是联营订单，取消必须一起取消，需要alert()提醒;
				clickCheckbox(hds[i].value);
			}
		}else{
			clickCheckbox(orderId);
		}
		
		}, 'json'
	);
}

function clickCheckbox(orderId){
	//var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: false}});
	 if($("#"+orderId).attr("checked")=="checked"){
		 $("#"+orderId).parent().parent().css('background', '#D1EEEE');
		 $.post('/so/searchOrderLocus.do?soDto.orderId=' + orderId, null,function(data){
				data = eval('(' + data + ')');
				var lonPoint = data.receiveLng;
				var latPoint = data.receiveLat;
				var parentSoCode = "";
				if(data.parentSoCode != null && data.parentSoCode != ""){
					parentSoCode = data.parentSoCode;
				}
				//终点
				var p1 = new BMap.Point(lonPoint,latPoint);
				var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+data.orderCode+"</td></tr><tr><td>商家名称：</td><td>"+data.shop.shopName+"</td></tr><tr><td>收货地址：</td><td>"+data.receiverAddress+"</td></tr></table>");
				addOrderSelectMarker(p1,infoWindow,orderId,parentSoCode);
				}, 'json'
			);
	 }else{
		 $("#"+orderId).parent().parent().css('background', '#FFFFFF');
		 $.post('/so/searchOrderLocus.do?soDto.orderId=' + orderId, null,function(data){
				data = eval('(' + data + ')');
				var lonPoint = data.receiveLng;
				var latPoint = data.receiveLat;
				var parentSoCode = "";
				if(data.parentSoCode != null && data.parentSoCode != ""){
					parentSoCode = data.parentSoCode;
				}
				var oldmarker = $('#infoSoTb').parent().parent();
				map.removeOverlay(oldmarker);
				//终点
				var p1 = new BMap.Point(lonPoint,latPoint);
				var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+data.orderCode+"</td></tr><tr><td>商家名称：</td><td>"+data.shop.shopName+"</td></tr><tr><td>收货地址：</td><td>"+data.receiverAddress+"</td></tr></table>");
				addOrderMarker(p1,infoWindow,orderId,"");
				}, 'json'
			);
	 }
	
}

//显示订单轨迹
function showOrderLocus(lonPoint,latPoint,shopLonPoint,shopLatPoint){
//	$.post('/so/searchOrderLocus.do?soDto.orderId=' + orderId, null,function(data){
//		data = eval('(' + data + ')');
//		var lonPoint = data.receiveLng;
//		var latPoint = data.receiveLat;
//		var shopLonPoint = data.shop.longPoint;
//		var shopLatPoint = data.shop.letPoint;
		//起点
		var p1 = new BMap.Point(shopLonPoint,shopLatPoint);
		//终点
		var p2 = new BMap.Point(lonPoint,latPoint);
		map.centerAndZoom(p2,16);  //初始化时，即可设置中心点和地图缩放级别。
		
		var marker = new BMap.Marker(p1);
		//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
		var size = new BMap.Size(11,11);
	    var icon = new BMap.Icon('/image/note6.png',size);
		marker.setIcon(icon);
		map.addOverlay(marker);
		var polyline = new BMap.Polyline([p1,p2], {strokeColor:"red", strokeWeight:4, strokeOpacity:0.5});
        map.addOverlay(polyline);
//        clickCheckbox(orderId);
		                                
//		var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: false}});
//		driving.search(p1, p2);
//		//var transit = new BMap.TransitRoute(map, {renderOptions:{map: map}}); 
//		//transit.search(p1, p2);  
//		transit.setSearchCompdrivingleteCallback(function(results){    
//			if (driving.getStatus() == BMAP_STATUS_SUCCESS){    
//				 var firstPlan = results.getPlan(0);  
//				 var route = firstPlan.getRoute(0);    
//				 map.addOverlay(new BMap.Polyline(route.getPath()));    
//			}
//	    })    
	    
//		}, 'json'
//	);
}


//物流人员标注
function addDistributionMarker(point, infoWindow, myLabel) {
	var marker = new BMap.Marker(point);
	var icon = BMapLib.MarkerTool.SYS_ICONS[10];
	marker.setIcon(icon);
	marker.addEventListener("click", function () {
		this.openInfoWindow(infoWindow);
	});
	try{
		var evt = document.createEvent("MouseEvents");
		evt.initEvent("click",true,true);
		myLabel.addEventListener("click", function () {
			marker.dispatchEvent(evt);
		});
	}catch(err){}
	map.addOverlay(marker);
	map.addOverlay(myLabel);
}




//订单标注
function addOrderMarker(point,infoWindow,orderId,parentSoCode) {
	var marker = new BMap.Marker(point);
	//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
	var size = new BMap.Size(34,34);
    var icon = new BMap.Icon('/image/note4.png',size);
	marker.setIcon(icon);
	marker.addEventListener("mouseover", function () {
		this.openInfoWindow(infoWindow);
	});
	marker.addEventListener("mouseout", function () {
		this.closeInfoWindow();
	});
	marker.addEventListener("click", function (e) {
		if(parentSoCode != ""){
			orderHighLightForParent(parentSoCode);
		}else{
			orderHighLight(orderId);
		}
	});
	map.addOverlay(marker);
}

//父子单，点击一个，另个全高亮
function orderHighLightForParent(parentSoCode){
	var hds = document.getElementsByName(parentSoCode+"_hidden");
	for(var i = 0; i < hds.length; i++) {
		orderHighLight(hds[i].value);
	}
}

//点击订单图标，高亮前排显示订单号所在列
function orderHighLight(orderId){
	var vTable=$("#WLTabel");//得到表格ID=TbData的jquery对象
	var orderTR = $("#"+orderId).parent().parent();
	var vTrClone=orderTR.clone(true);//创建行的副本对象vTrClone
	vTrClone.prependTo(vTable);//把副本单元格对象添加到表格下方
	orderTR.remove();
	var headTR = $("#head_tr");
	var headTRClone=headTR.clone(true);//创建行的副本对象vTrClone
	headTR.remove();
	headTRClone.prependTo(vTable);//把副本单元格对象添加到表格下方
	vTrClone.css('background', '#D1EEEE');
	$("#"+orderId).attr("checked",true);
	clickCheckbox(orderId);
}

//父子单，点击一个，所在列恢复正常
function orderNormalLightForParent(parentSoCode){
	var hds = document.getElementsByName(parentSoCode+"_hidden");
	for(var i = 0; i < hds.length; i++) {
		orderNormalLight(hds[i].value);
	}
}

//再次点击订单图标，订单号所在列恢复正常
function orderNormalLight(orderId){
	$("#"+orderId).parent().parent().css('background', '#FFFFFF');
	$("#"+orderId).attr("checked",false);;
	clickCheckbox(orderId);
}

//订单标注
function addOrderSelectMarker(point,infoWindow,orderId,parentSoCode) {
	var marker = new BMap.Marker(point);
	//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
	var size = new BMap.Size(34,34);
    var icon = new BMap.Icon('/all_img/note5.png',size);
	marker.setIcon(icon);
	marker.addEventListener("mouseover", function () {
		this.openInfoWindow(infoWindow);
	});
	marker.addEventListener("mouseout", function () {
		this.closeInfoWindow();
	});
	marker.addEventListener("click", function (e) {
		if(parentSoCode != ""){
			orderNormalLightForParent(parentSoCode);
		}else{
			orderNormalLight(orderId);
		}
	});
	map.addOverlay(marker);
	
}


function showDmOrderLocus(orderIdArray){
	//必须用arguments，有待观察
	if(arguments != null && arguments.length > 0){
		for(var m= 0;m<arguments.length;m++){
			$.post('/so/searchOrderLocus.do?soDto.orderId=' + arguments[m], null,function(data){
				data = eval('(' + data + ')');
				var lonPoint = data.receiveLng;
				var latPoint = data.receiveLat;
				var shopLonPoint = data.shop.longPoint;
				var shopLatPoint = data.shop.letPoint;
				//起点
				var p1 = new BMap.Point(shopLonPoint,shopLatPoint);
				//终点
				var p2 = new BMap.Point(lonPoint,latPoint);
				var marker = new BMap.Marker(p1);
				//var icon = BMapLib.MarkerTool.SYS_ICONS[9];
				var size = new BMap.Size(11,11);
			    var icon = new BMap.Icon('/all_img/note6.png',size);
				marker.setIcon(icon);
				map.addOverlay(marker);
				var polyline = new BMap.Polyline([p1,p2], {strokeColor:"red", strokeWeight:4, strokeOpacity:0.5});
		        map.addOverlay(polyline);
		        
//				var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: false}});
//				driving.search(p1, p2);
//				driving.setSearchCompleteCallback(function(results){    
//					if (driving.getStatus() == BMAP_STATUS_SUCCESS){    
//						 var firstPlan = results.getPlan(0);  
//						 var route = firstPlan.getRoute(0);    
//						 map.addOverlay(new BMap.Polyline(route.getPath()));    
//					}    
//			    })    
				}, 'json'
			);
		}
	}
	
}

//在地图上创建配送员,1单以及以下显示
function showDmOnMapWithOrder(dmList){
	if(dmList != null){
		//空闲配送员排序
		var p = 1;
		for (var i = 0; i < dmList.length; i++) {
			var point = new BMap.Point(dmList[i]['lonPoint'], dmList[i]['latPoint']);
			var distributionMemberName = dmList[i]['distributionMemberName'];
			var mobile = dmList[i]['mobile'];
			//创建信息窗口
			var context1 = "";
			var orderC = "";
			var status = "";
			if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 0){
				status = "空闲";
			}else if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 1){
				status = "配送中";
			}
			var dmSoList = dmList[i]['soList'];
			var infoWindow;
			if(dmSoList != null && dmSoList !="" && dmSoList.length>0){
				//获取所有订单ID
				var arrayObj = new Array([dmSoList.length]);　//创建一个数组并指定长度，注意不是上限，是长度
				for(var j= 0;j<dmSoList.length;j++){
					arrayObj[j]= dmSoList[j]['orderId'];
				}
				for(var k= 0;k<dmSoList.length;k++){
					var soStatus = "";
					if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 2){
						soStatus = "已调度";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 3){
						soStatus = "揽货中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 4){
						soStatus = "配送中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 5){
						soStatus = "送达";
					}
					
					orderC += "<tr><td><a href='#' onclick='showOrderLocus("+dmSoList[k]['orderId']+")' />"+dmSoList[k]['orderCode']+"</a></td><td>"+soStatus+"</td><td>"+dmSoList[k]['lastUpdateTime'].replace('T', '').substr(10,5)+"</td><td>"+dmSoList[k]['distance']+"M</td><td><input type='button' value ='移除' onclick='removeOrder("+dmSoList[k]['orderId']+","+ dmList[i]['distributionMemberId'] + ")'/></td></tr>";
				}
				context1 = "<h4>携带订单信息列表</h4><table border='1px'><tr><td><b>配送员：</b></td><td Colspan='4'>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td><b>订单号</b></td><td><b>状态</b></td><td><b>最后更新时间</b></td><td><b>配送距离</b></td><td><b>操作</b></td></tr>"+orderC+"<tr><td colspan='5'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='显示当前订单线路' onclick='showDmOrderLocus("+arrayObj+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}else{
				context1 = "<h4>空闲中</h4><table border='1px'><tr><td width=100px><b>配送员：</b></td><td>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td colspan='2'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}
			//定义显示的Label
			if(dmSoList == null || dmSoList.length <= 0){
				var myLabel = new BMap.Label("<font color='green' >【"+dmList[i]['acceptableOrderNum']+"】"+dmList[i]['distributionMemberName']+"：0单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
				p++;
			}else if(dmSoList.length > 3){
				var myLabel = new BMap.Label("<font color='red' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
					
			}else{
				var myLabel = new BMap.Label("<font color='#FFC125' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
			}
			if(dmSoList == null || dmSoList.length<=2){
				addDistributionMarker(point,infoWindow,myLabel);
			}
		}
	}
}

//在地图上创建配送员,全部显示
function showDmOnMap(dmList){
	if(dmList != null){
		//空闲配送员排序
		var p = 1;
		for (var i = 0; i < dmList.length; i++) {
			var point = new BMap.Point(dmList[i]['lonPoint'], dmList[i]['latPoint']);
			var distributionMemberName = dmList[i]['distributionMemberName'];
			var mobile = dmList[i]['mobile'];
			//创建信息窗口
			var context1 = "";
			var orderC = "";
			var status = "";
			if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 0){
				status = "空闲";
			}else if(dmList[i]['jobState'] != "" && dmList[i]['jobState'] == 1){
				status = "配送中";
			}
			var dmSoList = dmList[i]['soList'];
			var infoWindow;
			if(dmSoList != null && dmSoList !="" && dmSoList.length>0){
				//获取所有订单ID
				var arrayObj = new Array([dmSoList.length]);　//创建一个数组并指定长度，注意不是上限，是长度
				for(var j= 0;j<dmSoList.length;j++){
					arrayObj[j]= dmSoList[j]['orderId'];
				}
				for(var k= 0;k<dmSoList.length;k++){
					var soStatus = "";
					if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 2){
						soStatus = "已调度";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 3){
						soStatus = "揽货中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 4){
						soStatus = "配送中";
					}else if(dmSoList[k]['orderStatus'] != null && dmSoList[k]['orderStatus'] == 5){
						soStatus = "送达";
					}
					
					orderC += "<tr><td><a href='#' onclick='showOrderLocus("+dmSoList[k]['orderId']+")' />"+dmSoList[k]['orderCode']+"</a></td><td>"+soStatus+"</td><td>"+dmSoList[k]['lastUpdateTime'].replace('T', '').substr(10,5)+"</td><td>"+dmSoList[k]['distance']+"M</td><td><input type='button' value ='移除' onclick='removeOrder("+dmSoList[k]['orderId']+","+ dmList[i]['distributionMemberId'] + ")'/></td></tr>";
				}
				context1 = "<h4>携带订单信息列表</h4><table border='1px'><tr><td><b>配送员：</b></td><td Colspan='4'>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td><b>订单号</b></td><td><b>状态</b></td><td><b>最后更新时间</b></td><td><b>配送距离</b></td><td><b>操作</b></td></tr>"+orderC+"<tr><td colspan='5'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='显示当前订单线路' onclick='showDmOrderLocus("+arrayObj+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}else{
				context1 = "<h4>空闲中</h4><table border='1px'><tr><td width=100px><b>配送员：</b></td><td>"+distributionMemberName+"  " +mobile+"</td></tr><tr><td colspan='2'><input type='button' value ='新加订单'onclick='addOrder("+dmList[i]['distributionMemberId']+")'/><input type='button' value ='跑单记录'onclick='gotoDmOrderList("+dmList[i]['distributionMemberId']+")'/></td></tr></table>";
				infoWindow = new BMap.InfoWindow(context1);
			}
			//定义显示的Label
			if(dmSoList == null || dmSoList.length <= 0){
				var myLabel = new BMap.Label("<font color='green' >【"+dmList[i]['acceptableOrderNum']+"】"+dmList[i]['distributionMemberName']+"：0单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
			}else if(dmSoList.length > 3){
				var myLabel = new BMap.Label("<font color='red' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
					
			}else{
				var myLabel = new BMap.Label("<font color='#FFC125' >"+dmList[i]['distributionMemberName']+"："+dmSoList.length+"单</font>",{
					offset:new BMap.Size(-45,-40),
					position:point});
			}
			addDistributionMarker(point,infoWindow,myLabel);
		}
	}
}

//显示商家区域
function showShopArea(){
	var marketCircleIdList = document.getElementsByName("marketCircleIds");
	for(var i = 0; i < marketCircleIdList.length; i++) {
		if(marketCircleIdList[i].checked == true) {
			var marketCircleId = marketCircleIdList[i].value;
			var shopAreaList = document.getElementsByName(marketCircleId);
			if(shopAreaList != null){
				for(var j = 0; j < shopAreaList.length; j++) {
					shopAreaList[j].style.display ="";
				}
			}
		}else{
			var marketCircleId = marketCircleIdList[i].value;
			var shopAreaList = document.getElementsByName(marketCircleId);
			if(shopAreaList != null){
				for(var j = 0; j < shopAreaList.length; j++) {
					shopAreaList[j].style.display ="none";
					shopAreaList[j].firstChild.checked  = false;
				}
			}
		}
	}
	
}


//显示所有配送员
function searchALLDM(){
	var distributionMemberName = $("#distributionMemberName").val();
	var numType = $("#numType").val();
	//单独搜索某个配送员时清空覆盖物
	map.clearOverlays(); 
	//需要重新查询订单
	var marketCircleIdList = document.getElementsByName("marketCircleIds");
	var marketCircleIds = "";
	for(var i = 0; i < marketCircleIdList.length; i++) {
		if(marketCircleIdList[i].checked == true) {
			var marketCircleId = marketCircleIdList[i].value;
			if(marketCircleIds == "") {
				marketCircleIds = marketCircleId;
			}else {
				marketCircleIds = marketCircleIds + ";" + marketCircleId;
			}
		}
	}
	
	var shopAreaIdList = document.getElementsByName("shopAreaIds");
	var shopAreaIds = "";
	for(var j = 0; j < shopAreaIdList.length; j++) {
		if(shopAreaIdList[j].checked == true) {
			var shopAreaId = shopAreaIdList[j].value;
			if(shopAreaIds == "") {
				shopAreaIds = shopAreaId;
			}else {
				shopAreaIds = shopAreaIds + ";" + shopAreaId;
			}
		}
	}
	
	var marketCircleIdListR = document.getElementsByName("marketCircleIdsR");
	var marketCircleIdsR = "";
	for(var i = 0; i < marketCircleIdListR.length; i++) {
		if(marketCircleIdListR[i].checked == true) {
			var marketCircleIdR = marketCircleIdListR[i].value;
			if(marketCircleIdsR == "") {
				marketCircleIdsR = marketCircleIdR;
			}else {
				marketCircleIdsR = marketCircleIdsR + ";" + marketCircleIdR;
			}
		}
	}
	var cityIdForDispatchMap = $("#cityIdForDispatchMap").combobox('getValue');
	var cityIdForDm = $("#cityIdForDm").combobox('getValue');
	$('.mapLeftInMask').show();
	hidesuggest();
	$.post('/so/getAllDM.do?soDto.distributionMemberName='+distributionMemberName+'&cityIdR='+cityIdForDm+'&marketCircleIdsR='+marketCircleIdsR+'&numType='+numType+'&soDto.shopAreaIds='+shopAreaIds+'&soDto.marketCircleIds='+marketCircleIds+'&soDto.receiverCity='+cityIdForDispatchMap, null,function(data){
		if(data=="用户已经退出，请重新登录！"){
			$('.mapLeftInMask').hide();
			$("#uncheckSoNums").html("");
			$("#msg").html(data);
			return;
		}
		data = eval('(' + data + ')');
		var soList = data.soList;
		var dmList;
		if(data.dmMap!=null){
			dmList = data.dmMap['outstation'];
		}
		//显示大本营
		var stationList = data.stationList;
		if(stationList != null  && stationList.length>0){
			for (var i = 0; i < stationList.length; i++) {
				var lonPoint = stationList[i]['longPoint'];
				var latPoint = stationList[i]['letPoint'];
				var distributionStationCode = stationList[i]['distributionStationCode'];
				if(lonPoint==null || latPoint== null ||distributionStationCode == null){
					continue;
				}
				var stationName = stationList[i]['distributionStationName'];
				var inDmList;
				if(data.dmMap != null){
					inDmList = data.dmMap[distributionStationCode];
				}
				showHomeOnMap(stationName,lonPoint,latPoint,inDmList,distributionStationCode);
			}
			
		}
		if(soList != "" && soList != null && soList.length>0){
			showOrderOnMapWithoutList(soList);
			//将原来选中的订单继续默认选中
			for (var i = 0; i < soList.length; i++) {
				var orderId = soList[i]['orderId'];
				 if($("#"+orderId).attr("checked")=="checked"){
						var lonPoint = soList[i]['receiveLng'];
						var latPoint = soList[i]['receiveLat'];
						var parentSoCode = "";
						if(soList[i]['parentSoCode'] != null && soList[i]['parentSoCode'] != ""){
							parentSoCode = soList[i]['parentSoCode'];
						}
						//终点
						var p1 = new BMap.Point(lonPoint,latPoint);
						var infoWindow = new BMap.InfoWindow("<table><tr><td>订单号：</td><td>"+soList[i]['orderCode']+"</td></tr><tr><td>商家名称：</td><td>"+soList[i]['shop'].shopName+"</td></tr><tr><td>收货地址：</td><td>"+soList[i]['receiverAddress']+"</td></tr></table>");
						addOrderSelectMarker(p1,infoWindow,orderId,parentSoCode);
				 }
			}
		}
		if(dmList != "" && dmList != null && dmList.length>0){
			showDmOnMap(dmList);
			if(dmList.length == 1){
				var point = new BMap.Point(dmList[0]['lonPoint'], dmList[0]['latPoint']);
				map.centerAndZoom(point, 16);	// 初始化地图，设置中心点坐标和地图级别
			}
		}
		$('.mapLeftInMask').hide();
		//$("#msg").html("查询成功！");
		
		}, 'json'
	);
}

function removeOrder(orderId,distributionMemberId) {
	if(orderId == null || orderId == "" || distributionMemberId == null || distributionMemberId == ""){
		alert("获取订单ID或者配送员ID失败");
		return;
	}
	$.messager.confirm('消息', '确定要移除订单?', function(r) {
			if (r) {
				$('.mapLeftInMask').show();
				$.post('/so/removeSoForDM.do?soDto.orderId='+orderId+'&dmId='+Number(distributionMemberId), null,function(result){
						if (result.success) {
							//$.messager.alert('提示', "移除订单成功！", 'info');
							searchDispathSo();	
							//$("#msg").html("移除订单成功！");
						}else{
							//$.messager.alert('错误', result.message, 'error');
							$("#msg").html(result.message);
						}
					}, 'json'
				);
			}
	});
}

//给配送员追加订单
function addOrder(distributionMemberId) {
	var soIdList = document.getElementsByName("soIdList");
	var soIds = "";
	for(var i = 0; i < soIdList.length; i++) {
		if(soIdList[i].checked == true) {
			var soId = soIdList[i].value;
			if(soIds == "") {
				soIds = soId;
			}else {
				soIds = soIds + ";" + soId;
			}
		}
	}
	if(soIds == ""){
		alert("请先勾选要追加的订单！");
		//$("#msg").html("请先勾选要追加的订单！");
		return;
	}
	$.messager.confirm('消息', '确定要追加订单?', function(r) {
		if (r) {
			$('.mapLeftInMask').show();
			$.post('/so/soBatchSetDM.do?orderIds='+soIds+'&dmId='+Number(distributionMemberId), null,function(result){
					if (result.success) {
						//$.messager.alert('提示', "调度成功！", 'info');
						searchDispathSo();	
						//$("#msg").html('成功：调度成功！');
					}else{
						//$.messager.alert('错误', result.message, 'error');
						$("#msg").html(result.message);
					}
				}, 'json'
			);
		}
	});
}

