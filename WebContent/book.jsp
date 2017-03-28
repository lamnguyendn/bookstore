<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Quản lý sách</title>

<!-- DatePicker -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- Validate book -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script type="text/javascript" src="js/validateBook.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style.css">

<style type="text/css">
.dpimage {
	background-position: right;
	background-repeat: no-repeat;
	position: relative;
	top: 5px;
	left: -20px;
}

.DatePicker {
	height: 22px;
	width: 100px;
	font-family: Arial, sans-serif;
	font-size: 12px;
	color: #999999;
}

input[type="file"] {
	color: transparent;
}
</style>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<bean:define id="actionBook" property="actionBook" name="bookForm" />
	<bean:define id="submitName" property="submitName" name="bookForm" />
	<bean:define id="actionName" property="actionName" name="bookForm" />
	<div class="container">
		<h2>${actionName}</h2>
		<html:form action="${actionBook}" method="post" acceptCharset="UTF-8"
			styleId="myForm" enctype="multipart/form-data" styleClass="col-md-12">
			<div class="col-md-6">
				<div class="form-group ">
					<div class="col-md-11">
						<label for="txt">Mã sách</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<c:if test="${submitName eq 'Sửa'}">
						<html:text property="isbn" styleClass="form-control"
							readonly="true" styleId="isbn" />
						<bean:define id="bookDetail" name="bookForm" property="book" />
						<span style="color: red;"> <html:errors
								property="isbnError" />
						</span>
					</c:if>
					<c:if test="${submitName eq 'Thêm'}">
						<html:text property="isbn" styleClass="form-control"
							styleId="isbn" />
						<span style="color: red;"> <html:errors
								property="isbnError" />
						</span>
					</c:if>
				</div>
				<div class="form-group ">
					<div class="col-md-11">
						<label for="txt">Tên sách</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:text property="name" styleClass="form-control" styleId="name" />
					<span style="color: red;"> <html:errors property="nameError" />
					</span>
				</div>
				<div class="form-group">
					<div class="col-md-11">
						<label for="sel1">Thể loại</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:select property="categoryNum" styleClass="form-control"
						styleId="categoryNum">
						<option value="">--Chọn thể loại--</option>
						<html:optionsCollection name="bookForm"
							property="listOfCategories" label="categoryName"
							value="categoryNum" />
					</html:select>
					<span style="color: red;"> <html:errors
							property="categoryError" />
					</span>
				</div>
				<div class="form-group">
					<div class="col-md-11">
						<label for="sel1">Tác giả</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:select property="authorNum" styleClass="form-control"
						styleId="authorNum">
						<option value="">--Chọn tác giả--</option>
						<html:optionsCollection name="bookForm" property="listOfAuthors"
							label="authorName" value="authorNum" />
					</html:select>
					<span style="color: red;"> <html:errors
							property="authorError" />
					</span>
				</div>
				<div class="form-group">
					<div class="col-md-11">
						<label for="sel1">Nhà xuất bản</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:select property="publisherNum" styleClass="form-control"
						styleId="publisherNum">
						<option value="">--Chọn nhà xuất bản--</option>
						<html:optionsCollection name="bookForm"
							property="listOfPublishers" label="publisherName"
							value="publisherNum" />
					</html:select>
					<span style="color: red;"> <html:errors
							property="publisherError" />
					</span>
				</div>
				<div class="form-group">
					<div class="col-md-11">
						<label for="sel1">Ngày xuất bản</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<div class="form-inline">
						<html:text property="publishDate" styleId="publishDate"
							styleClass="DatePicker form-control" readonly="true"
							onkeypress="return isNumberKey(event)" />
					</div>
					<span style="color: red;"> <html:errors
							property="publishDateError" />
					</span>
				</div>
				<div class="col-md-11">
					<label for="txt">Mô tả</label>
				</div>
				<div class="col-md-1">
					<span style='color: red; float: right'>*</span>
				</div>
				<div class="form-group">
					<html:textarea styleClass="form-control" property="description"
						styleId="description" />
					<span style="color: red;"> <html:errors
							property="descriptionError" />
					</span>
				</div>
			</div>
			<div class="col-md-6">
				<div class="form-group ">
					<div class="col-md-11">
						<label for="txt">Số lượng</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:text property="quantity" styleClass="form-control"
						styleId="quantity" />
					<span style="color: red;"> <html:errors
							property="quantityError" />
					</span>
				</div>
				<div class="form-group ">
					<div class="col-md-11">
						<label for="txt">Đơn giá</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:text property="price" styleClass="form-control"
						styleId="price" />
					<span style="color: red;"> <html:errors
							property="priceError" />
					</span>
				</div>
				<div class="form-group ">
					<div class="col-md-11">
						<label for="txt">Hình ảnh :</label>
					</div>
					<div class="col-md-1">
						<span style='color: red; float: right'>*</span>
					</div>
					<html:file property="image_1" accept="image/*" styleId="image_1"
						onchange="previewFile1()" />
					<span style="color: red;"> <html:errors
							property="image_1Error" />
					</span>
					<html:img action="viewBookImage?isbn=${bookDetail.isbn}"
						styleId="imgBook1" height="320" width="220" />
				</div>
			</div>
			<div class="col-md-6">
				<html:submit property="submit" value="${submitName}"
					styleClass="btn btn-info">
				</html:submit>
				<html:reset styleClass="btn btn-info">
					Hủy
				</html:reset>
				<button type="button" class="btn btn-default" onclick="goBack()">
					Quay lại</button>
				<script>
					function goBack() {
						window.history.back();
					}
				</script>
			</div>
		</html:form>
	</div>
	<%@include file="footer.jsp"%>
	<script type="text/javascript">
		$(function() {
			$('.DatePicker').datepicker({
				showOn : "button",
				buttonImage : "images/calendar7.png",
				buttonImageOnly : false,
				constrainInput : false,
				dateFormat : 'dd-mm-yy',
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
	</script>
	<script>
		function previewFile1() {
			var preview = document.querySelector('#imgBook1');
			var file = document.querySelector('#image_1').files[0];
			var reader = new FileReader();
			reader.addEventListener("load", function() {
				preview.src = reader.result;
			}, false);
			if (file) {
				reader.readAsDataURL(file);
			}
		}
	</script>
</body>
</html>