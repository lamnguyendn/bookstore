package action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.DanhSachAccountForm;
import model.beans.Account;
import model.bo.AccountBO;
import model.bo.CategoryBO;

public class DanhSachAccountAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception, IOException {
		request.setCharacterEncoding("utf-8");
		CategoryBO categoryBO = new CategoryBO();
		DanhSachAccountForm nguoiDungForm = (DanhSachAccountForm) form;
		Account account = (Account) request.getSession().getAttribute("userName");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				ArrayList<Account> listAccount;
				AccountBO accountBO = new AccountBO();
				String userName = nguoiDungForm.getUserName();
				if (userName == null || userName.length() == 0) {
					listAccount = accountBO.getListAccount();
				} else {
					listAccount = accountBO.getListAccount(userName);
				}
				nguoiDungForm.setListAccount(listAccount);
				return mapping.findForward("dsAccount");
			}
		}
		return mapping.findForward("404");
	}
}
