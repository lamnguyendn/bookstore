package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.KhuyenMaiForm;
import model.beans.Account;
import model.beans.Promotion;
import model.bo.CategoryBO;
import model.bo.PromotionBO;

/**
 * Quản lý khuyến mãi - Sửa khuyến mãi
 * 
 * @author CongHK
 *
 */
public class SuaKhuyenMaiAction extends Action {
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
				request.setAttribute("management", "EditPromotion");

				KhuyenMaiForm khuyenMaiForm = (KhuyenMaiForm) form;
				PromotionBO khuyenMaiBO = new PromotionBO();
				String maKm = khuyenMaiForm.getMaKm();
				if ("Sửa".equals(khuyenMaiForm.getSubmit())) {
					String tenKm = khuyenMaiForm.getTenKm();
					float phanTramKm = khuyenMaiForm.getPhanTramKm();
					int trangThai = khuyenMaiForm.getTrangThai();
					khuyenMaiBO.suaKhuyenMai(maKm, tenKm, phanTramKm, trangThai);
					return mapping.findForward("suaKMxong");
				} else {
					Promotion khuyenMai = khuyenMaiBO.getThongTinKhuyenMai(maKm);
					khuyenMaiForm.setTenKm(khuyenMai.getTenKM());
					khuyenMaiForm.setPhanTramKm(khuyenMai.getPhanTramKM());
					khuyenMaiForm.setTrangThai(khuyenMai.getTrangThai());
					return mapping.findForward("suaKM");
				}
			}
		}
		return mapping.findForward("404");
	}
}
