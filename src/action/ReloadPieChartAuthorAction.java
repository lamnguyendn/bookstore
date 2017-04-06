package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.ConvertArrayListToJson;
import form.ThongKeForm;
import model.beans.ThongKe;
import model.bo.BooksSoldBO;
/**
 * 
 * @author DatTQ
 *
 */
public class ReloadPieChartAuthorAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThongKeForm tKForm = (ThongKeForm) form;
		BooksSoldBO booksSoldBO = new BooksSoldBO();
		ConvertArrayListToJson convert = new ConvertArrayListToJson();
		int cbNam = tKForm.getNamTacGia();
		int cbThang = tKForm.getThangTacGia();
		ArrayList<ThongKe> listTacGia = booksSoldBO.getListBooksSoldAuthorByDate(cbNam,cbThang);
		if (listTacGia.size() != 0) {
			String jsonAuthor = convert.convertToJson(listTacGia);
			response.getWriter().println(jsonAuthor);
		}
		return null;
	}
}
