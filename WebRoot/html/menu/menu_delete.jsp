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

	<h3 id="myModalLabel3">提示信息</h3>

</div>

<div class="modal-body">
	是否确认删除   菜单<span class="label label-important">${menu.menuName}</span>
	<p></p>
</div>

<div class="modal-footer">
	<button class="btn" data-dismiss="modal" aria-hidden="true">取消</button>
	<button data-dismiss="modal" class="btn blue" onclick="deleteMenu(${menu.menuId})">确定</button>
</div>
</body>
<script type="text/javascript">
jQuery(document).ready(function() {    
	   UIGeneral.init();
});
function deleteMenu(id){
	$.post('delete_menu.do',{"menu.menuId":id},function(data){
		if(data.isSuccess){
			alert('删除菜单成功');
			$('#myModal').modal('hide');
		}else{
			alert('删除菜单失败');
			$('#myModal').modal('hide');
		}
	},'json');
}
</script>
</html>