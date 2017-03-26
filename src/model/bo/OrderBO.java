package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import model.beans.CartInfo;
import model.beans.Order;
import model.dao.OrderDAO;

public class OrderBO {
	OrderDAO orderDAO = new OrderDAO();

	public void saveOrder(CartInfo cartInfo, HttpServletRequest request) {
		orderDAO.saveOrder(cartInfo, request);
	}

	public int getStatusOrder(String orderNum) {
		return orderDAO.getStatusOrder(orderNum);
	}

	public void updateOrder(String orderNum, int status) {
		orderDAO.updateOrder(orderNum, status);
	}

	public ArrayList<Order> getListOfOrders() {
		return orderDAO.getListOfOrders();
	}

	public Order findOrderByOrderNum(String orderNum) {
		return orderDAO.findOrderByOrderNum(orderNum);
	}

}
