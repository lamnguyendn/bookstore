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
import model.bo.CategoryBO;

/**
 * Quản lý sách - Xóa sách
 * 
 * @author LamNX
 *
 */
public class DeleteBookAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				BookBO bookBO = new BookBO();
				BookForm bookForm = (BookForm) form;
				String isbn = bookForm.getIsbn();
				bookBO.deleteBook(isbn);
				return mapping.findForward("deleteBook");
			}
		}
		return mapping.findForward("404");
	}
}
