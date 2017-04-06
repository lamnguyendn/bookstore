package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.ThongKeForm;
import model.beans.BooksSold;
import model.bo.BooksSoldBO;

public class ReloadTableTop4Action extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThongKeForm thongKeForm = (ThongKeForm) form;
		BooksSoldBO booksSoldBO = new BooksSoldBO();
		int thang = thongKeForm.getThangTop4();
		int nam = thongKeForm.getNamTop4();
		ArrayList<BooksSold> listTop4 = booksSoldBO.getList4BooksByDate(nam, thang);
		StringBuffer strResult = new StringBuffer();
		if (listTop4.size() != 0) {
			for (BooksSold booksSold : listTop4) {
				strResult = strResult.append("<tr>" + "<td>" + booksSold.getMaSachBan() + "</td>" + "<td>"
						+ booksSold.getTenSachBan() + "</td>" + "<td>" + booksSold.getSoLuongBan() + "</td>" + "</tr>");
			}

			response.getWriter().println(strResult.toString());
		}
		return null;
	}

}
