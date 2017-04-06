	$(document).ready(function(){
		$('#myForm').validate({
			rules: {
				email : {
					required : true,
					pattern : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
				},
				phone : {
					required : true,
					pattern : /[0-9]{10,11}/,
				}
			},
			messages: {
				email : {
					required : "<span style='color:red;'>Phải nhập email</span>",
					pattern : "<span style='color:red;'>Nhập email sai định dạng</span>",
				},
				phone : {
					required : "<span style='color:red;'>Phải nhập số điện thoại</span>",
					pattern : "<span style='color:red;'>Số điện thoại sai định dạng</span>",
				},
			}
		});
	});

