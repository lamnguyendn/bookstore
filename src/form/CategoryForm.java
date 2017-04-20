package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.beans.Category;

public class CategoryForm extends ActionForm {

	private String categoryNum;
	private String categoryName;
	private ArrayList<Category> listOfCategories;
	private ArrayList<Category> listOfCategoriesByFindkey;
	private String submit;

	private String findKey = "";

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getFindKey() {
		return findKey;
	}

	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public ArrayList<Category> getListOfCategories() {
		return listOfCategories;
	}

	public void setListOfCategories(ArrayList<Category> listOfCategories) {
		this.listOfCategories = listOfCategories;
	}

	public ArrayList<Category> getListOfCategoriesByFindkey() {
		return listOfCategoriesByFindkey;
	}

	public void setListOfCategoriesByFindkey(ArrayList<Category> listOfCategoriesByFindkey) {
		this.listOfCategoriesByFindkey = listOfCategoriesByFindkey;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

}
