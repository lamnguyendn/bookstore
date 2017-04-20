package action;

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
import model.bo.OrderDetailBO;

/**
 * Quản lý đơn hàng - Hiển thị chi tiết thông tin đơn hàng
 * 
 * @author LamNX
 *
 */
public class ViewOrderDetailAction extends Action {
	OrderBO orderBO = new OrderBO();
	OrderDetailBO orderDetailBO = new OrderDetailBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				request.setAttribute("management", "OrderDetail");
				
				OrderForm orderForm = (OrderForm) form;
				request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
				String orderNum = orderForm.getOrderNum();
				orderForm.setOrder(orderBO.findOrderByOrderNum(orderNum));
				orderForm.setListOfOrderDetails(orderDetailBO.getListOfOrderDetailsByOrderNum(orderNum));

				return mapping.findForward("view");
			}
		}
		return mapping.findForward("404");
	}

}