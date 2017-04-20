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

	public ArrayList<Publisher> getListOfPublishers() {

		ArrayList<Publisher> arr = new ArrayList<>();
		String sql = "SELECT * FROM nhaxuatban ;";
		Connection con = DataAccess.connect();
		Statement stm;
		ResultSet rs;
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

	public void addPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber) {
		Connection con = DataAccess.connect();
		String sql = String.format(
				"INSERT INTO nhaxuatban(ma_nxb,ten_nxb,diachi_nxb,sdt_nxb) " + " VALUES ( '%s',N'%s',N'%s','%s' )",
				publisherNum, publisherName, publisherAddress, publisherPhoneNumber);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean duplicateIdPublisher(String s) {
		Connection con = DataAccess.connect();
		ResultSet rs;
		PreparedStatement pstm;
		boolean result = true;
		String sql = "Select ma_nxb from nhaxuatban where ma_nxb = '" + s + "'";
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

	public void editPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber) {
		Connection con = DataAccess.connect();

		String sql = String.format("UPDATE nhaxuatban " + " SET ten_nxb = N'%s', diachi_nxb = N'%s',sdt_nxb ='%s'"
				+ " WHERE ma_nxb = '%s'", publisherName, publisherAddress, publisherPhoneNumber, publisherNum);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Publisher getInfoPublisher(String publisherNum) {
		Connection con = DataAccess.connect();

		String sql = "Select * from nhaxuatban where ma_nxb = '" + publisherNum + "'";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Publisher p = new Publisher();
		try {
			while (rs.next()) {
				p.setPublisherNum(rs.getString("ma_nxb"));
				p.setPublisherName(rs.getString("ten_nxb"));
				p.setPublisherAddress(rs.getString("diachi_nxb"));
				p.setPublisherPhoneNumber(rs.getString("sdt_nxb"));
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
		return p;
	}

	public void deletePublisher(String publisherNum) {
		Connection con = DataAccess.connect();
		String sql = "DELETE  from nhaxuatban where ma_nxb = '" + publisherNum + "'";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}