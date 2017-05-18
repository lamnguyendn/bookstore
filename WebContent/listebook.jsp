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
<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link href="css/carousel.css" rel="stylesheet">
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">
<script src="js/scrollBar.js"></script>
<style type="text/css">
hr.style18 {
	height: 30px;
	border-style: solid;
	border-color: #8c8b8b;
	border-width: 1px 0 0 0;
	border-radius: 20px;
}

hr.style18:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: #8c8b8b;
	border-width: 0 0 1px 0;
	border-radius: 20px;
}

.description {
	/*display:inline-block;
            width:180px;
            white-space: nowrap;
            overflow:hidden !important;
            text-overflow: ellipsis;*/
	/* height: 80px; */
	overflow: hidden;
	font-size: 14px;
	color: #444;
	font-weight: 700;
	padding: 5px 0 0;
	text-align: left;
	color: #444;
	font-weight: 700;
	padding: 5px 0 0;
	font-weight: 700;
	padding: 5px 0 0;
}

.title-book {
	/*overflow: hidden;*/
	/*width: 180px;*/
	/* white-space: nowrap;
	overflow: hidden !important;
	text-overflow: ellipsis; */
	display: block;
	font-size: 13px;
	color: #000;
	text-overflow: ellipsis;
	overflow: hidden;
	position: relative;
	text-align: left;
	margin: 8px auto 0;
	height: 36px;
	line-height: 18px;
}

body {
	padding-bottom: 0;
}
/*
*/
.home-header {
	background: #fafafa;
	border: 1px solid #e5e5e5;
	height: 38px;
	margin-top: 15px;
	margin-bottom: 18px;
	position: relative;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
}

.home-header h2 {
	background: #00b7f1;
	padding: 0 20px 0 15px;
	height: 100%;
	position: relative;
	margin: 0;
	float: left;
	-webkit-border-radius: 3px 0 0 3px;
	-moz-border-radius: 3px 0 0 3px;
	border-radius: 3px 0 0 3px;
}

.home-header>ul:before {
	display: table;
	content: "";
	clear: both;
}

.home-header h2 a {
	font-size: 18px;
	font-weight: 700;
	line-height: 36px;
	/* text-shadow: 1px 1px 1px rgba(0,0,0,.3); */
	display: block;
	color: #fff;
	padding: 0;
	margin: 0;
}

.home-header h2:after {
	content: " ";
	display: block;
	width: 0;
	height: 0;
	margin-left: 2px;
	vertical-align: middle;
	border-top: 18px solid transparent;
	border-left: 13px solid #00b7f1;
	border-right: 0 solid transparent;
	border-bottom: 18px solid transparent;
	position: absolute;
	top: 0;
	right: -13px;
}

a {
	color: #337ab7;
	text-decoration: none;
}

ul li {
	list-style-type: none;
}

li {
	display: list-item;
}

#sidebar>li>a {
	display: block;
	padding: 3px 20px;
	clear: both;
	font-weight: 400;
	line-height: 1.42857143;
	color: #333;
	white-space: nowrap;
}

#sidebar>li>a:focus, #sidebar>li>a:hover {
	color: #262626;
	text-decoration: none;
	background-color: #EAEAEA;
}

a {
	color: #337ab7;
	text-decoration: none;
}

a {
	color: #337ab7;
	text-decoration: none;
}

a {
	background-color: transparent;
}

#sidebar {
	top: 100%;
	left: 0;
	z-index: 1000;
	float: left;
	min-width: 160px;
	/* padding: 5px 0;
	margin: 2px 0 0; */
	padding: 0;
	margin: 0;
	font-size: 14px;
	text-align: left;
	list-style: none;
	-webkit-background-clip: padding-box;
	background-clip: padding-box;
	/* border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .15); */
	font-size: 14px;
	top: 100%;
	/* border-radius: 4px; */
	/* -webkit-box-shadow: 0 6px 12px rgba(0, 0, 0, .175);
	box-shadow: 0 6px 12px rgba(0, 0, 0, .175); */
}

.list-category {
	border: 1px solid #ccc;
	border: 1px solid rgba(0, 0, 0, .15);
	background-color: #fafafa;
}

.list-category-item>a {
	background: #fafafa;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Danh sách ebook đã mua</title>
</head>
<body>
	<%-- <logic:iterate id="book" name="bookForm" property="listEbook"
		indexId="index">
		<bean:define id="name" name="book"></bean:define>
		<html:link action="/showreadpages.do?name=${name}">
		${index+1}.
		<bean:write name="book" property="name" />
			<br>
		</html:link>
	</logic:iterate> --%>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div id="content" style="margin-top: 50px;">
			<div class="col-md-2 col-lg-2 visible-lg-block">
				<ul id="sidebar" class="list-category">
					<li id="title-category"
						style="display: block; color: #000; font-size: 12px; padding: 12px 15px; font-weight: 700; border-bottom: 1px solid #ddd;">Danh
						mục sách</li>
					<c:forEach items="${listOfCategories}" var="category">
						<li class="list-category-item"><bean:define id="categoryNum"
								name="category" property="categoryNum" /> <html:link
								style="font-size:13px;"
								action="/category?categoryNum=${categoryNum}">
								<bean:write name="category" property="categoryName" />
							</html:link></li>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-12 col-sm-12 col-xs-12 col-lg-10">
				<div class="container marketing">
					<center>
						<div class="row col-md-12 col-lg-10">

							<div class="container" style="margin-top: 50px;">
								<div class="row col-md-12 col-lg-10">
									<logic:iterate id="book" name="bookForm" property="listEbook"
										indexId="index">
										<div class="col-lg-3 col-md-3 col-sm-4 book-info">
											${index+1}.
											<%-- <bean:define id="isbn" name="book" property="isbn"></bean:define> --%>
											<bean:define id="image_1" property="image_1" name="book" />
											<html:img action="viewBookImage?isbn=${book.isbn}"
												style="height: 250px; width: 200px; margin-bottom: 20px;"></html:img>
											<center>
												<h2 class="title-book">
													<bean:write name="book" property="name" />
												</h2>
											</center>
											<p style="margin-top: 20px;">
												<html:link styleClass="btn btn-default btn-sm"
													action="/showreadpages.do?isbn=${book.isbn}&pagesNum=${book.pagesNum}">
								Đọc.. &raquo;
							</html:link>
											</p>
										</div>
									</logic:iterate>
								</div>

							</div>
						</div>
					</center>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
