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
import model.bo.CategoryBO;

/**
 * Thanh toán đơn hàng - Kiểm tra thông tin đơn hàng
 * 
 * @author LamNX
 *
 */
public class PayCartThirdStepAction extends Action {
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
			if (cartInfo.isEmpty()) {
				return mapping.findForward("showCart");
			} else if (!cartInfo.isValidCustomer()) {
				return mapping.findForward("payCartFirstStep");
			}
			CartProcess.checkQuantityOutOfStock(cartInfo);

			if (cartInfo.isOutOfStock()) {
				return mapping.findForward("showCart");
			}

			CartForm cartForm = (CartForm) form;
			cartForm.setCart(cartInfo);
			return mapping.findForward("confirmCart");
		}
		return mapping.findForward("login");
	}

}
