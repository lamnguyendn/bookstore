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
<title>Đăng ký tài khoản</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

</head>
<body style="background: url('images/hinh-nen-sach.jpg');">
	<%@include file="navbar.jsp"%>
	<div class="container"
		style="margin-top: 50px; height: auto; width: 500px; background: rgba(255, 255, 255, 0.25); border-radius: 10px;">
		<div class="container"
			style="background: rgb(255, 216, 99); border-bottom-right-radius: 10px; width: 500px; margin-left: -15px;">
			<h3 style="border-bottom: double; text-align: center;">Đăng ký
				tài khoản</h3>
		</div>
		<br>
		<html:form action="/dang-ky" method="post" style="margin-left:50px">
			<div class="row form-group">
				<label class="col-xs-4" style="color: white;">Tài khoản</label>
				<div class="col-xs-6">
					<html:text property="userName" styleClass="form-control"></html:text>
					<span style="color: red;"> <html:errors property="userError" />
					</span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4">Mật khẩu</label>
				<div class="col-xs-6">
					<html:password property="passWord" styleClass="form-control"></html:password>
					<span style="color: red;"><html:errors property="passError" /></span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4" style="width: 150px;">Nhập lại mật
					khẩu</label>
				<div class="col-xs-6">
					<html:password property="passWord1" styleClass="form-control"></html:password>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4">Họ tên</label>
				<div class="col-xs-6">
					<html:text property="ten" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4">Số điện thoại</label>
				<div class="col-xs-6">
					<html:text property="soDienThoai" styleClass="form-control"></html:text>
					<span style="color: red;"><html:errors property="phoneError" /></span>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4" style="color: white;">Địa chỉ</label>
				<div class="col-xs-6">
					<html:text property="diaChi" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-xs-4" style="color: white;">Email</label>
				<div class="col-xs-6">
					<html:text property="email" styleClass="form-control"></html:text>
					<span style="color: red;"> <html:errors
							property="emailError" />
					</span>
				</div>
			</div>
			<div class="row form-group">
				<div class="col-xs-12">
					<html:submit styleClass="btn btn-primary" property="submit"
						value="Đăng ký"></html:submit>
					<html:reset styleClass="btn btn-primary">Nhập lại</html:reset>
					<html:link styleClass="btn btn-default" style="float: right;"
						action="/danh-sach">Quay lại</html:link>
				</div>
			</div>
		</html:form>
		<br>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>