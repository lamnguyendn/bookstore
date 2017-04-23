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
				<h3>Quản lý tài khoản</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Sửa tài khoản</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form action="/suaAcc" method="post"
							styleClass="form-horizontal form-label-left" styleId="myForm">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tên
									tài khoản <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="userName" styleId="userName"
										readonly="true" styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="userName1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập tên tài khoản!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Tên tài khoản đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mật
									khẩu <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:password property="passWord" styleId="passWord"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="passWord1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mật khẩu!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Xác
									nhận mật khẩu <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:password property="passWord1" styleId="passWord1"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="passWord11"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Họ
									tên <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="ten" styleId="ten"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="ten1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Số
									điện thoại <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="soDienThoai" styleId="soDienThoai"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="soDienThoai1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if
											test="${msg eq 'Vui lòng nhập đúng định dạng số điện thoại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Địa
									chỉ <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="diaChi" styleId="diaChi"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="diaChi1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Email
									<span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="email" styleId="email"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="email1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Quyền
									<span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:select property="quyen" styleClass="form-control"
										styleId="quyen">
										<html:option value="ROLE_ADMIN">Admin</html:option>
										<html:option value="ROLE_USER">Khách hàng</html:option>
									</html:select>
								</div>
								<div id="quyen1"></div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit styleClass="btn btn-success" property="submit"
										value="Sửa"></html:submit>
									<html:link action="/danh-sach" styleClass="btn btn-default">Quay lại</html:link>
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
	$(document)
			.ready(
					function() {
						$('#myForm')
								.validate(
										{
											errorPlacement : function(error,
													element) {
												if (element.attr("name") == "userName")
													error
															.insertAfter("#userName1");
												else if (element.attr("name") == "passWord")
													error
															.insertAfter("#passWord1");
												else if (element.attr("name") == "passWord1")
													error
															.insertAfter("#passWord11");
												else if (element.attr("name") == "ten")
													error.insertAfter("#ten1");
												else if (element.attr("name") == "soDienThoai")
													error
															.insertAfter("#soDienThoai1");
												else if (element.attr("name") == "diaChi")
													error
															.insertAfter("#diaChi1");
												else if (element.attr("name") == "email")
													error
															.insertAfter("#email1");
											},
											rules : {
												userName : {
													required : true,
													maxlength : 20
												},
												passWord : {
													required : true,
													maxlength : 20
												},
												passWord1 : {
													required : true,
													equalTo : '#passWord'
												},
												ten : {
													required : true,
													maxlength : 50,
													pattern : /[a-zA-Z\s]+/
												},
												soDienThoai : {
													required : true,
													pattern : /[0-9]{10,11}/,
												},
												diaChi : {
													required : true,
													maxlength : 100
												},
												email : {
													required : true,
													pattern : /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
												}
											},
											messages : {
												userName : {
													required : 'Vui lòng nhập tên tài khoản!',
													maxlength : 'Tên tài khoản không được vượt quá 20 kí tự!'
												},
												passWord : {
													required : 'Vui lòng nhập tên khuyến mãi!',
													maxlength : 'Mật khẩu không được vượt quá 20 kí tự!'
												},
												passWord1 : {
													required : 'Vui lòng nhập xác nhận mật khẩu!',
													equalTo : 'Mật khẩu phải trùng khớp!',
												},
												ten : {
													required : 'Vui lòng nhập họ tên!',
													maxlength : 'Họ tên không được vượt quá 50 kí tự!',
													pattern : 'Họ tên không được chứa kí tự đặc biệt',
												},
												soDienThoai : {
													required : 'Vui lòng nhập số điện thoại!',
													pattern : 'Vui lòng nhập đúng định dạng số điện thoại!',
												},
												diaChi : {
													required : 'Vui lòng nhập địa chỉ!',
													maxlength : 'Địa chỉ không được vượt quá 100 kí tự!'
												},
												email : {
													required : 'Vui lòng nhập email!',
													pattern : 'Vui lòng nhập đúng định dạng email!'
												}
											}
										});
					});
</script>