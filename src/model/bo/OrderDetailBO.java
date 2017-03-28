package model.bo;

import java.util.ArrayList;

import model.beans.OrderDetail;
import model.dao.OrderDetailDAO;

public class OrderDetailBO {
	OrderDetailDAO orderDetailDAO = new OrderDetailDAO();

	public ArrayList<OrderDetail> getListOfOrderDetailsByOrderNum(String orderNum) {
		return orderDetailDAO.getListOfOrderDetailsByOrderNum(orderNum);
	}
}