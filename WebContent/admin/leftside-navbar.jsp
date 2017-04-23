<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- sidebar menu -->
<div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
	<div class="menu_section">
		<ul class="nav side-menu">
			<li><a href="/BookStore/index.do"><i class="fa fa-home"></i> Trang chủ </a></li>
			<li><a><i class="fa fa-book"></i> Quản lý sách <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/bookManagement">  Danh sách
							</html:link></li>
					<li><html:link action="/addBook"> Thêm mới </html:link></li>
				</ul></li>
			<li><a><i class="fa fa-bar-chart-o"></i> Quản lý thống kê <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><a href="form.html">General Form</a></li>
					<li><a href="form_advanced.html">Advanced Components</a></li>
					<li><a href="form_validation.html">Form Validation</a></li>
					<li><a href="form_wizards.html">Form Wizard</a></li>
					<li><a href="form_upload.html">Form Upload</a></li>
					<li><a href="form_buttons.html">Form Buttons</a></li>
				</ul></li>
			<li><a><i class="fa fa-paint-brush"></i> Quản lý nhà xuất
					bản <span class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/showlistpublisher">  Danh sách
							</html:link></li>
					<li><html:link action="/addPublisher"> Thêm mới </html:link></li>
				</ul></li>
			<li><a><i class="fa fa-edit"></i> Quản lý tác giả <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/showlistauthor">  Danh sách
							</html:link></li>
					<li><html:link action="/addAuthor"> Thêm mới </html:link></li>
				</ul></li>
			<li><a><i class="fa fa-list-ul"></i> Quản lý danh mục <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/showlistcategory">  Danh sách
							</html:link></li>
					<li><html:link action="/addCategory"> Thêm mới </html:link></li>
				</ul></li>
			<li><a><i class="fa fa-credit-card"></i> Quản lý đơn hàng <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/orderManagement">Danh sách</html:link>
					</li>
				</ul></li>
			<li><a><i class="fa fa-bullhorn"></i> Quản lý khuyến mãi <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/showlistkm">Danh sách</html:link></li>
					<li><html:link action="/themKM"> Thêm mới </html:link></li>
				</ul></li>
			<li><a><i class="fa fa-user"></i> Quản lý tài khoản <span
					class="fa fa-chevron-down"></span></a>
				<ul class="nav child_menu">
					<li><html:link action="/danh-sach">Danh sách</html:link></li>
					<li><html:link action="/themAcc"> Thêm mới </html:link></li>
				</ul></li>
		</ul>
	</div>
</div>
<!-- /sidebar menu -->