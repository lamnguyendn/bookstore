package model.beans;

public class Comment {
	private String noiDung;
	private String ngayBinhLuan;
	private String isbn;
	private int pheDuyet;
	private String userName;

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

}
