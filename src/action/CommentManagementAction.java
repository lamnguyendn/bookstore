package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.CommentForm;
import model.bo.CommentBO;

public class CommentManagementAction extends Action {
	CommentBO commentBO = new CommentBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		CommentForm commentForm = (CommentForm) form;
		request.setAttribute("management", "CommentManagement");

		commentForm.setListOfComments(commentBO.getListOfComments());
		return mapping.findForward("list");
	}

}
