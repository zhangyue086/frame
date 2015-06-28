<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% 
String rc = request.getContextPath(); 
request.setAttribute("path",rc);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
				<ul class="breadcrumb">
					<li><i class="icon-home"></i> <a href="index.do">首页</a> <i
						class="icon-angle-right"></i></li>
					<li><a href="#">信息</a> <i class="icon-angle-right"></i></li>
					<li><a href="#">物流点排班</a></li>
				</ul>
				<!-- END PAGE TITLE & BREADCRUMB-->
			</div>
		</div>
		<!-- END PAGE HEADER-->
		<!-- BEGIN PAGE CONTENT-->
		<div class="row-fluid">
			<div class="span6">
				<span class="label label-important">注意!</span>
				<span>点击日历中的事件，进行该日期排班</span>
				<br><br>
				<div class="portlet">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-reorder"></i>日历
					</div>
				</div>
				<div class="portlet-body light-grey">
					<div id="calendar1"></div>
				</div>
					<!-- END CALENDAR PORTLET-->
				</div>
			</div>
			<div class="span6">
				<div class="portlet">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-reorder"></i>物流点班次（${station.distributionStationName }）
					</div>
					<div class="tools">
						<a data-url="initAdd_schedule.do" class="btn green"  data-toggle='modal' href='#myModal' id="addScheduleInit"><i class="icon-pencil"></i>添加班次</a>
					</div>
				</div>
				<div class="portlet-body light-grey">
					<table class="table table-striped table-bordered table-hover table-full-width" id="mydemo">
						<thead>
							<tr>
								<th>班次名称</th>
								<th>班次时间</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
				</div>
			</div>
		</div>
		<div class="row-fluid">
			<div class="span12">
				<div class="portlet box grey">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-reorder"></i>${station.distributionStationName }排班(<span id="clickOverDate" class=""></span>)
					</div>
					<div class="tools">
						<a  href='#' data-url="initAddShift_schedule.do" class="btn green" id="addShiftInit" style="display:none"><i class="icon-pencil"></i>添加排班</a>
					</div>
				</div>
				<div class="portlet-body light-grey">
					<table class="table table-striped table-bordered table-hover table-full-width" id="mySchedule">
						<thead>
							<tr>
								<th>物流点</th>
								<th>班次</th>
								<th>配送员</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
				</div>
			</div>
		</div>
		<!-- END PAGE CONTENT-->
	</div>
	<!-- END PAGE CONTAINER-->
</body>
<script>
var otable;
var otable2;
jQuery(document).ready(function() {
	var date1 = new Date();
   	var year = date1.getFullYear();var month = date1.getMonth()+1;var day = date1.getDate();
   	$('#clickOverDate').text(year+"-"+month+"-"+day);
   	otable = $('#mydemo').dataTable({
        "dom": '<"top">rt<"bottom">',
        "bPaginate":false,
        "serverSide": true,
        "bFilter": false,
        "ajax": {
            "url": "list_schedule.do?now=" + new Date().getTime(),
            "type": "POST",
            "data":{
            	 "schedule.distributionStationId": 1,
            }
        },
        "columns": [
                    {"data": "scheduleId","bVisible":false},
                    {"data": "scheduleName"}
                  ],
        "columnDefs": [
                       {
                           "targets": [2],
                           "data": "scheduleId",
                           "render": function(data, type, full) {
                        	 var html = "<a class='btn mini black' id='deleteScheduleInit' data-toggle='modal' href='#myModal' data-url='initDelete_schedule.do?schedule.scheduleId="+data+"'><i class='icon-trash'></i>删除</a>";
                             return html;
                           }
                         }
                     ]
    });
   	otable2 = $('#mySchedule').dataTable({
        "dom": '<"top">rt<"bottom">',
        "bPaginate":false,
        "serverSide": true,
        "bFilter": false,
        "ajax": {
            "url": "shiftList_schedule.do?now=" + new Date().getTime(),
            "type": "POST",
            "data":{
            	 "shift.scheduleDate":new Date().Format("yyyy-MM-dd")
            }
        },
        "columns": [
                    {"data": "distributionStationName"},
                    {"data": "scheduleName"},
                    {"data": "distributionMemberNames"}
                  ],
        "columnDefs": [
                       {
                           "targets": [3],
                           "data": "shiftScheduleId",
                           "render": function(data, type, full) {
                          	 	var html= "<a class='btn mini black' id='deleteShiftInit' data-toggle='modal' href='#myModal' data-url='initDeleteShift_schedule.do?shift.shiftScheduleId="+data+"'><i class='icon-trash'></i>删除</a>";
                               return html;
                           }
                         }
                     ]
    });
	$('#calendar1').fullCalendar({
	    eventClick: function(event, jsEvent, view) {
	    	//如果大于一个星期不让排班
	    	var date1 = new Date(event.start);
	    	if(dateCompare(new Date().Format("yyyy-MM-dd"),date1.Format("yyyy-MM-dd"))){
	    		$('#addShiftInit').show();
	    	}else{
	    		$('#addShiftInit').hide();
	    	}
	    	$('#clickOverDate').text(date1.Format("yyyy-MM-dd"));
	    	 window.scrollTo(0,5000);
	       //刷新排班表信息
	    	 otable2.fnSettings().ajax={
    			"url": "shiftList_schedule.do",
    	        "type": "POST",
    	        "data":{
    	        	"shift.scheduleDate":date1.Format("yyyy-MM-dd")
    		}};
    		otable2.fnDraw();
	    },
	 	lang:'zh-cn',
	 	editable: true,
		events:{
			url: 'queryEvent_schedule.do?shift.distributionStationId=${station.distributionStationId}'
		}
	});
	$("#addScheduleInit").click(function(){
		$("#myModal").load($(this).attr("data-url"));
	});
	$("#deleteScheduleInit").die().live('click',function(){
		$("#myModal").load($(this).attr("data-url"));
	});
	$("#addShiftInit").die().live('click',function(){
		loadHtml($(this).attr("data-url")+"?date="+$('#clickOverDate').text());
	});
	$("#deleteShiftInit").die().live('click',function(){
		$("#myModal").load($(this).attr("data-url"));
	});
});
function dateCompare(startdate,enddate){   
	var arr=startdate.split("-");    
	var starttime=new Date(arr[0],arr[1],arr[2]);    
	var starttimes=starttime.getTime();   
	  
	var arrs=enddate.split("-");    
	var lktime=new Date(arrs[0],arrs[1],arrs[2]);    
	var lktimes=lktime.getTime();   
	  
	if(starttimes>=lktimes)    
	{   
	return false;   
	}   
	else  
	return true;   
	}  
</script>
</html>