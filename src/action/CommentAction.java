package action;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import form.CommentForm;
import model.beans.Account;
import model.beans.Comment;
import model.bo.CommentBO;

public class CommentAction extends Action {
	CommentBO commentBO = new CommentBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		CommentForm commentForm = (CommentForm) form;
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			String noiDung = commentForm.getNoiDung();
			String isbn = commentForm.getIsbn();
			byte[] arr = noiDung.getBytes(StandardCharsets.ISO_8859_1);
			noiDung = new String(arr, StandardCharsets.UTF_8);
			Comment comment = new Comment();
			comment.setNoiDung(noiDung);
			comment.setIsbn(isbn);
			comment.setUserName(account.getUserName());
			commentBO.insertComment(comment);
		} else {
			ActionErrors actionErrors = new ActionErrors();
			actionErrors.add("chuaDangNhap", new ActionMessage("error.chuaDangNhap"));
			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				return mapping.findForward("comment");
			}
		}
		return null;
	}

}
