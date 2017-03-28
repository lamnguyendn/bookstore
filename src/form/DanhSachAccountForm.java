package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.Account;

/**
 * DanhSachAccountForm.java
 *
 * Version 1.0
 *
 * Date: Mar 1, 2017
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- Mar
 * 1, 2017 LaNP Create
 */

@SuppressWarnings("serial")
public class DanhSachAccountForm extends ActionForm {
	private String userName;
	private ArrayList<Account> listAccount;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ArrayList<Account> getListAccount() {
		return listAccount;
	}

	public void setListAccount(ArrayList<Account> listAccount) {
		this.listAccount = listAccount;
	}

}
