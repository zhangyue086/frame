var DistribuitonStationAdd = function () {
    return {
        init: function () {
            var form1 = $('#stationAddForm');
            var error1 = $('.alert-error', form1);
            var success1 = $('.alert-success', form1);
            form1.validate({
                errorElement: 'span', //default input error message container
                errorClass: 'help-inline', // default input error message class
                focusInvalid: false, // do not focus the last invalid input
                ignore: "",
                rules: {
                    "station.cityId": {
                        required: true
                    },
                    "station.distributionStationName": {
                        required: true
                    },
                    "station.distributionStationMobile": {
                        required: true
                    },
                    "station.distributionStationMobile": {
                        required: true
                    },
                    "station.distributionStationAddress": {
                        required: true
                    },
                    "station.stationManagerName": {
                        required: true
                    },
                    "station.stationManagerMobile": {
                        required: true,
                        number:true,
                        rangelength:[11,11]
                    },
                    "station.lngPoint": {
                        required: true,
                        number:true
                    },
                    "station.latPoint": {
                        required: true,
                        number:true
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
                	var id = $('input[name="station.distributionStationId"]').val();
                	if(id != '' && id > 0){
                		url='/distribution/edit_station.do';
                	}else{
                		url="/distribution/add_station.do";
                	}
                    success1.show();
                    error1.hide();
                    if($('#changeStatus').prop("checked") == true){
                    	$('input[name="station.status"]').val(1);
                    }else{
                    	$('input[name="station.status"]').val(2);
                    }
                     var param = $("#stationAddForm").serialize(); 
				     $.ajax({ 
						url : url, 
						type : "post", 
						dataType : "json", 
						data: param, 
						success : function(data) { 
							if(data.isSuccess) { 
								loadHtml("/distribution/initList_station.do");
								otable.fnDraw();
							} else { 
							    var html="<div class='alert'>";
								html+="<a class='close' data-dismiss='alert'>×</a>";
								html+="<strong>错误!</strong> 编辑物流点失败</div>";
								$("#stationAddForm").append(html);
							} 
						} 
				     }); 
				  }
		 	});
		}
           
    };

}();