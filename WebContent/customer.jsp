<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Thanh toán</title>

<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>

<!-- Validate book -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<!-- <script type="text/javascript" src="js/validateCustomer.js"></script> -->
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

<style type="text/css">
.thumbnail {
	border: none;
}

.media-object {
	height: 80px;
	width: 65px;
}
</style>
</head>
<body>
	<script>
		$(document)
				.ready(
						function() {
							$('#myFormCustomerInfo')
									.validate(
											{
												errorPlacement : function(
														error, element) {
													if (element.attr("name") == "name")
														error
																.insertAfter("#nameCus");
													else if (element
															.attr("name") == "email")
														error
																.insertAfter("#emailCus");
													else if (element
															.attr("name") == "phone")
														error
																.insertAfter("#phoneCus");
													else if (element
															.attr("name") == "address")
														error
																.insertAfter("#addressCus");
												},
												rules : {
													name : {
														required : true,
														maxlength : 50,
														pattern : /^[áàãảạâấầẫẩậăắằẵẳặđêếềễểệóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựa-zA-Z\s]+$/
													},
													email : {
														required : true,
														pattern : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
														maxlength : 100
													},
													phone : {
														required : true,
														pattern : /[0-9]{10,11}/,
													},
													address : {
														required : true,
														maxlength : 100
													}
												},
												messages : {
													name : {
														required : "Vui lòng nhập họ tên!",
														maxlength : 'Họ tên không được vượt quá 50 kí tự!'
													},
													email : {
														required : "Vui lòng nhập email!",
														pattern : "Nhập email sai định dạng!",
														maxlength : 'Vui lòng nhập email không quá 100 kí tự!'
													},
													phone : {
														required : "Vui lòng nhập số điện thoại!",
														pattern : "Số điện thoại sai định dạng!"
													},
													address : {
														required : "Vui lòng nhập địa chỉ!",
														maxlength : 'Địa chỉ không được vượt quá 100 kí tự!'
													}
												}
											});
						});
	</script>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div class="container" id="content">
			<div class="row">
				<html:form action="/payCartSecondStep" method="post"
					styleId="myFormCustomerInfo"
					styleClass="form-horizontal form-label-left">
					<h2 style="margin-bottom: 20px;">Thông tin khách hàng</h2>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Họ và tên <span class="required">*</span>
						</label>
						<div class="col-md-5 col-sm-6 col-xs-12">
							<html:text property="name"
								styleClass="form-control col-md-7 col-xs-12" styleId="name" />
						</div>
						<html:errors property="customerNameError" />
						<div id="nameCus"></div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Số điện thoại <span class="required">*</span>
						</label>
						<div class="col-md-5 col-sm-6 col-xs-12">
							<html:text property="phone"
								styleClass="form-control col-md-7 col-xs-12" styleId="phone" />
						</div>
						<html:errors property="customerPhoneError" />
						<div id="phoneCus"></div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Địa chỉ <span class="required">*</span>
						</label>
						<div class="col-md-5 col-sm-6 col-xs-12">
							<html:text property="address"
								styleClass="form-control col-md-7 col-xs-12" styleId="address" />
						</div>
						<html:errors property="customerAddressError" />
						<div id="addressCus"></div>
					</div>
					<div class="item form-group">
						<label class="control-label col-md-3 col-sm-3 col-xs-12">
							Email <span class="required">*</span>
						</label>
						<div class="col-md-5 col-sm-6 col-xs-12">
							<html:text property="email"
								styleClass="form-control col-md-7 col-xs-12" styleId="email" />
						</div>
						<html:errors property="customerEmailError" />
						<div id="emailCus"></div>
					</div>
					<div class="ln_solid"></div>
					<div class="form-group">
						<div class="col-md-6 col-md-offset-3">
							<html:submit property="submit" value="Xác nhận"
								styleClass="btn btn-info">
							</html:submit>
							<html:link styleClass="btn btn-default pull-right"
								action="/showCart">
								Quay lại</html:link>
						</div>
					</div>
				</html:form>
			</div>
		</div>
		<script>
			function goBack() {
				window.history.back();
			}
		</script>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>