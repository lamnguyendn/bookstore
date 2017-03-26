package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;

@SuppressWarnings("serial")
public class LoginForm extends ActionForm {

	private String userName;
	private String password;
	private String message;
	private boolean logged;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}

	@Override
	public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
		ActionErrors actionErrors = new ActionErrors();
		if (StringProcess.notValid(userName)) {
			actionErrors.add("userNameError", new ActionMessage("error.userName"));
		}
		if (StringProcess.notValid(password)) {
			actionErrors.add("passwordError", new ActionMessage("error.password"));
		}
		return actionErrors;
	}

}