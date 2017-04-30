package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import form.CommentForm;

public class EditCommentAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommentForm commentForm = (CommentForm) form;
		String isbn = commentForm.getIsbn();
		String userName = commentForm.getUserName();
		int ma_BL = commentForm.getMa_BL();
		System.out.println("isbn = " + isbn + " userName = " + userName + " ma_BL = " + ma_BL);
		BookForm bookForm = new BookForm();
		bookForm.setIsbn(isbn);
		return mapping.findForward("edit");
	}

}
