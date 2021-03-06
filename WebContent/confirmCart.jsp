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
<title>Thanh toán</title>

<!-- Bootstrap -->
<script src="js/jquery.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style CSS/JS -->
<link rel="stylesheet" href="css/style1.css">

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
	<script>
		function displayModalNotExists() {
			$('#notExists').modal('show');
		}
		function displayModalBalanceNotEnough() {
			$('#balanceNotEnough').modal('show');
		}
	</script>
	<!-- Modal Thông báo tài khoản đăng nhập không thành công -->
	<div id="notExists" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 40%;">
			<!-- Modal content-->
			<div class="modal-content"
				style="color: #E9EDEF; background-color: rgba(231, 76, 60, 0.88); border-color: rgba(231, 76, 60, 0.88);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-exclamation-sign"></span> Thông
						báo!
					</h3>
				</div>
				<div class="modal-body">
					<p>Đăng nhập không thành công!</p>
					<p>Vui lòng thử lại...</p>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Thông báo tài khoản không đủ tiền để thanh toán -->
	<div id="balanceNotEnough" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 40%;">
			<!-- Modal content-->
			<div class="modal-content"
				style="color: #E9EDEF; background-color: rgba(231, 76, 60, 0.88); border-color: rgba(231, 76, 60, 0.88);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-exclamation-sign"></span> Thông
						báo!
					</h3>
				</div>
				<div class="modal-body">
					<p>Số dư tài khoản không đủ để thực hiện giao dịch!</p>
					<p>Vui lòng sử dụng hình thức thanh toán khác hoặc nạp tiền vào
						tài khoản!</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Thông báo không thể kết nối đến máy chủ -->
	<div id="balanceNotEnough" class="modal fade" role="dialog">
		<div class="modal-dialog" style="width: 40%;">
			<!-- Modal content-->
			<div class="modal-content"
				style="color: #E9EDEF; background-color: rgba(231, 76, 60, 0.88); border-color: rgba(231, 76, 60, 0.88);">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h3 class="modal-title">
						<span class="glyphicon glyphicon-exclamation-sign"></span> Thông
						báo!
					</h3>
				</div>
				<div class="modal-body">
					<p>Không thể kết nối đến máy chủ thanh toán trực tuyến!</p>
					<p>Vui lòng sử dụng hình thức thanh toán khác!</p>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Modal Đăng nhập để thanh toán -->
	<div id="myModalLoginToPay" class="modal fade" role="dialog">
		<html:form action="/thanhToanTraTruoc" method="post"
			styleId="loginPayForm">
			<div class="modal-dialog">
				<div class="row">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-title" id="myModalLabel">Thanh toán trực
								tuyến</h4>
						</div>
						<div class="modal-body">
							<div class="row form-group">
								<label class="col-md-3">Tên tài khoản</label>
								<div class="col-md-8">
									<html:text property="userName" styleClass="form-control"
										styleId="userName" />
								</div>
							</div>
							<div class="row form-group">
								<label class="col-md-3">Mật khẩu</label>
								<div class="col-md-8">
									<html:password property="password" styleClass="form-control"
										styleId="password" />
								</div>
							</div>
							<div class="row form-group">
								<div class="col-md-8 col-md-offset-3 remodal-bg">
									<html:submit styleClass="btn btn-primary" styleId="submitLogin"
										property="btnSubmit" value="Thanh toán" />
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Đóng</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</div>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<fmt:setLocale value="vi-VN" />
		<div id="content">
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
						<li>Địa chỉ: <bean:write name="customerInfo"
								property="address" /></li>
					</ul>
				</div>
				<div class="row">
					<!-- Edit Cart -->
					<html:link styleClass="navi-item btn btn-default"
						action="/showCart">
							Cập nhật giỏ hàng
						</html:link>
					<html:link styleClass="navi-item btn btn-info"
						action="/payCartFirstStep?edit=1">Sửa thông tin</html:link>
					<button data-toggle="collapse" data-target="#demo"
						class="btn btn-primary">Thanh toán</button>
					<div id="demo" class="collapse">
						<html:form method="POST" action="/payCartFourthStep"
							style="margin-top: 10px;">
							<html:submit value="Trả sau" styleClass="btn btn-warning" />
						</html:form>
						<%-- <html:form method="POST" action="/thanhToanTraTruoc"
							style="margin-top: 10px;">
							<html:submit value="Trả trước" styleClass="btn btn-success" />
						</html:form> --%>
						<button type="button" class="btn btn-success" data-toggle="modal"
							style="margin-top: 10px;" data-target="#myModalLoginToPay">Trả
							trước</button>
					</div>
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
														<bean:write name="line" property="book.name" />
													</h4>
												</div>
											</div>
										</td>
										<td class="col-sm-1 col-md-1" style="text-align: right"><strong>
												<bean:write property="quantity" name="line" />
										</strong></td>
										<td class="col-sm-2 col-md-2 text-right"><strong>
												<bean:define name="line" property="book.price" id="price" />
												<fmt:formatNumber value="${price}" type="currency"
													maxFractionDigits="0" />
										</strong></td>
										<td class="col-sm-1 col-md-1 text-right"><strong>
												<%-- <bean:write name="line" property="amount" /> --%> <bean:define
													id="amount" property="amount" name="line" /> <fmt:formatNumber
													value="${amount}" type="currency" maxFractionDigits="0" />
										</strong></td>
									</tr>
								</html:form>
							</logic:iterate>
							<tr>
								<td>
									<h5>Tổng số lượng</h5>
								</td>
								<td class="col-sm-2 col-md-2 text-right"><strong><bean:write
											name="cartInfo" property="quantityTotal" /> </strong></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td>
									<h5>Tổng tiền đơn hàng</h5>
								</td>
								<td></td>
								<td></td>
								<td class="text-right">
									<h5>
										<strong> <%-- <fmt:formatNumber
										value="${cartInfo.amountTotal}" type="currency"
										maxFractionDigits="0" /> --%> <bean:define name="cartForm"
												property="amountTotal" id="amountTotal" /> <fmt:formatNumber
												value="${amountTotal}" type="currency" maxFractionDigits="0" />
										</strong>
									</h5>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
	<c:if test="${sessionScope.balanceNotEnough eq 'true'}">
		<script type="text/javascript">
			displayModalBalanceNotEnough();
		</script>
		<%
			session.removeAttribute("balanceNotEnough");
		%>
	</c:if>
	<c:if test="${sessionScope.notExists eq 'true'}">
		<script type="text/javascript">
			displayModalNotExists();
		</script>
		<%
			session.removeAttribute("notExists");
		%>
	</c:if>
	<c:if test="${sessionScope.cannotConnectToServer eq 'true'}">
		<script type="text/javascript">
			displayModalNotExists();
		</script>
		<%
			session.removeAttribute("cannotConnectToServer");
		%>
	</c:if>
</body>
</html>