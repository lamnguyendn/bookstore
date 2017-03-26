package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.Book;
import model.beans.Category;

@SuppressWarnings("serial")
public class PublicForm extends ActionForm {
	private ArrayList<Category> listOfCategories;
	private ArrayList<Book> listOfBestBookSeller;
	private ArrayList<Book> listOfSuggestedBook;
	
	public ArrayList<Category> getListOfCategories() {
		return listOfCategories;
	}

	public void setListOfCategories(ArrayList<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}

	public ArrayList<Book> getListOfBestBookSeller() {
		return listOfBestBookSeller;
	}

	public void setListOfBestBookSeller(ArrayList<Book> listOfBestBookSeller) {
		this.listOfBestBookSeller = listOfBestBookSeller;
	}

	public ArrayList<Book> getListOfSuggestedBook() {
		return listOfSuggestedBook;
	}

	public void setListOfSuggestedBook(ArrayList<Book> listOfSuggestedBook) {
		this.listOfSuggestedBook = listOfSuggestedBook;
	}

}
