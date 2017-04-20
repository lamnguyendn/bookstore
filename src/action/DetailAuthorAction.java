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

public class DetailAuthorAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("management", "AuthorDetail");

		AuthorForm authorForm = (AuthorForm) form;
		AuthorBO authorBO = new AuthorBO();
		String authorNum = authorForm.getAuthorNum();
		Author author = authorBO.getInfoAuthor(authorNum); // hien thi thong tin
															// ra cac truong
		authorForm.setAuthorNum(author.getAuthorNum());
		authorForm.setAuthorName(author.getAuthorName());
		authorForm.setAuthorInformation(author.getAuthorInformation());
		return mapping.findForward("detailauthor");
	}

}
