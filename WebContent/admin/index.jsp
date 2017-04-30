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
<link rel="shortcut icon" href="images/favicon.ico">
<title>BookStore - Admin Management</title>

<!-- Bootstrap -->
<link href="admin/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="admin/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="admin/build/css/custom.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="admin/vendors/jquery/dist/jquery.min.js"></script>

<style>
.logo span span {
	color: #10c469 !important;
}

.logo {
	color: #ffffff !important;
	font-size: 32px;
	/* margin-top: 8px; */
	font-family: 'Rancho', cursive;
	float: right;
	padding: 5px 0px;
}

.menu-toggle:hover i {
	color: #D9DEE4;
}
/* top-navbar */
.nav .open>a, .nav .open>a:focus, .nav .open>a:hover {
	background-color: none;
	border-color: #337ab7;
}

.nav.navbar-nav>li>a:hover span {
	color: #fff;
}
</style>
<style>
input.error, select.error, textarea.error {
	border: 1px solid #CE5454;
	box-shadow: 0 0 4px -2px #CE5454;
	position: relative;
	left: 0;
	-moz-animation: .7s 1 shake linear;
	-webkit-animation: 0.7s 1 shake linear;
}

.error-form-control {
	border: 1px solid #CE5454;
	box-shadow: 0 0 4px -2px #CE5454;
	position: relative;
	left: 0;
	-moz-animation: .7s 1 shake linear;
	-webkit-animation: 0.7s 1 shake linear;
}

label.error {
	left: 0;
	float: left;
	margin: 0 0 0 40px;
	padding: 3px 10px;
	color: #FFF;
	border-radius: 3px 4px 4px 3px;
	background-color: #CE5454;
	/* max-width: 200px; */
	white-space: pre;
	position: relative;
	left: -15px;
	opacity: 1;
	z-index: 1;
	transition: 0.15s ease-out;
	border: 1px solid transparent;
	font-weight: 400 !important;
}

label.error::after {
	content: '';
	display: block;
	height: 0;
	width: 0;
	border-color: transparent #CE5454 transparent transparent;
	border-style: solid;
	border-width: 11px 7px;
	position: absolute;
	left: -13px;
	top: 1px;
}
</style>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<div class="col-md-3 left_col">
				<div class="left_col scroll-view">
					<div class="clearfix"></div>
					<br />
					<!-- sidebar menu -->
					<%@include file="leftside-navbar.jsp"%>
					<!-- /sidebar menu -->
				</div>
			</div>

			<!-- top navigation -->
			<%@include file="top-navbar.jsp"%>
			<!-- /top navigation -->
			<c:if test="${management eq 'BookManagement'}">
				<!-- page content -->
				<%@include file="content-Book-Management.jsp"%>
			</c:if>
			<c:if test="${management eq 'Book'}">
				<!-- page content -->
				<%@include file="content-Book.jsp"%>
			</c:if>

			<c:if test="${management eq 'OrderManagement'}">
				<!-- page content -->
				<%@include file="content-OrderManagement.jsp"%>
			</c:if>
			<c:if test="${management eq 'OrderDetail'}">
				<!-- page content -->
				<%@include file="content-OrderDetail.jsp"%>
			</c:if>

			<c:if test="${management eq 'AddAuthor'}">
				<!-- page content -->
				<%@include file="content-Author-Add.jsp"%>
			</c:if>
			<c:if test="${management eq 'EditAuthor'}">
				<!-- page content -->
				<%@include file="content-Author-Edit.jsp"%>
			</c:if>
			<c:if test="${management eq 'AuthorDetail'}">
				<!-- page content -->
				<%@include file="content-Author-Detail.jsp"%>
			</c:if>
			<c:if test="${management eq 'AuthorManagement'}">
				<!-- page content -->
				<%@include file="content-Author-Management.jsp"%>
			</c:if>

			<c:if test="${management eq 'AddCategory'}">
				<!-- page content -->
				<%@include file="content-Category-Add.jsp"%>
			</c:if>
			<c:if test="${management eq 'EditCategory'}">
				<!-- page content -->
				<%@include file="content-Category-Edit.jsp"%>
			</c:if>
			<c:if test="${management eq 'CategoryManagement'}">
				<!-- page content -->
				<%@include file="content-Category-Management.jsp"%>
			</c:if>

			<c:if test="${management eq 'PublisherManagement'}">
				<!-- page content -->
				<%@include file="content-Publisher-Management.jsp"%>
			</c:if>
			<c:if test="${management eq 'AddPublisher'}">
				<!-- page content -->
				<%@include file="content-Publisher-Add.jsp"%>
			</c:if>
			<c:if test="${management eq 'EditPublisher'}">
				<!-- page content -->
				<%@include file="content-Publisher-Edit.jsp"%>
			</c:if>


			<c:if test="${management eq 'PromotionManagement'}">
				<!-- page content -->
				<%@include file="content-Promotion-Management.jsp"%>
			</c:if>
			<c:if test="${management eq 'AddPromotion'}">
				<!-- page content -->
				<%@include file="content-Promotion-Add.jsp"%>
			</c:if>
			<c:if test="${management eq 'EditPromotion'}">
				<!-- page content -->
				<%@include file="content-Promotion-Edit.jsp"%>
			</c:if>

			<c:if test="${management eq 'AccountManagement'}">
				<!-- page content -->
				<%@include file="content-Account-Management.jsp"%>
			</c:if>
			<c:if test="${management eq 'AddAccount'}">
				<!-- page content -->
				<%@include file="content-Account-Add.jsp"%>
			</c:if>
			<c:if test="${management eq 'EditAccount'}">
				<!-- page content -->
				<%@include file="content-Account-Edit.jsp"%>
			</c:if>
			
			<c:if test="${management eq 'CommentManagement'}">
				<!-- page content -->
				<%@include file="content-Comment-Management.jsp"%>
			</c:if>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
			<div class="pull-right">BookStoreVN</div>
			<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
			
		</div>

	</div>

	<!-- Bootstrap -->
	<script src="admin/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
	<!-- Custom Theme Scripts -->
	<script src="admin/build/js/custom.min.js"></script>

</body>
</html>
