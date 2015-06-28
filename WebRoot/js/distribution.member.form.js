var DistribuitonMemberAdd = function () {
    return {
        init: function () {
            var form1 = $('#memberAddForm');
            var error1 = $('.alert-error', form1);
            var success1 = $('.alert-success', form1);
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    "member.cityId": {
                        required: true
                    },
                    "member.distributionStationId": {
                        required: true,
                        minlength:1
                    },
                    "member.distributionMemberName": {
                        required: true,
                        minlength:2 
                    },
                    "member.mobile": {
                        required: true,
                        number:true,
                        rangelength:[11,11] 
                    },
                    "member.status": {
                        required: true,
                    }
                },

                invalidHandler: function (event, validator) { //display error alert on form submit              
                    success1.hide();
                    error1.show();
                    App.scrollTo(error1, -200);
                },

                highlight: function (element) { // hightlight error inputs
                    $(element)
                        .closest('.help-inline').removeClass('ok'); // display OK icon
                    $(element)
                        .closest('.control-group').removeClass('success').addClass('error'); // set error class to the control group
                },

                unhighlight: function (element) { // revert the change dony by hightlight
                    $(element)
                        .closest('.control-group').removeClass('error'); // set error class to the control group
                },

                success: function (label) {
                    label
                        .addClass('valid').addClass('help-inline ok') // mark the current input as valid and display OK icon
                    .closest('.control-group').removeClass('error').addClass('success'); // set success class to the control group
                },

                submitHandler: function (form) {
                	var url;
                	var id = $('input[name="member.distributionMemberId"]').val();
                	if(id != '' && id > 0){
                		url='/distribution/edit_member.do';
                	}else{
                		url="/distribution/add_member.do";
                	}
                    success1.show();
                    error1.hide();
                     var param = $("#memberAddForm").serialize(); 
				     $.ajax({ 
						url : url, 
						type : "post", 
						dataType : "json", 
						data: param, 
						success : function(data) { 
							//var result = eval('('+data+')');
							if(data.isSuccess) { 
								loadHtml("/distribution/initList_member.do");
								otable.fnDraw();
							} else { 
								var html="<div class='alert'>";
								html+="<a class='close' data-dismiss='alert'>×</a>";
								html+="<strong>错误!</strong> 编辑配送员失败</div>";
								$("#memberAddForm").append(html);
							} 
						} 
				     }); 
				  }
		 	});
		}
           
    };

}();