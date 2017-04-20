package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.CategoryForm;
import model.bo.CategoryBO;

/**
 * 
 * @author HCD-Fresher151
 *
 */

public class DeleleCategoryAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CategoryForm categoryForm = (CategoryForm) form;
		CategoryBO categoryBO = new CategoryBO();
		String categoryNum = categoryForm.getCategoryNum();
		categoryBO.deleteCategory(categoryNum);
		return mapping.findForward("delete");
	}
}
