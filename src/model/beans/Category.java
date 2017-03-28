package model.beans;

import java.util.ArrayList;

public class Category {
	private String categoryNum;
	private String categoryName;
	private ArrayList<Book> listOfBooksByCategory;

	public Category() {
	}

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ArrayList<Book> getListOfBooksByCategory() {
		return listOfBooksByCategory;
	}

	public void setListOfBooksByCategory(ArrayList<Book> listOfBooksByCategory) {
		this.listOfBooksByCategory = listOfBooksByCategory;
	}
}