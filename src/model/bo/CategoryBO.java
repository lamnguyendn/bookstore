package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.ThanhToanException;
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

	public void editCategory(String categoryNum, String categoryName, HttpServletRequest request) throws ThanhToanException {
		categoryDAO.editCategory(categoryNum, categoryName, request);

	}

	public Category getInfoCategory(String categoryNum) {
		return categoryDAO.getInfoCategory(categoryNum);
	}

	public void addCategory(String categoryNum, String categoryName, HttpServletRequest request) throws ThanhToanException {
		categoryDAO.addCategory(categoryNum, categoryName, request);

	}

	public void deleteCategory(String categoryNum, HttpServletRequest request) throws ThanhToanException {
		categoryDAO.deleteCategory(categoryNum, request);

	}

	public ArrayList<Category> getListOfCategoriesByFindkey(String findKey) {
		categoryDAO.getListOfCategoriesByFindkey(findKey);
		return null;
	}
}