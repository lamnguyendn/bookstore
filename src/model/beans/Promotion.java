package model.beans;

public class Promotion {
	private String maKM;
	private String tenKM;
	private float phanTramKM;
	private String maDH;
	private int trangThai;

	public Promotion() {
	}

	public Promotion(String maKM, String tenKM, float phanTramKM, String maDH, int trangThai) {
		super();
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.phanTramKM = phanTramKM;
		this.maDH = maDH;
		this.trangThai = trangThai;
	}

	public String getMaKM() {
		return maKM;
	}

	public void setMaKM(String maKM) {
		this.maKM = maKM;
	}

	public String getTenKM() {
		return tenKM;
	}

	public void setTenKM(String tenKM) {
		this.tenKM = tenKM;
	}

	public float getPhanTramKM() {
		return phanTramKM;
	}

	public void setPhanTramKM(float phanTramKM) {
		this.phanTramKM = phanTramKM;
	}

	public String getMaDH() {
		return maDH;
	}

	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}