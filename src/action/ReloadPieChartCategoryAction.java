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
public class ReloadPieChartCategoryAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		ThongKeForm tKForm = (ThongKeForm) form;
		BooksSoldBO booksSoldBO = new BooksSoldBO();
		ConvertArrayListToJson convert = new ConvertArrayListToJson();
		int cbNam = tKForm.getNamTheLoai();
		int cbThang = tKForm.getThangTheLoai();
		ArrayList<ThongKe> listCatByDate = booksSoldBO.getListBooksSoldCateByDate(cbNam, cbThang);
		if (listCatByDate.size() != 0) {
			String jsonCat = convert.convertToJson(listCatByDate);
			response.getWriter().println(jsonCat);
		}
		return null;
	}
}
