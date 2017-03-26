package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.CartProcess;
import form.BookForm;
import model.beans.Book;
import model.beans.CartInfo;
import model.bo.BookBO;

public class AddBookToCartAction extends Action {
	BookBO bookBO = new BookBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Book book = null;
		BookForm bookForm = (BookForm) form;
		String isbn = bookForm.getIsbn();
		if (isbn != null && isbn.length() > 0) {
			book = bookBO.findBookByIsbn(isbn);
		}
		if (book != null && book.getQuantity() > 0) {
			CartInfo cartInfo = CartProcess.getCartInSession(request);
			cartInfo.addBook(book, 1);
		}
		return mapping.findForward("addBookToCart");
	}

}