package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Order;
import model.beans.OrderDetail;

public class OrderDetailDAO {
	Connection con = DataAccess.connect();
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public void saveOrderDetail(OrderDetail detail) {
		String sql = "INSERT INTO chitietdonhang VALUES(?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, detail.getBookNum());
			pstm.setString(2, detail.getOrderNum());
			pstm.setInt(3, detail.getQuantity());
			pstm.setFloat(4, detail.getPrice());

			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Save Order Detail has Error");
			e.printStackTrace();
		}
	}

	public ArrayList<OrderDetail> getListOfOrderDetailsByOrderNum(String orderNum) {
		ArrayList<OrderDetail> arr = new ArrayList<>();
		String sql = "SELECT * FROM chitietdonhang WHERE ma_dh = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, orderNum);
			rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				
				arr.add(orderDetail);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}
}
