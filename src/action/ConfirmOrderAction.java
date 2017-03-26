package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.OrderForm;
import model.bo.OrderBO;

public class ConfirmOrderAction extends Action {
	OrderBO orderBO = new OrderBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		OrderForm orderForm = (OrderForm) form;
		String orderNum = orderForm.getOrderNum();
		String result = "";
		int status = orderBO.getStatusOrder(orderNum);
		if (status == 0) {
			status = 1;
			result = "<a href=\"javascript:void(0)\" onclick=\"return setDelivery('" + orderNum + "')\">"
					+ " <i class=\"glyphicon glyphicon-ok\"></i>" + "</a>";
		} else {
			status = 0;
			result = "<a href=\"javascript:void(0)\" onclick=\"return setDelivery('" + orderNum + "')\">"
					+ "<i class=\"glyphicon glyphicon-remove\"></i>" + "</a>";
		}
		orderBO.updateOrder(orderNum, status);
		response.getWriter().println(result);
		return null;
	}

}
