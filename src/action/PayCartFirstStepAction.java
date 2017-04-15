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
 * Thêm/Sửa thông tin khách hàng
 * 
 * @author LamNX
 *
 */
public class PayCartFirstStepAction extends Action {
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
				return mapping.findForward("cartIsEmpty");
			}
			CartProcess.checkQuantityOutOfStock(cartInfo);

			if (cartInfo.isOutOfStock()) {
				return mapping.findForward("showCart");
			}

			// redirect form customer's information
			CustomerForm customerForm = (CustomerForm) form;
			String edit = customerForm.getEdit();
			CustomerForm customerInfo = cartInfo.getCustomerInfo();
			if (customerInfo == null) {
				return mapping.findForward("customer");
			}
			if ("1".equalsIgnoreCase(edit)) {
				customerForm.setAddress(customerInfo.getAddress());
				customerForm.setEmail(customerInfo.getEmail());
				customerForm.setName(customerInfo.getName());
				customerForm.setPhone(customerInfo.getPhone());
				return mapping.findForward("customer");
			}
			return mapping.findForward("payCartThirdStep");
		}
		return mapping.findForward("login");
	}

}
