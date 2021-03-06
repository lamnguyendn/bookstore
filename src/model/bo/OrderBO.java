package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataBaseException;
import common.ThanhToanException;
import model.beans.CartInfo;
import model.beans.Order;
import model.dao.OrderDAO;

public class OrderBO {
	OrderDAO orderDAO = new OrderDAO();

	public void saveOrderTraTruoc(CartInfo cartInfo, HttpServletRequest request) throws ThanhToanException {
		orderDAO.saveOrderTraTruoc(cartInfo, request);
	}
	public void saveOrderTraSau(CartInfo cartInfo, HttpServletRequest request) throws ThanhToanException {
		orderDAO.saveOrderTraSau(cartInfo, request);
	}

	public int getStatusOrder(String orderNum) {
		return orderDAO.getStatusOrder(orderNum);
	}

	public void updateOrder(String orderNum, int status, HttpServletRequest request) throws DataBaseException {
		orderDAO.updateOrder(orderNum, status, request);
	}

	public ArrayList<Order> getListOfOrders() {
		return orderDAO.getListOfOrders();
	}

	public Order findOrderByOrderNum(String orderNum) {
		return orderDAO.findOrderByOrderNum(orderNum);
	}

	public int countRows() {
		return orderDAO.countRows();
	}

	public int countRowsByFindKey(String findKey) {
		return orderDAO.countRowsByFindKey(findKey);
	}

	public ArrayList<Order> getListOfOrdersLimit(int first, int last) {
		return orderDAO.getListOfOrdersLimit(first,last);
	}

	public ArrayList<Order> getListOfOrdersLimitByFindKey(int first, int last, String findKey) {
		return orderDAO.getListOfOrdersLimitByFindKey(first,last,findKey);
	}

}