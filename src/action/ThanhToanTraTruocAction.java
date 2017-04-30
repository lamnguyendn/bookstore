package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.CartProcess;
import common.PayPal;
import common.StringProcess;
import model.beans.Account;
import model.beans.CartInfo;
import model.bo.CategoryBO;
import model.bo.OrderBO;

public class ThanhToanTraTruocAction extends Action {
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
			request.setAttribute("logged", true);
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
			// xu ly thanh toan tra truoc
			try {
				double total = 0;
				if (cartInfo.getListOfPromotionCodes().size() == 0) {
					total = cartInfo.getAmountTotal();
				} else {
					total = cartInfo.getAmountTotalAfterUsingPromotionCode();
				}
				String result = PayPal.payPal(account.getUserName(), total);
				if (result.contains("The balance is not enough to make a transaction")) {
					request.getSession().setAttribute("balanceNotEnough", "true");
					return mapping.findForward("payCartThirdStep");
				} else if (result.contains("does not exists")) {
					request.getSession().setAttribute("notExists", "true");
					return mapping.findForward("payCartThirdStep");
				} else if (result.contains("Successfully transacted")) {
					orderBO.saveOrderTraTruoc(cartInfo, request);
				}
			} catch (Exception e) {
				e.printStackTrace();
				return mapping.findForward("payCartThirdStep");
			}
			return mapping.findForward("payCartFinalStep");
		}
		return mapping.findForward("login");
	}

}
