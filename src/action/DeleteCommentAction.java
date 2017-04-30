package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.CommentForm;
import model.beans.Account;
import model.bo.CategoryBO;
import model.bo.CommentBO;

public class DeleteCommentAction extends Action {
	CommentBO commentBO = new CommentBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		CommentForm commentForm = (CommentForm) form;
		int ma_BL = commentForm.getMa_BL();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		// Account account = (Account)
		// request.getSession().getAttribute("userName");
		// if (null != account) {
		// request.setAttribute("logged", true);
		// commentBO.deleteComment(ma_BL);
		// if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
		// return mapping.findForward("list");
		// }
		// }
		commentBO.deleteComment(ma_BL);
		System.out.println("done");

		return null;

	}

}
