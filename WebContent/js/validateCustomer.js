	$(document).ready(function(){
		$('#myFormCustomerInfo').validate({
			rules: {
				name : {
					required : true,
				},
				email : {
					required : true,
					pattern : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
				},
				phone : {
					required : true,
					pattern : /[0-9]{10,11}/,
				},
				address : {
					required : true,
				}
			},
			messages: {
				name : {
					required : "Vui lòng nhập họ tên!"
				},
				email : {
					required : "Vui lòng nhập email!",
					pattern : "Nhập email sai định dạng!"
				},
				phone : {
					required : "Vui lòng nhập số điện thoại!",
					pattern : "Số điện thoại sai định dạng!"
				},
				address : {
					required : "Vui lòng nhập địa chỉ!"
				}
			}
		});
	});

