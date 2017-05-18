package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataBaseException;
import model.beans.Book;
import model.dao.BookDAO;

public class BookBO {
	BookDAO bookDAO = new BookDAO();

	public void addBook(Book book, HttpServletRequest request) throws DataBaseException {
		bookDAO.addBook(book, request);
	}

	public void updateBook(Book book, HttpServletRequest request) throws DataBaseException {
		bookDAO.updateBook(book, request);
	}

	public Book findBookByIsbn(String isbn) {
		return bookDAO.findBookByIsbn(isbn);
	}

	public void deleteBook(String isbn, HttpServletRequest request) throws DataBaseException {
		bookDAO.deleteBook(isbn, request);
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

	public ArrayList<Book> getListOfBooksLimitByAuthorNameOrBookName(int first, int last, String findKey) {
		return bookDAO.getListOfBooksLimitByAuthorNameOrBookName(first, last, findKey);
	}

	public ArrayList<Book> getListOfRelatedBooks(String isbn) {
		return bookDAO.getListOfRelatedBooks(isbn);
	}

	public boolean notExistsIsbn(String isbn) {
		return bookDAO.notExistsIsbn(isbn);
	}

	public ArrayList<Book> getListOfBooksByAuthor(String authorNum) {
		return bookDAO.getListOfBooksByAuthor(authorNum);
	}

	public ArrayList<Book> getListSachDX(String idTacGia) {
		return bookDAO.getListSachDX(idTacGia);
	}

	public ArrayList<Book> getListSachDX() {
		return bookDAO.getListSachDX();
	}

	public String getTacGia(String userName) {
		return bookDAO.getTacGia(userName);
	}

	public boolean checkXem(String userName) {
		return bookDAO.checkXem(userName);
	}

	public boolean checkXem(String userName, String isbn) {
		return bookDAO.checkXem(userName, isbn);
	}

	public int getLuotXem(String userName, String isbn) {
		return bookDAO.getLuotXem(userName, isbn);
	}

	public void updateLuotXem(String userName, String isbn, int luotXem) {
		bookDAO.updateLuotXem(userName, isbn, luotXem);
	}

	public void themLuotXem(String userName, String isbn) {
		bookDAO.themLuotXem(userName, isbn);
	}

	public ArrayList<Book> getListOfBooksByCategory(String categoryNum) {
		return bookDAO.getListOfBooksByCategory(categoryNum);
	}

	public ArrayList<Book> getAllOfBooks() {
		return bookDAO.getAllOfBooks();
	}

	public ArrayList<Book> getListOfBooksLimitByAuthor(int first, int last, String findKey) {
		return bookDAO.getListOfBooksLimitByAuthor(first, last, findKey);
	}

	public ArrayList<Book> getListOfBooksLimitByFindKey(int first, int last, String findKey) {
		return bookDAO.getListOfBooksLimitByFindKey(first, last, findKey);
	}

	public int countRowsByFindKeyOnlyBook(String findKey) {
		return bookDAO.countRowsByFindKeyOnlyBook(findKey);
	}
//	Start Edit by DatTQ
	/**
	 * Get List Ebook
	 * @param userName
	 * @return
	 */
	public ArrayList<Book> getListEbookByUserName(String userName) {
		BookDAO bookDAO = new BookDAO();
		return bookDAO.getListEbookByUserName(userName);
	}
	
	/**
	 * Get List Pages
	 * @param isbn
	 * @param pagesNum
	 * @return  ArrayList<String> arrPage
	 */
	public ArrayList<String> getListPages(String isbn, int pagesNum)  {
		ArrayList<String> arrPage = new ArrayList<>();
		
			for (int i = 1; i <=pagesNum; i++) {
				
				String path = "image/" + isbn +"/" + i + ".png";
				if ("".equals(path)) {
					System.out.println("null");
				} else {
					System.out.println(path);
					arrPage.add(path);
				}
			}
		
		return arrPage;
	}
//	End Edit by DatTQ
}