package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.KhuyenMaiForm;
import model.beans.Account;
import model.bo.CategoryBO;
import model.bo.PromotionBO;

/**
 * Quản lý khuyến mãi - Thêm mới khuyến mãi
 * 
 * @author CongHK
 *
 */
public class ThemKhuyenMaiAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);

				KhuyenMaiForm khuyenMaiForm = (KhuyenMaiForm) form;
				if ("submit".equals(khuyenMaiForm.getSubmit())) {
					ActionErrors actionErrors = new ActionErrors();
					if (StringProcess.notValid(khuyenMaiForm.getMaKm())) {
						actionErrors.add("maKmError", new ActionMessage("error.mkm.trong"));
					} else if (StringProcess.duplicateId(khuyenMaiForm.getMaKm())) {
						actionErrors.add("maKmError", new ActionMessage("error.mkm.trung"));
					} else if (StringProcess.symbolID(khuyenMaiForm.getMaKm())) {
						actionErrors.add("maKmError", new ActionMessage("error.mkm.kitu"));
					}
					if (actionErrors.size() > 0) {
						saveErrors(request, actionErrors);
						return mapping.findForward("themKMerror");
					}
				}
				if ("submit".equals(khuyenMaiForm.getSubmit())) {
					String maKm = khuyenMaiForm.getMaKm();
					String tenKm = khuyenMaiForm.getTenKm();
					float phanTramKm = khuyenMaiForm.getPhanTramKm();
					int trangThai = khuyenMaiForm.getTrangThai();
					PromotionBO khuyenMaiBO = new PromotionBO();
					khuyenMaiBO.themKhuyenMai(maKm, tenKm, phanTramKm, trangThai);
					return mapping.findForward("themKMxong");
				} else {
					return mapping.findForward("themKMerror");
				}
			}
		}
		return mapping.findForward("404");
	}
}
