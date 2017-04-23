<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<bean:define id="actionBook" property="actionBook" name="bookForm" />
<bean:define id="submitName" property="submitName" name="bookForm" />
<bean:define id="actionName" property="actionName" name="bookForm" />
<style>
input[type="file"] {
	color: transparent;
}
</style>
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
						<h2>${actionName}</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form styleClass="form-horizontal form-label-left"
							method="post" action="${actionBook}" styleId="myForm"
							enctype="multipart/form-data">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mã
									sách <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<c:if test="${submitName eq 'Sửa'}">
										<html:text property="isbn"
											styleClass="form-control col-md-7 col-xs-12" readonly="true"
											styleId="isbn" />
										<bean:define id="bookDetail" name="bookForm" property="book" />
									</c:if>
									<c:if test="${submitName eq 'Thêm'}">
										<html:text property="isbn"
											styleClass="form-control col-md-7 col-xs-12" styleId="isbn" />
									</c:if>
								</div>
								<div id="isbn1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã sách!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã sách đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="name">Tên sách <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="name"
										styleClass="form-control col-md-7 col-xs-12" styleId="name" />
								</div>
								<div id="name1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập tên sách!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="email">Danh mục <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<!-- <input type="email" id="email2" name="confirm_email"
										data-validate-linked="email" required="required"
										class="form-control col-md-7 col-xs-12"> -->
									<html:select property="categoryNum" styleClass="form-control"
										styleId="categoryNum">
										<option value="">--Chọn danh mục--</option>
										<html:optionsCollection name="bookForm"
											property="listOfCategories" label="categoryName"
											value="categoryNum" />
									</html:select>
								</div>
								<div id="categoryNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng chọn danh mục!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="authorNum">Tác giả <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:select property="authorNum"
										styleClass="form-control  col-md-7 col-xs-12"
										styleId="authorNum">
										<option value="">--Chọn tác giả--</option>
										<html:optionsCollection name="bookForm"
											property="listOfAuthors" label="authorName" value="authorNum" />
									</html:select>
								</div>
								<div id="authorNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng chọn tác giả!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="publisherNum">Nhà xuất bản <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:select property="publisherNum"
										styleClass="form-control col-md-7 col-xs-12"
										styleId="publisherNum">
										<option value="">--Chọn nhà xuất bản--</option>
										<html:optionsCollection name="bookForm"
											property="listOfPublishers" label="publisherName"
											value="publisherNum" />
									</html:select>
								</div>
								<div id="publisherNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng chọn nhà xuất bản!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="publishDate">Ngày xuất bản <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="publishDate" styleId="publishDate"
										styleClass="DatePicker form-control col-md-7 col-xs-12 "
										readonly="true" onkeypress="return isNumberKey(event)" />
								</div>
								<div id="publishDate1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng chọn ngày xuất bản!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if
											test="${msg eq 'Vui lòng nhập ngày xuất bản đúng định dạng!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label for="password" class="control-label col-md-3">Mô
									tả <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:textarea styleClass="form-control col-md-7 col-xs-12"
										property="description" styleId="description" />
								</div>
								<div id="description1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mô tả!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label for="password2"
									class="control-label col-md-3 col-sm-3 col-xs-12"> Số
									lượng <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="quantity"
										styleClass="form-control col-md-7 col-xs-12"
										styleId="quantity" />
								</div>
								<div id="quantity1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập số lượng!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if
											test="${msg eq 'Vui lòng nhập số lượng đúng định dạng!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if
											test="${msg eq 'Vui lòng nhập kiểu số dương cho số lượng!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="telephone">Đơn giá <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="price"
										styleClass="form-control col-md-7 col-xs-12" styleId="price" />
								</div>
								<div id="price1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập đơn giá!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã sách đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12"
									for="textarea">Hình ảnh <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:file property="image_1" accept="image/*"
										styleId="image_1" onchange="previewFile1()" />
									<html:img action="viewBookImage?isbn=${bookDetail.isbn}"
										styleId="imgBook1" height="320" width="220" />
								</div>
								<div id="image_11"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã sách!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã sách đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit property="submit" value="${submitName}"
										styleClass="btn btn-success">
									</html:submit>
									<button type="button" class="btn btn-default"
										onclick="goBack()">Quay lại</button>
									<script>
										function goBack() {
											window.history.back();
										}
									</script>
								</div>
							</div>
						</html:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- DatePicker -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery-ui.css" />
<!-- Validate book -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<!-- <script type="text/javascript" src="js/validateBook.js"></script> -->
<script>
	$.validator.addMethod('filesize', function(value, element, param) {
		return this.optional(element) || (element.files[0].size <= param)
	}, 'File size must be less than {0}');

	$(document).ready(function() {
		$('#myForm').validate({
			errorPlacement : function(error, element) {
				if (element.attr("name") == "isbn") {
					error.insertAfter("#isbn1");
				} else if (element.attr("name") == "categoryNum")
					error.insertAfter("#categoryNum1");
				else if (element.attr("name") == "authorNum")
					error.insertAfter("#authorNum1");
				else if (element.attr("name") == "publisherNum")
					error.insertAfter("#publisherNum1");
				else if (element.attr("name") == "name")
					error.insertAfter("#name1");
				else if (element.attr("name") == "publishDate")
					error.insertAfter("#publishDate1");
				else if (element.attr("name") == "description")
					error.insertAfter("#description1");
				else if (element.attr("name") == "quantity")
					error.insertAfter("#quantity1");
				else if (element.attr("name") == "price")
					error.insertAfter("#price1");
				else if (element.attr("name") == "image_1")
					error.insertAfter("#image_11");
			},
			rules : {
				isbn : {
					required : true,
				},
				categoryNum : {
					required : true,
				},
				authorNum : {
					required : true,
				},
				publisherNum : {
					required : true,
				},
				name : {
					required : true,
				},
				publishDate : {
					required : true,
				},
				description : {
					required : true,
				},
				quantity : {
					required : true,
					pattern : /[0-9]+/,
					min : 1,
				},
				price : {
					required : true,
					pattern : /[0-9]+/,
					min : 1,
				},
				image_1 : {
					required : true,
					accept : "image/*",
					filesize : 1048576,
				},
			},
			messages : {
				isbn : {
					required : 'Vui lòng nhập mã sách!',
				},
				categoryNum : {
					required : 'Vui lòng chọn thể loại!',
				},
				authorNum : {
					required : 'Vui lòng chọn tác giả!',
				},
				publisherNum : {
					required : 'Vui lòng chọn nhà xuất bản!',
				},
				name : {
					required : 'Vui lòng nhập tên sách!',
				},
				publishDate : {
					required : 'Vui lòng chọn ngày xuất bản!',
				},
				description : {
					required : 'Vui lòng nhập mô tả!',
				},
				quantity : {
					required : 'Vui lòng nhập số lượng!',
					pattern : 'Nhập số lượng sai định dạng!',
					min : 'Vui lòng nhập kiểu số dương cho số lượng!',
				},
				price : {
					required : 'Vui lòng nhập đơn giá!',
					pattern : 'Vui lòng nhập giá đúng định dạng!',
					min : 'Vui lòng nhập kiểu số dương cho đơn giá!',
				},
				image_1 : {
					required : 'Vui lòng chọn ảnh!',
					accept : 'Vui lòng chọn ảnh đúng định dạng!',
					filesize : 'Vui lòng chọn ảnh dưới 1MB!',
				},
			}
		});
	});
</script>
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
