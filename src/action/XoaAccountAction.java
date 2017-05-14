package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.AccountForm;
import model.beans.Account;
import model.bo.AccountBO;

/**
 * XoaAccountAction.java
 *
 * Version 1.0
 *
 * Date: Mar 1, 2017
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- Mar
 * 1, 2017 LaNP Create
 */

public class XoaAccountAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		request.setCharacterEncoding("utf-8");
		AccountForm accountForm = (AccountForm) form;
		AccountBO accountBO = new AccountBO();
		Account accountSession = (Account) request.getSession().getAttribute("userName");
		if (null != accountSession) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(accountSession.getRole())) {
				request.setAttribute("admin", true);
				String userName = accountForm.getUserName();
				if (userName.equals(accountSession.getUserName())) {
				} else {
					accountBO.xoaAccount(userName, request);
				}
				return mapping.findForward("xoaAccXong");
			}
		}
		return mapping.findForward("404");
	}
}
