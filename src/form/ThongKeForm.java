package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.BooksSold;
import model.beans.Nam;
import model.beans.Thang;
import model.beans.ThongKe;

/**
 * 
 * @author DatTQ
 *
 */
@SuppressWarnings("serial")
public class ThongKeForm extends ActionForm {
	private String maSachBan;
	private String tenSachBan;
	private int soLuongBan;
	private ArrayList<Nam> listNam;
	private ArrayList<Thang> listThang;
	private ArrayList<BooksSold> list4Books;
	private ArrayList<ThongKe> listSachTheoTL;
	private ArrayList<ThongKe> listSachTheoTG;

	private int namDoanhThu;
	private int thangTacGia;
	private int namTacGia;
	private int namTheLoai;
	private int thangTheLoai;
	private int thangTop4;
	private int namTop4;
	private String startDate;
	private String endDate;

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

	public int getThangTop4() {
		return thangTop4;
	}

	public void setThangTop4(int thangTop4) {
		this.thangTop4 = thangTop4;
	}

	public int getNamTop4() {
		return namTop4;
	}

	public void setNamTop4(int namTop4) {
		this.namTop4 = namTop4;
	}

	public ArrayList<BooksSold> getList4Books() {
		return list4Books;
	}

	public void setList4Books(ArrayList<BooksSold> list4Books) {
		this.list4Books = list4Books;
	}

	public int getNamDoanhThu() {
		return namDoanhThu;
	}

	public void setNamDoanhThu(int namDoanhThu) {
		this.namDoanhThu = namDoanhThu;
	}

	public int getThangTacGia() {
		return thangTacGia;
	}

	public void setThangTacGia(int thangTacGia) {
		this.thangTacGia = thangTacGia;
	}

	public int getNamTacGia() {
		return namTacGia;
	}

	public void setNamTacGia(int namTacGia) {
		this.namTacGia = namTacGia;
	}

	public int getNamTheLoai() {
		return namTheLoai;
	}

	public void setNamTheLoai(int namTheLoai) {
		this.namTheLoai = namTheLoai;
	}

	public int getThangTheLoai() {
		return thangTheLoai;
	}

	public void setThangTheLoai(int thangTheLoai) {
		this.thangTheLoai = thangTheLoai;
	}

	public ArrayList<Thang> getListThang() {
		return listThang;
	}

	public void setListThang(ArrayList<Thang> listThang) {
		this.listThang = listThang;
	}

	public ArrayList<ThongKe> getListSachTheoTL() {
		return listSachTheoTL;
	}

	public void setListSachTheoTL(ArrayList<ThongKe> listSachTheoTL) {
		this.listSachTheoTL = listSachTheoTL;
	}

	public ArrayList<ThongKe> getListSachTheoTG() {
		return listSachTheoTG;
	}

	public void setListSachTheoTG(ArrayList<ThongKe> listSachTheoTG) {
		this.listSachTheoTG = listSachTheoTG;
	}

	public ArrayList<Nam> getListNam() {
		return listNam;
	}

	public void setListNam(ArrayList<Nam> nam) {
		this.listNam = nam;
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

	public int getSoLuongBan() {
		return soLuongBan;
	}

	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}

}
