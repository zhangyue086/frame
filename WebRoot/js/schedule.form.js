jQuery.validator.addMethod("isTime",  function (value, element)  {       
     var  banci = /^\d{2}[:]\d{2}$/;
     return   this .optional(element)  ||  (banci.test(value));       
 } ,  " 请正确填写时间 " );   
 
var ScheduleAdd = function () {
    return {
        init: function () {
            var form1 = $('#scheduleAddForm');
            var error1 = $('.alert-error', form1);
            var success1 = $('.alert-success', form1);
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    "schedule.scheduleStart": {
                        required: true,
                        isTime : true
                    },
                    "schedule.scheduleEnd": {
                        required: true,
                         isTime : true
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
                    success1.show();
                    error1.hide();
                     var param = $("#scheduleAddForm").serialize(); 
				     $.ajax({ 
						url : 'add_schedule.do', 
						type : "post", 
						dataType : "json", 
						data: param, 
						success : function(data) { 
							if(data.isSuccess) { 
								$('#myModal').modal('hide');
								otable.fnDraw();
							} else { 
							    var html="<div class='alert'>";
								html+="<a class='close' data-dismiss='alert'>×</a>";
								html+="<strong>错误!</strong> 添加班次失败</div>";
								$("#scheduleAddForm").append(html);
							} 
						} 
				     }); 
				  }
		 	});
		}
           
    };

}();
var ScheduleEdit = function () {
    return {
        init: function () {
            var form1 = $('#scheduleAddForm');
            var error1 = $('.alert-error', form1);
            var success1 = $('.alert-success', form1);
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    "schedule.scheduleStart": {
                        required: true,
                        isTime : true
                    },
                    "schedule.scheduleEnd": {
                        required: true,
                         isTime : true
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
                    success1.show();
                    error1.hide();
                     var param = $("#scheduleAddForm").serialize(); 
				     $.ajax({ 
						url : 'add_schedule.do', 
						type : "post", 
						dataType : "json", 
						data: param, 
						success : function(data) { 
							if(data.isSuccess) { 
								$('#myModal').modal('hide');
								otable.fnDraw();
							} else { 
							    var html="<div class='alert'>";
								html+="<a class='close' data-dismiss='alert'>×</a>";
								html+="<strong>错误!</strong> 修改班次失败</div>";
								$("#scheduleAddForm").append(html);
							} 
						} 
				     }); 
				  }
		 	});
		}
           
    };

}();