<%@page import="common.FileProcess"%>
<%@page import="common.HistoryLogLine"%>
<%@page import="java.util.ArrayList"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Đơn hàng</title>

<!-- Pagination -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>

</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<bean:define id="listOfOrders" name="orderForm"
			property="listOfOrders" />
		<center style="margin-top: 150px;">
			<c:if test="${empty listOfOrders}">
				<h1>Không có đơn hàng nào!!!</h1>
				<html:img src="images/cart-empty.png" />
			</c:if>
		</center>
		<c:if test="${not empty listOfOrders}">
			<h2>Quản lý đơn hàng</h2>
			<html:form action="/bookManagement">
				<div class="form-group col-md-8">
					<html:text property="findKey" styleClass="form-control" />
				</div>
				<html:submit styleClass="btn btn-info">
					Tìm kiếm đơn hàng
				</html:submit>
			</html:form>
			<table class="table table-hover">
				<thead>
					<tr>
						<th>Mã đơn hàng</th>
						<th>Ngày tạo</th>
						<th>Tên khách hàng</th>
						<th>Số điện thoại khách hàng</th>
						<th>Địa chỉ khách hàng</th>
						<th>Tổng tiền</th>
						<th>Trạng thái</th>
						<th>Chi tiết</th>
					</tr>
				</thead>
				<tbody id="dataTable">
					<logic:iterate id="order" property="listOfOrders" name="orderForm">
						<tr>
							<bean:define id="orderNum" name="order" property="orderNum" />
							<td><bean:write name="order" property="orderNum" /></td>
							<td><bean:write name="order" property="createdDate" /></td>
							<td><bean:write name="order" property="customerName" /></td>
							<td><bean:write name="order" property="customerPhone" /></td>
							<td><bean:write name="order" property="customerAddress" /></td>
							<td><bean:write name="order" property="total" /></td>
							<bean:define id="status" name="order" property="status" />
							<td id="status-${orderNum}">
								<a href="javascript:void(0)" onclick="return setDelivery('${orderNum}')">
									<c:if test="${status == 1}">
										<i class="glyphicon glyphicon-ok"></i>
									</c:if>
									<c:if test="${status == 0}">
										<i class="glyphicon glyphicon-remove"></i>
									</c:if>
								</a>
							</td>
							<td><html:link
									action="/viewOrderDetail?orderNum=${orderNum}"
									styleClass="glyphicon glyphicon-edit"></html:link></td>
						</tr>
					</logic:iterate>
				</tbody>
			</table>
			<bean:define id="totalPages" property="totalPages" name="orderForm" />
			<bean:define id="findKey" property="findKey" name="orderForm" />

			<nav aria-label="Page navigation">
			<ul class="pagination" id="pagination"></ul>
			</nav>
			<script type="text/javascript">
				var findKey = '${findKey}';
				$(function() {
					window.pagObj = $('#pagination').twbsPagination({
						totalPages :<%=totalPages%>,
						visiblePages : 3,
						first : "Trang đầu",
						last : "Trang cuối",
						prev : "Trước",
						next : "Sau"
					}).on('page',function(event, page) {
									$.ajax({
										type : "POST",
										contentType : "application/json; charset=utf-8",
										url : "paginationOrder.do?findKey=" + findKey
												+ "&page=" + page,
										timeout : 100000,
										success : function(data) {
											display(data);
										}
									});
								});
					function display(data) {
						$("#dataTable").html(data);
					}
				});
			</script>
			<script type="text/javascript">
			    function setDelivery(orderNum) {
			        $.ajax({
			            url: "confirmOrder.do?orderNum="+orderNum,
			            type: "POST",
			            contentType : "application/json; charset=utf-8",
			            success: function (data) {
			                $("#status-" + orderNum).html(data);
			                console.log(data);
			            },
			            timeout : 100000,
			            error: function (e) {
			                console.log(e);
			            }
			        });
			    }
			</script>
		</c:if>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>