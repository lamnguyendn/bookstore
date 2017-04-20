package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.beans.Publisher;

public class PublisherForm extends ActionForm {

	private String publisherNum;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhoneNumber;
	private ArrayList<Publisher> listOfPublishers;
	private String submit;

	public String getPublisherNum() {
		return publisherNum;
	}

	public void setPublisherNum(String publisherNum) {
		this.publisherNum = publisherNum;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhoneNumber() {
		return publisherPhoneNumber;
	}

	public void setPublisherPhoneNumber(String publisherPhoneNumber) {
		this.publisherPhoneNumber = publisherPhoneNumber;
	}

	public ArrayList<Publisher> getListOfPublishers() {
		return listOfPublishers;
	}

	public void setListOfPublishers(ArrayList<Publisher> listOfPublishers) {
		this.listOfPublishers = listOfPublishers;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
