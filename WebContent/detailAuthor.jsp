
<%@page import="common.StringProcess"%>
<%@page import="java.util.ArrayList"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Trang chi tiết thông tin tác giả</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<h2>Chi tiết thông tin tác giả</h2>
		<br>
		<br>
		<html:form action="/detailAuthor" method="post">
			<div class="row form-group">
				<label class="col-lg-2">Mã tác giả</label>
				<div class="col-lg-3">
					<bean:write name="authorForm" property="authorNum" />
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Tên tác giả</label>
				<div class="col-lg-3">
					<bean:write name="authorForm" property="authorName" />
				</div>
			</div>
			<div class="row form-group">
				<label class="col-lg-2">Thông tin tác giả</label>
				<div class="col-lg-10" style="word-wrap: break-word;">
					<bean:write name="authorForm" property="authorInformation" />
				</div>
			</div>
			<div class="row form-group">
				<div class="col-lg-3 col-lg-offset-2">
					<html:link action="/showlistauthor" styleClass="btn btn-default">Quay lại</html:link>
				</div>
			</div>
		</html:form>

	</div>
	<%@include file="footer.jsp"%>
</body>
</html>