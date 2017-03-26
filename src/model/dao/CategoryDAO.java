package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Category;

public class CategoryDAO {
	Connection con = DataAccess.connect();
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public ArrayList<Category> getListOfCategories() {
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
		}
		return arr;
	}

	public String findCategoryByCategoryNum(String categoryNum) {
		String sql = "SELECT ten_tl FROM theloai WHERE ma_tl = '" + categoryNum + "';";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}