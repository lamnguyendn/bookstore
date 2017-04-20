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
				<h3>Chi tiết thông tin tác giả</h3>
			</div>
		</div>
		<div class="clearfix"></div>
		<div class="row">
			<div class="col-md-12 col-sm-12 col-xs-12">
				<div class="x_panel">
					<div class="x_title">
						<ul class="nav navbar-right panel_toolbox">
							<li><a class="close-link"><i class="fa fa-close"></i></a></li>
							<li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a></li>
						</ul>
						<div class="clearfix"></div>
					</div>
					<div class="x_content">
						<div class="row form-group">
							<label class="col-lg-2">Mã tác giả</label>
							<div class="col-lg-3">
								<bean:write name="authorForm" property="authorNum" />
							</div>
						</div>
						<div class="row form-group">
							<label class="col-lg-2">Tên tác giả</label>
							<div class="col-lg-3">
								<bean:write name="authorForm" property="authorName" />
							</div>
						</div>
						<div class="row form-group">
							<label class="col-lg-2">Thông tin tác giả</label>
							<div class="col-lg-10" style="word-wrap: break-word;">
								<bean:write name="authorForm" property="authorInformation" />
							</div>
						</div>
						<div style="border-top: 1px solid #E6E9ED; padding: 25px 0px;">
							<html:link action="/showlistauthor" styleClass="btn btn-default">
								<span class="glyphicon glyphicon-step-backward"></span>Quay lại
							</html:link>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>