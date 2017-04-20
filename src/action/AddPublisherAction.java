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
import form.PublisherForm;
import model.bo.PublisherBO;

public class AddPublisherAction extends Action {
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PublisherForm publisherForm = (PublisherForm) form;
		request.setAttribute("management", "AddPublisher");

		if ("Thêm".equals(publisherForm.getSubmit())) {
			ActionErrors actionErrors = new ActionErrors();
			if (StringProcess.notValid(publisherForm.getPublisherNum())) {
				actionErrors.add("maNXBError", new ActionMessage("error.mnxb.trong"));
			}
			if (StringProcess.duplicateIdPublisher(publisherForm.getPublisherNum())) {
				actionErrors.add("maNXBDuplicate", new ActionMessage("error.mnxb.trung"));
			}

			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				return mapping.findForward("adderror");
			}
		}
		if ("Thêm".equals(publisherForm.getSubmit())) {
			String publisherNum = publisherForm.getPublisherNum();
			String publisherName = publisherForm.getPublisherName();
			String publisherAddress = publisherForm.getPublisherAddress();
			String publisherPhoneNumber = publisherForm.getPublisherPhoneNumber();
			PublisherBO publisherBO = new PublisherBO();
			publisherBO.addPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber);
			return mapping.findForward("addsuccess");
		} else {
			return mapping.findForward("adderror");
		}
	}

}
