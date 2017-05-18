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
				<h3>Quản lý tác giả</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Sửa tác giả</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form action="/editAuthor" method="post"
							styleClass="form-horizontal form-label-left" styleId="myForm">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mã
									tác giả <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="authorNum" styleId="authorNum"
										readonly="true" styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="authorNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã tác giả!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã tác giả đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tên
									tác giả <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:text property="authorName" styleId="authorName"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="authorName1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập tên tác giả!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tiểu
									sử tác giả <span class="required">*</span>
								</label>
								<div class="col-md-6 col-sm-6 col-xs-12">
									<html:textarea property="authorInformation"
										styleId="authorInformation"
										styleClass="form-control col-md-7 col-xs-12" cols="25"
										rows="5" />
								</div>
								<div id="authorInformation1"></div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit styleClass="btn btn-success" property="submit"
										value="Sửa"></html:submit>
									<html:link action="/showlistauthor"
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
				if (element.attr("name") == "authorNum")
					error.insertAfter("#authorNum1");
				else if (element.attr("name") == "authorName")
					error.insertAfter("#authorName1");
				else if (element.attr("name") == "authorInformation")
					error.insertAfter("#authorInformation1");
			},
			rules : {
				authorNum : {
					required : true,
					maxlength : 50,
					pattern : /^[áàãảạÁÀÃẢẠâấầẫẩậÂẤẦẪẨẬăắằẵẳặĂẮÀẴẲẶđĐêếềễểệÊẾỀỄỂỆóòõỏọÓÒÕỎỌôốồỗổộÔỐỒỖỔỘơớờỡởợƠỚƠỠỞỢúùũủụÚÙŨỦỤưứừữửựƯỨỪỮỬỰa-zA-Z\s]+$/
				},
				authorName : {
					required : true,
					maxlength : 200
				},
				authorInformation : {
					required : true,
					maxlength : 5000
				}
			},
			messages : {
				authorNum : {
					required : 'Vui lòng nhập mã tác giả!',
					maxlength : 'Mã tác giả không được vượt quá 50 kí tự!'
				},
				authorName : {
					pattern : 'Không được chứa kí tự đặc biệt!',		
					required : 'Vui lòng nhập tên tác giả!',
					maxlength : 'Tên tác giả không được vượt quá 200 kí tự!'
				},
				authorInformation : {
					required : 'Vui lòng nhập tiểu sử tác giả!',
					maxlength : 'Tiểu sử tác giả không được vượt quá 5000 kí tự!'
				}
			}
		});
	});
</script>