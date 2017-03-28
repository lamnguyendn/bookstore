package action;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.OrderForm;
import model.beans.Order;
import model.bo.CategoryBO;
import model.bo.OrderBO;

/**
 * Quản lý đơn hàng - Phân trang
 * 
 * @author LamNX
 *
 */
public class PaginationOrderManagementAction extends Action {
	int first = 0;
	int last = 0;
	int page = 1;
	int totalPerPage = 5;
	OrderBO orderBO = new OrderBO();
	ArrayList<Order> result = new ArrayList<>();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		OrderForm orderForm = (OrderForm) form;
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		String findKey = orderForm.getFindKey();
		byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
		findKey = new String(utf8, StandardCharsets.UTF_8);
		orderForm.setFindKey(findKey);
		if (orderForm.getPage() != 0) {
			page = orderForm.getPage();
		} else {
			page = 1;
		}
		int totalPages = pagination(findKey);
		if (null == findKey || findKey.length() == 0) {
			result = orderBO.getListOfOrdersLimit(first, last);
		} else if (null != findKey || 0 != findKey.length()) {
			result = orderBO.getListOfOrdersLimitByFindKey(first, last, findKey);
		}
		StringBuffer strResult = new StringBuffer();
		for (Order order : result) {
			strResult.append("<tr>"
					+ "<td class=\"text-right\">" + order.getCreatedDate() + "</td>" + "<td>" + order.getCustomerName() + "</td>" + "<td>"
					+ order.getCustomerPhone() + "</td>" + "<td>" + order.getCustomerAddress() + "</td>"
					+ "<td id=\"status-" + order.getOrderNum() + "\" class=\"text-center\">"
					+ "<a href=\"javascript:void(0)\" onclick=\"return setDelivery('" + order.getOrderNum() + "')\">");
			if (order.getStatus() == 1) {
				strResult.append("<i class=\"glyphicon glyphicon-ok\"></i>");
			} else if (order.getStatus() == 0) {
				strResult.append("<i class=\"glyphicon glyphicon-remove\"></i>");
			}
			strResult.append("</a>" + "</td>" + "<td class=\"text-center\">" + "<a href=\"/BookStore/viewOrderDetail.do?orderNum="
					+ order.getOrderNum() + "\" " + "class=\"glyphicon glyphicon-edit\">" + "</a>" + "</td>" + "</tr>");
		}
		response.getWriter().println(strResult.toString());
		orderForm.setTotalPages(totalPages);
		return null;
	}

	private int pagination(String findKey) {
		int total = 0;
		first = 0;
		last = 5;
		if (null == findKey || findKey.length() == 0) {
			total = orderBO.countRows();
		} else if (null != findKey || 0 != findKey.length()) {
			total = orderBO.countRowsByFindKey(findKey);
		}

		if (total <= 5) {
			first = 0;
			last = total;
		} else {
			first = (page - 1) * 5;
			last = 5;
		}

		int totalPages = 0, num = 0;

		if ((total / 5) % 2 != 0) {
			num = total / 5;
		} else {
			num = (total + 1) / 5;
		}

		if (total % 2 == 0) {
			totalPages = (total / 5) + 1;
		} else {
			if (total < (num * 5) + 5 && total != num * 5) {
				totalPages = (total / 5) + 1;
			} else {
				totalPages = (total / 5);
			}
		}
		return totalPages;
	}
}
