package model.bo;

import java.util.ArrayList;

import model.beans.Promotion;
import model.dao.PromotionDAO;

public class PromotionBO {
	PromotionDAO promotionDAO = new PromotionDAO();

	public Promotion getPromotionByCode(String promotionCodeForm) {
		return promotionDAO.getPromotionByCode(promotionCodeForm);
	}

	public ArrayList<Promotion> getListKhuyenMai() {
		return promotionDAO.getListKhuyenMai();
	}

	public void themKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai) {
		promotionDAO.themKhuyenMai(maKm, tenKm, phanTramKm, trangThai);

	}

	public Promotion getThongTinKhuyenMai(String maKm) {
		return promotionDAO.getThongTinKhuyenMai(maKm);
	}

	public void suaKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai) {
		promotionDAO.suaKhuyenMai(maKm, tenKm, phanTramKm, trangThai);

	}

	public int getTrangThaiKm(String maKm) {
		return promotionDAO.getTrangThaiKm(maKm);
	}

	public void suaTrangThaiKm(String maKm, int trangThai) {
		promotionDAO.suaTrangThaiKm(maKm, trangThai);
	}

	public void xoaKhuyenMai(String maKm) {
		promotionDAO.xoaKhuyenMai(maKm);

	}

}