<%@page import="common.StringProcess"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sửa khuyến mại</title>

<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<div class="container">
		<h2>Sửa khuyến mại</h2>
		<br>
		<html:form action="/suaKM" method="post">
			<div class="row form-group">
				<label class="col-lg-2">Mã khuyến mại</label>
				<div class="col-lg-3">
					<html:text property="maKm" styleClass="form-control"
						readonly="true"></html:text>

				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Tên khuyến mại</label>
				<div class="col-lg-3">
					<html:text property="tenKm" styleClass="form-control"></html:text>

				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Phần trăm khuyến mại</label>
				<div class="col-lg-3">
					<html:text property="phanTramKm" styleClass="form-control"></html:text>
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Trạng Thái</label>
				<div class="col-lg-3">
					<html:select property="trangThai" styleClass="form-control">
						<html:option value="0">Chưa kích hoạt</html:option>
						<html:option value="1">Kích hoạt</html:option>
					</html:select>
				</div>
			</div>

			<div class="row form-group">
				<div class="col-lg-3 col-lg-offset-2">
					<html:submit styleClass="btn btn-primary" property="submit"
						value="submit">Lưu</html:submit>
					<button class="btn btn-primary" onclick="history.go(-1);">Quay
						lại</button>
				</div>
			</div>
		</html:form>
	</div>
</body>
</html>