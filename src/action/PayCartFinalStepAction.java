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

/**
 * Thanh toán đơn hàng
 * 
 * @author LamNX
 *
 */
public class PayCartFinalStepAction extends Action {
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
			CartInfo cartInfo = CartProcess.getCartInSession(request);
			if (cartInfo.isEmpty()) {
				return mapping.findForward("cartIsEmpty");
			}
			CartProcess.checkQuantityOutOfStock(cartInfo);

			if (cartInfo.isOutOfStock()) {
				return mapping.findForward("showCart");
			}
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
			// Remove Cart In Session.
			CartProcess.removeCartInSession(request);

			// Store Last ordered cart to Session.
			CartProcess.storeLastOrderedCartInSession(request, cartInfo);
			return mapping.findForward("payCartFinalStep");
		}
		return null;
	}
}
