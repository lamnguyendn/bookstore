package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import model.beans.Account;
import model.beans.Category;
import model.bo.CategoryBO;
import form.CategoryForm;

public class ListCategoryAction extends Action {
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
			request.setAttribute("management", "CategoryManagement");

			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				ArrayList<Category> listOfCategories = new ArrayList<>();
				request.setCharacterEncoding("UTF-8");
				CategoryForm categoryForm = (CategoryForm) form;
				listOfCategories = categoryBO.getListOfCategories();
				categoryForm.setListOfCategories(listOfCategories);
				return mapping.findForward("showcategorysuccess");
			}
		}
		return mapping.findForward("404");
	}
}
