package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Publisher;

public class PublisherDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public ArrayList<Publisher> getListOfPublishers() {
		ArrayList<Publisher> arr = new ArrayList<>();
		String sql = "SELECT * FROM nhaxuatban ;";
		Connection con = DataAccess.connect();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Publisher p = new Publisher();
				p.setPublisherNum(rs.getString("ma_nxb"));
				p.setPublisherAddress(rs.getString("diachi_nxb"));
				p.setPublisherName(rs.getString("ten_nxb"));
				p.setPublisherPhoneNumber(rs.getString("sdt_nxb"));
				arr.add(p);
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