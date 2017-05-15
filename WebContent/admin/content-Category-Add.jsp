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
				<h3>Quản lý danh mục</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Thêm danh mục</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<html:form action="/addCategory" method="post"
							styleClass="form-horizontal form-label-left" styleId="myForm">
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Mã
									danh mục <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-5 col-xs-12">
									<html:text property="categoryNum" styleId="categoryNum"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="categoryNum1"></div>
								<logic:messagesPresent>
									<html:messages id="msg">
										<c:if test="${msg eq 'Vui lòng nhập mã danh mục!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if test="${msg eq 'Mã danh mục đã tồn tại!' }">
											<label class="error">${msg}</label>
										</c:if>
										<c:if
											test="${msg eq 'Mã danh mục không được chứa kí tự đặc biệt!' }">
											<label class="error">${msg}</label>
										</c:if>
									</html:messages>
								</logic:messagesPresent>
							</div>
							<div class="item form-group">
								<label class="control-label col-md-3 col-sm-3 col-xs-12">Tên
									danh mục <span class="required">*</span>
								</label>
								<div class="col-md-5 col-sm-5 col-xs-12">
									<html:text property="categoryName" styleId="categoryName"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
								<div id="categoryName1"></div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-md-offset-3">
									<html:submit styleClass="btn btn-success" property="submit"
										value="Thêm"></html:submit>
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
				if (element.attr("name") == "categoryNum")
					error.insertAfter("#categoryNum1");
				else if (element.attr("name") == "categoryName")
					error.insertAfter("#categoryName1");
			},
			rules : {
				categoryNum : {
					pattern : /^[a-zA-Z0-9]+$/,
					required : true,
					maxlength : 50
				},
				categoryName : {
					required : true,
					pattern : /^[áàãảạâấầẫẩậăắằẵẳặđêếềễểệóòõỏọôốồỗổộơớờỡởợúùũủụưứừữửựa-zA-Z\s]+$/,		
					maxlength : 200
				}
			},
			messages : {
				categoryNum : {
					pattern : 'Không được chứa kí tự đặc biệt!',					
					required : 'Vui lòng nhập mã danh mục!',
					maxlength : 'Mã danh mục không được vượt quá 50 kí tự!'
				},
				categoryName : {
					pattern : 'Không được chứa kí tự đặc biệt!',			
					required : 'Vui lòng nhập tên danh mục!',
					maxlength : 'Tên danh mục không được vượt quá 200 kí tự!'
				}
			}
		});
	});
</script>