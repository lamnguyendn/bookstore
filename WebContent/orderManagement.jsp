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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý đơn hàng</title>

<!-- Pagination -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style CSS/JS -->
<link rel="stylesheet" href="css/style1.css">

<!-- DataTable -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/currency.js"></script>
<script src="js/numeric-comma.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">

<script>
	$(document)
			.ready(
					function() {
						jQuery.extend(jQuery.fn.dataTableExt.oSort, {
							"my-currency-pre" : function(a) {
								return parseFloat(a.replace(/,/gi, ''));
							},
							"my-currency-asc" : function(a, b) {
								return ((a < b) ? -1 : ((a > b) ? 1 : 0));
							},
							"my-currency-desc" : function(a, b) {
								return ((a < b) ? 1 : ((a > b) ? -1 : 0));
							}
						});
						$('#example')
								.DataTable(
										{
											pagingType : 'full_numbers',
											"lengthMenu" : [
													[ 5, 10, 25, 50, -1 ],
													[ 5, 10, 25, 50, "Tất cả" ] ],
											"aoColumnDefs" : [ {
												"sType" : "my-currency",
												"aTargets" : [ 4 ]
											} ],
											language : {
												"search" : "Tìm kiếm:",
												"zeroRecords" : "Không tìm thấy dữ liệu tương ứng",
												"info" : "Hiển thị _START_ đến _END_ của _TOTAL_ dòng",
												"infoEmpty" : "Hiển thị 0 đến 0 của 0 dòng",
												"infoFiltered" : "(đã lọc từ _MAX_ dòng)",
												"lengthMenu" : "Hiển thị _MENU_ dòng",
												paginate : {
													first : 'Đầu tiên',
													previous : 'Trước',
													next : 'Sau',
													last : 'Cuối'
												},
												aria : {
													paginate : {
														first : 'Đầu tiên',
														previous : 'Trước',
														next : 'Sau',
														last : 'Cuối'
													}
												}
											}
										});
					});
</script>

</head>
<body>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<fmt:setLocale value="vi-VN" />
		<div id="content">
			<div class="container">
				<bean:define id="listOfOrders" name="orderForm"
					property="listOfOrders" />
				<center>
					<c:if test="${empty listOfOrders}">
						<h1>Không có đơn hàng nào!!!</h1>
						<html:img src="images/cart-empty.png" />
					</c:if>
				</center>
				<c:if test="${not empty listOfOrders}">
					<h2>Quản lý đơn hàng</h2>
					<hr size="2px">
					<div class="row" style="margin-top: 20px; margin-bottom: 20px;"></div>
					<table class="table table-hover" id="example">
						<thead>
							<tr>
								<th class="text-center">Ngày tạo</th>
								<th class="text-center">Tên khách hàng</th>
								<th class="text-center">Số điện thoại khách hàng</th>
								<th class="text-center">Địa chỉ khách hàng</th>
								<th class="text-center">Tổng tiền</th>
								<th class="text-center">Trạng thái</th>
								<th class="text-center">Chi tiết</th>
							</tr>
						</thead>
						<tbody id="dataTable">
							<logic:iterate id="order" property="listOfOrders"
								name="orderForm">
								<tr>
									<bean:define id="orderNum" name="order" property="orderNum" />
									<td class="text-right"><bean:write name="order"
											property="createdDate" /></td>
									<td><bean:write name="order" property="customerName" /></td>
									<td><bean:write name="order" property="customerPhone" /></td>
									<td><bean:write name="order" property="customerAddress" /></td>
									<td><bean:define name="order" property="total" id="total" />
										<fmt:formatNumber value="${total}" type="currency" var="pat"
											maxFractionDigits="0" /> ${fn:replace(pat, ".", ",")}</td>
									<bean:define id="status" name="order" property="status" />
									<td id="status-${orderNum}" class="text-center"><a
										href="javascript:void(0)"
										onclick="return setDelivery('${orderNum}')"> <c:if
												test="${status == 1}">
												<i class="glyphicon glyphicon-ok"></i>
											</c:if> <c:if test="${status == 0}">
												<i class="glyphicon glyphicon-remove"></i>
											</c:if>
									</a></td>
									<td class="text-center"><html:link
											action="/viewOrderDetail?orderNum=${orderNum}"
											styleClass="glyphicon glyphicon-edit"></html:link></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
					<bean:define id="totalPages" property="totalPages" name="orderForm" />
					<bean:define id="findKey" property="findKey" name="orderForm" />

					<!-- <nav aria-label="Page navigation">
					<ul class="pagination" id="pagination"></ul>
					</nav>
					<script type="text/javascript">
						$(function() {
							var findKey = '${findKey}';
							console.log("findKey : " + findKey);
							window.pagObj = $('#pagination')
									.twbsPagination({
										totalPages :<%=totalPages%>,
										visiblePages : 3,
										first : "Trang đầu",
										last : "Trang cuối",
										prev : "Trước",
										next : "Sau"
									})
									.on('page',function(event, page) {
												$.ajax({
													type : "POST",
													contentType : "application/json; charset=utf-8",
													url : "paginationOrder.do?findKey="
																	+ findKey
																	+ "&page="
																	+ page,
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
					-->
					<script type="text/javascript">
						function setDelivery(orderNum) {
							$
									.ajax({
										url : "confirmOrder.do?orderNum="
												+ orderNum,
										type : "POST",
										contentType : "application/json; charset=utf-8",
										success : function(data) {
											$("#status-" + orderNum).html(data);
											console.log(data);
										},
										timeout : 100000,
										error : function(e) {
											console.log(e);
										}
									});
						}
					</script>
				</c:if>

			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>