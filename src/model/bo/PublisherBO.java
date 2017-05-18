package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataBaseException;
import model.beans.Publisher;
import model.dao.PublisherDAO;

public class PublisherBO {
	PublisherDAO publisherDAO = new PublisherDAO();

	public ArrayList<Publisher> getListOfPublishers() {
		return publisherDAO.getListOfPublishers();
	}

	public void addPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber, HttpServletRequest request) throws DataBaseException {
		publisherDAO.addPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber, request);
	}

	public void editPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber, HttpServletRequest request) throws DataBaseException {
		publisherDAO.editPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber, request);
	}

	public Publisher getInfoPublisher(String publisherNum) {
		return publisherDAO.getInfoPublisher(publisherNum);
	}

	public void deletePublisher(String publisherNum, HttpServletRequest request) throws DataBaseException {
		publisherDAO.deletePublisher(publisherNum, request);
	}
}