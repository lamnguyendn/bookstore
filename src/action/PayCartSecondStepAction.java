package action;

import java.nio.charset.StandardCharsets;

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

/**
 * get information from customerForm of customer
 * 
 * @author HCD-Fresher157
 *
 */
public class PayCartSecondStepAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			CartInfo cartInfo = CartProcess.getCartInSession(request);
			CustomerForm customerForm = (CustomerForm) form;
			String name = customerForm.getName();
			byte[] utf8 = name.getBytes(StandardCharsets.UTF_8);
			name = new String(utf8, StandardCharsets.UTF_8);
			customerForm.setName(name);
			// check information customer here
			// validate -> has error -> forward('payCartFirstStep')
			// else
			cartInfo.setCustomerInfo(customerForm);
			return mapping.findForward("payCartThirdStep");
		}
		return mapping.findForward("login");
	}
}