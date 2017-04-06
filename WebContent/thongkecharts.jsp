<%@page import="common.FileProcess"%>
<%@page import="common.HistoryLogLine"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Thống kê</title>
<style type="text/css">
.dpimage {
	background-position: right;
	background-repeat: no-repeat;
	position: relative;
	top: 5px;
	left: -20px;
}

body {
	background-color: #e1e7f2;
}

.margintop {
	margin-top: 20px;
}

.DatePicker {
	height: 22px;
	width: 100px;
	font-family: Arial, sans-serif;
	font-size: 12px;
	color: #999999;
}
</style>
<link rel="stylesheet" href="css/jquery-ui.css">
<script type="text/javascript" src="js/jquery-1.12.4.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/style1.css">
<script type="text/javascript" src="js/fusioncharts.js"></script>
<script type="text/javascript" src="js/json2.js"></script>
<script type="text/javascript" src="js/json_parse_state.js"></script>
<script type="text/javascript" src="js/json_parse.js"></script>
<script type="text/javascript" src="js/cycle.js"></script>
<script src="js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/bootstrap.min.css" />
<style type="text/css">
.dpimage {
	background-position: right;
	background-repeat: no-repeat;
	position: relative;
	top: 5px;
	left: -20px;
}

.DatePicker {
	height: 22px;
	width: 100px;
	font-family: Arial, sans-serif;
	font-size: 12px;
	color: #999999;
}
/* div{
	margin-bottom:20px;
} */
.table table-hover {
	height: 400 px;
	width: 500 px;
}
</style>
</head>
<style>
.inner {
	width: auto;
	margin: auto;
}
</style>
<script type="text/javascript">
//Tạo biểu đồ được render ở thẻ div có id là #chart-tacgia
FusionCharts.ready(function(){
    var fusioncharts = new FusionCharts({
    type: 'pie2d',
    renderAt: 'chart-tacgia',
    dataFormat: 'json',
    height:"400",
    width:"500",
    dataSource: {
    chart: {
    		enableSmartLabels: "1",       
   		    bgColor: "#ffffff",
   		    caption: "Thống Kê Sách Bán Theo Tác Giả",
            showPercentValues: "1",
            showLabels: "0",
            showBorder: "1",
            use3DLighting: "0",
            showShadow: "0",
    		},
   			 data:${jsonTacGia}
    	}
	}
);
    fusioncharts.render();
});

//Tạo biểu đồ tròn cho id #chart-theloai
FusionCharts.ready(function(){
    var fusioncharts = new FusionCharts({
    type: 'pie2d',
    renderAt: 'chart-theloai',
    dataFormat: 'json',
    height:"400",
    width:"500",
    dataSource: {
    chart: {
    		enableSmartLabels: "1",       
   		    bgColor: "#ffffff",
   		    caption: "Thống Kê Sách Bán Theo Thể Loại",
            showPercentValues: "1",
            showLabels: "0",
            showBorder: "1",
            use3DLighting: "0",
            showShadow: "0",
    		},
   			 data:${jsonTheLoai}
    	}
	}
);
    fusioncharts.render();
});
//Tạo biểu đồ render ở thẻ div có id là chart-doanhthu
	FusionCharts.ready(
		function () {
// Create a new instance of FusionCharts for rendering inside an HTML
// `<div>` element with id `my-chart-container`.

 		var myChart = new FusionCharts({
				type: 'column2d',
				renderAt: 'chart-doanhthu',
				height:"400",
				width:"500",
				dataFormat: 'json',
				dataSource: {
					chart: {
						enableSmartLabels: "1",       
						bgColor: "#ffffff",
    					showLabels: "1",
    					showBorder: "1",
    					use3DLighting: "0",
    					showShadow: "0",
						caption: "Doanh Thu theo 12 Tháng",
							},
					data:${jsonDoanhThu}

					}
		});
			// Render the chart.
			myChart.render();
			}); 
		//END CHART
</script>
</head>
<body>
	<div id="wrapper">
		<%@include file="navbar.jsp"%>
		<div id="content">
			<div class="container">
				<div class="col-md-6 col-lg-6 col-sm-6">
					<h3>
						<label for="doanhthu">Thống kê doanh thu theo năm:</label>
					</h3>

					<div id="doanhthu">
						<!-- Thẻ div thống kê doanh thu 12 tháng theo năm -->
						<div>
							<div class="col-lg-4">
								<label for="thongkedoanhthu">Chọn Năm Thống Kê:</label>
								<html:form styleId="thongkedoanhthu">
									<!-- Chọn năm để thống kê doanh thu 12 tháng -->
									<html:select styleId="tkDoanhThu" property="namDoanhThu"
										name="thongKeForm">
										<html:option value="">--Chọn Năm--</html:option>
										<html:optionsCollection name="thongKeForm" property="listNam"
											label="tenNam" value="nam" />
									</html:select>
								</html:form>
							</div>
							<div id="chart-doanhthu">Biểu đồ thống kê doanh thu 12
								tháng theo năm tự chọn</div>
						</div>
					</div>
				</div>
				<!-- thẻ div thống kê sách bán theo thể loại  -->
				<div class="col-md-6 col-lg-6 col-sm-6">
					<h3>
						<label for="theloai">Thống kê Thể Loại Có Sách Bán Được:</label>
					</h3>
					<div id="theloai">
						<label for="thongketheloai">Chọn Thời Gian Thống Kê:</label>
						<html:form styleId="thongketheloai">
							<!-- Chọn tháng để thống kê sách bán theo thể loại -->
							<html:select property="thangTheLoai" styleId="tkThangTheLoai"
								styleClass="thang" name="thongKeForm">
								<html:option value="">--Chọn Tháng--</html:option>
								<html:optionsCollection name="thongKeForm" property="listThang"
									label="tenThang" value="thang" />
							</html:select>
							<!-- Chọn năm để thống kê sách bán -->
							<html:select property="namTheLoai" styleId="tkNamTheLoai"
								styleClass="nam" name="thongKeForm">
								<html:option value="">--Chọn Năm--</html:option>
								<html:optionsCollection name="thongKeForm" property="listNam"
									label="tenNam" value="nam" />
							</html:select>
						</html:form>
					</div>
					<div id="chart-theloai">Biểu đồ thống kê những thể loại có
						sách bán</div>
				</div>
				<!-- Thẻ div thống kê sách bán theo tác giả -->
				<div class="col-md-6 col-lg-6 col-sm-6">
					<h3>
						<label for="tacgia">Thống Kê Tác Giả Có Sách Bán Được:</label>
					</h3>
					<div styleId="tacgia">
						<label for="thongketacgia">Chọn Thời Gian Thống Kê:</label>
						<html:form styleId="thongketacgia">
							<!-- Chọn tháng để thống kê -->
							<html:select property="thangTacGia" styleId="tkTacGiaThang"
								styleClass="thang">
								<html:option value="">--Chọn Tháng--</html:option>
								<html:optionsCollection name="thongKeForm" property="listThang"
									label="tenThang" value="thang" />
							</html:select>
							<!--Chọn năm để thống kê  -->
							<html:select property="namTacGia" styleId="tkTacGiaNam"
								styleClass="nam">
								<html:option value="">--Chọn Năm--</html:option>
								<html:optionsCollection name="thongKeForm" property="listNam"
									label="tenNam" value="nam" />
							</html:select>
						</html:form>
					</div>
					<div id="chart-tacgia" class="margintop">Biểu đồ thống kê
						sách bán theo tác giả</div>

				</div>
				<div class="col-md-6" style="margin-top: 35px;">
					<h2>Thống kê 4 Sách Bán chạy</h2>
					<div>
						<label for="thongketacgia">Chọn Thời Gian Thống Kê:</label>
						<html:form styleId="thongketacgia">
							<!-- Chọn tháng để thống kê -->
							<html:select property="thangTop4" styleId="tkThangTop4"
								styleClass="thang">
								<html:option value="">--Chọn Tháng--</html:option>
								<html:optionsCollection name="thongKeForm" property="listThang"
									label="tenThang" value="thang" />
							</html:select>
							<!--Chọn năm để thống kê  -->
							<html:select property="namTop4" styleId="tkNamTop4"
								styleClass="nam">
								<html:option value="">--Chọn Năm--</html:option>
								<html:optionsCollection name="thongKeForm" property="listNam"
									label="tenNam" value="nam" />
							</html:select>
						</html:form>
					</div>
					<div style="border: 1px solid; margin-bottom: 20px;">
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Mã Sách</th>
									<th>Tên Sách</th>
									<th class="text-right">Số Lượng</th>
								</tr>
							</thead>
							<tbody id="table4Books">
								<logic:iterate id="book" property="list4Books"
									name="thongKeForm">
									<tr>
										<td><bean:write name="book" property="maSachBan" /></td>
										<td><bean:write name="book" property="tenSachBan" /></td>
										<td class="text-right"><bean:write name="book"
												property="soLuongBan" /></td>
									</tr>
								</logic:iterate>
						</table>
					</div>
					<html:form action="/detail" styleClass="margin-top:30px;">
						<html:submit styleClass="btn btn-warning">Thống kê chi tiết sách bán</html:submit>
					</html:form>
				</div>
			</div>


			<div>

				<%-- <html:link action="/detail">click here</html:link> --%>
			</div>
			<script type="text/javascript">
	//validate ngay
	$("#tkTacGiaThang").change(function(){
		$('#tkTacGiaNam option:contains("--Chọn Năm--")').prop('selected',true);
		});
	$("#tkThangTheLoai").change(function(){
		$('#tkNamTheLoai option:contains("--Chọn Năm--")').prop('selected',true);
		});
	$("#tkThangTop4").change(function(){
		$('#tkNamTop4 option:contains("--Chọn Năm--")').prop('selected',true);
		});
	//pagination table
	$("#tkNamTop4").change(function(){
			//get value was selected
			var thang = document.getElementById("tkThangTop4").value;
			var nam = document.getElementById("tkNamTop4").value;
					$.ajax({
						type : "POST",
						contentType : "application/json;charset=utf-8",
						url : "reloadTableTop4Action.do?namTop4="+nam+"&thangTop4="+thang,
						timeout : 100000,
						success : function(data) {
							display(data);
						}
					});
		function display(data) {
			$("#table4Books").html(data);
		}
	});
	
	
		//Chart processing
		$("#tkDoanhThu").change(function(){
			//get value was selected
			var value = document.getElementById("tkDoanhThu").value;
			//call ajax
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : "ReloadChartDoanhThu.do?namDoanhThu="+value,
				timeout : 100000,
				success :function(data) {
					//call update chart
					 update(data);
					
				}
			});
		//Update chart method
			 function update(data){
				 var strJson = JSON.parse(data);
				 var fc = new FusionCharts({
						type: 'column2d',
						renderAt: 'chart-doanhthu',
						height:"400",
						width:"500",
						dataFormat: 'json',
						dataSource: {
							chart: {
								enableSmartLabels: "1",       
								bgColor: "#ffffff",
							    showLabels: "1",
							    showBorder: "1",
							    use3DLighting: "0",
							    showShadow: "0",
								caption: "Doanh Thu 12 Tháng",
							},
							data:strJson
						}
					});  
					fc.render('chart-doanhthu');
				}
			});	 
		//call function when html:select #tkSachNam was selected
		$("#tkTacGiaNam").change(function(){
			var nam = document.getElementById("tkTacGiaNam").value;
			var thang = document.getElementById("tkTacGiaThang").value;
			//call ajax
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : "ReloadChartTacGia.do?namTacGia="+nam+"&thangTacGia="+thang,
				timeout : 100000,
				success :function(data) {
					 update(data);
				}
			});
		//Update chart in div tag #chart-container2
			 function update(data){
				 var strJson = JSON.parse(data);
					  var fc = new FusionCharts({
						type: 'pie2d',
						renderAt: 'chart-tacgia',
						height:"400",
						width:"500",
						dataFormat: 'json',
						dataSource: {
							chart: {
								enableSmartLabels: "1",       
					   		    bgColor: "#ffffff",
					   		    caption: "Thống Kê Sách Bán Theo Tác Giả",
					            showPercentValues: "1",
					            showLabels: "0",
					            showBorder: "1",
					            use3DLighting: "0",
					            showShadow: "0",
							},
							data:strJson
						}
					});  
					fc.render('chart-tacgia');
				}
			});	 
		//call function when html:select #tkThangTheLoai was selected
		$("#tkNamTheLoai").change(function(){
			var nam = document.getElementById("tkNamTheLoai").value;
			var thang = document.getElementById("tkThangTheLoai").value;
			//call ajax
			$.ajax({
				type : "POST",
				contentType : "application/json; charset=utf-8",
				url : "ReloadChartTheLoai.do?namTheLoai="+nam+"&thangTheLoai="+thang,
				timeout : 100000,
				success :function(data) {
					 update(data);
				}
			});
		//Update chart in div tag #chart-theloai
			 function update(data){
				 var strJson = JSON.parse(data);
					  var fc = new FusionCharts({
						type: 'pie2d',
						renderAt: 'chart-theloai',
						height:"400",
						width:"500",
						dataFormat: 'json',
						dataSource: {
							chart: {
								enableSmartLabels: "1",       
					   		    bgColor: "#ffffff",
					   		    caption: "Thống Kê sách bán theo thể loại",
					            showPercentValues: "1",
					            showLabels: "0",
					            showBorder: "1",
					            use3DLighting: "0",
					            showShadow: "0",
							},
							data:strJson
						}
					});  
					fc.render('chart-theloai');
				}
			});	 	
		</script>
		</div>
		<%@include file="footer.jsp"%>
	</div>
</body>
</html>