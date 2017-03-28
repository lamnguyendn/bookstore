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
<%@page import="model.dao.AccountDAO"%><html>
<head lang="en">
<meta charset="UTF-8">
<title>Danh sách tài khoản</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container">
		<div class="row">
			<html:form action="/danh-sach" method="get">
				<div class="col-lg-4">
					<script type="text/javascript">
						$("[name='userName']")
								.val(
										'<bean:write name="danhSachAccountForm" property="userName"/>');
					</script>
				</div>
				<br></br>
				<div class="col-lg-2 pull-left">
					<html:link styleClass="btn btn-primary" action="/themAcc">Thêm mới</html:link>
				</div>
			</html:form>
		</div>
		<br>
		<div class="bs-example">
			<table class="table table-striped" id="myTable">
				<thead>
					<tr>
						<th>Tài khoản</th>
						<th>Tên</th>
						<th>Số điện thoại</th>
						<th>Địa chỉ</th>
						<th>Email</th>
						<th>Quyền</th>
						<th>Xử lý</th>
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
							<td><bean:write name="acc" property="role" /></td>
							<td><bean:define id="userName" name="acc"
									property="userName"></bean:define> <html:link
									action="/suaAcc?userName=${userName}">
									<span class="glyphicon glyphicon-edit">Sửa </span>
								</html:link> <html:link action="/xoaAcc?userName=${userName}"
									style="margin-left: 30px;">
									<span class="glyphicon glyphicon-trash">Xóa </span>
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
					<button type="button" class="btn btn-default" data-dismiss="modal">Hủy</button>
					<html:link styleClass="btn btn-default" action="/dang-xuat.do">Đồng ý</html:link>
				</div>
			</div>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>
