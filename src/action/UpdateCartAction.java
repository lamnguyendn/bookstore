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

public class UpdateCartAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CartInfo cartInfo = CartProcess.getCartInSession(request);
		CartForm cartForm = (CartForm) form;
		String isbn = cartForm.getIsbn();
		int quantity = cartForm.getQuantity();
		cartInfo.updateBook(isbn, quantity);

		return mapping.findForward("updated");
	}

}