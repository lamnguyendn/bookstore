package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BooksSoldForm;
import model.beans.BooksSold;
import model.bo.BooksSoldBO;

public class PaginationBooksSoldAction extends Action {
	int first = 0, last = 0, page = 1, totalPerPage = 5;
	BooksSoldBO booksSoldBO = new BooksSoldBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BooksSoldForm booksSoldForm = (BooksSoldForm) form;
		String startDate = booksSoldForm.getStartDate();
		String endDate = booksSoldForm.getEndDate();
		ArrayList<BooksSold> result = new ArrayList<>();
		if (booksSoldForm.getPage() != 0) {
			page = booksSoldForm.getPage();
		} else {
			page = 1;
		}
		int totalPages = pagination(startDate, endDate);
		if ((null == startDate && null == endDate) || (0 == startDate.length() && 0 == endDate.length())) {
			result = booksSoldBO.getListBooksSoldByLimit(first, last);
		} else {
			result = booksSoldBO.getListBooksSoldLimitByDate(first, last, startDate, endDate);
		}
		StringBuffer strResult = new StringBuffer();
		for (BooksSold item : result) {
			strResult = strResult.append("<tr>" + "<td>" + item.getMaSachBan() + "</td>" + "<td>" + item.getTenSachBan()
					+ "</td>" + "<td>" + item.getTenTL() + "</td>" + "<td>" + item.getTenTG() + "</td>" + "<td>"
					+ item.getTenNXB() + "</td>" + "<td class=\"text-right\">" + item.getSoLuongBan() + "</td>" + "</tr>");
		}
		booksSoldForm.setTotalPages(totalPages);
		response.getWriter().println(strResult.toString());

		return null;
	}

	private int pagination(String startDate, String endDate) {
		int total = 0;
		first = 0;
		last = 5;

		if ((null == startDate && null == endDate) || (0 == startDate.length() && 0 == endDate.length())) {
			total = booksSoldBO.countRowsLimit();
		} else if (0 != startDate.length() && 0 != endDate.length()) {
			total = booksSoldBO.countRowByLimitDate(startDate, endDate);
		}
		if (total <= 5) {
			first = 0;
			last = total;
		} else {
			first = (page - 1) * 5;
			last = 5;
		}

		int totalPages = 0, num = 0;

		if ((total / 5) % 2 != 0) {
			num = total / 5;
		} else {
			num = (total + 1) / 5;
		}

		if (total % 2 == 0) {
			totalPages = (total / 5) + 1;
		} else {
			if (total < (num * 5) + 5 && total != num * 5) {
				totalPages = (total / 5) + 1;
			} else {
				totalPages = (total / 5);
			}
		}
		return totalPages;
	}

}