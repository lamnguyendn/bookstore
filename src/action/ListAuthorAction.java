package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import model.beans.Account;
import model.beans.Author;
import model.bo.AuthorBO;
import form.AuthorForm;

public class ListAuthorAction extends Action {
	AuthorBO authorBO = new AuthorBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
			request.setAttribute("management", "AuthorManagement");

			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				ArrayList<Author> listOfAuthors = new ArrayList<>();
				request.setCharacterEncoding("UTF-8");
				AuthorForm authorForm = (AuthorForm) form;
				listOfAuthors = authorBO.getListOfAuthors();
				authorForm.setListOfAuthors(listOfAuthors);
				return mapping.findForward("showauthorsuccess");
			}
		}
		return mapping.findForward("404");
	}
}
