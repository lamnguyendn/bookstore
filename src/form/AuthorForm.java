package form;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.beans.Author;

public class AuthorForm extends ActionForm {

	private String authorNum;
	private String authorName;
	private String authorInformation;
	private ArrayList<Author> listOfAuthors;
	private String submit;

	public String getAuthorNum() {
		return authorNum;
	}

	public void setAuthorNum(String authorNum) {
		this.authorNum = authorNum;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorInformation() {
		return authorInformation;
	}

	public void setAuthorInformation(String authorInformation) {
		this.authorInformation = authorInformation;
	}

	public ArrayList<Author> getListOfAuthors() {
		return listOfAuthors;
	}

	public void setListOfAuthors(ArrayList<Author> listOfAuthors) {
		this.listOfAuthors = listOfAuthors;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
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
