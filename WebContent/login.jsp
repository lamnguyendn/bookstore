<%@page import="common.FileProcess"%>
<%@page import="common.HistoryLogLine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đăng nhập</title>


<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="col-md-6 col-md-offset-3" id="content">
			<div class="panel panel-primary" style="margin-top: 50px;">
				<div class="panel-heading">Đăng nhập hệ thống</div>
				<div class="container">
					<br>
					<html:form action="/checkLogin" method="post">
						<div class="row form-group">
							<label class="col-xs-2">Tên đăng nhập</label>
							<div class="col-xs-3">
								<html:text property="userName" styleClass="form-control"
									value=""></html:text>
								<html:errors property="userNameError" />
							</div>
						</div>
						<div class="row form-group">
							<label class="col-xs-2">Mật khẩu</label>
							<div class="col-xs-3">
								<html:password property="password" styleClass="form-control"
									value=""></html:password>
								<html:errors property="passwordError" />
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-3 col-xs-offset-2">
								<p style="color: red;">
									<bean:write name="loginForm" property="message" />
								</p>
							</div>
						</div>
						<div class="row form-group">
							<div class="col-xs-3 col-xs-offset-2">
								<html:submit styleClass="btn btn-primary">Đăng nhập</html:submit>
								<button class="btn btn-primary" type="reset">Hủy</button>
								<html:link styleClass="btn btn-success" action="/dang-ky" style="float:right;">Đăng ký</html:link>
							</div>
						</div>
					</html:form>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>