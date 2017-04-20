package model.bo;

import java.util.ArrayList;

import model.beans.Publisher;
import model.dao.PublisherDAO;

public class PublisherBO {
	PublisherDAO publisherDAO = new PublisherDAO();

	public ArrayList<Publisher> getListOfPublishers() {
		return publisherDAO.getListOfPublishers();
	}

	public void addPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber) {
		publisherDAO.addPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber);
	}

	public void editPublisher(String publisherNum, String publisherName, String publisherAddress,
			String publisherPhoneNumber) {
		publisherDAO.editPublisher(publisherNum, publisherName, publisherAddress, publisherPhoneNumber);
	}

	public Publisher getInfoPublisher(String publisherNum) {
		return publisherDAO.getInfoPublisher(publisherNum);
	}

	public void deletePublisher(String publisherNum) {
		publisherDAO.deletePublisher(publisherNum);
	}
}