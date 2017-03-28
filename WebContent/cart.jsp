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

<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

<style type="text/css">
.thumbnail {
	border: none;
}

.media-object {
	height: 80px;
	width: 65px;
}
</style>
</head>
<body>
	<fmt:setLocale value="vi-VN" />
	<%@include file="navbar.jsp"%>
	<div class="container" id="content">
		<div class="row">
			<bean:define id="cartInfo" name="cartForm" property="cart" />
			<bean:define id="cartLines" name="cartInfo" property="cartLines" />
			<div class="col-sm-12 col-md-10 col-md-offset-1">
				<center style="margin-top: 150px;">
					<c:if test="${empty cartLines}">
						<h1>Không có sản phẩm nào trong giỏ hàng!!!</h1>
						<html:img src="images/cart-empty.png" />
					</c:if>
				</center>
				<c:if test="${not empty cartLines}">
					<h1>Chi tiết giỏ hàng</h1>
					<table class="table table-hover">
						<thead>
							<tr>
								<th>Tên sách</th>
								<th>Số lượng</th>
								<th class="text-center">Đơn giá</th>
								<th class="text-center">Thành tiền</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<bean:define id="cartInfo" name="cartForm" property="cart" />
							<logic:iterate id="line" property="cartLines" name="cartInfo"
								indexId="id">
								<html:form action="/updateCart" method="post">
									<tr>
										<bean:define id="isbn" name="line" property="book.isbn" />
										<html:hidden property="isbn" name="cartForm" value="${isbn}" />
										<bean:define id="image_1" name="line" property="book.image_1" />
										<td class="col-sm-8 col-md-6">
											<div class="media">
												<a class="thumbnail pull-left" href="#"> <html:img
														styleClass="media-object"
														action="viewBookImage?isbn=${isbn}" />
												</a>
												<div class="media-body">
													<h4 class="media-heading">
														<html:link action="/detailBook?isbn=${isbn}">
															<bean:write name="line" property="book.name" />
														</html:link>
													</h4>
												</div>
											</div>
										</td>
										<td class="col-sm-1 col-md-1" style="text-align: center">
											<bean:define id="quantity" name="line" property="quantity" />
											<bean:define id="outOfStock" name="line"
												property="outOfStock" /> <c:if test="${not outOfStock}">
												<html:text styleClass="form-control" property="quantity"
													name="line" />
											</c:if> <c:if test="${outOfStock}">
												<html:text styleClass="form-control" property="quantity"
													name="line" />
												<span style="color: red;"> <html:errors
														property="cartIsOutOfStockError" />
												</span>
											</c:if>
										</td>
										<td class="col-sm-2 col-md-2 text-center"><strong>
												<bean:define name="line" property="book.price" id="price" />
												<fmt:formatNumber value="${price}" type="currency"
													maxFractionDigits="0" />
										</strong></td>
										<td class="col-sm-1 col-md-1 text-center"><strong>
												<bean:define name="line" property="amount" id="amount" /> <fmt:formatNumber
													value="${amount}" type="currency" maxFractionDigits="0" />
										</strong></td>
										<td><c:if test="${not outOfStock}">
												<html:submit styleClass="btn btn-default">
												Cập nhật
												</html:submit>
											</c:if> <c:if test="${outOfStock}">
												<html:submit styleClass="btn btn-default">
												Cập nhật
												</html:submit>
											</c:if></td>
										<td class="col-sm-1 col-md-1"><html:link
												styleClass="btn btn-danger"
												action="/removeBookFromCart?isbn=${isbn}">
												<span class="glyphicon glyphicon-remove"></span>Xóa
										</html:link></td>
									</tr>
								</html:form>
							</logic:iterate>
							<tr>
								<html:form action="/showCart" method="post">
									<td></td>
									<td></td>
									<td></td>
									<td>
										<h5>Nhập mã khuyến mãi</h5>
									</td>
									<td class="text-right"><html:text property="promotionCode"
											styleClass="form-control" /> <span style="color: red;">
											<html:errors property="promotionCodeError" />
									</span></td>
									<td><html:submit styleClass="btn btn-default">Xác nhận</html:submit></td>
								</html:form>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td>
									<h5>Tổng tiền đơn hàng</h5>
								</td>
								<td class="text-right">
									<h5>
										<strong> <bean:define name="cartForm"
												property="amountTotal" id="amountTotal" /> <fmt:formatNumber
												value="${amountTotal}" type="currency" maxFractionDigits="0" />
										</strong>
									</h5>
								</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td></td>
								<td><html:link styleClass="btn btn-default" action="/index">
										<span class="glyphicon glyphicon-shopping-cart"></span>Tiếp
										tục mua sắm
									</html:link></td>
								<td><html:link styleClass="btn btn-success"
										action="/payCartFirstStep">
									Thanh toán<span class="glyphicon glyphicon-play"> </span>
									</html:link></td>
							</tr>
						</tbody>
					</table>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>