package model.bo;

import java.util.ArrayList;

import model.beans.Author;
import model.dao.AuthorDAO;

public class AuthorBO {
	AuthorDAO authorDAO = new AuthorDAO();

	public ArrayList<Author> getListOfAuthors() {
		return authorDAO.getListOfAuthors();
	}

	public void addAuthor(String authorNum, String authorName, String authorInformation) {
		authorDAO.addAuthor(authorNum, authorName, authorInformation);
	}

	public Author getInfoAuthor(String authorNum) {

		return authorDAO.getInfoAuthor(authorNum);
	}

	public void editAuthor(String authorNum, String authorName, String authorInformation) {
		authorDAO.editAuthor(authorNum, authorName, authorInformation);

	}

	public void deleteAuthor(String authorNum) {
		authorDAO.deleteAuthor(authorNum);

	}

	public Author findAuthorByAuthorNum(String authorNum) {
		return authorDAO.findAuthorByAuthorNum(authorNum);
	}

}
