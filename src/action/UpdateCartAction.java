package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CartProcess;
import form.CartForm;
import model.beans.CartInfo;
import model.bo.CategoryBO;

/**
 * Giỏ hàng - Cập nhật sản phẩm trong giỏ hàng
 * 
 * @author LamNX
 *
 */
public class UpdateCartAction extends Action {
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CartInfo cartInfo = CartProcess.getCartInSession(request);
		CartForm cartForm = (CartForm) form;
		cartInfo.updateBook(cartForm.getIsbn(), cartForm.getQuantity());
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		return mapping.findForward("updated");
	}

}