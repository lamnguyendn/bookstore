<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Chi tiết sách</title>


<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

<style type="text/css">
.content1-before {
	overflow: hidden;
	height: 500px;
}

.content1-after {
	overflow: visible;
}

.btn1-after {
	visibility: hidden;
}

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

@media only screen and (max-width : 480px) {
	img {
		width: 70%;
		height: 50%;
	}
	.content1-before {
		overflow: hidden;
		height: 100px;
	}
	.content1-after {
		overflow: visible;
	}
}

@media only screen and (min-width : 480px) {
	img {
		width: 50%;
	}
	.content1-before {
		overflow: hidden;
		height: 300px;
	}
	.content1-after {
		overflow: visible;
	}
	.book-detail {
		text-align: center;
	}
}

@media only screen and (min-width: 768px) {
	.book-detail {
		text-align: left;
	}
}

@media only screen and (min-width : 970px) {
	img {
		width: 70%;
		height: 70%;
	}
	.content1-before {
		overflow: hidden;
		height: 500px;
	}
	.content1-after {
		overflow: visible;
	}
}

.description {
	/*display:inline-block;
            width:180px;
            white-space: nowrap;
            overflow:hidden !important;
            text-overflow: ellipsis;*/
	/* height: 80px; */
	overflow: hidden;
}

.title-book {
	/*overflow: hidden;*/
	/*width: 180px;*/
	white-space: nowrap;
	overflow: hidden !important;
	text-overflow: ellipsis;
}

.link:link {
	text-decoration: none;
}

.link:visited {
	text-decoration: none;
}

.link:hover {
	text-decoration: underline;
}

.link:active {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div id="wrapper">
		<!-- Navbar -->
		<%@include file="navbar.jsp"%>
		<!-- End Navbar -->
		<fmt:setLocale value="vi-VN" />
		<bean:define id="bookDetail" name="bookForm" property="book" />
		<div id="content">
			<div class="container">
				<div class="row">
					<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
						<center>
							<html:img action="viewBookImage?isbn=${bookDetail.isbn}" />
							<div class="row">
								<html:link style="margin-top: 20px; margin-bottom: 20px;"
									styleClass="btn btn-warning"
									action="/buyBook?isbn=${bookDetail.isbn}">
									<span class="glyphicon glyphicon-shopping-cart"> </span> Thêm vào
									giỏ hàng
								</html:link>
							</div>
						</center>
					</div>
					<div class="col-lg-8 col-md-8 col-sm-8 col-xs-12 book-detail">
						<!-- <div id="content1" class="content1-before"> -->
						<h1 class="item-name">
							<i> ${bookDetail.name} </i>
						</h1>
						<h3>
							Tác giả:
							<html:link
								action="findBookByAuthor?authorNum=${bookDetail.authorNum}"
								styleClass="link">${bookDetail.authorName}
						</html:link>
						</h3>
						<h5>Giới thiệu: ${bookDetail.description}</h5>
						<h3 style="color: red;">

							Giá:
							<fmt:formatNumber value="${bookDetail.price}" type="currency"
								maxFractionDigits="0" />
						</h3>
					</div>
					<%-- <center>
					<button id="btn1" class="btn btn-primary" style="margin-top: 20px;">Xem
						thêm</button>
				</center> --%>
					<!-- </div> -->
				</div>
			</div>
			<hr class="style18">
			<div class="container">
				<div class="row">
					<div class="container marketing">
						<center>
							<div class="row col-md-12 col-lg-12 col-xs-12 col-sm-12">
								<center>
									<h1 style="margin-bottom: 40px;">
										<a href="#" style="text-decoration: none; color: #5a5a5a;">
											Các sách liên quan </a>
									</h1>
								</center>
								<logic:iterate id="book" property="listOfRelatedBooks"
									name="bookForm">
									<div class="col-md-3 col-lg-3 col-sm-4 col-xs-12 book-info">
										<bean:define id="image_1" property="image_1" name="book" />
										<html:img action="viewBookImage?isbn=${book.isbn}"
											style="height: 250px; width: 200px; margin-bottom: 20px;"></html:img>
										<h2 class="title-book">
											<bean:write name="book" property="name" />
										</h2>
										<p class="description">
											<bean:write name="book" property="description" />
										</p>
										<p style="margin-top: 20px;">
											<html:link styleClass="btn btn-default"
												action="detailBook?isbn=${book.isbn}">
								Xem thêm &raquo;
							</html:link>
										</p>
									</div>
								</logic:iterate>
							</div>
						</center>
					</div>
				</div>
			</div>
		</div>
		<!-- Footer -->
		<%@include file="footer.jsp"%>
		<!-- End Footer -->
	</div>
	<script type="text/javascript">
		$('#btn1').click(function() {
			if ($('#content1').hasClass('content1-before')) {
				$('#content1').removeClass('content1-before');
				$('#content1').addClass('content1-after');
				$('#btn1').addClass('btn1-after');
			} else {
				$('#content1').removeClass('content1-after');
				$('#content1').addClass('content1-before');
			}
		});
	</script>
</body>
</html>