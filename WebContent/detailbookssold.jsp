<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Chi tiết sách bán</title>

<!-- DatePicker -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">

</head>
<body>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<fmt:setLocale value="vi-VN" />
		<div id="content">
			<!-- Thẻ div chứa bảng thống kê các sách đã bán  -->
			<div class="col-md-12" style="margin-top: 20px;">
				<div>
					<h1>Bảng Thống Kê Sách Bán</h1>
				</div>
				<html:form style="margin-top: 20px;" action="/detail">
					<!-- Chọn ngày bắt đầu thống kê  -->
					<label for="Startdate">Từ Ngày:</label>
					<html:text property="startDate" styleId="from" name="booksSoldForm"
						styleClass="DatePicker" onkeypress="return isNumberKey(event)" />
					<!-- Chọn ngày kết thúc thống kê -->
					<label for="Enddate">Đến Ngày:</label>
					<html:text property="endDate" styleId="to" name="booksSoldForm"
						styleClass="DatePicker" onkeypress="return isNumberKey(event)" />
					<html:submit styleClass="btn btn-primary" styleId="submit"
						disabled="true">
				Thống Kê
			</html:submit>
				</html:form>
				<div class="container">
					<div>
						<!-- Table thong ke cac sach ban duoc -->
						<table class="table table-hover">
							<thead>
								<tr>
									<th class="text-center">Mã Sách</th>
									<th class="text-left">Tên Sách</th>
									<th class="text-left">Thể Loại</th>
									<th class="text-left">Tác Giả</th>
									<th class="text-left">Nhà Xuất Bản</th>
									<th class="text-center">Số Lượng Bán</th>
								</tr>
							</thead>
							<tbody id="tableBooksSold">
								<logic:iterate id="book" property="listBooksSold"
									name="booksSoldForm">
									<tr>
										<td class="text-center"><bean:write name="book"
												property="maSachBan" /></td>
										<td class="text-left"><bean:write name="book"
												property="tenSachBan" /></td>
										<td class="text-left"><bean:write name="book"
												property="tenTL" /></td>
										<td class="text-left"><bean:write name="book"
												property="tenTG" /></td>
										<td class="text-left"><bean:write name="book"
												property="tenNXB" /></td>
										<td class="text-right"><bean:write name="book"
												property="soLuongBan" /></td>

									</tr>
								</logic:iterate>
						</table>
						<%-- <html:link action="export.do?method=exportExcel">Excel</html:link> --%>
						<bean:define id="totalPages" property="totalPages"
							name="booksSoldForm" />
					</div>

					<div class="col-md-6" style="margin-top: 20px;">
						<html:link styleClass="btn btn-primary" action="/thongke">Quay lại
						</html:link>
					</div>

					<div class="col-md-6">
						<nav aria-label="Page navigation">
						<ul class="pagination" id="pagination"></ul>
						</nav>
					</div>
				</div>
			</div>
			<script type="text/javascript">
				//onchange date picker
				$('#to').change(function() {
					$("#submit").removeAttr('disabled');
				});

				//add properties to datetimepicker
				$(function() {
					$('.DatePicker').datepicker({
						showOn : "button",
						buttonImage : "images/calendar7.png",
						buttonImageOnly : false,
						constrainInput : false,
						dateFormat : 'yy-mm-dd',
						minDate : new Date(1900, 01, 01),
						maxDate : (0)
					});
					$("img[class='ui-datepicker-trigger']")
							.each(
									function() {
										$(this)
												.attr(
														'style',
														'background-position: right; background-repeat: no-repeat; position:relative; top: 6px; left: -20px;');
									});
				});
				//date time picker funtion
				$(function() {
					var dateFormat = "yy-mm-dd", from = $("#from").datepicker({
						defaultDate : "+1w",
						changeMonth : true,
						numberOfMonths : 1,
						dateFormat : 'yy-mm-dd'
					}).on("change", function() {
						to.datepicker("option", "minDate", getDate(this));
					}), to = $("#to").datepicker({
						defaultDate : "+1w",
						changeMonth : true,
						numberOfMonths : 1,
						dateFormat : 'yy-mm-dd'
					}).on("change", function() {
						from.datepicker("option", "maxDate", getDate(this));
					});
					function getDate(element) {
						var date;
						try {
							date = $.datepicker.parseDate(dateFormat,
									element.value);
							date.setDate(date.getDate() + 1);
						} catch (error) {
							date = null;
						}
						return date;
					}
				});
				//validate input in date time picker
				function isNumberKey(evt) {
					var charCode = (evt.which) ? evt.which : event.keyCode;
					if (charCode != null) {
						return false;
					}
					return true;
				}
				//pagination
			</script>
			<script>
				$(function() {
					var startDate = document.getElementById("from").value;
					var endDate = document.getElementById("to").value;
					console.log("startDate : " + startDate + " endDate : "
							+ endDate);
					window.pagObj = $('#pagination')
							.twbsPagination({
								totalPages :
			<%=totalPages%>
				,
								visiblePages : 3
							})
							.on(
									'page',
									function(event, page) {
										$
												.ajax({
													type : "POST",
													contentType : "application/json;charset=utf-8",
													url : "paginationBooksSoldAction.do?startDate="
															+ startDate
															+ "&endDate="
															+ endDate
															+ "&page=" + page,
													timeout : 100000,
													success : function(data) {
														display(data);
													}
												});
									});
					function display(data) {
						$("#tableBooksSold").html(data);
					}
				});
			</script>
		</div>
	</div>
	<%@include file="footer.jsp"%>
</body>
</html>