package model.bo;

import java.util.ArrayList;

import model.beans.Publisher;
import model.dao.PublisherDAO;

public class PublisherBO {
	PublisherDAO publisherDAO = new PublisherDAO();

	public ArrayList<Publisher> getListOfPublishers() {
		return publisherDAO.getListOfPublishers();
	}
}