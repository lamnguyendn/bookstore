package model.beans;

import java.util.Date;

public class Promotion {
	private String maKM;
	private String tenKM;
	private float phanTramKM;
	private Date ngayBatDau;
	private Date ngayKetThuc;
	private String maDH;

	public Promotion() {
	}

	public Promotion(String maKM, String tenKM, float phanTramKM, Date ngayBatDau, Date ngayKetThuc, String maDH) {
		this.maKM = maKM;
		this.tenKM = tenKM;
		this.phanTramKM = phanTramKM;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.maDH = maDH;
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

	public Date getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(Date ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public Date getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(Date ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getMaDH() {
		return maDH;
	}

	public void setMaDH(String maDH) {
		this.maDH = maDH;
	}

}