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
import model.beans.CartInfo;
import model.beans.Promotion;
import model.bo.PromotionBO;

public class ShowCartAction extends Action {
	PromotionBO promotionBO = new PromotionBO();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CartInfo myCart = CartProcess.getCartInSession(request);
		ActionErrors actionErrors = new ActionErrors();
		if (myCart.getCartLines().size() == 0) {
			myCart.setAmountTotal(0);
			myCart.setAmountTotalAfterUsingPromotionCode(0);
			myCart.setListOfPromotionCodes(new ArrayList<>());
			myCart.setOutOfStock(false);
		}

		if (myCart.isOutOfStock()) {
			actionErrors.add("cartIsOutOfStockError", new ActionMessage("error.cartIsOutOfStockError"));
		}

		CartProcess.checkQuantityOutOfStock(myCart);

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