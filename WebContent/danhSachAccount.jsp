<%@page import="common.StringProcess"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="en">
<meta charset="UTF-8">
<title>Danh sách tài khoản</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
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
												"aTargets" : [ 5 ]
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
		<div id="content">
			<div class="container">
				<h2>Quản lý tài khoản</h2>
				<hr size="2px">
				<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
					<html:link styleClass="btn btn-primary pull-right"
						action="/themAcc">
						<span class="glyphicon glyphicon-plus-sign"></span>
							Thêm mới</html:link>
				</div>
				<br>
				<div class="bs-example">
					<table class="table table-hover" id="example">
						<thead>
							<tr>
								<th>Tài khoản</th>
								<th>Tên</th>
								<th>Số điện thoại</th>
								<th>Địa chỉ</th>
								<th>Email</th>
								<th>Sửa</th>
								<th>Xóa</th>
							</tr>
						</thead>
						<tbody>
							<logic:iterate name="danhSachAccountForm" property="listAccount"
								id="acc">
								<tr>
									<th scope="row"><bean:write name="acc" property="userName" />
									</th>
									<td><bean:write name="acc" property="ten" /></td>
									<td><bean:write name="acc" property="soDienThoai" /></td>
									<td><bean:write name="acc" property="diaChi" /></td>
									<td><bean:write name="acc" property="email" /></td>
									<td><bean:define id="userName" name="acc"
											property="userName"></bean:define> <html:link
											action="/suaAcc?userName=${userName}">
											<span class="glyphicon glyphicon-edit">Sửa </span>
										</html:link></td>
									<td><html:link action="/xoaAcc?userName=${userName}"
											style="margin-left: 30px;">
											<span class="glyphicon glyphicon-trash"></span>
										</html:link></td>
								</tr>
							</logic:iterate>
						</tbody>
					</table>
				</div>
			</div>
			<!-- dxModal -->
			<div class="modal fade" id="dxModal" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4 class="modal-title">Đăng xuất</h4>
						</div>
						<div class="modal-body">
							<p>Bạn chắc chắn muốn đăng xuất.</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Hủy</button>
							<html:link styleClass="btn btn-default" action="/dang-xuat.do">Đồng ý</html:link>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>
