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
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Book Store</title>

<!-- Pagination -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

<style type="text/css">
hr.style18 {
	height: 30px;
	border-style: solid;
	border-color: #8c8b8b;
	border-width: 1px 0 0 0;
	border-radius: 20px;
}

hr.style18:before {
	display: block;
	content: "";
	height: 30px;
	margin-top: -31px;
	border-style: solid;
	border-color: #8c8b8b;
	border-width: 0 0 1px 0;
	border-radius: 20px;
}

.description {
	/*display:inline-block;
            width:180px;
            white-space: nowrap;
            overflow:hidden !important;
            text-overflow: ellipsis;*/
	overflow: hidden;
}

.title-book {
	/*overflow: hidden;*/
	/*width: 180px;*/
	white-space: nowrap;
	overflow: hidden !important;
	text-overflow: ellipsis;
}

.category-detail {
	margin-bottom: 50px;
}
</style>
</head>
<body>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div class="container marketing" id="content">
			<bean:define id="listOfBooksByFindKey" name="bookForm"
				property="listOfBooksByFindKey" />
			<c:if test="${fn:length(listOfBooksByFindKey)>0}">
				<center>
					<h1 style="margin-bottom: 100px;">
						<bean:write property="categoryName" name="bookForm" />
					</h1>
				</center>
				<center>
					<div class="container">
						<div class="row col-lg-12 category-detail" id="dataTable">
							<logic:iterate id="book" property="listOfBooksByFindKey"
								name="bookForm">
								<div class="col-md-3 col-lg-3 col-sm-4 col-xs-6 book-info">
									<bean:define id="image_1" property="image_1" name="book" />
									<html:img action="viewBookImage?isbn=${book.isbn}"
										style="height: 250px; width: 200px; margin-bottom: 20px;"></html:img>
									<h2 class="title-book">
										<bean:write name="book" property="name" />
									</h2>
									<p class="description">
										<bean:write name="book" property="description" />
									</p>
									<p>
										<html:link styleClass="btn btn-default"
											action="detailBook?isbn=${book.isbn}">
								Xem thêm &raquo;
							</html:link>
									</p>
								</div>
							</logic:iterate>
						</div>
						<bean:define id="totalPages" property="totalPages" name="bookForm" />
						<bean:define id="categoryNum" property="categoryNum"
							name="bookForm" />

						<nav aria-label="Page navigation">
						<ul class="pagination" id="pagination"></ul>
						</nav>
					</div>
				</center>
			</c:if>
			<c:if test="${empty listOfBooksByFindKey}">
				<center>
					<h1 style="margin-top: 100px;">Không tìm thấy dữ liệu tương
						ứng</h1>
					<html:link styleClass="btn btn-success" action="/index">
					Trở lại trang chủ
									</html:link>
				</center>
			</c:if>
			<bean:define id="totalPages" property="totalPages" name="bookForm" />
			<bean:define id="findKey" property="findKey" name="bookForm" />
		</div>
		<script type="text/javascript">
			var categoryNum = '${categoryNum}';
			$(function() {
				window.pagObj = $('#pagination')
						.twbsPagination({
							totalPages :
		<%=totalPages%>
			,
							visiblePages : 3,
							first : "Trang đầu",
							last : "Trang cuối",
							prev : "Trước",
							next : "Sau"
						})
						.on(
								'page',
								function(event, page) {
									$
											.ajax({
												type : "POST",
												contentType : "application/json; charset=utf-8",
												url : "paginationFindBook.do?findKey="
														+ categoryNum
														+ "&page=" + page,
												timeout : 100000,
												success : function(data) {
													console.log(data);
													display(data);
												}
											});
								});
				function display(data) {
					$("#dataTable").html(data);
				}
			});
		</script>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>