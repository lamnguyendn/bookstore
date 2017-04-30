package form;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import model.beans.Comment;

public class CommentForm extends ActionForm {
	private int ma_BL;
	private String noiDung;
	private String ngayBinhLuan;
	private String isbn;
	private int pheDuyet;
	private String userName;
	private List<Comment> listOfComments;

	public int getMa_BL() {
		return ma_BL;
	}

	public void setMa_BL(int ma_BL) {
		this.ma_BL = ma_BL;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public String getNgayBinhLuan() {
		return ngayBinhLuan;
	}

	public void setNgayBinhLuan(String ngayBinhLuan) {
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

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
