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
import model.bo.CategoryBO;

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
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != accountSession) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(accountSession.getRole())) {
				request.setAttribute("admin", true);
				// xoa account
				String userName = accountForm.getUserName();
				if ("submit".equals(accountForm.getSubmit())) {
					accountBO.xoaAccount(userName);
					return mapping.findForward("xoaAccXong");
				} else if (userName.equals(accountSession.getUserName())) {
					return mapping.findForward("xoaAccXong");
				} else {
					Account account = accountBO.getThongTinAccount(userName);
					accountForm.setPassWord(account.getPassword());
					accountForm.setTen(account.getTen());
					accountForm.setDiaChi(account.getDiaChi());
					accountForm.setSoDienThoai(account.getSoDienThoai());
					accountForm.setEmail(account.getEmail());
					accountForm.setQuyen(account.getRole());
					return mapping.findForward("xoaAcc");
				}
			}
		}
		return mapping.findForward("404");
	}
}
