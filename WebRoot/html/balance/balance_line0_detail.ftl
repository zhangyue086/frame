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
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 id="myModalLabel3">信息</h3>
</div>
<div class="modal-body">
		<!-- BEGIN FORM-->
		<form class="form-horizontal" action="#">
			<div class="control-group">
				<label class="control-label">结算单号</label>
				<div class="controls">
					<s:if test="${sessionScope.status =='0'}">
					  	 <input type="text" class="m-wrap large" placeholder="请输入结算单号"> 
					  	 <input type="text" class="m-wrap large" value="${sessionScope.status =='0'}"> 
		            </s:if>
		            <s:else>
					 	 <input type="text" class="m-wrap large" value="20347174adyiq01846" disabled="disabled">
					</s:else> 
				</div>
			</div>
		</form>
		<!-- END FORM-->
</div>
<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<button data-dismiss="modal" class="btn blue" onclick="deleteDistributionStation(${station.distributionStationId })">确定</button>
</div>
</body>
<script type="text/javascript">
jQuery(document).ready(function() {    
	   UIGeneral.init();
});
function deleteDistributionStation(id){
	$.post('delete_station.do',{"station.distributionStationId":id},function(data){
		if(data.isSuccess){
			alert(data.info);
			otable.fnDraw();
			$('#myModal').modal('hide');
		}else{
			alert(data.info);
			$('#myModal').modal('hide');
		}
	},'json');
}
</script>
</html>