package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Account;

public class AccountDAO {
	DataAccess da = new DataAccess();

	public Account checkLogin(String userName, String password) {
		Account account = new Account();
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		PreparedStatement pstm = null;

		String sql = "SELECT * FROM taikhoan WHERE username =  ? and password = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, password);
			rs = pstm.executeQuery();
			while (rs.next()) {
				account.setUserName(rs.getString("username"));
				account.setRole(rs.getString("role"));
				account.setTen(rs.getString("ten"));
				return account;
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

		return null;
	}

	/**
	 * Kiem tra tai khoan da ton tai chua
	 * 
	 * @param userName
	 * @return
	 * @throws SQLException
	 */
	public boolean checkUserName(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT username FROM taikhoan WHERE username = '%s'", userName);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (rs.next()) {
				return true;
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
		return false;
	}

	public void themAccount(String userName, String passWord, String ten, String soDienThoai, String diaChi,
			String email, String role) {
		Connection con = DataAccess.connect();
		String sql = String.format(
				"INSERT INTO taikhoan (username, password,ten, sodienthoai, diachi, email, role) "
						+ " VALUES ( '%s',N'%s',N'%s','%s', N'%s', N'%s', N'%s' )",
				userName, passWord, ten, soDienThoai, diaChi, email, role);
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

	/**
	 * kiem tra dinh dang so dien thoai
	 * 
	 * @param phone
	 * @return
	 */
	public boolean checkPhone(String phone) {
		for (int i = 0; i < phone.length(); i++) {
			if (Character.isLetter(phone.charAt(i))) {
				return false;
			}
			if (i + 1 == phone.length()) {
				if (phone.length() == 10 || phone.length() == 11) {
					if (phone.length() == 10) {
						if (phone.substring(0, 2).equals("09")) {
							return true;
						} else {
							return false;
						}
					} else if (phone.substring(0, 2).equals("01") || phone.substring(0, 2).equals("05")) {
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * kiem tra dinh dang email
	 * 
	 * @param email
	 * @return
	 */
	public boolean checkEmail(String email) {
		String EMAIL_REGEX = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Boolean b = email.matches(EMAIL_REGEX);
		return b;
	}

	public ArrayList<Account> getListAccount() {
		Connection con = DataAccess.connect();
		String sql = "SELECT username, password, ten, sodienthoai, diachi, email, role"
				+ " FROM  taikhoan ORDER BY username";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Account> list = new ArrayList<Account>();
		Account account;
		try {
			while (rs.next()) {
				account = new Account();
				account.setUserName(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setTen(rs.getString("ten"));
				account.setSoDienThoai(rs.getString("sodienthoai"));
				account.setDiaChi(rs.getString("diachi"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("role"));
				list.add(account);
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
		return list;
	}

	public ArrayList<Account> getListAccount(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT username, password, ten, sodienthoai, diachi, email, role"
				+ " FROM taikhoan WHERE username = '%s'", userName);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Account> list = new ArrayList<Account>();
		Account account;
		try {
			while (rs.next()) {
				account = new Account();
				account.setUserName(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setTen(rs.getString("ten"));
				account.setSoDienThoai(rs.getString("sodienthoai"));
				account.setDiaChi(rs.getString("diachi"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("role"));
				list.add(account);
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
		return list;
	}

	public void suaAccount(String userName, String passWord, String ten, String soDienThoai, String diaChi,
			String email, String role) {
		Connection con = DataAccess.connect();
		String sql = String.format("UPDATE taikhoan "
				+ " SET password = %s, ten = N'%s', sodienthoai ='%s', diachi = N'%s', email='%s', role='%s' "
				+ " WHERE username = '%s'", passWord, ten, soDienThoai, diaChi, email, role, userName);
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

	public void xoaAccount(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("DELETE FROM taikhoan WHERE username = '%s'", userName);
		System.out.println(sql);
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

	public Account getThongTinAccount(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT username, password, ten, sodienthoai, diachi, email, role "
				+ " FROM taikhoan WHERE username = '%s'", userName);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Account account = new Account();
		try {
			while (rs.next()) {
				account.setUserName(rs.getString("username"));
				account.setPassword(rs.getString("password"));
				account.setTen(rs.getString("ten"));
				account.setSoDienThoai(rs.getString("sodienthoai"));
				account.setDiaChi(rs.getString("diachi"));
				account.setEmail(rs.getString("email"));
				account.setRole(rs.getString("role"));
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
		return account;
	}

}