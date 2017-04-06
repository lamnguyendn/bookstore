package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BooksSoldForm;
import model.beans.Account;
import model.beans.BooksSold;
import model.bo.BooksSoldBO;
import model.bo.CategoryBO;

public class DetailBooksSoldAction extends Action {
	int first = 0, last = 0, pages = 1, total, totalPerPage = 5;
	BooksSoldBO booksSoldBO = new BooksSoldBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		ArrayList<BooksSold> listBooksSold = new ArrayList<>();

		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				CategoryBO categoryBO = new CategoryBO();
				request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
				request.setAttribute("admin", true);
				request.setCharacterEncoding("UTF-8");
				response.setCharacterEncoding("UTF-8");
				BooksSoldForm booksSoldForm = (BooksSoldForm) form;
				String startDate = booksSoldForm.getStartDate();
				String endDate = booksSoldForm.getEndDate();
				int totalPages = pagination(startDate, endDate);
				if (booksSoldForm.getPage() != 0) {
					pages = booksSoldForm.getPage();
				} else {
					pages = 1;
				}
				if (null == startDate || null == endDate) {
					listBooksSold = booksSoldBO.getListBooksSoldByLimit(first, last);
				} else {
					listBooksSold = booksSoldBO.getListBooksSoldLimitByDate(first, last, startDate, endDate);
				}
				booksSoldForm.setListBooksSold(listBooksSold);
				booksSoldForm.setTotalPages(totalPages);
				return mapping.findForward("showlistbookssold");
			}
			return mapping.findForward("404");
		}
		return mapping.findForward("404");

	}

	private int pagination(String startDate, String endDate) {
		total = 0;
		first = 0;
		last = 5;
		if (null == startDate && null == endDate) {
			total = booksSoldBO.countRowsLimit();
		} else {
			total = booksSoldBO.countRowByLimitDate(startDate, endDate);
		}

		if (total <= 5) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * 5;
			last = 5;
		}

		int totalPages = 0, num = 0;

		if ((total / 5) % 2 != 0) {
			num = total / 5;
		} else {
			num = (total + 1) / 5;
		}

		if (total % 2 == 0) {
			totalPages = (total / 5);
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