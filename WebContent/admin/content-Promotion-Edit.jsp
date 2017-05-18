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
				<h3>Quản lý khuyến mãi</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Sửa khuyến mãi</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form action="/suaKM" method="post"
							styleClass="form-horizontal form-label-left" styleId="myForm">
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12">Mã
									khuyến mại <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="maKm" styleId="maKm" readonly="true"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="maKm1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã khuyến mãi!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã khuyến mãi đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if
											test="${msg eq 'Mã khuyến mãi không được chứa kí tự đặc biệt!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12">Tên
									khuyến mại <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="tenKm" styleId="tenKm"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="tenKm1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập tên khuyến mãi!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12">Phần
									trăm khuyến mại <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:text property="phanTramKm" styleId="phanTramKm"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="phanTramKm1"></div>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-2 col-sm-3 col-xs-12">Trạng
									Thái <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-6 col-xs-12">
									<html:select property="trangThai" styleId="trangThai"
										styleClass="form-control col-md-7 col-xs-12">
										<html:option value="0">Chưa kích hoạt</html:option>
										<html:option value="1">Kích hoạt</html:option>
									</html:select>
								</div>
								<div id="trangThai1"></div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit styleClass="btn btn-success" property="submit"
										value="Sửa"></html:submit>
									<html:link action="/showlistkm"
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
				if (element.attr("name") == "maKm")
					error.insertAfter("#maKm1");
				else if (element.attr("name") == "tenKm")
					error.insertAfter("#tenKm1");
				else if (element.attr("name") == "phanTramKm")
					error.insertAfter("#phanTramKm1");
			},
			rules : {
				maKm : {
					required : true,
					pattern : /^[a-zA-Z0-9]+$/,
					maxlength : 50
				},
				tenKm : {
					required : true,
					pattern : /^[áàãảạÁÀÃẢẠâấầẫẩậÂẤẦẪẨẬăắằẵẳặĂẮÀẴẲẶđĐêếềễểệÊẾỀỄỂỆóòõỏọÓÒÕỎỌôốồỗổộÔỐỒỖỔỘơớờỡởợƠỚƠỠỞỢúùũủụÚÙŨỦỤưứừữửựƯỨỪỮỬỰa-zA-Z\s]+$/
					maxlength : 100
				},
				phanTramKm : {
					required : true,
					pattern : /[0-9]+/,
					min : 10
				}
			},
			messages : {
				maKm : {
					required : 'Vui lòng nhập mã khuyến mãi!',
					pattern : 'Mã khuyến mãi không được chứa kí tự đặc biệt!',
					maxlength : 'Mã khuyến mãi không được vượt quá 50 kí tự!'
				},
				tenKm : {
					required : 'Vui lòng nhập tên khuyến mãi!',
					pattern : 'Không được chứa kí tự đặc biệt!',
					maxlength : 'Tên khuyến mãi không được vượt quá 100 kí tự!'
				},
				phanTramKm : {
					min : 'Phần trăm khuyến mãi phải lớn hơn 10!',
					pattern : 'Vui lòng nhập chữ số!',
					required : 'Vui lòng nhập phần trăm khuyến mãi!',
				}
			}
		});
	});
</script>