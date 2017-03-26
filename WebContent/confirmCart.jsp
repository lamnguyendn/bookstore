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
<title>Xác nhận thông tin thanh toán</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- <script src="js/jquery.min.js"></script> -->
<script
	src="https://ajax.googleapis.com/…/li…/jquery/2.1.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="http://code.jquery.com/…/1.…/themes/smoothness/jquery-ui.css" />
<script src="js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link href="css/carousel.css" rel="stylesheet">
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
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="row">
			<bean:define id="cartInfo" name="cartForm" property="cart" />
			<bean:define id="customerInfo" name="cartInfo"
				property="customerInfo" />
			<h3>Thông tin khác hàng:</h3>
			<ul>
				<li>Tên: <bean:write name="customerInfo" property="name" />
				</li>
				<li>Email: <bean:write name="customerInfo" property="email" /></li>
				<li>Số điện thoại: <bean:write name="customerInfo"
						property="phone" /></li>
				<li>Địa chỉ: <bean:write name="customerInfo" property="address" /></li>
			</ul>
		</div>
		<div class="row">
			<html:form method="POST" action="/payCartFourthStep">
				<!-- Edit Cart -->
				<html:link styleClass="navi-item" action="/showCart">Cập nhật giỏ hàng</html:link>
				<html:link styleClass="navi-item" action="/payCartFirstStep?edit=1">Sửa thông tin</html:link>
				<div><html:submit value="Xác nhận" styleClass="btn btn-warning" /></div>
			</html:form>
		</div>
		<div class="row" style="margin-top: 50px;">
			<h3>Chi tiết đơn hàng</h3>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tên sách</th>
						<th>Số lượng</th>
						<th class="text-center">Đơn giá</th>
						<th class="text-center">Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<bean:define id="cartInfo" name="cartForm" property="cart" />
					<logic:iterate id="line" property="cartLines" name="cartInfo"
						indexId="id">
						<html:form action="/updateCart" method="post">
							<tr>
								<html:hidden property="isbn" name="cartForm" value="${isbn}" />
								<bean:define id="isbn" name="line" property="book.isbn" />
								<bean:define id="image_1" name="line" property="book.image_1" />
								<bean:define id="price" name="line" property="book.price" />
								<bean:define id="amount" name="line" property="amount" />
								<td class="col-sm-8 col-md-6">
									<div class="media">
										<a class="thumbnail pull-left" href="#"> <html:img
												styleClass="media-object" src="images/${image_1}" />
										</a>
										<div class="media-body">
											<h4 class="media-heading">
												<bean:write name="line" property="book.name" />
											</h4>
										</div>
									</div>
								</td>
								<td class="col-sm-1 col-md-1 text-right"><strong>
										<bean:write property="quantity" name="line" />
								</strong></td>
								<td class="col-sm-2 col-md-2 text-right"><strong>
										<fmt:formatNumber value="${price}" type="number"
											maxFractionDigits="0" />
								</strong></td>
								<td class="col-sm-1 col-md-1 text-right"><strong>
										<fmt:formatNumber value="${amount}" type="number"
											maxFractionDigits="0" />
								</strong></td>
							</tr>
						</html:form>
					</logic:iterate>
					<tr>
						<td>
							<h5>Tổng số lượng</h5>
						</td>
						<td class="col-sm-1 col-md-1 text-right"><strong><bean:write
									name="cartInfo" property="quantityTotal" /></strong></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<h5>Tổng tiền đơn hàng</h5>
						</td>
						<td class="text-right">
							<h5>
								<strong> <fmt:formatNumber
										value="${cartInfo.amountTotalAfterUsingPromotionCode}"
										type="number" maxFractionDigits="0" />
								</strong>
							</h5>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>