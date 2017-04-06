<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style type="text/css">
@media ( max-width : 767px) {
	.navbar-inverse .navbar-nav .open .dropdown-menu>li>a {
		color: #fff;
	}
}

@media ( min-width : 768px) {
	.navbar-nav>li>a {
		padding-top: 10px;
		padding-bottom: 15px;
	}
}

.navbar-nav>li>.dropdown-menu {
	margin-top: 5px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}

.form-search {
	display: inline-block;
	width: 546px;
	position: relative;
	z-index: 1;
	float: left;
}

.search-wrap {
	width: 475.9px;
	height: 40px;
	border-radius: 4px;
	background-color: #fff;
	box-shadow: 0 1px 2px 0 rgba(98, 98, 98, .5);
	display: table;
	vertical-align: middle;
	width: 100%;
	z-index: 1;
	position: relative;
	margin: 0px 15px;
}

.navbar-inverse .navbar-collapse, .navbar-inverse .navbar-form {
	border-color: #fff;
}

header#header .header-form-container .search-wrap input[type=text] {
	display: table-cell;
	width: calc(100% - 95px);
	max-width: none;
	float: left;
}

#header .search-wrap input[type=text] {
	width: calc(100% -  95px);
	height: 40px;
	border: 0;
	font-size: 13px;
}

#header input[type=email], #header input[type=password], #header input[type=text]
	{
	width: 229px;
	max-width: 100%;
	height: 32px;
	border-radius: 3px;
	font-size: 13px;
	border: 1px solid #efefef;
	padding: 0 8px;
	color: #333;
}

header#header .header-form-container .search-wrap button[type=submit] {
	display: table-cell;
	width: auto;
	float: right;
	vertical-align: top;
	color: #4a4a4a;
}

#header .search-wrap button[type=submit] {
	float: right;
	height: 40px;
	border: 0;
	font-size: 14px;
	padding: 0 16px;
	background: #EFF1F5;
	border-radius: 0 4px 4px 0;
	cursor: pointer;
	font-weight: 500;
}

header#header .top-promo .left a, header#header .top-promo .sticky-screen-banner.right a
	{
	margin: 0;
	padding: 2px 12px 0;
	background: rgba(255, 255, 255, .08);
}

header#header .top-promo a {
	font-size: 12px;
	color: #fff;
	font-weight: 300;
	line-height: 26px;
	display: inline-block;
	letter-spacing: .5px;
	margin: 0 0 0 30px;
}

header#header .top-promo a {
	font-size: 12px;
	color: #fff;
	font-weight: 300;
	line-height: 26px;
	display: inline-block;
	letter-spacing: .5px;
	margin: 0 0 0 30px;
}

a {
	color: #337ab7;
	text-decoration: none;
}

a {
	background-color: transparent;
}

.navbar-inverse .navbar-brand {
	color: #fff;
}

.navbar-inverse .navbar-nav>li>a {
	color: #fff;
}

.navbar-inverse {
	border-color: #189EFF;
}

.navbar-brand {
	float: left;
	height: 50px;
	padding: 10px 15px;
	font-size: 18px;
	line-height: 20px;
}

.navbar-inverse .navbar-nav>.open>a, .navbar-inverse .navbar-nav>.open>a:focus,
	.navbar-inverse .navbar-nav>.open>a:hover {
	color: #fff;
	background-color: #189EFF;
}
/* login */
* {
  box-sizing:border-box;
}

html,body {
  min-height:100%;
  height:100%;
  /* background-image:url(http://theartmad.com/wp-content/uploads/Dark-Grey-Texture-Wallpaper-5.jpg); */
  background-size:cover;
  background-position:top center;
  font-family:helvetica neue, helvetica, arial, sans-serif;
  font-weight:200;
  &.modal-active {
    overflow: hidden;
  }
}

#modal-container {
  position:fixed;
  display:table;
  height:100%;
  width:100%;
  top:0;
  left:0;
  transform:scale(0);
  z-index:1;
  &.one {
    transform:scaleY(.01) scaleX(0);
    animation:unfoldIn 1s cubic-bezier(0.165, 0.840, 0.440, 1.000) forwards;
    .modal-background {
      .modal {
        transform:scale(0);
        animation: zoomIn .5s .8s cubic-bezier(0.165, 0.840, 0.440, 1.000) forwards;
      }
    }
    &.out {
      transform:scale(1);
      animation:unfoldOut 1s .3s cubic-bezier(0.165, 0.840, 0.440, 1.000) forwards;
      .modal-background {
        .modal {
          animation: zoomOut .5s cubic-bezier(0.165, 0.840, 0.440, 1.000) forwards;
        }
      }
    }
  }
  .modal-background {
    display:table-cell;
    background:rgba(0,0,0,.8);
    text-align:center;
    vertical-align:middle;
    .modal {
      background:white;
      padding:50px;
      display:inline-block;
      border-radius:3px;
      font-weight:300;
      position:relative;
      h2 {
        font-size:25px;
        line-height:25px;
        margin-bottom:15px;
      }
      p {
        font-size:18px;
        line-height:22px;
      }
      .modal-svg {
        position:absolute;
        top:0;
        left:0;
        height:100%;
        width:100%;
        border-radius:3px;
        rect {
          stroke: #fff;
          stroke-width: 2px;
          stroke-dasharray: 778;
          stroke-dashoffset: 778;
        }
      }
    }
  }
}

.content {
  min-height:100%;
  height:100%;
  background:white;
  position:relative;
  z-index:0;
  h1 {
    padding:75px 0 30px 0;
    text-align:center;
    font-size:30px;
    line-height:30px;
  }
  .buttons {
    max-width:800px;
    margin:0 auto;
    padding:0;
    text-align:center;
    .button {
      display:inline-block;
      text-align:center;
      padding:10px 15px;
      margin:10px;
      background:red;
      font-size:18px;
      background-color:#efefef;
      border-radius:3px;
      box-shadow:0 1px 2px rgba(0,0,0,.3);
      cursor:pointer;
      &:hover {
        color:white;
        background:#009bd5;
      }
    }
  } 
}

@keyframes unfoldIn {
  0% {
    transform:scaleY(.005) scaleX(0);
  }
  50% {
    transform:scaleY(.005) scaleX(1);
  }
  100% {
    transform:scaleY(1) scaleX(1);
  }
}

@keyframes unfoldOut {
  0% {
    transform:scaleY(1) scaleX(1);
  }
  50% {
    transform:scaleY(.005) scaleX(1);
  }
  100% {
    transform:scaleY(.005) scaleX(0);
  }
}

@keyframes zoomIn {
  0% {
    transform:scale(0);
  }
  100% {
    transform:scale(1);
  }
}

@keyframes zoomOut {
  0% {
    transform:scale(1);
  }
  100% {
    transform:scale(0);
  }
}

@keyframes fadeIn {
  0% {
    background:rgba(0,0,0,.0);
  }
  100% {
    background:rgba(0,0,0,.7);
  }
}

@keyframes fadeOut {
  0% {
    background:rgba(0,0,0,.7);
  }
  100% {
    background:rgba(0,0,0,.0);
  }
}

@keyframes scaleUp {
  0% {
    transform:scale(.8) translateY(1000px);
    opacity:0;
  }
  100% {
    transform:scale(1) translateY(0px);
    opacity:1;
  }
}

@keyframes scaleDown {
  0% {
    transform:scale(1) translateY(0px);
    opacity:1;
  }
  100% {
    transform:scale(.8) translateY(1000px);
    opacity:0;
  }
}

@keyframes scaleBack {
  0% {
    transform:scale(1);
  }
  100% {
    transform:scale(.85);
  }
}

@keyframes scaleForward {
  0% {
    transform:scale(.85);
  }
  100% {
    transform:scale(1);
  }
}

@keyframes quickScaleDown {
  0% {
    transform:scale(1);
  }
  99.9% {
    transform:scale(1);
  }
  100% {
    transform:scale(0);
  }
}

@keyframes slideUpLarge {
  0% {
    transform:translateY(0%);
  }
  100% {
    transform:translateY(-100%);
  }
}

@keyframes slideDownLarge {
  0% {
    transform:translateY(-100%);
  }
  100% {
    transform:translateY(0%);
  }
}

@keyframes moveUp {
  0% {
    transform:translateY(150px);
  }
  100% {
    transform:translateY(0);
  }
}

@keyframes moveDown {
  0% {
    transform:translateY(0px);
  }
  100% {
    transform:translateY(150px);
  }
}

@keyframes blowUpContent {
  0% {
    transform:scale(1);
    opacity:1;
  }
  99.9% {
    transform:scale(2);
    opacity:0;
  }
  100% {
    transform:scale(0);
  }
}

@keyframes blowUpContentTwo {
  0% {
    transform:scale(2);
    opacity:0;
  }
  100% {
    transform:scale(1);
    opacity:1;
  }
}

@keyframes blowUpModal {
  0% {
    transform:scale(0);
  }
  100% {
    transform:scale(1);
  }
}

@keyframes blowUpModalTwo {
  0% {
    transform:scale(1);
    opacity:1;
  }
  100% {
    transform:scale(0);
    opacity:0;
  }
}

@keyframes roadRunnerIn {
  0% {
    transform:translateX(-1500px) skewX(30deg) scaleX(1.3);
  }
  70% {
    transform:translateX(30px) skewX(0deg) scaleX(.9);
  }
  100% {
    transform:translateX(0px) skewX(0deg) scaleX(1);
  }
}

@keyframes roadRunnerOut {
  0% {
    transform:translateX(0px) skewX(0deg) scaleX(1);
  }
  30% {
    transform:translateX(-30px) skewX(-5deg) scaleX(.9);
  }
  100% {
    transform:translateX(1500px) skewX(30deg) scaleX(1.3);
  }
}

@keyframes sketchIn {
	0% {
		stroke-dashoffset: 778;
	}
	100% {
		stroke-dashoffset: 0;
	}
}

@keyframes sketchOut {
	0% {
		stroke-dashoffset: 0;
	}
	100% {
		stroke-dashoffset: 778;
	}
}

@keyframes modalFadeIn {
  0% {
    background-color:transparent;
  }
  100% {
    background-color:white;
  }
}

@keyframes modalFadeOut {
  0% {
    background-color:white;
  }
  100% {
    background-color:transparent;
  }
}

@keyframes modalContentFadeIn {
  0% {
    opacity:0;
    top:-20px;
  }
  100% {
    opacity:1;
    top:0;
  }
}

@keyframes modalContentFadeOut {
  0% {
    opacity:1;
    top:0px;
  }
  100% {
    opacity:0;
    top:-20px;
  }
}

@keyframes bondJamesBond {
  0% {
    transform:translateX(1000px);
  }
  80% {
    transform:translateX(0px);
    border-radius:75px;
    height:75px;
    width:75px;
  }
  90% {
    border-radius:3px;
    height:182px;
    width:247px;
  }
  100% {
    border-radius:3px;
    height:162px;
    width:227px;
  }
}

@keyframes killShot {
  0% {
    transform:translateY(0) rotate(0deg);
    opacity:1;
  }
  100% {
    transform:translateY(300px) rotate(45deg);
    opacity:0;
  }
}

@keyframes fadeToRed {
  0% {
    box-shadow:inset 0 0 0 rgba(201,24,24,.8);
  }
  100% {
    box-shadow:inset 0 2000px 0 rgba(201,24,24,.8);
  }
}

@keyframes slowFade {
  0% {
    opacity:1;
  }
  99.9% {
    opacity:0;
    transform:scale(1);
  }
  100% {
    transform:scale(0);
  }
}
</style>


<div id="modal-container">
  <div class="modal-background">
    <div class="modal">
      <h2>I'm a Modal</h2>
      <p>Hear me roar.</p>
      <svg class="modal-svg" xmlns="http://www.w3.org/2000/svg" width="100%" height="100%" preserveAspectRatio="none">
								<rect x="0" y="0" fill="none" width="226" height="162" rx="3" ry="3"></rect>
							</svg>
    </div>
  </div>
</div>









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
				<%-- <html:form styleClass="navbar-form navbar-left" action="/findBook"
					method="post">
					<div class="input-group">
						<html:text styleClass="form-control" property="findKey" />
						<span class="input-group-btn"> <html:submit
								styleClass="btn btn-default">Tìm kiếm</html:submit>
						</span>
					</div>
				</html:form> --%>
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
													<span style="color: red;"> 
														<%-- <logic:messagesPresent>
															<html:messages id="msg">
																<c:if test="${msg eq 'Vui lòng nhâp tên tài khoản!'}">
																	${msg}
																</c:if>		
															</html:messages>
														</logic:messagesPresent> --%>
														<html:errors property="userNameError" />
													</span>
												</div>
											</div>
											<div class="row form-group">
												<label class="col-md-3">Mật khẩu</label>
												<div class="col-md-8">
													<html:password property="password"
														styleClass="form-control" styleId="password" />
													<span style="color: red;">
														<%-- <logic:messagesPresent>
															<html:messages id="msg">
																<bean:write name="msg" />
															</html:messages>
														</logic:messagesPresent> --%>
														<html:errors property="passwordError" />
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
												<div class="col-md-8 col-md-offset-3">
													<html:submit styleClass="btn btn-primary"
														styleId="submitLogin" property="btnSubmit" value="Đăng nhập" />
													<button type="button" class="btn btn-default"
														data-dismiss="modal">Hủy</button>
													<html:link styleClass="btn btn-success" action="/dang-ky"
														style="float:right;">Đăng ký</html:link>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</html:form>
					</div>
					<li><a data-toggle="modal" data-target="#modalLogin" id="one" class="button"> <span
							class="glyphicon glyphicon-user"></span>Đăng nhập
					</a></li>
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
<script type="text/javascript">
		function displayModal(){
			   $('#modalLogin').modal('show');
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
$('.button').click(function(){
	  var buttonId = $(this).attr('id');
	  $('#modal-container').removeAttr('class').addClass(buttonId);
	  $('body').addClass('modal-active');
	})

	$('#modal-container').click(function(){
	  $(this).addClass('out');
	  $('body').removeClass('modal-active');
	});
</script>
<c:if test="${sessionScope.errorLogin == 'true' }">
	<script type="text/javascript">		
		displayModal();	
	</script>
	<%
		session.removeAttribute("errorLogin");
	%>
</c:if>
<!-- 
<script>
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
							location.reload();
							/* $("#modalLogin").on('hidden.bs.modal', function(e) {
								});
								$("#modalLogin").modal('hide');
							$('#modalLogin').removeClass("in");
							 $('body').removeClass("modal-open");
							$('.modal-backdrop').remove();
							  $('#modalLogin').modal('show'); */
					}
				});

				return false;
			});
</script>
 -->
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
<script type="text/javascript">
/* $(document).ready(function(){
	$('#modalLogin').modal('show'); 
}); */
</script>
