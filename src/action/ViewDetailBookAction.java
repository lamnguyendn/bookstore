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
import model.bo.CommentBO;

/**
 * Hiển thị chi tiết sách và các đầu sách liên quan
 * 
 * @author LamNX
 *
 */
public class ViewDetailBookAction extends Action {
	BookBO bookBO = new BookBO();
	CategoryBO categoryBO = new CategoryBO();
	CommentBO commentBO = new CommentBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		BookForm bookForm = (BookForm) form;
		String isbn = bookForm.getIsbn();
		bookForm.setBook(bookBO.findBookByIsbn(isbn));
		bookForm.setListOfRelatedBooks(bookBO.getListOfRelatedBooks(isbn));
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		request.setAttribute("listOfComments", commentBO.getListOfCommentsByIsbn(isbn));
		if (null != account) {
			request.setAttribute("logged", true);
			String userName = account.getUserName();
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
			}
			if (bookBO.checkXem(userName, isbn)) {
				int luotXem = bookBO.getLuotXem(userName, isbn);
				bookBO.updateLuotXem(userName, isbn, luotXem + 1);
			}
			if (!bookBO.checkXem(userName, isbn)) {
				bookBO.themLuotXem(userName, isbn);
			}
		}
		return mapping.findForward("view");
	}

	public static void main(String[] args) {
		String str = "Film festivals used to be do-or-die moments for movie makers. They were where you met the producers that";
		System.out.println(str.length());
	}
}
