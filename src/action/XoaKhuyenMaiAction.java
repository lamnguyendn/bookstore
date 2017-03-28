package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.KhuyenMaiForm;
import model.beans.Account;
import model.bo.CategoryBO;
import model.bo.PromotionBO;

/**
 * Quản lý khuyến mãi - Xóa khuyến mãi
 * 
 * @author CongHK
 *
 */
public class XoaKhuyenMaiAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);

				KhuyenMaiForm khuyenMaiForm = (KhuyenMaiForm) form;
				PromotionBO khuyenMaiBO = new PromotionBO();
				String maKm = khuyenMaiForm.getMaKm();
				khuyenMaiBO.xoaKhuyenMai(maKm);
				return mapping.findForward("xoaKm");
			}
		}
		return mapping.findForward("404");
	}
}
