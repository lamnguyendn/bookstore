package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import common.DataAccess;
import model.beans.Promotion;

public class PromotionDAO {
	Connection con = DataAccess.connect();
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public Promotion getPromotionByCode(String promotionCodeForm) {
		String sql = "SELECT * from khuyenmai WHERE ma_km = '" + promotionCodeForm + "'";
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
		}
		return null;
	}

	public void addPromotionCodeIntoOrder(String promotionCode, String orderNum) {
		String sql = "INSERT INTO khuyenmai_donhang VALUES(?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, promotionCode);
			pstm.setString(2, orderNum);

			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Save Order Detail has Error");
			e.printStackTrace();
		}
	}

}
