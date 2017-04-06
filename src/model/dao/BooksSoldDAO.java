package model.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.BooksSold;
import model.beans.Nam;
import model.beans.Thang;
import model.beans.ThongKe;

public class BooksSoldDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;
	CallableStatement callstmt;

	public ArrayList<BooksSold> getList4BooksThisMonth() {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> list4Books = new ArrayList<>();

		BooksSold booksSold;
		try {
			callstmt = con.prepareCall("{call sp_getList4BooksThisMonth}");
			rs = callstmt.executeQuery();
			while (rs.next()) {
				booksSold = new BooksSold();
				booksSold.setMaSachBan(rs.getString("ma_sach"));
				booksSold.setTenSachBan(rs.getString("tensach"));
				booksSold.setSoLuongBan(rs.getInt("tongsoluong"));
				booksSold.setHinhAnh(rs.getBytes("hinhanh_1"));
				booksSold.setMota(rs.getString("mota"));
				booksSold.setDonGia(rs.getFloat("dongia"));
				list4Books.add(booksSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list4Books;
	}

	public ArrayList<BooksSold> getList4BooksByDate(int nam, int thang) {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> list4Books = new ArrayList<>();
		try {
			callstmt = con.prepareCall("{call sp_getList4BooksByDate(?,?)}");
			callstmt.setInt(1, nam);
			callstmt.setInt(2, thang);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				BooksSold booksSold = new BooksSold();
				booksSold.setMaSachBan(rs.getString("ma_sach"));
				booksSold.setTenSachBan(rs.getString("tensach"));
				booksSold.setSoLuongBan(rs.getInt("tongsoluong"));
				list4Books.add(booksSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return list4Books;
	}

	public ArrayList<ThongKe> getListDoanhThuThisYear() {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> listDoanhThu = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListDoanhThuThisYear}");
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("thang"));
				thongKe.setValue(rs.getString("doanhthu"));
				listDoanhThu.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDoanhThu;
	}

	public ArrayList<ThongKe> getListDoanhThuByYear(int year) {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> listDoanhThu = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListDoanhThuByYear(?)}");
			callstmt.setInt(1, year);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("thang"));
				thongKe.setValue(rs.getString("doanhthu"));
				listDoanhThu.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listDoanhThu;
	}

	public ArrayList<BooksSold> getListBooksSoldByLimit(int first, int last) {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> arr = new ArrayList<>();
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldLimit(?,?)}");
			callstmt.setInt(1, first);
			callstmt.setInt(2, last);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				BooksSold bookSold = new BooksSold();
				bookSold.setMaSachBan(rs.getString("ma_sach"));
				bookSold.setTenSachBan(rs.getString("tensach"));
				bookSold.setTenTG(rs.getString("ten_tg"));
				bookSold.setTenTL(rs.getString("ten_tl"));
				bookSold.setTenNXB(rs.getString("ten_nxb"));
				bookSold.setSoLuongBan(rs.getInt("soluongban"));
				arr.add(bookSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<BooksSold> getListBooksSoldLimitByDate(int first, int last, String startDate, String endDate) {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> arr = new ArrayList<>();
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldLimitByDate(?,?,?,?)}");
			callstmt.setInt(3, first);
			callstmt.setInt(4, last);
			callstmt.setString(1, startDate);
			callstmt.setString(2, endDate);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				BooksSold bookSold = new BooksSold();
				bookSold.setMaSachBan(rs.getString("ma_sach"));
				bookSold.setTenSachBan(rs.getString("tensach"));
				bookSold.setTenTG(rs.getString("ten_tg"));
				bookSold.setTenTL(rs.getString("ten_tl"));
				bookSold.setTenNXB(rs.getString("ten_nxb"));
				bookSold.setSoLuongBan(rs.getInt("soluongban"));
				arr.add(bookSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<Nam> getListNam() {
		Connection con = DataAccess.connect();
		ArrayList<Nam> listNam = new ArrayList<>();
		String sql = "select year(ngaytao) as nam from donhang group by year(ngaytao) order by year(ngaytao)";
		try {
			Nam nam;
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				nam = new Nam();
				nam.setTenNam(rs.getString("nam"));
				nam.setNam(rs.getInt("nam"));
				listNam.add(nam);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listNam;
	}

	public ArrayList<ThongKe> getListBooksSoldAuthorThisMonth() {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> arr = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldAuthorThisMonth}");
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("ten_tg"));
				thongKe.setValue(rs.getString("tong_ban"));
				arr.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<ThongKe> getListBooksSoldAuthorByDate(int year, int month) {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> arr = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldAuthorByDate(?,?)}");
			callstmt.setInt(1, year);
			callstmt.setInt(2, month);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("ten_tg"));
				thongKe.setValue(rs.getString("tong_ban"));
				arr.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<Thang> getListThang() {
		Connection con = DataAccess.connect();
		ArrayList<Thang> listThang = new ArrayList<>();
		String sql = "select month(ngaytao) as thang from donhang group by month(ngaytao) order  by month(ngaytao)";
		try {
			Thang thang;
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			while (rs.next()) {
				thang = new Thang();
				thang.setTenThang(rs.getString("thang"));
				thang.setThang(rs.getInt("thang"));
				listThang.add(thang);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return listThang;
	}

	// Lấy danh sách thể loại theo tháng và năm
	public ArrayList<ThongKe> getListBooksSoldCateThisMonth() {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> arr = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldCateThisMonth}");
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("ten_tl"));
				thongKe.setValue(rs.getString("tong_ban"));
				arr.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	// Lấy danh sách thể loại theo tháng và năm
	public ArrayList<ThongKe> getListBooksSoldCateByDate(int year, int month) {
		Connection con = DataAccess.connect();
		ArrayList<ThongKe> arr = new ArrayList<>();
		ThongKe thongKe;
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldCateByDate(?,?)}");
			callstmt.setInt(1, year);
			callstmt.setInt(2, month);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				thongKe = new ThongKe();
				thongKe.setLabel(rs.getString("ten_tl"));
				thongKe.setValue(rs.getString("tong_ban"));
				arr.add(thongKe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	// đếm số dòng khi có ngày
	public int countRowByLimitDate(String startDate, String endDate) {
		BooksSoldDAO b = new BooksSoldDAO();
		return b.getListBooksSoldByDate(startDate, endDate).size();
	}

	private ArrayList<BooksSold> getListBooksSoldByDate(String startDate, String endDate) {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> arr = new ArrayList<>();
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSoldByDate(?,?)}");
			callstmt.setString(1, startDate);
			callstmt.setString(2, endDate);
			rs = callstmt.executeQuery();
			while (rs.next()) {
				BooksSold bookSold = new BooksSold();
				bookSold.setMaSachBan(rs.getString("ma_sach"));
				bookSold.setTenSachBan(rs.getString("tensach"));
				bookSold.setTenTG(rs.getString("ten_tg"));
				bookSold.setTenTL(rs.getString("ten_tl"));
				bookSold.setTenNXB(rs.getString("ten_nxb"));
				bookSold.setSoLuongBan(rs.getInt("soluongban"));
				arr.add(bookSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	// đếm số dòng khi không có ngày
	public int countRowByLimit() {

		BooksSoldDAO b = new BooksSoldDAO();
		return b.getListBooksSold().size();
	}

	private ArrayList<BooksSold> getListBooksSold() {
		Connection con = DataAccess.connect();
		ArrayList<BooksSold> arr = new ArrayList<>();
		try {
			callstmt = con.prepareCall("{call sp_getListBooksSold}");
			rs = callstmt.executeQuery();
			while (rs.next()) {
				BooksSold bookSold = new BooksSold();
				bookSold.setMaSachBan(rs.getString("ma_sach"));
				bookSold.setTenSachBan(rs.getString("tensach"));
				bookSold.setTenTG(rs.getString("ten_tg"));
				bookSold.setTenTL(rs.getString("ten_tl"));
				bookSold.setTenNXB(rs.getString("ten_nxb"));
				bookSold.setSoLuongBan(rs.getInt("soluongban"));
				arr.add(bookSold);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		BooksSoldDAO b = new BooksSoldDAO();
		ArrayList<Nam> n = new ArrayList<>();
		n = b.getListNam();
		for (Nam nn : n) {
			System.out.println("nam : " + nn.getTenNam());
		}
	}
}