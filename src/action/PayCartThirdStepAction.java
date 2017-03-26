package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import common.CartProcess;
import form.CartForm;
import model.beans.Account;
import model.beans.CartInfo;

/**
 * confirm cart
 * 
 * @author HCD-Fresher157
 *
 */
public class PayCartThirdStepAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			CartInfo myCart = CartProcess.getCartInSession(request);
			if (myCart.isEmpty()) {
				return mapping.findForward("showCart");
			} else if (!myCart.isValidCustomer()) {
				return mapping.findForward("payCartFirstStep");
			}

			CartProcess.checkQuantityOutOfStock(myCart);

			if (myCart.isOutOfStock()) {
				return mapping.findForward("showCart");
			}

			CartForm cartForm = (CartForm) form;
			if (myCart.getListOfPromotionCodes().size() == 0)
				cartForm.setAmountTotal(myCart.getAmountTotal());
			else
				cartForm.setAmountTotal(myCart.getAmountTotalAfterUsingPromotionCode());
			cartForm.setCart(myCart);
			return mapping.findForward("confirmCart");
		}
		return mapping.findForward("login");
	}
}