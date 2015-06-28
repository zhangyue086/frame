<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>报损原因</title>
</head>
<body>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 id="myModalLabel3">损失原因(不超过300字)</h3>
</div>
<div class="modal-body">
		<!-- BEGIN FORM-->
	<div class="row-fluid">
		<div class="span12">
			<div class="controls">
					<textarea rows="3" id="loss_desc" class="large m-wrap" style="width:500px;"  value="">${(orderLoss.lossDesc)!''}</textarea>
		</div>
	</div>
	</div>
		<!-- END FORM-->
	</div>
<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<button data-dismiss="modal" id="modal_yes"class="btn blue" >确定</button>
</div>
</body>
<script type="text/javascript">
	$("#modal_yes").die().live("click",function(){
		$.post("/loss/updateById_loss.do",{"lossDesc":$.trim($("#loss_desc").val()),"lossId":#{(orderLoss.lossId)!0}},function(data){
			if(data != null  && data !=""){
				if(data.isSuccess){
					$('#loss_search').click()
				}else{
					alert("异常");
				}
			}else{
				alert("异常");
			}
		});
	});
</script>
</html>