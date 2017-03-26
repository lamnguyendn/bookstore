package model.bo;

import java.util.ArrayList;

import model.beans.Book;
import model.dao.BookDAO;

public class BookBO {
	BookDAO bookDAO = new BookDAO();

	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	public void updateBook(Book book) {
		bookDAO.updateBook(book);
	}

	public Book findBookByIsbn(String isbn) {
		return bookDAO.findBookByIsbn(isbn);
	}

	public void deleteBook(String isbn) {
		bookDAO.deleteBook(isbn);
	}

	public ArrayList<Book> getListOfBooksLimit(int first, int last) {
		return bookDAO.getListOfBooksLimit(first, last);
	}

	public ArrayList<Book> getListOfBooksLimitByCategoryNum(int first, int last, String categoryNum) {
		return bookDAO.getListOfBooksLimitByCategoryNum(first, last, categoryNum);
	}

	public int countRows() {
		return bookDAO.countRows();
	}

	public int countRowsByCategoryNum(String categoryNum) {
		return bookDAO.countRowsByCategoryNum(categoryNum);
	}

	public int countRowsByFindKey(String findKey) {
		return bookDAO.countRowsByFindKey(findKey);
	}

	public ArrayList<Book> getListOfBooksLimitByFindKey(int first, int last, String findKey) {
		return bookDAO.getListOfBooksLimitByFindKey(first, last, findKey);
	}

	public ArrayList<Book> getListOfSuggestedBook() {
		return bookDAO.getListOfSuggestedBook();
	}

}