package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.CartProcess;
import form.CartForm;
import model.beans.Account;
import model.beans.CartInfo;
import model.beans.Promotion;
import model.bo.CategoryBO;
import model.bo.PromotionBO;

/**
 * Giỏ hàng - Hiển thị danh sách sản phẩm trong giỏ hàng
 * 
 * @author LamNX
 *
 */
public class ShowCartAction extends Action {
	PromotionBO promotionBO = new PromotionBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
			}
		}
		CartInfo myCart = CartProcess.getCartInSession(request);
		ActionErrors actionErrors = new ActionErrors();
		if (myCart.getCartLines().size() == 0) {
			myCart.setAmountTotal(0);
			myCart.setAmountTotalAfterUsingPromotionCode(0);
			myCart.setListOfPromotionCodes(new ArrayList<>());
			myCart.setOutOfStock(false);
		}
		CartProcess.checkQuantityOutOfStock(myCart);
		if (myCart.isOutOfStock()) {
			actionErrors.add("cartIsOutOfStockError", new ActionMessage("error.cartIsOutOfStockError"));
		}

		CartForm cartForm = (CartForm) form;
		String promotionCodeForm = cartForm.getPromotionCode();
		if (null != promotionCodeForm && 0 != promotionCodeForm.length()) {
			Promotion promotion = promotionBO.getPromotionByCode(promotionCodeForm);
			if (null != promotion) {
				if (!myCart.checkPromotionCodeHasBeenUsed(promotion)) {
					myCart.setPromotionCode(promotion);
				} else {
					actionErrors.add("promotionCodeError", new ActionMessage("error.promotionCodeError"));
				}
			} else {
				actionErrors.add("promotionCodeError", new ActionMessage("error.promotionCodeErrorNotExists"));
			}
		}
		if (myCart.getListOfPromotionCodes().size() == 0) {
			cartForm.setAmountTotal(myCart.getAmountTotal());
		} else {
			myCart.getAmountTotalUsingPromotionCode();
			cartForm.setAmountTotal(myCart.getAmountTotalAfterUsingPromotionCode());
		}
		cartForm.setCart(myCart);
		saveErrors(request, actionErrors);

		return mapping.findForward("showCart");
	}
}