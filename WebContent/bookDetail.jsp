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
	.img {
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
	.img {
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
	.img {
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
<style>
.x_panel {
	position: relative;
	width: 100%;
	margin-bottom: 10px;
	padding: 10px 17px;
	display: inline-block;
	background: #fff;
	border: 1px solid #E6E9ED;
	-webkit-column-break-inside: avoid;
	-moz-column-break-inside: avoid;
	column-break-inside: avoid;
	opacity: 1;
	transition: all .2s ease;
}

.x_title {
	border-bottom: 2px solid #E6E9ED;
	padding: 1px 5px 6px;
	margin-bottom: 10px;
}

.x_title h2 {
	margin: 5px 0 6px;
	float: left;
	display: block;
	text-overflow: ellipsis;
	overflow: hidden;
	white-space: nowrap;
}

h2 {
	font-size: 18px;
	font-weight: 400;
}

.panel_toolbox {
	float: right;
	min-width: 70px;
}

.panel_toolbox>li {
	float: left;
	cursor: pointer;
}

<!--
Binh luan -->.list-unstyled {
	padding-left: 0;
	list-style: none;
}

ul.msg_list li {
	background: #f7f7f7;
	padding: 5px;
	display: -ms-flexbox;
	display: flex;
	margin: 6px 6px 0;
	width: 96% !important;
}

ul.msg_list li a {
	padding: 3px 5px !important;
	text-decoration: none;
}

ul.msg_list li a .time {
	font-size: 11px;
	font-style: italic;
	font-weight: bold;
	position: absolute;
	/* right: 35px; */
}

ul.msg_list li a .message {
	display: block !important;
	font-size: 15px;
	color: #333;
}

.userName {
	font-size: 16px;
	font-weight: bold;
}

ul.msg_list li .image img {
	border-radius: 2px 2px 2px 2px;
	-webkit-border-radius: 2px 2px 2px 2px;
	float: left;
	margin-right: 10px;
	width: 75px;
}

.image:after {
	content: ".";
	visibility: hidden;
}
</style>
<!-- Font Awesome -->
<link href="admin/vendors/font-awesome/css/font-awesome.min.css"
	rel="stylesheet">

</head>
<body>
	<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Xác nhận xóa</h4>
				</div>
				<div class="modal-body">
					<p>Bạn có muốn xóa không ?</p>
					<p class="debug-url"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Không</button>
					<a class="btn btn-danger btn-ok">Có</a>
				</div>
			</div>
		</div>
	</div>
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
							<html:img action="viewBookImage?isbn=${bookDetail.isbn}"
								styleClass="img" />
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
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<div class="x_panel">
							<div class="x_title">
								<h2>Bình luận</h2>
								<ul class="nav navbar-right panel_toolbox">
									<li><a class="collapse-link"><i
											class="fa fa-chevron-up"></i></a></li>
								</ul>
								<div class="clearfix"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Bạn phải đăng nhập để bình luận!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="x_content">
								<div class="row">
									<ul class="list-unstyled msg_list" id="listOfComments">
										<c:forEach items="${listOfComments}" var="comment">
											<li class="row"><span
												class="image col-lg-1 col-md-1 col-sm-2 col-xs-2"> <img
													src="image/noavatar.png" alt="img"> &nbsp;
											</span> <span class="col-lg-10 col-md-10 col-sm-10 col-xs-10">
													<span
													class="userName col-lg-12 col-md-12 col-sm-12 col-xs-12">${comment.ten}
												</span> <span class="message col-lg-12">${comment.noiDung} </span>
											</span> <span class="col-lg-2 col-md-2"> <span
													class="col-lg-12 col-sm-12 col-md-12"> <a> <span class="time">
																${comment.ngayBinhLuan}</span></a>
												</span> <c:if
														test="${comment.userName eq sessionScope.userName.userName || sessionScope.userName.role eq 'ROLE_ADMIN'}">
														<%-- <span class="col-lg-12"> <html:link
																action="/editComment?userName=${comment.userName}&isbn=${comment.isbn}&ma_BL=${comment.ma_BL}">
																<span class="glyphicon glyphicon-pencil col-lg-12"></span>
															</html:link>
														</span> --%>
														<span class="col-lg-12"> <a data-toggle="modal"
															data-target="#confirm-delete"
															data-href="return deleteComment(${comment.ma_BL})"><span
																class="glyphicon glyphicon-remove col-lg-12"></span> </a>
														</span>
													</c:if>
											</span></li>
										</c:forEach>
									</ul>
								</div>
								<div class="ln_solid"></div>
								<html:form action="/addComment" method="post"
									acceptCharset="UTF-8" styleClass="form-horizontal"
									styleId="myForm1">
									<div class="item form-group">
										<label class="control-label col-md-12 col-sm-12 col-xs-12"
											style="margin-bottom: 10px; text-align: left;">Viết
											nhận xét của bạn vào bên dưới <span class="required">*</span>
										</label>
										<div class="col-md-9 col-sm-9 col-lg-9 col-xs-12"
											id="noiDung1">
											<html:textarea property="noiDung" styleId="noiDung"
												styleClass="form-control col-md-7 col-xs-12" />
											<html:hidden property="isbn" value="${bookDetail.isbn}" />
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<html:submit styleClass="btn btn-success" value="Gửi nhận xét" />
									</div>
								</html:form>
							</div>
						</div>
					</div>
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
	<script>
		function deleteComment(ma_BL) {
			$.ajax({
				url : "deleteComment.do?ma_BL=" + ma_BL,
				type : "POST",
				contentType : "application/json; charset=utf-8",
				success : function(data) {
					$('#confirm-delete')
					.modal('hide');
					$("#listOfComments").load(
							location.href + " #listOfComments");
				},
				timeout : 100000,
				error : function(e) {
					console.log(e);
				}
			});
		}
		$('#confirm-delete').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('onclick',
							$(e.relatedTarget).data('href'));
				});
	</script>
	<!-- Validate  -->
	<script type="text/javascript" src="js/jquery-validation.js"></script>
	<script type="text/javascript" src="js/additional-methods.min.js"></script>
	<script>
		$(document)
				.ready(
						function() {
							$('#myForm1')
									.validate(
											{
												errorPlacement : function(
														error, element) {
													if (element.attr("name") == "noiDung")
														error
																.insertAfter("#noiDung1");
												},
												rules : {
													noiDung : {
														required : true,
													}
												},
												messages : {
													noiDung : {
														required : 'Vui lòng nhập nội dung bình luận!'
													}
												},
												submitHandler : function(form) {
													var userName = '${sessionScope.userName}';
													var noiDung = $('#noiDung')
															.val();

													if (userName == '') {
														$(document).ready(function(){
															$("label").remove(".error");
														});
														$(
																"<label for='noiDung' generated='true' class='error' style='display: block;'>Vui lòng đăng nhập để bình luận!</label>")
																.insertAfter(
																		'#noiDung1');
														return false;
													}
													console.log(noiDung);
													var isbn = '${bookDetail.isbn}';
													$
															.ajax({
																url : "addComment.do?noiDung="
																		+ noiDung
																		+ "&isbn="
																		+ isbn,
																method : "post",
																contentType : "application/json; charset=utf-8",
																timeout : 100000,
																success : function(
																		data) {
																	$(
																			"#listOfComments")
																			.load(
																					location.href
																							+ " #listOfComments");
																	$(
																			'#noiDung')
																			.val(
																					'');
																}
															});
													return false;
												}
											});
						});
	</script>
</body>
</html>