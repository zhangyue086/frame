var RoleMenuAdd = function() {
	return {
		init : function() {
			var form1 = $('#roleMenuAddForm');
			var error1 = $('.alert-error', form1);
			var success1 = $('.alert-success', form1);
			form1.validate( {
						errorElement : 'span',
						errorClass : 'help-inline',
						focusInvalid : false,
						ignore : "",
						rules : {
							"roleMenu.roleId" : {
								required : true
							},
							"roleMenu.menuId" : {
								required : true,
								maxlength : 300
							}
						},

						invalidHandler : function(event, validator) {
							success1.hide();
							error1.show();
							App.scrollTo(error1, -200);
						},

						highlight : function(element) {
							$(element).closest('.help-inline')
									.removeClass('ok');
							$(element).closest('.control-group').removeClass(
									'success').addClass('error');
						},

						unhighlight : function(element) {
							$(element).closest('.control-group').removeClass(
									'error');
						},

						success : function(label) {
							label.addClass('valid').addClass('help-inline ok')
									.closest('.control-group').removeClass(
											'error').addClass('success');
						},

						submitHandler : function(form) {
							var url;
							var id = $('input[name="roleMenu.id"]').val();
							if (id != '' && id > 0) {
								url = '/role/edit_roleMenu.do';
							} else {
								url = "/role/add_roleMenu.do";
							}
							success1.show();
							error1.hide();
							var param = $("#roleMenuAddForm").serialize();
							$.ajax( {
										url : url,
										type : "post",
										dataType : "json",
										data : param,
										success : function(data) {
											//var result = eval('('+data+')');
										if (data.isSuccess) {
											loadHtml("/role/initList_roleMenu.do");
											otable.fnDraw();
										} else {
											var html = "<div class='alert'>";
											html += "<a class='close' data-dismiss='alert'>×</a>";
											html += "<strong>错误!</strong> 添加或编辑角色菜单失败</div>";
											$("#roleMenuAddForm").append(html);
										}
									}
									});
						}
					});
		}

	};

}();