package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PublicForm;
import model.beans.Account;
import model.bo.BookBO;
import model.bo.CategoryBO;

public class PublicAction extends Action {
	BookBO bookBO = new BookBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("UTF_8");
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
		}
		PublicForm publicForm = (PublicForm) form;
		publicForm.setListOfCategories(categoryBO.getListOfCategories());
		publicForm.setListOfSuggestedBook(bookBO.getListOfSuggestedBook());

		return mapping.findForward("index");
	}

}
