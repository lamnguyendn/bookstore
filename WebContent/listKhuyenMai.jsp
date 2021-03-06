<%@page import="common.StringProcess"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quản lý khuyến mãi</title>

<meta name="viewport" content="width=device-width, initial-scale=1">


<!-- Pagination -->
<script src="js/jquery.min.js"></script>
<script src="js/jquery.twbsPagination.min.js"></script>
<script src="js/jquery.unobtrusive-ajax.js"></script>
<!-- Bootstrap -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/bootstrap.min.js"></script>
<!-- Style Css -->
<link rel="stylesheet" href="css/style1.css">
<!-- DataTable -->
<script src="js/jquery.dataTables.min.js"></script>
<script src="js/currency.js"></script>
<script src="js/numeric-comma.js"></script>
<script src="js/dataTables.bootstrap.min.js"></script>
<link rel="stylesheet" href="css/dataTables.bootstrap.min.css">

<script>
	$(document).ready(function() {
		$('#example').DataTable({
			pagingType : 'full_numbers',
	        "lengthMenu": [[5,10, 25, 50, -1], [5,10, 25, 50, "Tất cả"]],
			"aoColumnDefs" : [ {
				"sType" : "my-currency",
				"aTargets" : [ 5 ]
			} ],
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
</head>
<body>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div id="content">
			<div class="modal fade" id="confirm-delete" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
							<button type="button" class="btn btn-default"
								data-dismiss="modal">Không</button>
							<a class="btn btn-danger btn-ok">Có</a>
						</div>
					</div>
				</div>
			</div>
			<div class="container">
				<h2>Quản lý khuyến mãi</h2>
				<hr size="2px">
				<div class="row" style="margin-top: 20px; margin-bottom: 20px;">
					<html:link styleClass="btn btn-info" action="/themKM" style="float:right;">
						<span class="glyphicon glyphicon-plus-sign"></span> Thêm khuyến mại</html:link>
				</div>
				<table class="table table-hover" id="example">
					<thead>
						<tr>
							<th class="text-center">Mã khuyến mãi</th>
							<th class="text-center">Tên khuyến mãi</th>
							<th class="text-center">Phần trăm khuyến mãi</th>
							<th class="text-center">Trạng thái</th>
							<th class="text-center">Sửa</th>
							<th class="text-center">Xóa</th>
						</tr>
					</thead>
					<tbody>
						<logic:iterate name="danhSachKhuyenMaiForm"
							property="listKhuyenMai" id="km">
							<tr>
								<th scope="row">
									<bean:write name="km" property="maKM" />
								</th>
								<bean:define id="maKm" name="km" property="maKM" />
								<td>
									<bean:write name="km" property="tenKM" />
								</td>
								<td class="text-center">
									<bean:write name="km"
										property="phanTramKM" /> %</td>
								<bean:define id="trangThai" property="trangThai" name="km" />
								<td id="trangThai-${maKm}" class="text-center"><a
									href="javascript:void(0)"
									onclick="return setDelivery('${maKm}')"> <c:if
											test="${trangThai == 1}">
											<i class="glyphicon glyphicon-ok"></i>
										</c:if> <c:if test="${trangThai == 0}">
											<i class="glyphicon glyphicon-remove"></i>
										</c:if>
								</a></td>
								<td class="text-center"><html:link
										action="/suaKM?maKm=${maKm}" style="margin-left: 30px;">
										<span class="glyphicon glyphicon-edit"></span>
									</html:link></td>
								<td class="text-center"><a
									data-href="/BookStore/xoaKm.do?maKm=${maKm}"
									data-toggle="modal" data-target="#confirm-delete"><span
										class="glyphicon glyphicon-trash"></span> </a></td>
							</tr>
						</logic:iterate>
					</tbody>
				</table>
			</div>
			<script>
				$('#confirm-delete').on(
						'show.bs.modal',
						function(e) {
							$(this).find('.btn-ok').attr('href',
									$(e.relatedTarget).data('href'));
						});
			</script>
			<script type="text/javascript">
				function setDelivery(maKm) {
					$.ajax({
						url : "trangThaiKM.do?maKm=" + maKm,
						type : "POST",
						contentType : "application/json; charset=utf-8",
						success : function(data) {
							$("#trangThai-" + maKm).html(data);
							console.log(data);
						},
						timeout : 100000,
						error : function(e) {
							console.log(e);
						}
					});
				}
			</script>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>