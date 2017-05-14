package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.ThanhToanException;
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

	public void themKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai, HttpServletRequest request) throws ThanhToanException {
		promotionDAO.themKhuyenMai(maKm, tenKm, phanTramKm, trangThai, request);

	}

	public Promotion getThongTinKhuyenMai(String maKm) {
		return promotionDAO.getThongTinKhuyenMai(maKm);
	}

	public void suaKhuyenMai(String maKm, String tenKm, float phanTramKm, int trangThai, HttpServletRequest request) throws ThanhToanException {
		promotionDAO.suaKhuyenMai(maKm, tenKm, phanTramKm, trangThai, request);

	}

	public int getTrangThaiKm(String maKm) {
		return promotionDAO.getTrangThaiKm(maKm);
	}

	public void suaTrangThaiKm(String maKm, int trangThai, HttpServletRequest request) throws ThanhToanException {
		promotionDAO.suaTrangThaiKm(maKm, trangThai, request);
	}

	public void xoaKhuyenMai(String maKm, HttpServletRequest request) throws ThanhToanException {
		promotionDAO.xoaKhuyenMai(maKm, request);

	}

}