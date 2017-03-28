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
<meta charset="UTF-8">

<title>Xóa tài khoản</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<div class="container">
		<h3>
			Xóa tài khoản:
			<bean:write name="accountForm" property="userName" />
		</h3>
		<br>
		<html:form action="/xoaAcc" method="post">
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
					<html:text property="passWord" styleClass="form-control"
						readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Họ tên</label>
				<div class="col-lg-3">
					<html:text property="ten" styleClass="form-control" readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Số điện thoại</label>
				<div class="col-lg-3">
					<html:text property="soDienThoai" styleClass="form-control"
						readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Địa chỉ</label>
				<div class="col-lg-3">
					<html:text property="diaChi" styleClass="form-control"
						readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Email</label>
				<div class="col-lg-3">
					<html:text property="email" styleClass="form-control"
						readonly="true"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Quyền</label>
				<div class="col-lg-3">
					<html:select property="quyen" styleClass="form-control"
						disabled="true">
						<html:option value="ROLE_ADMIN">Admin</html:option>
						<html:option value="ROLE_USER">Khách hàng</html:option>
					</html:select>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-lg-3 col-lg-offset-2">
					<html:submit styleClass="btn btn-primary" property="submit"
						value="submit"></html:submit>
					<button class="btn btn-primary" onclick="history.go(-1);">Quay
						lại</button>
				</div>
			</div>
		</html:form>
	</div>
</body>
</html>