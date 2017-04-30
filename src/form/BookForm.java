package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import model.beans.Author;
import model.beans.Book;
import model.beans.Category;
import model.beans.Publisher;

@SuppressWarnings("serial")
public class BookForm extends ActionForm {

	private String isbn;
	private String name;
	private String price;
	private String quantity;
	private String publishDate;
	private FormFile image_1;

	private String description;

	private String authorNum;
	private String categoryNum = "";
	private String publisherNum;

	private String authorName;
	private String categoryName;
	private String publisherName;

	private String submit;
	private String actionBook;
	private String actionName;
	private String submitName;

	private ArrayList<Book> listOfBooks;
	private ArrayList<Category> listOfCategories;
	private ArrayList<Publisher> listOfPublishers;
	private ArrayList<Author> listOfAuthors;
	private ArrayList<Book> listOfRelatedBooks;
	private ArrayList<Book> listOfBooksByCategory;
	private ArrayList<Book> listOfBooksLimitByAuthorNameOrBookName;
	private ArrayList<Book> listOfBooksByAuthor;

	private String[] multiSelectedAuthor = {};

	private String[] multiSelectedCategory = {};

	public String[] getMultiSelectedAuthor() {
		return multiSelectedAuthor;
	}

	public void setMultiSelectedAuthor(String[] multiSelectedAuthor) {
		this.multiSelectedAuthor = multiSelectedAuthor;
	}

	public String[] getMultiSelectedCategory() {
		return multiSelectedCategory;
	}

	public void setMultiSelectedCategory(String[] multiSelectedCategory) {
		this.multiSelectedCategory = multiSelectedCategory;
	}

	private int page;
	private int totalPages;
	private String findKey = "";
	private int isSearch = 0;
	private int rdSearch = 2;

	public int getRdSearch() {
		return rdSearch;
	}

	public void setRdSearch(int rdSearch) {
		this.rdSearch = rdSearch;
	}

	private Book book;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getIsSearch() {
		return isSearch;
	}

	public void setIsSearch(int isSearch) {
		this.isSearch = isSearch;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorNum() {
		return authorNum;
	}

	public void setAuthorNum(String authorNum) {
		this.authorNum = authorNum;
	}

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getPublisherNum() {
		return publisherNum;
	}

	public void setPublisherNum(String publisherNum) {
		this.publisherNum = publisherNum;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public String getActionBook() {
		return actionBook;
	}

	public void setActionBook(String actionBook) {
		this.actionBook = actionBook;
	}

	public String getSubmitName() {
		return submitName;
	}

	public void setSubmitName(String submitName) {
		this.submitName = submitName;
	}

	public ArrayList<Book> getListOfBooks() {
		return listOfBooks;
	}

	public void setListOfBooks(ArrayList<Book> listOfBooks) {
		this.listOfBooks = listOfBooks;
	}

	public ArrayList<Category> getListOfCategories() {
		return listOfCategories;
	}

	public void setListOfCategories(ArrayList<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}

	public ArrayList<Publisher> getListOfPublishers() {
		return listOfPublishers;
	}

	public void setListOfPublishers(ArrayList<Publisher> listOfPublishers) {
		this.listOfPublishers = listOfPublishers;
	}

	public ArrayList<Author> getListOfAuthors() {
		return listOfAuthors;
	}

	public void setListOfAuthors(ArrayList<Author> listOfAuthors) {
		this.listOfAuthors = listOfAuthors;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public String getFindKey() {
		return findKey;
	}

	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ArrayList<Book> getListOfRelatedBooks() {
		return listOfRelatedBooks;
	}

	public void setListOfRelatedBooks(ArrayList<Book> listOfRelatedBooks) {
		this.listOfRelatedBooks = listOfRelatedBooks;
	}

	public FormFile getImage_1() {
		return image_1;
	}

	public void setImage_1(FormFile image_1) {
		this.image_1 = image_1;
	}

	public ArrayList<Book> getListOfBooksByCategory() {
		return listOfBooksByCategory;
	}

	public void setListOfBooksByCategory(ArrayList<Book> listOfBooksByCategory) {
		this.listOfBooksByCategory = listOfBooksByCategory;
	}

	public ArrayList<Book> getListOfBooksLimitByAuthorNameOrBookName() {
		return listOfBooksLimitByAuthorNameOrBookName;
	}

	public void setListOfBooksLimitByAuthorNameOrBookName(ArrayList<Book> listOfBooksLimitByAuthorNameOrBookName) {
		this.listOfBooksLimitByAuthorNameOrBookName = listOfBooksLimitByAuthorNameOrBookName;
	}

	public ArrayList<Book> getListOfBooksByAuthor() {
		return listOfBooksByAuthor;
	}

	public void setListOfBooksByAuthor(ArrayList<Book> listOfBooksByAuthor) {
		this.listOfBooksByAuthor = listOfBooksByAuthor;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}