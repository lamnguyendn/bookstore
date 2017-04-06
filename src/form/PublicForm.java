package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.Book;
import model.beans.BooksSold;
import model.beans.Category;

@SuppressWarnings("serial")
public class PublicForm extends ActionForm {
	private ArrayList<Category> listOfCategories;
	private ArrayList<BooksSold> listOfBestBookSeller;
	private ArrayList<Book> listOfSuggestedBook;
	private ArrayList<Book> listOfHomeBooks;

	public ArrayList<Category> getListOfCategories() {
		return listOfCategories;
	}

	public void setListOfCategories(ArrayList<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}

	public ArrayList<Book> getListOfSuggestedBook() {
		return listOfSuggestedBook;
	}

	public void setListOfSuggestedBook(ArrayList<Book> listOfSuggestedBook) {
		this.listOfSuggestedBook = listOfSuggestedBook;
	}

	public ArrayList<Book> getListOfHomeBooks() {
		return listOfHomeBooks;
	}

	public void setListOfHomeBooks(ArrayList<Book> listOfHomeBooks) {
		this.listOfHomeBooks = listOfHomeBooks;
	}

	public ArrayList<BooksSold> getListOfBestBookSeller() {
		return listOfBestBookSeller;
	}

	public void setListOfBestBookSeller(ArrayList<BooksSold> listOfBestBookSeller) {
		this.listOfBestBookSeller = listOfBestBookSeller;
	}

}
