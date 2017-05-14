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

import common.PasswordEncoder;
import common.StringProcess;
import form.AccountForm;
import model.beans.Account;
import model.bo.AccountBO;
import model.bo.CategoryBO;

/**
 * ThemAccountAction.java
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

public class ThemAccountAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		request.setCharacterEncoding("utf-8");
		AccountForm accountForm = (AccountForm) form;
		AccountBO accountBO = new AccountBO();
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				request.setAttribute("management", "AddAccount");

				// them account
				if ("Thêm".equals(accountForm.getSubmit())) {
					ActionErrors actionErrors = new ActionErrors();
					if (StringProcess.notValid(accountForm.getUserName())) {
						actionErrors.add("userError", new ActionMessage("error.username.trong"));
					} else if (accountBO.checkUserName(accountForm.getUserName())) {
						actionErrors.add("userError", new ActionMessage("error.username.zo"));
					}
					if (!accountForm.getPassWord().equals(accountForm.getPassWord1())) {
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
						return mapping.findForward("themAccError");
					}
				}
				if ("Thêm".equals(accountForm.getSubmit())) {
					String userName = accountForm.getUserName();
					String passWord = PasswordEncoder.createHash(accountForm.getPassWord());
					String ten = accountForm.getTen();
					String soDienThoai = accountForm.getSoDienThoai();
					String diaChi = accountForm.getDiaChi();
					String email = accountForm.getEmail();
					String quyen = accountForm.getQuyen();
					accountBO.themAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen, request);
					return mapping.findForward("themAccXong");
				} else {
					return mapping.findForward("themAcc");
				}
			}
		}
		return mapping.findForward("404");
	}
}
