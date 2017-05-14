package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.AuthorForm;
import model.beans.Author;
import model.bo.AuthorBO;

public class EditAuthorAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("management", "EditAuthor");

		AuthorForm authorForm = (AuthorForm) form;
		AuthorBO authorBO = new AuthorBO();
		String authorNum = authorForm.getAuthorNum();
		if ("Sửa".equals(authorForm.getSubmit())) {
			String authorName = authorForm.getAuthorName();
			String authorInformation = authorForm.getAuthorInformation();
			authorBO.editAuthor(authorNum, authorName, authorInformation, request);
			return mapping.findForward("editsuccess");
		} else {
			Author author = authorBO.getInfoAuthor(authorNum);
			authorForm.setAuthorNum(author.getAuthorNum());
			authorForm.setAuthorName(author.getAuthorName());
			authorForm.setAuthorInformation(author.getAuthorInformation());
			return mapping.findForward("editauthor");
		}
	}

}
