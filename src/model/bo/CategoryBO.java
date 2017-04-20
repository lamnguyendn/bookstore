package model.bo;

import java.util.ArrayList;

import model.beans.Category;
import model.dao.CategoryDAO;

public class CategoryBO {
	CategoryDAO categoryDAO = new CategoryDAO();

	public ArrayList<Category> getListOfCategories() {
		return categoryDAO.getListOfCategories();
	}

	public String findCategoryByCategoryNum(String categoryNum) {
		return categoryDAO.findCategoryByCategoryNum(categoryNum);
	}

	public void editCategory(String categoryNum, String categoryName) {
		categoryDAO.editCategory(categoryNum, categoryName);

	}

	public Category getInfoCategory(String categoryNum) {
		return categoryDAO.getInfoCategory(categoryNum);
	}

	public void addCategory(String categoryNum, String categoryName) {
		categoryDAO.addCategory(categoryNum, categoryName);

	}

	public void deleteCategory(String categoryNum) {
		categoryDAO.deleteCategory(categoryNum);

	}

	public ArrayList<Category> getListOfCategoriesByFindkey(String findKey) {
		categoryDAO.getListOfCategoriesByFindkey(findKey);
		return null;
	}
}