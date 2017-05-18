package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataAccess;
import common.DataBaseException;
import model.beans.Promotion;

public class PromotionDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public Promotion getPromotionByCode(String promotionCodeForm) {
		Connection con = DataAccess.connect();
		String sql = "SELECT * from khuyenmai WHERE ma_km = '" + promotionCodeForm + "' AND trangthai = 1";
		Promotion promotion = new Promotion();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				promotion.setMaKM(rs.getString("ma_km"));
				promotion.setPhanTramKM(rs.getFloat("phantram_km"));
				return promotion;
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

		return null;
	}

	public void addPromotionCodeIntoOrder(String promotionCode, String orderNum) {
		String sql = "INSERT INTO khuyenmai_donhang VALUES(?,?)";
		Connection con = DataAccess.connect();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, promotionCode);
			pstm.setString(2, orderNum);

			pstm.executeUpdate();
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

	public ArrayList<Promotion> getListKhuyenMai() {
		String sql = "SELECT ma_km, ten_km, phantram_km, trangthai FROM khuyenmai";
		ResultSet rs = null;
		ArrayList<Promotion> list = new ArrayList<Promotion>();
		Connection con = DataAccess.connect();
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Promotion km = new Promotion();
				km.setMaKM(rs.getString("ma_km"));
				km.setTenKM(rs.getString("ten_km"));
				km.setPhanTramKM(Float.parseFloat(rs.getString("phantram_km")));
				km.setTrangThai(rs.getInt("trangthai"));
				list.add(km);
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

	public void themKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai, HttpServletRequest request) throws DataBaseException {
		String sql = String.format(
				"INSERT INTO khuyenmai(ma_km,ten_km,phantram_km,trangthai) " + " VALUES ( '%s',N'%s','%s','%s' )", maKm,
				tenKm, phanTramKm, trangThai);
		Connection con = DataAccess.connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Promotion getThongTinKhuyenMai(String maKm) {
		String sql = "Select * from khuyenmai where ma_km = '" + maKm + "'";
		ResultSet rs = null;
		Promotion km = new Promotion();
		Connection con = DataAccess.connect();
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				km = new Promotion();
				km.setMaKM(rs.getString("ma_km"));
				km.setTenKM(rs.getString("ten_km"));
				km.setPhanTramKM(Float.parseFloat(rs.getString("phantram_km")));
				km.setTrangThai(rs.getInt("trangthai"));
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
		return km;
	}

	public void suaKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai, HttpServletRequest request) throws DataBaseException {
		String sql = String.format("UPDATE khuyenmai " + " SET ten_km = N'%s', phantram_km = '%s',trangthai = '%s'"
				+ " WHERE ma_km = '%s'", tenKm, phanTramKm, trangThai, maKm);
		Connection con = DataAccess.connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getTrangThaiKm(String maKm) {
		String sql = "SELECT * FROM khuyenmai WHERE ma_km = ?";
		Connection con = DataAccess.connect();
		int trangThai = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, maKm);
			rs = pstm.executeQuery();
			while (rs.next()) {
				trangThai = rs.getInt("trangthai");
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
		return trangThai;
	}

	public void suaTrangThaiKm(String maKm, int trangThai, HttpServletRequest request) throws DataBaseException {
		String sql = "UPDATE khuyenmai SET trangthai = ? WHERE ma_km = ?";
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, trangThai);
			pstm.setString(2, maKm);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public void xoaKhuyenMai(String maKm, HttpServletRequest request) throws DataBaseException {
		String sql = String.format("DELETE FROM khuyenmai WHERE ma_km = '%s'", maKm);
		Connection con = DataAccess.connect();
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataBaseException(request);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public boolean duplicateId(String s) {
		boolean result = true;
		String sql = "Select ma_km from khuyenmai where ma_km = '" + s + "'";
		Connection con = DataAccess.connect();
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
}
