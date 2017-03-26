package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.DataAccess;
import model.beans.Account;

public class AccountDAO {
	DataAccess da = new DataAccess();
	Connection con;
	PreparedStatement pstm;
	Statement stm;

	public Account checkLogin(String userName, String password) {
		Account account = new Account();
		con = DataAccess.connect();
		ResultSet rs;
		String sql = "SELECT * FROM taikhoan WHERE username =  ? and password = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			while (rs.next()) {
				account.setUserName(rs.getString("username"));
				account.setRole(rs.getString("role"));
				return account;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

}