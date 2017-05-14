package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataAccess;
import common.ThanhToanException;
import model.beans.Category;

public class CategoryDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public ArrayList<Category> getListOfCategories() {
		Connection con = DataAccess.connect();

		ArrayList<Category> arr = new ArrayList<>();
		String sql = "SELECT * FROM theloai ;";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Category c = new Category();
				c.setCategoryNum(rs.getString("ma_tl"));
				c.setCategoryName(rs.getString("ten_tl"));
				arr.add(c);
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

	public String findCategoryByCategoryNum(String categoryNum) {
		Connection con = DataAccess.connect();

		String sql = "SELECT ten_tl FROM theloai WHERE ma_tl = '" + categoryNum + "';";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				return rs.getString(1);
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
		return null;
	}

	public void editCategory(String categoryNum, String categoryName, HttpServletRequest request) throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = String.format("UPDATE theloai " + " SET ten_tl = N'%s'" + " WHERE ma_tl = '%s'", categoryName,
				categoryNum);
		try {
			stm = con.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ThanhToanException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public Category getInfoCategory(String categoryNum) {
		Connection con = DataAccess.connect();

		String sql = "Select * from theloai where ma_tl = '" + categoryNum + "'";
		ResultSet rs = null;
		Category c = new Category();

		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				c.setCategoryNum(rs.getString("ma_tl"));
				c.setCategoryName(rs.getString("ten_tl"));
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
		return c;
	}

	public boolean duplicateIdCategory(String s) {
		Connection con = DataAccess.connect();

		boolean result = true;
		String sql = "Select ma_tl from theloai where ma_tl = '" + s + "'";
		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			result = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public void addCategory(String categoryNum, String categoryName, HttpServletRequest request) throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = String.format("INSERT INTO theloai(ma_tl,ten_tl) " + " VALUES ( '%s',N'%s' )", categoryNum,
				categoryName);
		try {
			stm = con.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ThanhToanException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void deleteCategory(String categoryNum, HttpServletRequest request) throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = "DELETE  from theloai where ma_tl = '" + categoryNum + "'";
		try {
			stm = con.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ThanhToanException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public ArrayList<Category> getListOfCategoriesByFindkey(String findKey) {
		Connection con = DataAccess.connect();
		ArrayList<Category> arr = new ArrayList<Category>();
		Category c;
		String sql = "SELECT *" + " FROM theloai" + " WHERE ma_tl like '%" + findKey + "%' or ten_tl like N'%" + findKey
				+ "%' ";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				c = new Category();
				c.setCategoryNum(rs.getString("ma_tl"));
				c.setCategoryName(rs.getString("ten_tl"));
				arr.add(c);
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
}