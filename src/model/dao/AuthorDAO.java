package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Author;

public class AuthorDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public ArrayList<Author> getListOfAuthors() {
		ArrayList<Author> arr = new ArrayList<>();
		Connection con = DataAccess.connect();
		String sql = "SELECT * FROM tacgia ;";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Author a = new Author();
				a.setAuthorNum(rs.getString("ma_tg"));
				a.setAuthorName(rs.getString("ten_tg"));
				a.setAuthorInformation(rs.getString("tieusu_tg"));
				arr.add(a);
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

	public Author findAuthorByAuthorNum(String authorNum) {
		Connection con = DataAccess.connect();
		String sql = "SELECT * FROM tacgia WHERE ma_tg = '" + authorNum + "'";
		Author a = new Author();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				a.setAuthorNum(rs.getString("ma_tg"));
				a.setAuthorName(rs.getString("ten_tg"));
				a.setAuthorInformation(rs.getString("tieusu_tg"));
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
		return a;
	}
}
