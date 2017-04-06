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
import model.beans.Account;
import model.beans.Order;
import model.bo.CategoryBO;
import model.bo.OrderBO;

/**
 * Quản lý đơn hàng - Hiển thị danh sách đơn hàng
 * 
 * @author LamNX
 *
 */
public class OrderManagementAction extends Action {
	OrderBO orderBO = new OrderBO();
	int first = 0, last = 0, page = 1, totalPerPage = 5;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ArrayList<Order> result = new ArrayList<>();
		CategoryBO categoryBO = new CategoryBO();
		Account account = (Account) request.getSession().getAttribute("userName");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);

				OrderForm orderForm = (OrderForm) form;
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
				if (null != result)
					orderForm.setListOfOrders(result);
				orderForm.setTotalPages(totalPages);

				return mapping.findForward("listOfOrders");
			}
		}
		return mapping.findForward("404");
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