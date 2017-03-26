package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.beans.Account;
import model.bo.BookBO;

public class DeleteBookAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.getSession().setAttribute("userName", account);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				BookBO bookBO = new BookBO();
				BookForm bookForm = (BookForm) form;
				String isbn = bookForm.getIsbn();
				bookBO.deleteBook(isbn);
				return mapping.findForward("deleteBook");
			}
		}
		return mapping.findForward("index");
	}
}
