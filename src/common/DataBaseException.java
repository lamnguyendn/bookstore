package common;

import javax.servlet.http.HttpServletRequest;

public class DataBaseException extends Exception {

	public DataBaseException(HttpServletRequest request) {
		request.getSession().setAttribute("cannotCommit", "true");
	}
}
