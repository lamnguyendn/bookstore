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
<title>Insert title here</title>

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
	<div class="container">
		<h2>Quản lý sách</h2>
		<html:form action="/bookManagement">
			<div class="form-group col-md-8">
				<html:text property="findKey" styleClass="form-control" />
			</div>
			<html:submit styleClass="btn btn-info">
				Tìm kiếm sách
			</html:submit>
		</html:form>
		<html:link action="/addBook" styleClass="btn btn-success"
			style="float:right;margin-top:20px;margin-bottom:20px;">
			<span class="glyphicon glyphicon-plus-sign"></span> Thêm sách
		</html:link>
		<table class="table table-hover">
			<thead>
				<tr>
					<th>Mã sách</th>
					<th>Tên sách</th>
					<th>Tác giả</th>
					<th>Thể loại</th>
					<th>Nhà xuất bản</th>
					<th>Số lượng</th>
					<th>Đơn giá</th>
					<th>Xóa</th>
					<th>Sửa</th>
				</tr>
			</thead>
			<tbody id="dataTable">
				<logic:iterate id="book" property="listOfBooks" name="bookForm">
					<tr>
						<bean:define id="isbn" name="book" property="isbn" />
						<td><bean:write name="book" property="isbn" /></td>
						<td><bean:write name="book" property="name" /></td>
						<td><bean:write name="book" property="authorName" /></td>
						<td><bean:write name="book" property="categoryName" /></td>
						<td><bean:write name="book" property="publisherName" /></td>
						<td><bean:write name="book" property="quantity" /></td>
						<td><bean:write name="book" property="price" /></td>
						<td><a data-href="/BookStore/deleteBook.do?isbn=${isbn}"
							data-toggle="modal" data-target="#confirm-delete"><span
								class="glyphicon glyphicon-trash"></span> </a></td>
						<td><html:link action="/updateBook?isbn=${isbn}"
								styleClass="glyphicon glyphicon-edit"></html:link></td>
					</tr>
				</logic:iterate>
			</tbody>
		</table>
		<bean:define id="totalPages" property="totalPages" name="bookForm" />
		<bean:define id="categoryNum" property="categoryNum" name="bookForm" />
		<bean:define id="findKey" property="findKey" name="bookForm" />

		<nav aria-label="Page navigation">
		<ul class="pagination" id="pagination"></ul>
		</nav>
	</div>
	<script type="text/javascript">
		var findKey = '${findKey}';
		var categoryNum = '${categoryNum}';
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages :
	<%=totalPages%>
		,
				visiblePages : 3,
				first : "Trang đầu",
				last : "Trang cuối",
				prev : "Trước",
				next : "Sau"
			}).on(
					'page',
					function(event, page) {
						$.ajax({
							type : "POST",
							contentType : "application/json; charset=utf-8",
							url : "pagination.do?findKey=" + findKey
									+ "&categoryNum=" + categoryNum + "&page="
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
	<script>
		$('#confirm-delete').on(
				'show.bs.modal',
				function(e) {
					$(this).find('.btn-ok').attr('href',
							$(e.relatedTarget).data('href'));
				});
	</script>
	<%@include file="footer.jsp"%>
</body>
</html>