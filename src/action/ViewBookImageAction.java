package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.beans.Book;
import model.bo.BookBO;

public class ViewBookImageAction extends Action {
	BookBO bookBO = new BookBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Book book = null;
		BookForm bookForm = (BookForm) form;
		String isbn = bookForm.getIsbn();
		if (isbn != null) {
			book = this.bookBO.findBookByIsbn(isbn);
		}
		if (book != null && book.getImage_1() != null) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(book.getImage_1());
		}
		response.getOutputStream().close();
		return null;
	}
}
