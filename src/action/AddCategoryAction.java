package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.CategoryForm;
import model.bo.CategoryBO;

public class AddCategoryAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// request.setCharacterEncoding("UTF-8");
		// response.setContentType("text/html;charset=UTF-8");
		CategoryForm categoryForm = (CategoryForm) form;
		request.setAttribute("management", "AddCategory");

		if ("Thêm".equals(categoryForm.getSubmit())) {
			ActionErrors actionErrors = new ActionErrors();
			if (StringProcess.notValid(categoryForm.getCategoryNum())) {
				actionErrors.add("maTlError", new ActionMessage("error.mtl.trong"));
			} else if (StringProcess.symbolID(categoryForm.getCategoryNum())) {
				actionErrors.add("maTlSymbolError", new ActionMessage("error.mtl.kitu"));
			} else if (StringProcess.duplicateIdCategory(categoryForm.getCategoryNum())) {
				actionErrors.add("maTlDuplicate", new ActionMessage("error.mtl.trung"));
			}
			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				return mapping.findForward("adderror");
			}
		}
		if ("Thêm".equals(categoryForm.getSubmit())) {
			String categoryNum = categoryForm.getCategoryNum();
			String categoryName = categoryForm.getCategoryName();
			CategoryBO categoryBO = new CategoryBO();
			categoryBO.addCategory(categoryNum, categoryName, request);
			return mapping.findForward("addsuccess");
		} else {
			return mapping.findForward("adderror");
		}
	}
}
