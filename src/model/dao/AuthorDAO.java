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
	Connection con = DataAccess.connect();
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public ArrayList<Author> getListOfAuthors() {
		ArrayList<Author> arr = new ArrayList<>();
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
		}
		return arr;
	}
}
