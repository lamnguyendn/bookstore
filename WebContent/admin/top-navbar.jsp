<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="modal fade" id="confirm-logout" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Xác nhận đăng xuất</h4>
			</div>
			<div class="modal-body">
				<p>Bạn có muôn đăng xuất không ?</p>
				<p class="debug-url"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Không</button>
				<a class="btn btn-danger btn-ok">Có</a>
			</div>
		</div>
	</div>
</div>
<div class="top_nav">
	<div class="nav_menu">
		<nav>
			<div class="nav toggle">
				<a id="menu_toggle" style="color: #fff;" class="menu-toggle"> <i
					class="fa fa-bars"></i>
				</a>
			</div>
			<ul
				class="nav navbar-nav navbar-right visible-sm-block visible-md-inline visible-lg-inline">
				<li class="" id="wrapper"><a href="javascript:;" id="cityclick"
					class="user-profile dropdown-toggle" data-toggle="dropdown"
					aria-expanded="false"> <span
						style="font-size: 20px; display: inline-block; font-family: FontAwesome; font-style: normal; font-weight: normal;">
							Xin chào <c:out value="${sessionScope.ten}"></c:out>
					</span>
				</a>
					<ul class="dropdown-menu dropdown-usermenu pull-right"
						id="citydrop">
						<li><a href="javascript:;"> Profile</a></li>
						<li><a data-href="/BookStore/logout.do" data-toggle="modal"
							data-target="#confirm-logout"><span
								class="fa fa-sign-out pull-right"></span> Đăng xuất </a></li>
					</ul></li>
				<li class="logo"><span>BookStore <span> Admin
							Management</span>
				</span></li>
			</ul>
		</nav>
	</div>
</div>
<script>
	$(document).ready(function() {
		$("#cityclick").mouseover(function() {
			$("#citydrop").slideDown('fast');
		});

		$("#wrapper").mouseleave(function() {
			$("#citydrop").slideUp('fast');
		});
	});
</script>
<script>
	$('#confirm-logout').on('show.bs.modal', function(e) {
		$(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));
	});
</script>