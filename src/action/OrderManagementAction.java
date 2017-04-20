package action;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.OrderForm;
import model.beans.Account;
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
		CategoryBO categoryBO = new CategoryBO();
		Account account = (Account) request.getSession().getAttribute("userName");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				request.setAttribute("management", "OrderManagement");

				OrderForm orderForm = (OrderForm) form;
				String findKey = orderForm.getFindKey();
				byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
				findKey = new String(utf8, StandardCharsets.UTF_8);
				orderForm.setFindKey(findKey);

				orderForm.setListOfOrders(orderBO.getListOfOrders());

				return mapping.findForward("listOfOrders");
			}
		}
		return mapping.findForward("404");
	}

}