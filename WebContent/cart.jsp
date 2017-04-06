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
<title>Giỏ hàng</title>

<!-- Validate cart -->
<script src="js/jquery-3.2.0.min.js"></script>
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script src="js/validateQuantityCart.js"></script>
<script src="js/scrollBar.js"></script>
<script src="js/jquery.bootstrap-touchspin.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" type="text/css" href="css/style1.css">
<style type="text/css">
.thumbnail {
	border: none;
}

.media-object {
	height: 80px;
	width: 65px;
}

.media-heading {
	padding-bottom: 5px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<fmt:setLocale value="vi-VN" />
		<%@include file="navbar.jsp"%>
		<div id="content" >
			<div class="container">
				<bean:define id="cartInfo" name="cartForm" property="cart" />
				<bean:define id="cartLines" name="cartInfo" property="cartLines" />
				<center>
					<c:if test="${empty cartLines}">
						<h1>Không có sản phẩm nào trong giỏ hàng!!!</h1>
						<html:img src="images/cart-empty.png" />
						<center>
							<html:link styleClass="btn btn-default" action="/index">
								<span class="glyphicon glyphicon-chevron-left"></span> Tiếp
												tục mua sắm
										</html:link>
						</center>
					</c:if>
				</center>
				<c:if test="${not empty cartLines}">
					<div class="row">
						<div class="col-lg-8 col-md-12">
							<div class="row"
								style="border-bottom: 1px solid #e1e1e1; margin-bottom: 20px;">
								<div class="title-cart">
									<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
										<h5>Giỏ hàng</h5>
									</div>
									<div class="col-lg-2 col-md-2  col-sm-2 col-xs-2">
										<h6>Giá mua</h6>
									</div>
									<div class="col-lg-2 col-md-2  col-sm-2 col-xs-2 text-right">
										<h6>Số lượng</h6>
									</div>
								</div>
							</div>
							<bean:define id="cartInfo" name="cartForm" property="cart" />
							<logic:iterate id="line" property="cartLines" name="cartInfo"
								indexId="id">
								<div class="row"
									style="border-bottom: 1px solid #e1e1e1; margin-bottom: 20px;">
									<html:form action="/updateCart" method="post" styleId="myForm">
										<bean:define id="isbn" name="line" property="book.isbn" />
										<bean:define id="book" name="line" property="book" />
										<html:hidden property="isbn" name="cartForm" value="${isbn}" />
										<bean:define id="image_1" name="line" property="book.image_1" />
										<div class="col-lg-8 col-md-8 col-sm-8 col-xs-8">
											<div class="media">
												<a class="thumbnail pull-left" href="#"> <html:img
														styleClass="media-object"
														action="viewBookImage?isbn=${isbn}" />
												</a>
												<div class="media-body">
													<h4 class="media-heading">
														<html:link action="/detailBook?isbn=${isbn}"
															style="color:#000;">
															<bean:write name="line" property="book.name" />
														</html:link>
													</h4>
													<p class="note">
														Tác giả:
														<html:link
															action="/findBookByAuthor?authorNum=${book.authorNum}">${book.authorName}</html:link>
													</p>
													<p class="note">
														Danh mục:
														<html:link
															action="/category?categoryNum=${book.categoryNum}">${book.categoryName}</html:link>
													</p>
													<html:link action="/removeBookFromCart?isbn=${isbn}">
														Xóa
												</html:link>
												</div>
											</div>
										</div>
										<div class="col-lg-2 col-md-2  col-sm-2 col-xs-2">
											<strong> <bean:define name="line"
													property="book.price" id="price" /> <fmt:formatNumber
													value="${price}" type="currency" maxFractionDigits="0" />
											</strong>
										</div>
										<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
											<bean:define id="quantity" name="line" property="quantity" />
											<bean:define id="outOfStock" name="line"
												property="outOfStock" />
											<c:if test="${not outOfStock}">
												<html:text styleClass="form-control" property="quantity"
													styleId="quantity" name="line"
													onkeydown="return chkNumeric(event)" />
											</c:if>
											<c:if test="${outOfStock}">
												<html:text styleClass="form-control" property="quantity"
													styleId="quantity" name="line"
													onkeydown="return chkNumeric(event)" />
												<span style="color: red;"> <html:errors
														property="cartIsOutOfStockError" />
												</span>
											</c:if>
											<div style="display: none;">
												<c:if test="${not outOfStock}">
													<html:submit styleClass="btn btn-default">
														Cập nhật
														</html:submit>
												</c:if>
												<c:if test="${outOfStock}">
													<html:submit styleClass="btn btn-default">
														Cập nhật
														</html:submit>
												</c:if>
											</div>
										</div>
									</html:form>
								</div>
							</logic:iterate>
						</div>
						<div class="col-lg-4 col-md-12"
							style="float: right; padding-left: 15px;">
							<div class="visible-lg-block" id="sidebar">
								<div class="row">
									<div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
										<div
											style="border: 1px solid black; padding: 15px; margin-bottom: 15px; border-radius: 5px; border-color: #ddd;">
											<h5>
												Tạm tính: <strong style="float: right;"> <bean:define
														name="cartForm" property="amountTotal" id="amountTotal" />
													<fmt:formatNumber value="${amountTotal}" type="currency"
														maxFractionDigits="0" />
												</strong>
											</h5>
											<h5 style="border-top: 2px solid #e54d42; padding-top: 12px;">
												Thành tiền:</h5>
										</div>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
										<html:link styleClass="btn btn-large btn-checkout"
											action="/payCartFirstStep" style="margin-bottom:15px;">
											TIẾN HÀNH ĐẶT HÀNG
									</html:link>
									</div>
									<div class="col-lg-12 col-md-12 col-sm-6 col-xs-6">
										<div class="promotion-group">
											<div class="promotion-panel-heading"
												style="padding: 15px; border: 1px solid transparent; background: linear-gradient(#fff, #f7f7f7); border-color: #ddd;">
												<a data-toggle="collapse" href="#form-promotion"
													class="form-promotion-name"
													style="color: inherit; text-decoration: none;">Mã giảm
													giá<span class="glyphicon glyphicon-chevron-down"
													style="float: right;"></span>
												</a>
											</div>
											<div id="form-promotion" class="collapse in">
												<div class="panel-collapse"
													style="padding: 15px; border: 1px solid transparent; border-color: #ddd;">
													<html:form action="/showCart" method="post">
														<div class="input-group collapse">
															<html:text property="promotionCode"
																styleClass="form-control" />
															<span class="input-group-btn"> <html:submit
																	styleClass="btn btn-default">Xác nhận</html:submit>
															</span>
														</div>
														<div class="promotion-errors">
															<span style="color: red;"> <html:errors
																	property="promotionCodeError" /></span>
														</div>
													</html:form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row col-md-12 col-lg-12 col-sm-12 col-xs-12">
						<html:link styleClass="btn btn-default" action="/index"
							style="background: linear-gradient(#fff,#f7f7f7); margin-bottom:20px;">
							<span class="glyphicon glyphicon-chevron-left"></span> Tiếp
												tục mua sắm
											</html:link>
					</div>
					<div class="row">
						<div class="col-sm-6 col-xs-6">
							<div class="visible-xs-block visible-sm-block visible-md-block">
								<div class="promotion-group">
									<div class="promotion-panel-heading"
										style="padding: 15px; border: 1px solid transparent; background: linear-gradient(#fff, #f7f7f7); border-color: #ddd;">
										<a data-toggle="collapse" href="#form-promotion-2"
											class="form-promotion-name"
											style="color: inherit; text-decoration: none;">Mã giảm
											giá<span class="glyphicon glyphicon-chevron-down"
											style="float: right;"></span>
										</a>
									</div>
									<div id="form-promotion-2" class="collapse in">
										<div class="panel-collapse"
											style="padding: 15px; border: 1px solid transparent; border-color: #ddd;">
											<html:form action="/showCart" method="post">
												<div class="input-group collapse">
													<html:text property="promotionCode"
														styleClass="form-control" />
													<span class="input-group-btn"> <html:submit
															styleClass="btn btn-default">Xác nhận</html:submit>
													</span>
												</div>
												<div class="promotion-errors">
													<span style="color: red;"> <html:errors
															property="promotionCodeError" /></span>
												</div>
											</html:form>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="col-sm-6 col-xs-6" style="float: right;">
							<div class="visible-xs-block visible-sm-block visible-md-block">
								<div
									style="border: 1px solid black; padding: 15px; margin-bottom: 15px; border-radius: 5px; border-color: #ddd;">
									<h5>
										Tạm tính: <strong style="float: right;"> <bean:define
												name="cartForm" property="amountTotal" id="amountTotal" />
											<fmt:formatNumber value="${amountTotal}" type="currency"
												maxFractionDigits="0" />
										</strong>
									</h5>
									<h5 style="border-top: 2px solid #e54d42; padding-top: 12px;">
										Thành tiền:</h5>
								</div>
								<html:link styleClass="btn btn-large btn-checkout"
									action="/payCartFirstStep" style="margin-bottom:15px;">
											TIẾN HÀNH ĐẶT HÀNG
									</html:link>
							</div>
						</div>
					</div>
				</c:if>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>