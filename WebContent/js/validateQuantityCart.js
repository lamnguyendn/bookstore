$(document).ready(function(){
			$('#myForm').on('keydown', '.quantity', 
					function(e){-1!==$.inArray(e.keyCode,[46,8,9,27,13])||/65|67|86|88/.test(e.keyCode)&&(!0===e.ctrlKey||!0===e.metaKey)||35<=e.keyCode&&40>=e.keyCode||(e.shiftKey||48>e.keyCode||57<e.keyCode)&&(96>e.keyCode||105<e.keyCode)&&e.preventDefault()});
			$('#myForm').validate({
				rules: {
					quantity : {
						required : /^[0-9]+$/,
						min : 1,
						max : 20
					}
				},
				messages: {
					quantity : {
						required : "<span style='color:red;'>Vui lòng nhập số lượng</span>",
						min : "<span style='color:red;'>Vui lòng nhập số lượng lớn hơn 0</span>",
						max : "<span style='color:red;'>Vui lòng nhập số lượng nhỏ hơn 20</span>"
					}
				}
			});
		});
function chkNumeric(e) {
	-1 !== $.inArray(e.keyCode, [ 46, 8, 9, 27, 13 ])
			|| /65|67|86|88/.test(e.keyCode)
			&& (!0 === e.ctrlKey || !0 === e.metaKey) || 35 <= e.keyCode
			&& 40 >= e.keyCode
			|| (e.shiftKey || 48 > e.keyCode || 57 < e.keyCode)
			&& (96 > e.keyCode || 105 < e.keyCode) && e.preventDefault()
}
