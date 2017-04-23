package form;

import java.util.Date;
import java.util.List;

import org.apache.struts.action.ActionForm;

import model.beans.Comment;

public class CommentForm extends ActionForm {
	private String noiDung;
	private Date ngayBinhLuan;
	private String isbn;
	private int pheDuyet;
	private String userName;
	private List<Comment> listOfComments;

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Date getNgayBinhLuan() {
		return ngayBinhLuan;
	}

	public void setNgayBinhLuan(Date ngayBinhLuan) {
		this.ngayBinhLuan = ngayBinhLuan;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getPheDuyet() {
		return pheDuyet;
	}

	public void setPheDuyet(int pheDuyet) {
		this.pheDuyet = pheDuyet;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<Comment> getListOfComments() {
		return listOfComments;
	}

	public void setListOfComments(List<Comment> listOfComments) {
		this.listOfComments = listOfComments;
	}

}
