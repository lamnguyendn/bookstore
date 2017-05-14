package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.CategoryForm;
import model.beans.Category;
import model.bo.CategoryBO;

public class EditCategoryAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CategoryForm categoryForm = (CategoryForm) form;
		CategoryBO categoryBO = new CategoryBO();
		String categoryNum = categoryForm.getCategoryNum();
		request.setAttribute("management", "EditCategory");

		if ("Sửa".equals(categoryForm.getSubmit())) {
			String categoryName = categoryForm.getCategoryName();
			categoryBO.editCategory(categoryNum, categoryName, request);
			return mapping.findForward("editsuccess");
		} else {
			Category category = categoryBO.getInfoCategory(categoryNum);
			categoryForm.setCategoryNum(category.getCategoryNum());
			categoryForm.setCategoryName(category.getCategoryName());
			return mapping.findForward("editcategory");
		}
	}

}
