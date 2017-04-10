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
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!--  jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<!--  Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<!-- Select2 -->
<!-- <link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css"
	rel="stylesheet" />
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script> -->
<link href="select2/dist/css/select2.min.css" rel="stylesheet" />
<!-- <script src="select2/dist/js/select2.min.js"></script> -->
<!-- Validate book -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script src="select2/dist/js/select2.js"></script>
<!-- Select2 -->
<script>
	$(document)
			.ready(
					function() {
						$(".select2_multiple_category").select2({
							maximumSelectionLength : 3,
							placeholder : "Chọn danh mục mà bạn quan tâm",
							allowClear : true,
							dropdownAutoWidth : true,
							width : '100%',
							"language" : {
								"noResults" : function() {
									return "Không tìm thấy kết quả";
								},
								"maximumSelected" : function() {
									return "Chỉ chọn được 3 danh mục";
								}
							},
							escapeMarkup : function(markup) {
								return markup;
							}
						});
						$(".select2_multiple_author").select2({
							maximumSelectionLength : 3,
							placeholder : "Chọn tác giả mà bạn quan tâm",
							allowClear : true,
							dropdownAutoWidth : true,
							width : '100%',
							"language" : {
								"noResults" : function() {
									return "Không tìm thấy kết quả";
								},
								"maximumSelected" : function() {
									return "Chỉ chọn được 3 tác giả";
								}
							},
							escapeMarkup : function(markup) {
								return markup;
							}
						});
						$(document)
								.ready(
										function() {
											$('#myForm')
													.validate(
															{
																rules : {
																	multiSelectedCategory : {
																		required : true,
																	},
																	multiSelectedAuthor : {
																		required : true,
																	}
																},
																messages : {
																	multiSelectedCategory : {
																		required : "<span style='color:red;'>Vui lòng chọn tác giả</span>",
																	},
																	multiSelectedAuthor : {
																		required : "<span style='color:red;'>Vui lòng chọn danh mục</span>",
																	}
																},
																submitHandler : function(
																		form) {
																	var multiSelectedAuthor = $(
																			'#multiSelectedAuthor')
																			.val();
																	var multiSelectedCategory = $(
																			'#multiSelectedCategory')
																			.val();
																	$
																			.ajax({
																				url : "getResultMultiSelected.do?multiSelectedAuthor="
																						+ multiSelectedAuthor
																						+ "&multiSelectedCategory="
																						+ multiSelectedCategory,
																				method : "post",
																				contentType : "application/json; charset=utf-8",
																				timeout : 100000,
																				success : function(
																						data) {
																					$(
																							"#myModal")
																							.modal(
																									'hide');
																					$(
																							'#modalSuccess')
																							.modal(
																									'show');
																				}
																			});
																	return false;
																}
															});
										});
					});
</script>
<style>
.select2-choice {
	background-color: #00f !important;
}

.select2-container--default .select2-selection--multiple
 .select2-selection__choice {
	background-color: #26B99A;
	border: 1px solid #fff;
}

.select2-selection__rendered {
	width: 420px !important;
}

.select2-container--default .select2-selection--multiple .select2-selection__clear
	{
	margin-right: 15px;
}

#modalSuccess .modal-dialog {
	width: 20%;
	top: 30%;
}

#myModal .modal-dialog {
	top: 10%;
}

.flat-butt {
	display: inline-block;
	font-family: "Lato", sans-serif;
	font-size: 16.5px;
	padding: 6px 10px;
	border: 0;
	background: #34495E;
	color: #FFF;
	cursor: pointer;
	outline: 0;
}

.flat-butt:hover {
	background: #005580;
}

.flat-primary-butt {
	background: #1abc9c;
}

.flat-primary-butt:hover {
	background: #2fe2bf;
}

.modal-content {
	border-radius: 0;
}

.modal-footer {
	padding: 5px;
}
</style>
<!-- /Select2 -->
</head>

<body>
	<div class="modal fade" id="modalSuccess" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<center>
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
						<h4 class="modal-title" id="myModalLabel">Cảm ơn bạn đã phản
							hồi!</h4>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<button type="button" class="flat-butt flat-primary-butt"
							data-dismiss="modal">Đồng ý</button>
					</div>
				</center>
			</div>
		</div>
	</div>
	<button type="button" class="btn btn-info btn-lg" data-toggle="modal"
		data-target="#myModal">Open Modal</button>
	<!-- Modal -->
	<div class="modal fade" id="myModal" role="dialog"
		data-backdrop="static" data-keyboard="false">
		<div class="modal-dialog">
			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-body">
					<h4>
						<label class="control-label">Bạn quan tâm đến...</label>
					</h4>
					<html:form action="/getResultMultiSelected" styleId="myForm">
						<div class="form-group row">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" style="margin-top:5px;">
								Tác giả nào?</label>
							<div class="col-md-9 col-sm-9 col-xs-12">
								<html:select property="multiSelectedAuthor"
									styleClass="select2_multiple_author form-control" multiple="true"
									styleId="multiSelectedAuthor">
									<html:optionsCollection name="bookForm"
										property="listOfAuthors" label="authorName" value="authorNum" />
								</html:select>
							</div>
						</div>
						<div class="form-group row">
							<label class="control-label col-md-3 col-sm-3 col-xs-12" style="margin-top:5px;">
								Danh mục nào?</label>
							<div class="col-md-9 col-sm-9 col-xs-12">
								<html:select property="multiSelectedCategory"
									styleClass="select2_multiple_category form-control" multiple="true"
									styleId="multiSelectedCategory">
									<html:optionsCollection name="bookForm"
										property="listOfCategories" label="categoryName"
										value="categoryNum" />
								</html:select>
							</div>
						</div>
						<div class="row" style="margin: 0px;">
							<html:submit styleId="submitLogin" onclick="submitModal();"
								styleClass="flat-butt flat-primary-butt" style="float:right;">Đồng ý</html:submit>
						</div>
					</html:form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$('#myModal').modal({
			backdrop : 'static',
			keyboard : false
		});
		$(window).on('load', function() {
			$('#myModal').modal('show');
		});
	</script>
</body>
</html>