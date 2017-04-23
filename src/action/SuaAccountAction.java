package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import form.AccountForm;
import model.beans.Account;
import model.bo.AccountBO;
import model.bo.CategoryBO;

/**
 * SuaAccountAction.java
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

public class SuaAccountAction extends Action {
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
				request.setAttribute("management", "EditAccount");

				// sua account
				String userName = accountForm.getUserName();
				if ("Sửa".equals(accountForm.getSubmit())) {
					ActionErrors actionErrors = new ActionErrors();
					if (!accountForm.getPassWord().equals(accountForm.getPassWord())) {
						actionErrors.add("passError", new ActionMessage("error.password.trong"));
					}
					if (!accountBO.checkPhone(accountForm.getSoDienThoai())) {
						actionErrors.add("phoneError", new ActionMessage("error.phone.trong"));
					}
					if (!accountBO.checkEmail(accountForm.getEmail())) {
						actionErrors.add("emailError", new ActionMessage("error.email.trong"));
					}
					saveErrors(request, actionErrors);
					if (actionErrors.size() > 0) {
						return mapping.findForward("suaAcc");
					}
				}
				if ("Sửa".equals(accountForm.getSubmit())) {
					String passWord = accountForm.getPassWord();
					String ten = accountForm.getTen();
					String soDienThoai = accountForm.getSoDienThoai();
					String diaChi = accountForm.getDiaChi();
					String email = accountForm.getEmail();
					String quyen = accountForm.getQuyen();
					accountBO.suaAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen);
					return mapping.findForward("suaAccXong");
				} else {
					Account account = accountBO.getThongTinAccount(userName);
					accountForm.setPassWord(account.getPassword());
					accountForm.setTen(account.getTen());
					accountForm.setSoDienThoai(account.getSoDienThoai());
					accountForm.setDiaChi(account.getDiaChi());
					accountForm.setEmail(account.getEmail());
					accountForm.setQuyen(account.getRole());
					return mapping.findForward("suaAcc");
				}
			}
		}
		return mapping.findForward("404");
	}
}
