package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import common.DataAccess;
import common.DataBaseException;
import common.ThanhToanException;
import form.CustomerForm;
import model.beans.Account;
import model.beans.CartInfo;
import model.beans.CartLineInfo;
import model.beans.Order;
import model.beans.OrderDetail;
import model.beans.Promotion;

public class OrderDAO {
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	BookDAO bookDAO = new BookDAO();
	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
	PromotionDAO promotionDAO = new PromotionDAO();

	public void saveOrderTraSau(CartInfo cartInfo, HttpServletRequest request) throws ThanhToanException {
		Order order = new Order();
		Account account = (Account) request.getSession().getAttribute("userName");

		order.setOrderNum(UUID.randomUUID().toString());
		order.setCreatedDate(new Date());
		if (cartInfo.getListOfPromotionCodes().size() == 0) {
			order.setTotal(cartInfo.getAmountTotal());
		} else {
			order.setTotal(cartInfo.getAmountTotalAfterUsingPromotionCode());
		}
		order.setUserName(account.getUserName());
		order.setStatus(0);

		CustomerForm customerForm = cartInfo.getCustomerInfo();
		order.setCustomerName(customerForm.getName());
		order.setCustomerEmail(customerForm.getEmail());
		order.setCustomerPhone(customerForm.getPhone());
		order.setCustomerAddress(customerForm.getAddress());

		saveOrder(order);

		for (Promotion promotion : cartInfo.getListOfPromotionCodes()) {
			promotionDAO.addPromotionCodeIntoOrder(promotion.getMaKM(), order.getOrderNum());
		}

		for (CartLineInfo line : cartInfo.getCartLines()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrderNum(order.getOrderNum());
			detail.setPrice(line.getBook().getPrice());
			detail.setQuantity(line.getQuantity());
			String isbn = line.getBook().getIsbn();
			detail.setBookNum(isbn);

			orderDetailDAO.saveOrderDetail(detail, request);
		}
	}

	public void saveOrderTraTruoc(CartInfo cartInfo, HttpServletRequest request) throws ThanhToanException {
		Order order = new Order();
		Account account = (Account) request.getSession().getAttribute("userName");

		order.setOrderNum(UUID.randomUUID().toString());
		order.setCreatedDate(new Date());
		if (cartInfo.getListOfPromotionCodes().size() == 0) {
			order.setTotal(cartInfo.getAmountTotal());
		} else {
			order.setTotal(cartInfo.getAmountTotalAfterUsingPromotionCode());
		}
		order.setUserName(account.getUserName());
		order.setStatus(1);

		CustomerForm customerForm = cartInfo.getCustomerInfo();
		order.setCustomerName(customerForm.getName());
		order.setCustomerEmail(customerForm.getEmail());
		order.setCustomerPhone(customerForm.getPhone());
		order.setCustomerAddress(customerForm.getAddress());

		saveOrder(order);

		for (Promotion promotion : cartInfo.getListOfPromotionCodes()) {
			promotionDAO.addPromotionCodeIntoOrder(promotion.getMaKM(), order.getOrderNum());
		}

		for (CartLineInfo line : cartInfo.getCartLines()) {
			OrderDetail detail = new OrderDetail();
			detail.setOrderNum(order.getOrderNum());
			detail.setPrice(line.getBook().getPrice());
			detail.setQuantity(line.getQuantity());
			String isbn = line.getBook().getIsbn();
			detail.setBookNum(isbn);

			orderDetailDAO.saveOrderDetail(detail, request);
		}
	}

	private void saveOrder(Order order) {
		String sql = "INSERT INTO donhang VALUES(?,?,?,?,?,?,?,?,?)";
		Connection con = DataAccess.connect();

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, order.getOrderNum());
			pstm.setDate(2, new java.sql.Date(order.getCreatedDate().getTime()));
			pstm.setDouble(3, order.getTotal());
			pstm.setInt(4, order.getStatus());
			pstm.setString(5, order.getCustomerName());
			pstm.setString(6, order.getCustomerPhone());
			pstm.setString(7, order.getCustomerAddress());
			pstm.setString(8, order.getCustomerEmail());
			pstm.setString(9, order.getUserName());

			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Save order has Error");
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int getStatusOrder(String orderNum) {
		String sql = "SELECT * FROM donhang WHERE ma_dh = ?";
		Connection con = DataAccess.connect();

		int status = 0;
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, orderNum);
			rs = pstm.executeQuery();
			while (rs.next()) {
				status = rs.getInt("trangthai");
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
		return status;
	}

	public void updateOrder(String orderNum, int status, HttpServletRequest request) throws DataBaseException {
		String sql = "UPDATE donhang SET trangthai = ? WHERE ma_dh = ?";
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, status);
			pstm.setString(2, orderNum);
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

	public ArrayList<Order> getListOfOrders() {
		ArrayList<Order> arr = new ArrayList<>();
		String sql = "SELECT * FROM donhang";
		Connection con = DataAccess.connect();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setCreatedDate(rs.getDate("ngaytao"));
				order.setCustomerAddress(rs.getString("diachi"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerName(rs.getString("ten"));
				order.setCustomerPhone(rs.getString("sodienthoai"));
				order.setOrderNum(rs.getString("ma_dh"));
				order.setStatus(rs.getInt("trangthai"));
				order.setTotal(rs.getDouble("tongtien"));

				arr.add(order);
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

	public Order findOrderByOrderNum(String orderNum) {
		String sql = "SELECT * FROM donhang WHERE ma_dh = ?";
		Connection con = DataAccess.connect();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, orderNum);
			rs = pstm.executeQuery();
			while (rs.next()) {
				Order order = new Order();
				order.setCreatedDate(rs.getDate("ngaytao"));
				order.setCustomerAddress(rs.getString("diachi"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerName(rs.getString("ten"));
				order.setCustomerPhone(rs.getString("sodienthoai"));
				order.setOrderNum(rs.getString("ma_dh"));
				order.setStatus(rs.getInt("trangthai"));
				order.setTotal(rs.getDouble("tongtien"));

				return order;
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

	public ArrayList<Order> getListOfOrdersLimit(int first, int last) {
		ArrayList<Order> arr = new ArrayList<>();
		String sql = "SELECT * FROM donhang" + " LIMIT " + first + " ," + last + " ; ";
		Connection con = DataAccess.connect();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setCreatedDate(rs.getDate("ngaytao"));
				order.setCustomerAddress(rs.getString("diachi"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerName(rs.getString("ten"));
				order.setCustomerPhone(rs.getString("sodienthoai"));
				order.setOrderNum(rs.getString("ma_dh"));
				order.setStatus(rs.getInt("trangthai"));
				order.setTotal(rs.getDouble("tongtien"));
				order.setUserName(rs.getString("username"));

				arr.add(order);
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

	public ArrayList<Order> getListOfOrdersLimitByFindKey(int first, int last, String findKey) {
		ArrayList<Order> arr = new ArrayList<>();
		String sql = "SELECT * FROM donhang WHERE ten LIKE N'%" + findKey + "%' OR username LIKE N'%" + findKey + "%'"
				+ " LIMIT " + first + " ," + last + " ;";
		Connection con = DataAccess.connect();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Order order = new Order();
				order.setCreatedDate(rs.getDate("ngaytao"));
				order.setCustomerAddress(rs.getString("diachi"));
				order.setCustomerEmail(rs.getString("email"));
				order.setCustomerName(rs.getString("ten"));
				order.setCustomerPhone(rs.getString("sodienthoai"));
				order.setOrderNum(rs.getString("ma_dh"));
				order.setStatus(rs.getInt("trangthai"));
				order.setTotal(rs.getDouble("tongtien"));
				order.setUserName(rs.getString("username"));

				arr.add(order);
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

	public int countRowsByFindKey(String findKey) {
		String sql = "SELECT count(ma_dh) FROM donhang WHERE ten LIKE N'%" + findKey + "%' OR username LIKE N'%"
				+ findKey + "%';";
		Connection con = DataAccess.connect();
		int count = 0;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

	public int countRows() {
		String sql = "SELECT count(ma_dh) FROM donhang";
		Connection con = DataAccess.connect();
		int count = 0;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
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
		return count;
	}

}
