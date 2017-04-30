<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- DataTable -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/currency.js"></script>
<script src="js/numeric-comma.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">

<script>
	$(document)
			.ready(
					function() {
						$('#example')
								.DataTable(
										{
											pagingType : 'full_numbers',
											"lengthMenu" : [
													[ 5, 10, 25, 50, -1 ],
													[ 5, 10, 25, 50, "Tất cả" ] ],
											language : {
												"search" : "Tìm kiếm:",
												"zeroRecords" : "Không tìm thấy dữ liệu tương ứng",
												"info" : "Hiển thị _START_ đến _END_ của _TOTAL_ dòng",
												"infoEmpty" : "Hiển thị 0 đến 0 của 0 dòng",
												"infoFiltered" : "(đã lọc từ _MAX_ dòng)",
												"lengthMenu" : "Hiển thị _MENU_ dòng",
												paginate : {
													first : 'Đầu tiên',
													previous : 'Trước',
													next : 'Sau',
													last : 'Cuối'
												},
												aria : {
													paginate : {
														first : 'Đầu tiên',
														previous : 'Trước',
														next : 'Sau',
														last : 'Cuối'
													}
												}
											}
										});
					});
</script>
<fmt:setLocale value="vi-VN" />
<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="myModalLabel">Xác nhận xóa</h4>
			</div>
			<div class="modal-body">
				<p>Bạn có muốn xóa không ?</p>
				<p class="debug-url"></p>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Không</button>
				<a class="btn btn-danger btn-ok">Có</a>
			</div>
		</div>
	</div>
</div>
<div class="right_col" role="main">
	<div class="">
		<div class="page-title">
			<div class="title_left">
				<h3>Quản lý bình luận</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<h2>Danh sách bình luận</h2>
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div id="datatable_wrapper"
							class="dataTables_wrapper form-inline dt-bootstrap no-footer">
							<table class="table table-hover" id="example">
								<thead>
									<tr>
										<th>Mã bình luận</th>
										<th>Tên tài khoản</th>
										<th>Tên sách</th>
										<th>Nội dung</th>
										<th class="text-center">Xóa</th>
									</tr>
								</thead>
								<tbody>
									<logic:iterate name="commentForm" property="listOfComments"
										id="cmt">
										<tr>
											<td><bean:write name="cmt" property="ma_BL" /></td>
											<td><bean:write name="cmt" property="userName" /></td>
											<td><bean:write name="cmt" property="tenSach" /></td>
											<td><bean:write name="cmt" property="noiDung" /></td>
											<td class="text-center"><bean:define id="ma_BL"
													name="cmt" property="ma_BL"></bean:define> <a
												data-href="/BookStore/deleteComment.do?ma_BL=${ma_BL}"
												data-toggle="modal" data-target="#confirm-delete"><span
													class="glyphicon glyphicon-trash"></span> </a></td>
										</tr>
									</logic:iterate>
								</tbody>
							</table>
							<script>
								$('#confirm-delete').on(
										'show.bs.modal',
										function(e) {
											$(this).find('.btn-ok').attr(
													'href',
													$(e.relatedTarget).data(
															'href'));
										});
							</script>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>