package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CartProcess;
import form.CustomerForm;
import model.beans.Account;
import model.beans.CartInfo;
import model.bo.CategoryBO;

/**
 * Thanh toán đơn hàng - Xác nhận thông tin khách hàng
 * 
 * @author LamNX
 *
 */
public class PayCartSecondStepAction extends Action {
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
			CustomerForm customerForm = (CustomerForm) form;
			// check information customer here
			// validate -> has error -> forward('payCartFirstStep')
			// else
			cartInfo.setCustomerInfo(customerForm);
			return mapping.findForward("payCartThirdStep");
		}
		return mapping.findForward("login");
	}

}
