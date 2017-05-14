package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PublisherForm;
import model.beans.Publisher;
import model.bo.PublisherBO;

public class EditPublisherAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		request.setAttribute("management", "EditPublisher");

		PublisherForm publisherForm = (PublisherForm) form;
		PublisherBO publisherBO = new PublisherBO();
		String publisherNum = publisherForm.getPublisherNum();
		if ("Sửa".equals(publisherForm.getSubmit())) {
			String publisherName = publisherForm.getPublisherName();
			String publisherAddress = publisherForm.getPublisherAddress();
			String publisherPhoneNumber = publisherForm.getPublisherPhoneNumber();
			publisherBO.editPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber, request);
			return mapping.findForward("editsuccess");
		} else {
			Publisher publisher = publisherBO.getInfoPublisher(publisherNum);
			publisherForm.setPublisherNum(publisher.getPublisherNum());
			publisherForm.setPublisherName(publisher.getPublisherName());
			publisherForm.setPublisherAddress(publisher.getPublisherAddress());
			publisherForm.setPublisherPhoneNumber(publisher.getPublisherPhoneNumber());
			return mapping.findForward("editpublisher");
		}
	}

}
