package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.LoginForm;
import model.beans.Account;
import model.bo.AccountBO;

/**
 * Kiá»ƒm tra Ä‘Äƒng nháº­p
 * 
 * @author HCD-Fresher157
 *
 */
public class CheckLoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		LoginForm loginForm = (LoginForm) form;
		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		AccountBO accountBO = new AccountBO();
		if ("Đăng nhập".equals(loginForm.getBtnSubmit())) {
			ActionErrors actionErrors = new ActionErrors();
			if (StringProcess.notValid(userName)) {
				actionErrors.add("userNameError", new ActionMessage("error.userName"));
			}
			if (StringProcess.notValid(password)) {
				actionErrors.add("passwordError", new ActionMessage("error.password"));
			}
			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				request.getSession().setAttribute("errorLogin", "true");
				return mapping.findForward("loginFailed");
			}
		}
		Account account = accountBO.checkLogin(userName, password);
		if (null != account) {
			request.getSession().setAttribute("logged", "true");
			request.getSession().setAttribute("userName", account);
			request.getSession().setAttribute("ten", account.getTen());
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				return mapping.findForward("loginAdminSuccess");
			} else if ("ROLE_USER".equalsIgnoreCase(account.getRole())) {
				return mapping.findForward("loginUserSuccess");
			}
		}
		loginForm.setMessage("Đăng nhập không thành công!");
		request.getSession().setAttribute("errorLogin", "true");
		return mapping.findForward("loginFailed");
	}

}