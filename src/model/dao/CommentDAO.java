package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.DataAccess;
import model.beans.Comment;

public class CommentDAO {

	public List<Comment> getListOfComments(String isbn) {
		Connection con = DataAccess.connect();
		String sql = "SELECT * "
				+ " FROM binhluan bl "
				+ " INNER JOIN sach s ON s.isbn = bl.isbn "
				+ " INNER JOIN taikhoan tk ON tk.username = bl.username AND bl.isbn = ? ";
		ResultSet rs = null;
		PreparedStatement pstm = null;
		List<Comment> arr = new ArrayList<>();
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, isbn);
			rs = pstm.executeQuery();
			Comment cm;
			while(rs.next()) {
				cm = new Comment();
				cm.setUserName(rs.getString("username"));
				cm.setNoiDung(rs.getString("noidung"));
				cm.setNgayBinhLuan(rs.getDate("ngaybinhluan"));
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

}
