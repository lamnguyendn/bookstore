package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.BooksSold;

/**
 * 
 * @author DatTQ
 *
 */
public class BooksSoldForm extends ActionForm {
	private String maSachBan;
	private String tenSachBan;
	private String tenTL;
	private String tenTG;
	private String tenNXB;
	private float donGia;
	private int soLuongBan;
	private int soLuongConLai;
	private String startDate;
	private String endDate;
	private int page;
	private int totalPages;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	private ArrayList<BooksSold> listBooksSold;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public ArrayList<BooksSold> getListBooksSold() {
		return listBooksSold;
	}

	public void setListBooksSold(ArrayList<BooksSold> listBooksSold) {
		this.listBooksSold = listBooksSold;
	}

	public String getMaSachBan() {
		return maSachBan;
	}

	public void setMaSachBan(String maSachBan) {
		this.maSachBan = maSachBan;
	}

	public String getTenSachBan() {
		return tenSachBan;
	}

	public void setTenSachBan(String tenSachBan) {
		this.tenSachBan = tenSachBan;
	}

	public String getTenTL() {
		return tenTL;
	}

	public void setTenTL(String tenTL) {
		this.tenTL = tenTL;
	}

	public String getTenTG() {
		return tenTG;
	}

	public void setTenTG(String tenTG) {
		this.tenTG = tenTG;
	}

	public String getTenNXB() {
		return tenNXB;
	}

	public void setTenNXB(String tenNXB) {
		this.tenNXB = tenNXB;
	}

	public float getDonGia() {
		return donGia;
	}

	public void setDonGia(float donGia) {
		this.donGia = donGia;
	}

	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

	public int getSoLuongConLai() {
		return soLuongConLai;
	}

	public void setSoLuongConLai(int soLuongConLai) {
		this.soLuongConLai = soLuongConLai;
	}

	
}
