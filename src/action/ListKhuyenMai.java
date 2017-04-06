package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.DanhSachKhuyenMaiForm;
import model.beans.Account;
import model.beans.Promotion;
import model.bo.CategoryBO;
import model.bo.PromotionBO;;

/**
 * Quản lý khuyến mãi - Hiển thị danh sách khuyến mãi
 * 
 * @author CongKH
 *
 */
public class ListKhuyenMai extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				DanhSachKhuyenMaiForm khuyenMaiForm = (DanhSachKhuyenMaiForm) form;
				ArrayList<Promotion> listKhuyenMai;
				PromotionBO khuyenMaiBO = new PromotionBO();
				listKhuyenMai = khuyenMaiBO.getListKhuyenMai();
				if (listKhuyenMai.size() != 0) {
					khuyenMaiForm.setListKhuyenMai(listKhuyenMai);
					return mapping.findForward("thanhcong");
				}
			}
		}
		return mapping.findForward("404");
	}
}
