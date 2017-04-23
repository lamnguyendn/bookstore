package action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.AccountForm;
import model.bo.AccountBO;

/**
 * DangKyAccountAction.java
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

public class DangKyAccountAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		request.setCharacterEncoding("utf-8");

		AccountForm accountForm = (AccountForm) form;
		HttpSession session = request.getSession();
		AccountBO accountBO = new AccountBO();
		if (session.getAttribute("quyen") == "khachhang") {
			return mapping.findForward("dangNhapRoi");
		}
		if ("Đăng ký".equals(accountForm.getSubmit())) {
			ActionErrors actionErrors = new ActionErrors();
			if (StringProcess.notValid(accountForm.getUserName())) {
				actionErrors.add("userError", new ActionMessage("error.username.trong"));
			} else if (accountBO.checkUserName(accountForm.getUserName())) {
				actionErrors.add("userError", new ActionMessage("error.username.zo"));
			} else if (!accountForm.getPassWord().equals(accountForm.getPassWord1())) {
				actionErrors.add("passError", new ActionMessage("error.password.trong"));
			} else if (!accountBO.checkPhone(accountForm.getSoDienThoai())) {
				actionErrors.add("phoneError", new ActionMessage("error.phone.trong"));
			} else if (!accountBO.checkEmail(accountForm.getEmail())) {
				actionErrors.add("emailError", new ActionMessage("error.email.trong"));
			}
			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				request.getSession().setAttribute("errorRegistration", "true");
				return mapping.findForward("dkAccError");
			}
		}
		if ("Đăng ký".equals(accountForm.getSubmit())) {
			String userName = accountForm.getUserName();
			String passWord = accountForm.getPassWord();
			String ten = accountForm.getTen();
			String soDienThoai = accountForm.getSoDienThoai();
			String diaChi = accountForm.getDiaChi();
			String email = accountForm.getEmail();
			String quyen = "ROLE_USER";
			accountBO.themAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen);
			request.getSession().setAttribute("dkx", "#modalLogin");
			return mapping.findForward("dkAccXong");
		} else {
			return mapping.findForward("dkAcc");
		}
	}
}
