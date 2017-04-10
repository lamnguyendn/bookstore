package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.bo.AuthorBO;
import model.bo.CategoryBO;

public class TestMultipleSelectedAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuthorBO authorBO = new AuthorBO();
		CategoryBO categoryBO = new CategoryBO();
		BookForm bookForm = (BookForm) form;
		bookForm.setListOfAuthors(authorBO.getListOfAuthors());
		bookForm.setListOfCategories(categoryBO.getListOfCategories());
		
		return mapping.findForward("view");
	}

}
