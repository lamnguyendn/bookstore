package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.beans.Account;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.bo.CategoryBO;

public class FindBookByAuthorAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CategoryBO categoryBO = new CategoryBO();
		AuthorBO authorBO = new AuthorBO();

		Account account = (Account) request.getSession().getAttribute("userName");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
			}
		}
		BookForm bookForm = (BookForm) form;
		BookBO bookBO = new BookBO();
		String authorNum = bookForm.getAuthorNum();
		bookForm.setListOfBooksByAuthor(bookBO.getListOfBooksByAuthor(authorNum));
		bookForm.setAuthorName(authorBO.findAuthorByAuthorNum(authorNum).getAuthorName());
		return mapping.findForward("view");
	}

}
