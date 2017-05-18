<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Quản lý nhà xuất bản</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Sửa nhà xuất bản</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form action="/editPublisher" method="post"
							styleClass="form-horizontal form-label-left" styleId="myForm">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mã
									nhà xuất bản <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="publisherNum" styleId="publisherNum"
										styleClass="form-control col-md-7 col-xs-12" readonly="true" />
								</div>
								<div id="publisherNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã nhà xuất bản!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã nhà xuất bản đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tên
									nhà xuất bản <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="publisherName" styleId="publisherName"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="publisherName1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Địa
									chỉ <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="publisherAddress"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="publisherAddress1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Số
									điện thoại <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="publisherPhoneNumber"
										styleId="publisherPhoneNumber"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="publisherPhoneNumber1"></div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit styleClass="btn btn-success" property="submit"
										value="Sửa"></html:submit>
									<html:link action="/showlistpublisher"
										styleClass="btn btn-default">Quay lại</html:link>
								</div>
							</div>
						</html:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Validate  -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script>
	$(document).ready(function() {
		$('#myForm').validate({
			errorPlacement : function(error, element) {
				if (element.attr("name") == "publisherNum")
					error.insertAfter("#publisherNum1");
				else if (element.attr("name") == "publisherName")
					error.insertAfter("#publisherName1");
				else if (element.attr("name") == "publisherAddress")
					error.insertAfter("#publisherAddress1");
				else if (element.attr("name") == "publisherPhoneNumber")
					error.insertAfter("#publisherPhoneNumber1");
			},
			rules : {
				publisherNum : {
					required : true,
					pattern : /^[a-zA-Z0-9]+$/,
					maxlength : 50
				},
				publisherName : {
					required : true,
					pattern : /^[áàãảạÁÀÃẢẠâấầẫẩậÂẤẦẪẨẬăắằẵẳặĂẮÀẴẲẶđĐêếềễểệÊẾỀỄỂỆóòõỏọÓÒÕỎỌôốồỗổộÔỐỒỖỔỘơớờỡởợƠỚƠỠỞỢúùũủụÚÙŨỦỤưứừữửựƯỨỪỮỬỰa-zA-Z\s]+$/
					maxlength : 100
				},
				publisherAddress : {
					required : true,
					maxlength : 200
				},
				publisherPhoneNumber : {
					required : true,
					pattern : /\d{10}/,
					maxlength : 11,
				}
			},
			messages : {
				publisherNum : {
					required : 'Vui lòng nhập mã nhà xuất bản!',
					pattern : 'Không được chứa kí tự đặc biệt!',
					maxlength : 'Không được vượt quá 50 kí tự!'
				},
				publisherName : {
					required : 'Vui lòng nhập tên nhà xuất bản!',
					pattern : 'Không được chứa kí tự đặc biệt!',
					maxlength : 'Không được vượt quá 100 kí tự!'
				},
				publisherAddress : {
					required : 'Vui lòng nhập địa chỉ nhà xuất bản!',
					maxlength : 'Không được vượt quá 200 kí tự!'
				},
				publisherPhoneNumber : {
					required : 'Vui lòng nhập số điện thoại nhà xuất bản!',
					pattern : "Vui lòng nhập đúng định dạng số điện thoại!",
					maxlength : "Vui lòng nhập đúng định dạng số điện thoại!!",
				}
			}
		});
	});
</script>