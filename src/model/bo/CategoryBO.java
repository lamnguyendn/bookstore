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
}