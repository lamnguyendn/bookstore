package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.LoginForm;
import model.beans.Account;
import model.bo.AccountBO;

public class CheckLoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LoginForm loginForm = (LoginForm) form;
		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		AccountBO accountBO = new AccountBO();

		Account account = accountBO.checkLogin(userName, password);
		if (null != account) {
			request.setAttribute("logged", true);
			request.getSession().setAttribute("userName", account);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole()))
				return mapping.findForward("loginAdminSuccess");
			else if ("ROLE_USER".equalsIgnoreCase(account.getRole()))
				return mapping.findForward("loginUserSuccess");
		}
		loginForm.setMessage("Username or password incorrect!");
		return mapping.findForward("loginFailed");
	}

}