package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PublisherForm;
import model.bo.PublisherBO;

public class DeletePublisherAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		PublisherForm publisherForm = (PublisherForm) form;
		PublisherBO publisherBO = new PublisherBO();
		String publisherNum = publisherForm.getPublisherNum();
		publisherBO.deletePublisher(publisherNum, request);
		return mapping.findForward("delete");
	}

}
