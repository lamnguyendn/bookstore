	$.validator.addMethod('filesize', function (value, element, param) {
		return this.optional(element) || (element.files[0].size <= param)
	}, 'File size must be less than {0}');

	$(document).ready(function(){
		$('#myForm').validate({
			rules: {
				isbn : {
					required : true,
				},
				categoryNum : {
					required : true,
				},
				authorNum : {
					required : true,
				},
				publisherNum : {
					required : true,
				},
				name : {
					required : true,
				},
				publishDate : {
					required : true,
				},
				description : {
					required : true,
				},
				quantity : {
					required : true,
					pattern : /[0-9]+/,
					min : 1,
				},
				price : {
					required : true,
					pattern : /[0-9]+/,
					min : 1,
				},
				image_1 : {
					accept : "image/*",
					filesize : 1048576,
				},
			},
			messages: {
				isbn : {
					required : "<span style='color:red;'>Phải nhập mã sách</span>",
				},
				categoryNum : {
					required : "<span style='color:red;'>Phải chọn thể loại</span>",
				},
				authorNum : {
					required : "<span style='color:red;'>Phải chọn tác giả</span>",
				},
				publisherNum : {
					required : "<span style='color:red;'>Phải chọn nhà xuất bản</span>",
				},
				name : {
					required : "<span style='color:red;'>Phải nhập tên sách</span>",
				},
				publishDate : {
					required : "<span style='color:red;'>Phải chọn ngày xuất bản</span>",
				},
				description : {
					required : "<span style='color:red;'>Phải nhập mô tả</span>",
				},
				quantity : {
					required : "<span style='color:red;'>Phải nhập số lượng</span>",
					pattern : "<span style='color:red;'>Nhập số lượng sai định dạng</span>",
					min : "<span style='color:red;'>Số lượng phải lớn hơn 0</span>",
				},
				price : {
					required : "<span style='color:red;'>Phải nhập giá</span>",
					pattern : "<span style='color:red;'>Nhập giá sai định dạng</span>",
					min : "<span style='color:red;'>Đơn giá phải lớn hơn 0</span>",
				},
				image_1 : {
					accept : "<span style='color:red;'>Chọn ảnh sai định dạng</span>",
					filesize : "<span style='color:red;'>Dung lượng ảnh vượt quá giới hạn</span>",
				},
			}
		});
	});

