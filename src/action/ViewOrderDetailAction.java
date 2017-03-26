package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.OrderForm;
import model.bo.OrderBO;
import model.bo.OrderDetailBO;

public class ViewOrderDetailAction extends Action {
	OrderBO orderBO = new OrderBO();
	OrderDetailBO orderDetailBO = new OrderDetailBO();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		OrderForm orderForm = (OrderForm) form;
		String orderNum = orderForm.getOrderNum();
		orderForm.setOrder(orderBO.findOrderByOrderNum(orderNum));
		orderForm.setListOfOrderDetails(orderDetailBO.getListOfOrderDetailsByOrderNum(orderNum));
		return super.execute(mapping, form, request, response);
	}

}
