package model.bo;

import java.util.ArrayList;

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
			String email, String quyen) {
		accountDAO.themAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen);
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
			String email, String quyen) {
		accountDAO.suaAccount(userName, passWord, ten, soDienThoai, diaChi, email, quyen);
	}

	public void xoaAccount(String userName) {
		accountDAO.xoaAccount(userName);
	}

	public Account getThongTinAccount(String userName) {
		return accountDAO.getThongTinAccount(userName);
	}
}