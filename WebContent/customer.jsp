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
<script type="text/javascript" src="js/validateCustomer.js"></script>
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
	<%@include file="navbar.jsp"%>
	<div class="container" style="margin-top: 100px;" id="content">
		<div class="row">
			<html:form action="/payCartSecondStep" method="post" styleId="myForm">
				<div class="col-md-6 col-md-offset-3">
					<h2 style="margin-bottom: 20px;">Thông tin khách hàng</h2>
					<div class="form-group">
						<label>Họ và tên</label>
						<html:text property="name" styleClass="form-control"
							styleId="name" />
						<html:errors property="customerNameError" />
					</div>
					<div class="form-group">
						<label>Số điện thoại</label>
						<html:text property="phone" styleClass="form-control"
							styleId="phone" />
						<html:errors property="customerPhoneError" />
					</div>
					<div class="form-group">
						<label>Địa chỉ</label>
						<html:text property="address" styleClass="form-control"
							styleId="address" />
						<html:errors property="customerAddressError" />
					</div>
					<div class="form-group">
						<label>Email</label>
						<html:text property="email" styleClass="form-control"
							styleId="email" />
						<html:errors property="customerEmailError" />
					</div>
					<div class="form-group">
						<html:submit property="submit" value="Xác nhận"
							styleClass="btn btn-info">
						</html:submit>
						<html:reset styleClass="btn btn-default">Nhập lại</html:reset>
						<button type="button" class="btn btn-default" onclick="goBack()">
							Quay lại
						</button>
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
</body>
</html>