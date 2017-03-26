package model.bo;

import model.beans.Account;
import model.dao.AccountDAO;

public class AccountBO {
	AccountDAO accountDAO = new AccountDAO();
	
	public Account checkLogin(String userName, String password) {
		return accountDAO.checkLogin(userName, password);
	}

}