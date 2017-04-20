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
import form.AuthorForm;
import model.bo.AuthorBO;

public class AddAuthorAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AuthorForm authorForm = (AuthorForm) form;
		request.setAttribute("management", "AddAuthor");

		if ("Thêm".equals(authorForm.getSubmit())) {
			ActionErrors actionErrors = new ActionErrors();
			if (StringProcess.notValid(authorForm.getAuthorNum())) {
				actionErrors.add("maTgError", new ActionMessage("error.mtg.trong"));
			}
			if (StringProcess.duplicateIdAuthor(authorForm.getAuthorNum())) {
				actionErrors.add("maTgDuplicate", new ActionMessage("error.mtg.trung"));
			}
			if (StringProcess.notValid(authorForm.getAuthorName())) {
				actionErrors.add("tenTgError", new ActionMessage("error.ttg.trong"));
			}
			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				return mapping.findForward("adderror");
			}
		}
		if ("Thêm".equals(authorForm.getSubmit())) {
			String authorNum = authorForm.getAuthorNum();
			String authorName = authorForm.getAuthorName();
			String authorInformation = authorForm.getAuthorInformation();
			AuthorBO authorBO = new AuthorBO();
			authorBO.addAuthor(authorNum, authorName, authorInformation);
			return mapping.findForward("addsuccess");
		} else {
			return mapping.findForward("adderror");
		}
	}

}
