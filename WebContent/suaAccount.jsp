<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sửa tài khoản</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<h3>
			Sửa Account:
			<bean:write name="accountForm" property="ten" />
		</h3>
		<br>
		<html:form action="/suaAcc" method="post">
			<div class="row form-group">
				<label class="col-lg-2">Tài khoản</label>
				<div class="col-lg-3">
					<html:text property="userName" styleClass="form-control"
						readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Mật khẩu</label>
				<div class="col-lg-3">
					<html:password property="passWord" styleClass="form-control"></html:password>
					<span style="color: red;"><html:errors property="passError" /></span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Họ tên</label>
				<div class="col-lg-3">
					<html:text property="ten" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Số điện thoại</label>
				<div class="col-lg-3">
					<html:text property="soDienThoai" styleClass="form-control"></html:text>
					<span style="color: red;"> <html:errors
							property="phoneError" /></span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Địa chỉ</label>
				<div class="col-lg-3">
					<html:text property="diaChi" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Email</label>
				<div class="col-lg-3">
					<html:text property="email" styleClass="form-control"></html:text>
					<span style="color: red;"> <html:errors
							property="emailError" />
					</span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Quyền</label>
				<div class="col-lg-3">
					<html:select property="quyen" styleClass="form-control">
						<html:option value="ROLE_ADMIN">Admin</html:option>
						<html:option value="ROLE_USER">Khách hàng</html:option>
					</html:select>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-lg-3 col-lg-offset-2">
					<html:submit styleClass="btn btn-primary" property="submit"
						value="Lưu lại"></html:submit>
					<html:link styleClass="btn btn-default" style="float: right;"
						action="/danh-sach">Quay lại</html:link>
				</div>
			</div>
		</html:form>
	</div>
</body>
</html>