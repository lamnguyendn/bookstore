package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DataAccess;
import common.StringProcess;
import model.beans.Comment;

public class CommentDAO {

	public List<Comment> getListOfComments(String isbn) {
		Connection con = DataAccess.connect();
		String sql = "SELECT bl.noidung, bl.ngaybinhluan, tk.ten " + " FROM binhluan bl "
				+ " INNER JOIN sach s ON s.isbn = bl.isbn "
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
				cm.setUserName(rs.getString("ten"));
				cm.setNoiDung(rs.getString("noidung"));
				cm.setNgayBinhLuan(StringProcess.convertDateSqlToDateUtil(rs.getDate("ngaybinhluan")));
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

	public void insertComment(Comment comment) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

		String sql = "INSERT INTO binhluan(noidung,ngaybinhluan,isbn,username,pheduyet) " + " VALUES(?,?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, comment.getNoiDung());
			pstm.setDate(2, new java.sql.Date(new java.util.Date().getTime()));
			pstm.setString(3, comment.getIsbn());
			pstm.setString(4, comment.getUserName());
			pstm.setInt(5, 0);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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
