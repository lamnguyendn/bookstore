package model.bo;

import java.util.ArrayList;

import model.beans.Author;
import model.dao.AuthorDAO;

public class AuthorBO {
	AuthorDAO authorDAO = new AuthorDAO();

	public ArrayList<Author> getListOfAuthors() {
		return authorDAO.getListOfAuthors();
	}

	public Author findAuthorByAuthorNum(String authorNum) {
		return authorDAO.findAuthorByAuthorNum(authorNum);
	}

}
