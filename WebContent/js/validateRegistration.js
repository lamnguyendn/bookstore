$(document).ready(
		function() {
			$('#myForm').validate({
				rules : {
					userName : {
						required : true,
						pattern : /^[a-zA-Z0-9]+$/,
					},
					passWord : {
						required : true,
					},
					passWord1 : {
						required : true,
						equalTo : "#passWord",
					},
					ten : {
						required : true,
					},
					soDienThoai : {
						required : true,
						pattern : /\d{10,11}/
					},
					diaChi : {
						required : true,
					},
					email : {
						required : true,
						pattern : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
					}
				},
				messages : {
					userName : {
						required : "<span style='color:red;'>Vui lòng nhập tên đăng nhập</span>",
						pattern : "<span style='color:red;'>Vui lòng nhập tên không chứa kí tự đặc biệt hoặc có dấu</span>"
					},
					passWord : {
						required : "<span style='color:red;'>Vui lòng nhập mật khẩu</span>",
					},
					passWord1 : {
						required : "<span style='color:red;'>Vui lòng nhập xác nhận mật khẩu</span>",
						equalTo : "<span style='color:red' >Mật khẩu phải trùng với mật khẩu ở trên!</span>",
					},
					ten : {
						required : "<span style='color:red;'>Vui lòng nhập họ tên</span>",
					},
					soDienThoai : {
						required : "<span style='color:red;'>Vui lòng nhập số điện thoại</span>",
						pattern : "<span style='color:red;'>Vui lòng nhập số điện thoại đúng định dạng</span>"
					},
					diaChi : {
						required : "<span style='color:red;'>Vui lòng nhập địa chỉ</span>",
					},
					email : {
						required : "<span style='color:red;'>Vui lòng nhập email</span>",
						pattern : "<span style='color:red;'>Vui lòng nhập email đúng định dạng</span>"
					}
				}
									/*
									 * , submitHandler : function(form) { var
									 * userName = $('#userName') .val(); var
									 * passWord = $('#passWord') .val(); $
									 * .ajax({ url : "dang-ky.do?userName=" +
									 * userName + "&passWord=" + passWord +
									 * "&ten=" + ten + "&soDienThoai=" +
									 * soDienThoai + "&diaChi=" + diaChi +
									 * "&email=" + email, method : "post",
									 * contentType : "application/json;
									 * charset=utf-8", timeout : 100000, success :
									 * function( data) { $("#re-modal") .modal(
									 * 'hide'); $( '#modalSuccess') .modal(
									 * 'show'); } }); return false; }
									 */
		});
	});