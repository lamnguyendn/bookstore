package model.beans;

/**
 * 
 * @author DatTQ
 *
 */
public class BooksSold {
	private String maSachBan;
	private String tenSachBan;
	private String tenTL;
	private String tenTG;
	private String tenNXB;
	private float donGia;
	private int soLuongBan;
	private int soLuongConLai;
	private int row;
	private byte[] hinhAnh;
	private String mota;

	/**
	 * create no param constructor
	 */
	public BooksSold() {

	}

	public BooksSold(String maSachBan, String tenSachBan, byte[] hinhAnh, String mota) {
		super();
		this.maSachBan = maSachBan;
		this.tenSachBan = tenSachBan;
		this.hinhAnh = hinhAnh;
		this.mota = mota;
	}

	public byte[] getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(byte[] hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
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
