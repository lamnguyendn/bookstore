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
import model.beans.Account;
import model.beans.BooksSold;
import model.beans.Nam;
import model.beans.Thang;
import model.beans.ThongKe;
import model.bo.BooksSoldBO;
import model.bo.CategoryBO;

/**
 * 
 * @author DatTQ
 *
 */
public class ThongKeAction extends Action {
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				ThongKeForm tKForm = (ThongKeForm) form;
				ConvertArrayListToJson convert = new ConvertArrayListToJson();
				BooksSoldBO booksSoldBO = new BooksSoldBO();
				ArrayList<Nam> listNam = booksSoldBO.getListNam();
				ArrayList<Thang> listThang = booksSoldBO.getListThang();
				if (null != listNam) {
					tKForm.setListNam(listNam);
				}
				tKForm.setListThang(listThang);
				ArrayList<ThongKe> listCat = booksSoldBO.getListBooksSoldCateThisMonth();
				ArrayList<ThongKe> listAuthor = booksSoldBO.getListBooksSoldAuthorThisMonth();
				ArrayList<ThongKe> listDoanhThu = booksSoldBO.getListDoanhThuThisYear();
				ArrayList<BooksSold> list4Books = booksSoldBO.getList4BooksThisMonth();
//				if (listDoanhThu.size() != 0 && listCat.size() != 0 && listAuthor.size() != 0) {
					tKForm.setList4Books(list4Books);
					String jsonDoanhThu = convert.convertToJson(listDoanhThu);
					request.setAttribute("jsonDoanhThu", jsonDoanhThu);
					String jsonCat = convert.convertToJson(listCat);
					request.setAttribute("jsonTheLoai", jsonCat);
					String jsonAuthor = convert.convertToJson(listAuthor);
					request.setAttribute("jsonTacGia", jsonAuthor);
					return mapping.findForward("showbooksold");
//				}
			}
		}
		return mapping.findForward("404");
	}
}
