package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CartProcess;
import model.beans.Account;
import model.beans.CartInfo;
import model.bo.CategoryBO;
import model.bo.OrderBO;

/**
 * Thanh toán đơn hàng - bước xác nhận đơn hàng
 * 
 * @author LamNX
 *
 */
public class PayCartFourthStepAction extends Action {
	OrderBO orderBO = new OrderBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			CartInfo cartInfo = CartProcess.getCartInSession(request);
			// Cart have no products.
			if (cartInfo.isEmpty()) {
				// Redirect to shoppingCart page.
				return mapping.findForward("showCart");
			} else if (!cartInfo.isValidCustomer()) {
				// Enter customer info.
				return mapping.findForward("payCartFirstStep");
			}
			CartProcess.checkQuantityOutOfStock(cartInfo);

			if (cartInfo.isOutOfStock()) {
				return mapping.findForward("showCart");
			}

			try {
				orderBO.saveOrder(cartInfo, request);
			} catch (Exception e) {
				// Need: Propagation.NEVER?
				return mapping.findForward("payCartThirdStep");
			}

			// Redirect to successful page.
			return mapping.findForward("payCartFinalStep");
		}
		return mapping.findForward("login");
	}

}
