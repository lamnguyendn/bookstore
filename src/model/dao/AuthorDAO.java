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
import model.beans.Author;

public class AuthorDAO {

	public ArrayList<Author> getListOfAuthors() {
		ArrayList<Author> arr = new ArrayList<>();
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
				rs.close();
				stm.close();
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
		return arr;
	}

	public void addAuthor(String authorNum, String authorName, String authorInformation, HttpServletRequest request)
			throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = String.format("INSERT INTO tacgia(ma_tg,ten_tg,tieusu_tg) " + " VALUES ( '%s',N'%s',N'%s' )",
				authorNum, authorName, authorInformation);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
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

	public boolean duplicateIdAuthor(String s) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm;
		ResultSet rs;

		boolean result = true;
		String sql = "Select ma_tg from tacgia where ma_tg = '" + s + "'";
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

	public Author getInfoAuthor(String authorNum) {
		Connection con = DataAccess.connect();

		String sql = "Select * from tacgia where ma_tg = '" + authorNum + "'";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Author a = new Author();
		try {
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

	public void editAuthor(String authorNum, String authorName, String authorInformation, HttpServletRequest request)
			throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = "UPDATE tacgia " + " SET ten_tg = ?, tieusu_tg = ?" + " WHERE ma_tg = ? ";
		try {
			// Statement stmt = con.createStatement();
			PreparedStatement pstm = con.prepareStatement(sql);
			pstm.setString(1, authorName);
			pstm.setString(2, authorInformation);
			pstm.setString(3, authorNum);
			pstm.executeUpdate();
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

	public void deleteAuthor(String authorNum, HttpServletRequest request) throws ThanhToanException {
		Connection con = DataAccess.connect();

		String sql = "DELETE  from tacgia where ma_tg = '" + authorNum + "'";
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
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

	public Author findAuthorByAuthorNum(String authorNum) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public ArrayList<Author> getListOfAuthorsByFindKey(String findKey) {
		ArrayList<Author> arr = new ArrayList<>();
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;
		String sql = "SELECT * FROM tacgia " + " WHERE ten_tg LIKE N'%" + findKey + "%' ;";
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
				rs.close();
				stm.close();
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
		return arr;
	}
}
