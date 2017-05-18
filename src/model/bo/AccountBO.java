package model.bo;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import common.DataBaseException;
import common.ThanhToanException;
import model.beans.Account;
import model.dao.AccountDAO;

public class AccountBO {
	AccountDAO accountDAO = new AccountDAO();

	public Account checkLogin(String userName, String password) {
		return accountDAO.checkLogin(userName, password);
	}

	public boolean checkUserName(String userName) {
		return accountDAO.checkUserName(userName);
	}

	public void themAccount(String userName, String passWord, String ten, String soDienThoai, String diaChi,
			String email, String quyen, HttpServletRequest request) throws DataBaseException {
		accountDAO.themAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen, request);
	}

	public boolean checkPhone(String phone) {
		return accountDAO.checkPhone(phone);
	}

	public boolean checkEmail(String email) {
		return accountDAO.checkEmail(email);
	}

	public ArrayList<Account> getListAccount() {
		return accountDAO.getListAccount();
	}

	public ArrayList<Account> getListAccount(String userName) {
		return accountDAO.getListAccount(userName);
	}

	public void suaAccount(String userName, String passWord, String ten, String soDienThoai, String diaChi,
			String email, String quyen, HttpServletRequest request) throws DataBaseException {
		accountDAO.suaAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen, request);
	}

	public void xoaAccount(String userName, HttpServletRequest request) throws DataBaseException {
		accountDAO.xoaAccount(userName, request);
	}

	public Account getThongTinAccount(String userName) {
		return accountDAO.getThongTinAccount(userName);
	}
}