package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.AuthorForm;
import model.bo.AuthorBO;

public class DeleteAuthorAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		AuthorForm authorForm = (AuthorForm) form;
		AuthorBO authorBO = new AuthorBO();
		String authorNum = authorForm.getAuthorNum();
		authorBO.deleteAuthor(authorNum);
		return mapping.findForward("delete");
	}

}
