package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.OrderDetail;

public class OrderDetailDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public void saveOrderDetail(OrderDetail detail) {
		String sql = "INSERT INTO chitietdonhang VALUES(?,?,?,?)";
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, detail.getBookNum());
			pstm.setString(2, detail.getOrderNum());
			pstm.setInt(3, detail.getQuantity());
			pstm.setDouble(4, detail.getPrice());

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

	public ArrayList<OrderDetail> getListOfOrderDetailsByOrderNum(String orderNum) {
		ArrayList<OrderDetail> arr = new ArrayList<>();
		String sql = "SELECT ctdh.ma_sach, ctdh.dongia, ctdh.soluong, s.tensach " + " FROM chitietdonhang ctdh, sach s "
				+ " WHERE ctdh.ma_dh = ? AND s.isbn = ctdh.ma_sach";
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, orderNum);
			rs = pstm.executeQuery();
			while (rs.next()) {
				OrderDetail orderDetail = new OrderDetail();
				orderDetail.setBookName(rs.getString(4));
				orderDetail.setBookNum(rs.getString(1));
				orderDetail.setPrice(rs.getFloat(2));
				orderDetail.setQuantity(rs.getInt(3));
				orderDetail.setAmount(orderDetail.getPrice() * orderDetail.getQuantity());
				arr.add(orderDetail);
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
}