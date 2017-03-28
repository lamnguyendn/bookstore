<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse" style="margin-bottom: 0px;"
	id="header">
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
								<li><html:link action="/orderManagement" target="_blank">Đơn hàng</html:link>
								</li>
								<li><html:link action="/danh-sach" target="_blank">Tài
										Khoản</html:link></li>
								<li><html:link action="/bookManagement" target="_blank">Sách</html:link>
								</li>
								<li><html:link action="/showlistkm" target="_blank">Khuyến
								Mãi</html:link></li>
								<li><a href="quanlythongke.html" target="_blank">Thống
										Kê</a></li>
							</ul></li>
					</c:if>
				</c:if>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Thể loại<span class="caret"></span></a>
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
				<html:form styleClass="navbar-form navbar-left" action="/findBook"
					method="post">
					<div class="input-group">
						<html:text styleClass="form-control" property="findKey" />
						<span class="input-group-btn"> <html:submit
								styleClass="btn btn-default">Tìm kiếm</html:submit>
						</span>
					</div>
				</html:form>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!logged}">
					<div class="modal fade" id="modalLogin" tabindex="-1" role="dialog"
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
													<html:errors property="userNameError" />
												</div>
											</div>
											<div class="row form-group">
												<label class="col-md-3">Mật khẩu</label>
												<div class="col-md-8">
													<html:password property="password"
														styleClass="form-control" styleId="password" />
													<html:errors property="passwordError" />
												</div>
											</div>
											<div>
												<div>
													<p style="color: red;">
														<bean:write name="loginForm" property="message" />
													</p>
												</div>
											</div>
											<div class="row form-group">
												<div class="col-md-8 col-md-offset-3">
													<html:submit styleClass="btn btn-primary"
														styleId="submitLogin">Đăng nhập</html:submit>
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Hủy</button>
													<html:link styleClass="btn btn-success" action="/dang-ky"
														style="float:right;">Đăng ký</html:link>
												</div>
											</div>
										</div>
										<div class="modal-footer"></div>
									</div>
								</div>
							</div>
						</html:form>
					</div>
					<li><a data-toggle="modal" data-target="#modalLogin"> <span
							class="glyphicon glyphicon-user"></span>Đăng nhập
					</a></li>
					<!-- 
					<li><a href="login.jsp"><span
							class="glyphicon glyphicon-user"></span>Đăng Nhập</a></li> -->

				</c:if>
				<li><html:link action="/showCart">
						<span class="glyphicon glyphicon-shopping-cart"></span>Giỏ Hàng
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
						data-target="#confirm-logout"> <span
							class="glyphicon glyphicon-log-out"></span>Đăng xuất
					</a></li>
				</c:if>
			</ul>
		</div>
	</div>
</nav>
<script>
	$('#confirm-logout').on('show.bs.modal', function(e) {
		$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
	$('#login').on('show.bs.modal', function(e) {
		/* $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href')); */
	});
</script>
<!-- <script>
	$("#submitLogin").click(
			function() {
				var userName = document.getElementById('userName').value;
				var password = document.getElementById('password').value;
				$.ajax({
					url : "checkLogin.do?userName=" + userName + "&password="
							+ password,
					method : "post",
					contentType : "application/json; charset=utf-8",
					timeout : 100000,
					success : function(data) {
						if (data != 'failed') {
							location.reload();
							/* 	$("#modalLogin").on('hidden.bs.modal', function(e) {
								});
								$("#modalLogin").modal('hide'); */
							/* $('#modalLogin').removeClass("in"); */
							$('body').removeClass("modal-open");
							$('.modal-backdrop').remove();
						}
					}
				});

				return false;
			});
</script> -->
<script type="text/javascript">
$(document).ready(function(){
    $(document).ready(function(){
        $("<%out.print(session.getAttribute("dkx").toString());%>").modal();
	});
});
</script>
<%
	session.setAttribute("dkx", "#123");
%>
