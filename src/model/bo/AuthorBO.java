package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.ThanhToanException;
import model.beans.Author;
import model.dao.AuthorDAO;

public class AuthorBO {
	AuthorDAO authorDAO = new AuthorDAO();

	public ArrayList<Author> getListOfAuthors() {
		return authorDAO.getListOfAuthors();
	}

	public void addAuthor(String authorNum, String authorName, String authorInformation, HttpServletRequest request) throws ThanhToanException {
		authorDAO.addAuthor(authorNum, authorName, authorInformation, request);
	}

	public Author getInfoAuthor(String authorNum) {

		return authorDAO.getInfoAuthor(authorNum);
	}

	public void editAuthor(String authorNum, String authorName, String authorInformation, HttpServletRequest request) throws ThanhToanException {
		authorDAO.editAuthor(authorNum, authorName, authorInformation, request);

	}

	public void deleteAuthor(String authorNum, HttpServletRequest request) throws ThanhToanException {
		authorDAO.deleteAuthor(authorNum, request);

	}

	public Author findAuthorByAuthorNum(String authorNum) {
		return authorDAO.findAuthorByAuthorNum(authorNum);
	}

	public ArrayList<Author> getListOfAuthorsByFindKey(String findKey) {
		return authorDAO.getListOfAuthorsByFindKey(findKey);
	}

}
