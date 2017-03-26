package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.beans.Order;
import model.beans.OrderDetail;

@SuppressWarnings("serial")
public class OrderForm extends ActionForm {
	private ArrayList<Order> listOfOrders;
	private ArrayList<OrderDetail> listOfOrderDetails;
	private Order order;
	
	private int page;
	private int totalPages;
	private String findKey = "";

	private String orderNum;

	public ArrayList<Order> getListOfOrders() {
		return listOfOrders;
	}

	public void setListOfOrders(ArrayList<Order> listOfOrders) {
		this.listOfOrders = listOfOrders;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getFindKey() {
		return findKey;
	}

	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public ArrayList<OrderDetail> getListOfOrderDetails() {
		return listOfOrderDetails;
	}

	public void setListOfOrderDetails(ArrayList<OrderDetail> listOfOrderDetails) {
		this.listOfOrderDetails = listOfOrderDetails;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
