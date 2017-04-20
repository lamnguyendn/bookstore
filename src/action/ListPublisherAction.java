package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PublisherForm;
import model.beans.Account;
import model.beans.Publisher;
import model.bo.PublisherBO;

public class ListPublisherAction extends Action {
	PublisherBO publisherBO = new PublisherBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("management", "PublisherManagement");

			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				ArrayList<Publisher> listOfPublishers = new ArrayList<>();
				request.setCharacterEncoding("UTF-8");
				PublisherForm publisherForm = (PublisherForm) form;
				listOfPublishers = publisherBO.getListOfPublishers();
				publisherForm.setListOfPublishers(listOfPublishers);
				;
				return mapping.findForward("showpublishersuccess");
			}
		}
		return mapping.findForward("404");
	}

}
