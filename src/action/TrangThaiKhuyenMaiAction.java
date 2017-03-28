package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.KhuyenMaiForm;
import model.bo.PromotionBO;

/**
 * Quản lý khuyến mãi - Cập nhật trạng thái khuyến mãi
 * 
 * @author CongHK
 *
 */
public class TrangThaiKhuyenMaiAction extends Action {
	PromotionBO khuyenMaiBO = new PromotionBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		KhuyenMaiForm khuyenMaiForm = (KhuyenMaiForm) form;
		String maKm = khuyenMaiForm.getMaKm();
		String result = "";
		int trangThai = khuyenMaiBO.getTrangThaiKm(maKm);
		if (trangThai == 0) {
			trangThai = 1;
			result = "<a href=\"javascript:void(0)\" onclick=\"return setDelivery('" + maKm + "')\">"
					+ " <i class=\"glyphicon glyphicon-ok\"></i>" + "</a>";
		} else {
			trangThai = 0;
			result = "<a href=\"javascript:void(0)\" onclick=\"return setDelivery('" + maKm + "')\">"
					+ "<i class=\"glyphicon glyphicon-remove\"></i>" + "</a>";
		}
		khuyenMaiBO.suaTrangThaiKm(maKm, trangThai);
		response.getWriter().println(result);
		return null;

	}

}
