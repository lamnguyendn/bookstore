<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


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
<fmt:setLocale value="vi-VN" />
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Chi tiết đơn hàng</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<bean:define id="order" name="orderForm" property="order" />
						<div
							style="border-bottom: 1px solid #E6E9ED; margin-bottom: 25px;">
							<div class="media-body">
								<label class="title" href="#">Tên khách hàng:</label>
								<p>${order.customerName}</p>
							</div>
							<div class="media-body">
								<label class="title" href="#">Email khách hàng:</label>
								<p>${order.customerEmail}</p>
							</div>
							<div class="media-body">
								<label class="title" href="#">Số điện thoại khách hàng:</label>
								<p>${order.customerPhone}</p>
							</div>
							<div class="media-body">
								<label class="title" href="#">Địa chỉ khách hàng:</label>
								<p>${order.customerAddress}</p>
							</div>
							<div class="media-body">
								<label class="title" href="#">Tổng tiền đơn hàng:</label>
								<p>
									<fmt:formatNumber value="${order.total}" type="currency"
										maxFractionDigits="0" />
								</p>
							</div>
						</div>
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
						<div style="border-top: 1px solid #E6E9ED; padding: 25px 0px;">
							<html:link action="/orderManagement" styleClass="btn btn-default">
								<span class="glyphicon glyphicon-step-backward"></span>Quay lại
						</html:link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>