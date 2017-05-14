package common;

import javax.servlet.http.HttpServletRequest;

public class ThanhToanException extends Exception {

	public ThanhToanException(HttpServletRequest request) {
		request.getSession().setAttribute("cannotConnectToServer", "true");
	}
}
