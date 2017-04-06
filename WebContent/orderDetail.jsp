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
<title>Quản lý đơn hàng</title>

<!-- Pagination -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

</head>
<body>
	<%@include file="navbar.jsp"%>
	<fmt:setLocale value="vi-VN" />
	<div class="container">
		<div class="row">
			<h2>Thông tin khách hàng</h2>
			<bean:define id="order" name="orderForm" property="order" />
			<ul style="list-style-type: none;">
				<li>Tên khách hàng: ${order.customerName}</li>
				<li>Email khách hàng: ${order.customerEmail}</li>
				<li>Số điện thoại khách hàng: ${order.customerPhone}</li>
				<li>Địa chỉ khách hàng: ${order.customerAddress}</li>
				<li>Tổng tiền đơn hàng :<fmt:formatNumber
						value="${order.total}" type="currency" maxFractionDigits="0" />
			</ul>
		</div>
		<div class="row">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Tên sách</th>
						<th>Số lượng</th>
						<th>Đơn giá</th>
						<th>Thành tiền</th>
					</tr>
				</thead>
				<tbody>
					<logic:iterate id="orderDetailInfo" name="orderForm"
						property="listOfOrderDetails">
						<tr>
							<td>${orderDetailInfo.bookName}</td>
							<td>${orderDetailInfo.quantity}</td>
							<td><fmt:formatNumber value="${orderDetailInfo.price}"
									type="currency" maxFractionDigits="0" /></td>
							<td><fmt:formatNumber value="${orderDetailInfo.amount}"
									type="currency" maxFractionDigits="0" /></td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
		</div>
		<button type="button" class="btn btn-default" onclick="goBack()">
			<span class="glyphicon glyphicon-step-backward"></span>Quay lại
		</button>
	</div>
	<script>
		function goBack() {
			window.history.back();
		}
	</script>
	<%@include file="footer.jsp"%>
</body>
</html>