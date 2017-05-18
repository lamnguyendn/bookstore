package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import common.DataAccess;
import common.StringProcess;
import common.DataBaseException;
import model.beans.Comment;

public class CommentDAO {
	public List<Comment> getListOfComments() {
		Connection con = DataAccess.connect();
		String sql = "SELECT bl.ma_bl, bl.noidung, bl.ngaybinhluan, bl.username, s.tensach" + " FROM binhluan bl "
				+ " INNER JOIN sach s ON bl.isbn = s.isbn ORDER BY ma_bl DESC";

		ResultSet rs = null;
		PreparedStatement pstm = null;
		List<Comment> arr = new ArrayList<>();

		try {
			pstm = con.prepareStatement(sql);
			rs = pstm.executeQuery();
			Comment cm;
			while (rs.next()) {
				cm = new Comment();
				cm.setMa_BL(rs.getInt(1));
				cm.setNoiDung(rs.getString(2));
				cm.setNgayBinhLuan(StringProcess.convertDateSqlToDateUtil(rs.getDate(3)));
				cm.setUserName(rs.getString(4));
				cm.setTenSach(rs.getString(5));
				arr.add(cm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public List<Comment> getListOfCommentsByIsbn(String isbn) {
		Connection con = DataAccess.connect();
		String sql = "SELECT bl.ma_bl, bl.noidung, bl.ngaybinhluan, bl.isbn, bl.username, tk.ten "
				+ " FROM binhluan bl " + " INNER JOIN sach s ON s.isbn = bl.isbn "
				+ " INNER JOIN taikhoan tk ON tk.username = bl.username AND bl.isbn = ? ORDER BY ma_bl DESC;";
		// AND bl.pheduyet = 1
		ResultSet rs = null;
		PreparedStatement pstm = null;
		List<Comment> arr = new ArrayList<>();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, isbn);
			rs = pstm.executeQuery();
			Comment cm;
			while (rs.next()) {
				cm = new Comment();
				cm.setMa_BL(rs.getInt("ma_bl"));
				cm.setIsbn(rs.getString("isbn"));
				cm.setUserName(rs.getString("username"));
				cm.setNoiDung(rs.getString("noidung"));
				cm.setNgayBinhLuan(StringProcess.convertDateSqlToDateUtil(rs.getDate("ngaybinhluan")));
				cm.setTen(rs.getString("ten"));
				arr.add(cm);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public void insertComment(Comment comment, HttpServletRequest request) throws DataBaseException {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

		String sql = "INSERT INTO binhluan(noidung,ngaybinhluan,isbn,username) " + " VALUES(?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, comment.getNoiDung());
			pstm.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			pstm.setString(3, comment.getIsbn());
			pstm.setString(4, comment.getUserName());
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Comment editComment(int ma_BL) {
		Comment cm = new Comment();
		String sql = "SELECT";
		return cm;
	}

	public void deleteComment(int ma_BL, HttpServletRequest request) throws DataBaseException {
		String sql = "DELETE FROM binhluan WHERE ma_BL = ? ";
		PreparedStatement pstm = null;
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, ma_BL);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
