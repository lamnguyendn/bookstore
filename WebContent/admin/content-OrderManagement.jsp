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
<fmt:setLocale value="vi-VN" />
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
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Quản lý sách</h3>
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
						<div id="datatable_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<bean:define id="listOfOrders" name="orderForm"
								property="listOfOrders" />
							<c:if test="${not empty listOfOrders}">
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
												<td><bean:define name="order" property="total"
														id="total" /> <fmt:formatNumber value="${total}"
														type="currency" var="pat" maxFractionDigits="0" />
													${fn:replace(pat, ".", ",")}</td>
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
														styleClass="glyphicon glyphicon-eye-open"></html:link></td>
											</tr>
										</logic:iterate>
									</tbody>
								</table>
								<script type="text/javascript">
									function setDelivery(orderNum) {
										$
												.ajax({
													url : "confirmOrder.do?orderNum="
															+ orderNum,
													type : "POST",
													contentType : "application/json; charset=utf-8",
													success : function(data) {
														$("#status-" + orderNum)
																.html(data);
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
				</div>
			</div>
		</div>
	</div>
</div>