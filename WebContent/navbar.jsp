<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="dist/remodal.css">
<link rel="stylesheet" href="dist/remodal-default-theme.css">
<link href="build/css/custom.min.css" rel="stylesheet">
<link href="css/navbar.css" rel="stylesheet">
<!-- Validate -->
<script type="text/javascript" src="js/jquery-validation.js"></script>
<script type="text/javascript" src="js/additional-methods.min.js"></script>
<script type="text/javascript" src="js/validateRegistration.js"></script>
<script type="text/javascript">
	function displayModalLogin() {
		$('#modalLogin').modal('show');
	}
	function removeModalLogin() {
		$('#modalLogin').modal('hide');
	}
	function displayModalRegistration() {
		$('#modalRegistration').modal('show');
	}
</script>

<nav class="navbar navbar-inverse navbar-fixed-top" id="header"
	style="background: #189eff; padding-top: 10px;">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<html:link styleClass="navbar-brand" action="/index">Book Store</html:link>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<c:if test="${logged}">
					<c:if test="${admin}">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">Quản lý<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><html:link action="/orderManagement">Đơn hàng</html:link>
								</li>
								<li><html:link action="/danh-sach">Tài
										Khoản</html:link></li>
								<li><html:link action="/bookManagement">Sách</html:link></li>
								<li><html:link action="/showlistkm">Khuyến
								Mãi</html:link></li>
								<li><html:link action="/thongke">Thống
										Kê</html:link></li>
							</ul></li>
					</c:if>
				</c:if>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Danh mục<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<c:forEach items="${listOfCategories}" var="category">
							<li><bean:define id="categoryNum" name="category"
									property="categoryNum" /> <html:link
									action="/category?categoryNum=${categoryNum}">
									<bean:write name="category" property="categoryName" />
								</html:link></li>
						</c:forEach>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav">
				<div class="form-search">
					<form id="search_form" action="/BookStore/findBook.do"
						method="post">
						<div class="search-wrap">
							<input type="text" name="findKey" autocomplete="off" value=""
								placeholder="Tìm sách theo tên tác giả hoặc tên sách mong muốn ...">
							<button type="submit">
								<span>Tìm kiếm</span>
							</button>
						</div>
					</form>
				</div>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!logged}">
					<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
						style="background: rgba(43, 46, 56, 0.9);"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<html:form action="/checkLogin" method="post" styleId="loginform">
							<div class="modal-dialog">
								<div class="row">
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal"
												aria-hidden="true">&times;</button>
											<h4 class="modal-title" id="myModalLabel">Đăng nhập</h4>
										</div>
										<div class="modal-body">
											<div class="row form-group">
												<label class="col-md-3">Tên đăng nhập</label>
												<div class="col-md-8">
													<html:text property="userName" styleClass="form-control"
														styleId="userName" />
													<span style="color: red;"> <html:errors
															property="userNameError" />
													</span>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-md-3">Mật khẩu</label>
												<div class="col-md-8">
													<html:password property="password"
														styleClass="form-control" styleId="password" />
													<span style="color: red;"> <html:errors
															property="passwordError" />
													</span>
												</div>
											</div>
											<div>
												<label class="col-md-3"></label>
												<div class="col-md-8">
													<p style="color: red;">
														<bean:write name="loginForm" property="message" />
													</p>
												</div>
											</div>
											<div class="row form-group">
												<div class="col-md-8 col-md-offset-3 remodal-bg">
													<html:submit styleClass="btn btn-primary"
														styleId="submitLogin" property="btnSubmit"
														value="Đăng nhập" />
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Hủy</button>
													<button type="button" class="btn btn-info"
														style="float: right;" onclick="return removeModalLogin();"
														data-toggle="modal" data-target="#modalRegistration">Đăng
														ký</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</html:form>
					</div>
					<li><a data-toggle="modal" data-target="#modalLogin" id="one"
						class="button"> <span class="glyphicon glyphicon-user"></span>
							Đăng nhập
					</a></li>
				</c:if>
				<li><html:link action="/showCart">
						<span class="glyphicon glyphicon-shopping-cart"></span> Giỏ Hàng
					</html:link></li>
				<c:if test="${logged}">
					<div class="modal fade" id="confirm-logout" tabindex="-1"
						role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">Xác nhận đăng
										xuất</h4>
								</div>
								<div class="modal-body">
									<p>Bạn có muôn đăng xuất không ?</p>
									<p class="debug-url"></p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default"
										data-dismiss="modal">Không</button>
									<a class="btn btn-danger btn-ok">Có</a>
								</div>
							</div>
						</div>
					</div>
					<li><a data-href="/BookStore/logout.do" data-toggle="modal"
						data-target="#confirm-logout"><span
							class="glyphicon glyphicon-log-out"></span> Đăng xuất </a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<script type="text/javascript">
	function displayModalLogin() {
		$('#modalLogin').modal('show');
	}
	function removeModalLogin() {
		$('#modalLogin').modal('hide');
	}
	function displayModalRegistration() {
		$('#modalRegistration').modal('show');
	}
</script>
<script>
	$('#confirm-logout').on('show.bs.modal', function(e) {
		$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
	$('#modalLogin').on('show.bs.modal', function(e) {
		/* $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href')); */
	});
</script>
<script>
	$('.button').click(function() {
		var buttonId = $(this).attr('id');
		$('#modal-container').removeAttr('class').addClass(buttonId);
		$('body').addClass('modal-active');
	})

	$('#modal-container').click(function() {
		$(this).addClass('out');
		$('body').removeClass('modal-active');
	});
</script>

<c:if test="${sessionScope.errorLogin == 'true' }">
	<script type="text/javascript">
		displayModalLogin();
	</script>
	<%
		session.removeAttribute("errorLogin");
	%>
</c:if>
<div id="modalRegistration" class="modal fade" role="dialog"
	style="background: rgba(43, 46, 56, 0.9);">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<h4 class="modal-title" id="myModalLabel" style="float: left;">Đăng
					ký tài khoản</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12 col-sm-12 col-xs-12">
						<html:form styleId="myForm"
							styleClass="form-horizontal form-label-left" action="/dang-ky"
							method="post">
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-3 col-xs-12"
									for="userName">Tên tài khoản <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-9 col-xs-12">
									<html:text styleId="userName" property="userName"
										styleClass="form-control col-md-7 col-xs-12" />
									<span style="color: red;"> <html:errors
											property="userError" />
									</span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12"
									for="passWord">Mật khẩu <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:password property="passWord"
										styleClass="form-control col-md-7 col-xs-12"
										styleId="passWord" />
									<span style="color: red;"><html:errors
											property="passError" /></span>
								</div>
							</div>
							<div class="form-group">
								<label for="re-password"
									class="control-label col-md-4 col-sm-4 col-xs-12">Nhập
									lại mật khẩu <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:password property="passWord1"
										styleClass="form-control col-md-7 col-xs-12"
										styleId="passWord1" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12">Họ
									và tên <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:text styleId="ten"
										styleClass="form-control col-md-7 col-xs-12 col-md-7 col-xs-12"
										property="ten" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12">Số
									điện thoại </label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:text property="soDienThoai"
										styleClass="form-control col-md-7 col-xs-12"
										styleId="soDienThoai" />
									<span style="color: red;"><html:errors
											property="phoneError" /></span>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12">Địa
									chỉ <span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:text property="diaChi"
										styleClass="form-control col-md-7 col-xs-12" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-4 col-sm-4 col-xs-12">Email
									<span class="required">*</span>
								</label>
								<div class="col-md-8 col-sm-8 col-xs-12">
									<html:text property="email"
										styleClass="form-control col-md-7 col-xs-12" />
									<span style="color: red;"> <html:errors
											property="emailError" /></span>
								</div>
							</div>
							<div class="ln_solid"></div>
							<div class="form-group">
								<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
									<html:submit styleClass="remodal-confirm" property="submit"
										value="Đăng ký"></html:submit>
									<html:reset styleClass="remodal-cancel">Nhập lại</html:reset>
								</div>
							</div>
						</html:form>
					</div>
				</div>
			</div>
		</div>
		<br>
	</div>
</div>
<c:if test="${sessionScope.errorRegistration == 'true'}">
	<script type="text/javascript">
		displayModalRegistration();
	</script>
	<%
		session.removeAttribute("errorRegistration");
	%>
</c:if>
<c:if test="${sessionScope.dkx == '#modalLogin'}">
	<script type="text/javascript">
		displayModalLogin();
	</script>
	<%
		session.removeAttribute("dkx");
	%>
</c:if>
s
<!-- <script type="text/javascript">
	$(document).ready(function() {
		$('#modalRegistration').modal('show');
	});
</script> -->
<script src="dist/remodal.min.js"></script>
<script src="build/js/custom.min.js"></script>