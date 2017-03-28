<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Book Store</title>

<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<link href="css/carousel.css" rel="stylesheet">
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

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
	height: 80px;
	overflow: hidden;
}

.title-book {
	/*overflow: hidden;*/
	/*width: 180px;*/
	white-space: nowrap;
	overflow: hidden !important;
	text-overflow: ellipsis;
}

body {
	padding-bottom: 0;
}
</style>
</head>
<body>
	<!-- Navbar -->
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div id="content">
			<!-- End Navbar -->

			<!-- Carousel
    ================================================== -->
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				<div class="carousel-inner" role="listbox">
					<div class="item active">
						<img class="first-slide" src="image/banner2.jpg" alt="First slide">
					</div>
					<div class="item">
						<img class="second-slide" src="image/banner1.png"
							alt="Second slide">
					</div>
					<div class="item">
						<img class="third-slide" src="image/banner3.jpg" alt="Third slide">
					</div>
				</div>
				<a class="left carousel-control" href="#myCarousel" role="button"
					data-slide="prev"> <span
					class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					<span class="sr-only">Previous</span>
				</a> <a class="right carousel-control" href="#myCarousel" role="button"
					data-slide="next"> <span
					class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					<span class="sr-only">Next</span>
				</a>
			</div>
			<!-- /.carousel -->
			<!-- Marketing messaging and featurettes
    ================================================== -->
			<!-- Wrap the rest of the page in another container to center all the content. -->
			<hr class="style18">
			<div class="container marketing">
				<center>
					<h1 style="margin-bottom: 40px;">Các sách bán chạy nhất</h1>
				</center>
				<!-- Three columns of text below the carousel -->
				<center>
					<div class="container">
						<div class="row col-lg-12">
							<div class="col-lg-3">
								<img class="img-circle" src="image/sach1.jpg"
									alt="Generic placeholder image" width="140" height="140">
								<h2 class="title-book">Conan</h2>
								<p class="description">Sự thật nào sẽ được làm sáng tỏ đằng
									sau mối bất hòa giữa hai con người phục vụ công lí ở hai vị thế
									khác nhau - mật vụ FBI Akai và cảnh sát Amuro!?</p>
								<p>
									<a class="btn btn-default" href="detail.html" role="button">Xem
										thêm &raquo;</a>
								</p>
							</div>
							<!-- /.col-lg-4 -->
							<div class="col-lg-3">
								<img class="img-circle" src="image/sach2.jpg"
									alt="Generic placeholder image" width="140" height="140">
								<h2 class="title-book">Sinh ra đâu phải để buồn</h2>
								<p class="description">"Mình sinh ra đâu phải để buồn", cuốn
									sách đánh dấu sự hợp tác ăn ý giữa Hamlet Trương và Iris Cao,
									"cặp đôi vàng văn học" được độc giả trẻ yêu mến sau nhiều tác
									phẩm trở thành hiện tượng như Thương nhau để đó, Ai rồi cũng
									khác, Mỉm cười cho qua...</p>
								<p>
									<a class="btn btn-default" href="detail.html" role="button">Xem
										thêm &raquo;</a>
								</p>
							</div>
							<!-- /.col-lg-4 -->
							<div class="col-lg-3">
								<img class="img-circle" src="image/sach3.jpg"
									alt="Generic placeholder image" width="140" height="140">
								<h2 class="title-book">Atlas muôn loài</h2>
								<p class="description">Atlas muôn loài</p>
								<p>
									<a class="btn btn-default" href="detail.html" role="button">Xem
										thêm &raquo;</a>
								</p>
							</div>
							<!-- /.col-lg-4 -->
							<div class="col-lg-3">
								<img class="img-circle" src="image/sach1.jpg"
									alt="Generic placeholder image" width="140" height="140">
								<h2 class="title-book">Conan</h2>
								<p class="description">Sự thật nào sẽ được làm sáng tỏ đằng
									sau mối bất hòa giữa hai con người phục vụ công lí ở hai vị thế
									khác nhau - mật vụ FBI Akai và cảnh sát Amuro!?</p>
								<p>
									<a class="btn btn-default" href="detail.html" role="button">Xem
										thêm &raquo;</a>
								</p>
							</div>
							<!-- /.col-lg-4 -->
						</div>
						<!-- /.row -->
					</div>
				</center>
			</div>
			<hr class="style18">
			<div class="container marketing">
				<center>
					<div class="row col-md-12">
						<center>
							<h1 style="margin-bottom: 40px;">Có thể bạn quan tâm</h1>
						</center>
						<logic:iterate id="book" property="listOfSuggestedBook"
							name="publicForm">
							<div class="col-md-4">
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
			<!-- START THE FEATURETTES -->
			<bean:define id="listOfCategories" property="listOfCategories"
				name="publicForm" />
			<c:forEach items="${listOfCategories}" var="cat">
				<c:if test="${fn:length(cat.listOfBooksByCategory)>0}">
					<hr class="style18">
					<div class="container marketing">
						<center>
							<div class="row col-md-12">
								<center>
									<h1 style="margin-bottom: 40px;">
										<a
											href="/BookStore/category.do?categoryNum=${cat.categoryNum}"
											style="text-decoration: none; color: #5a5a5a;">
											${cat.categoryName} </a>
									</h1>
								</center>
								<c:forEach items="${cat.listOfBooksByCategory}" var="book">
									<div class="col-md-3">
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
								</c:forEach>
							</div>
						</center>
					</div>
				</c:if>
			</c:forEach>
			<!-- /END THE FEATURETTES -->
		</div>
		<!-- Footer -->
		<%@include file="footer.jsp"%>
		<!-- End Footer -->
		<!-- /.container -->
	</div>
</body>
</html>