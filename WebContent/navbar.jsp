<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<nav class="navbar navbar-inverse" style="margin-bottom: 0px;">
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
				<!-- <li class="active"><a href="index.jsp">Trang chủ</a></li> -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Quản lý<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><html:link action="/orderManagement" target="_blank">Đơn Hàng</html:link>
						</li>
						<li><a href="quanlytaikhoan.html" target="_blank">Tài
								Khoản</a></li>
						<li><a href="quanlytheloai.html" target="_blank">Thể Loại</a></li>
						<li><html:link action="/bookManagement" target="_blank">Sách</html:link>
						</li>
						<li><a href="quanlytacgia.html" target="_blank">Tác Giả</a></li>
						<li><a href="quanlynhaxuatban.html" target="_blank">Nhà
								Xuất Bản</a></li>
						<li><a href="quanlykhuyenmai.html" target="_blank">Khuyến
								Mãi</a></li>
						<li><a href="quanlythongke.html" target="_blank">Thống Kê</a></li>
					</ul></li>
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#">Thể loại<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="quanlynhaxuatban.html" target="_blank">
								Thiếu Nhi</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav">
				<form class="navbar-form navbar-left">
					<div class="input-group">
						<input type="text" class="form-control"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="submit">Tìm kiếm</button>
						</span>
					</div>
				</form>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<c:if test="${!logged}">
					<li><a href="login.jsp"><span
							class="glyphicon glyphicon-user"></span>Đăng Nhập</a></li>
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
</script>