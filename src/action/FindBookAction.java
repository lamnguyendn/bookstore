package action;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.beans.Account;
import model.beans.Book;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

public class FindBookAction extends Action {
	int first = 0;
	int last = 0;
	int pages = 1;
	int dataPerPage = 8;
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();
	ArrayList<Book> result = new ArrayList<>();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BookForm bookForm = (BookForm) form;
		bookForm.setListOfCategories(categoryBO.getListOfCategories());
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
			}
		}
		if (bookForm.getPage() != 0) {
			pages = bookForm.getPage();
		} else {
			pages = 1;
		}
		String findKey = bookForm.getFindKey();
		System.out.println("findKey input : " + findKey);
		if (null != findKey && !"".equalsIgnoreCase(findKey) && findKey.length() != 0) {
			byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
			findKey = new String(utf8, StandardCharsets.UTF_8);
			bookForm.setFindKey(findKey);
			System.out.println("findKey UTF-8 : " + findKey);
			byte[] latin1 = new String(utf8, "UTF-8").getBytes("ISO-8859-1");
			String latin = new String(latin1);
			System.out.println("latin : " + latin);
			int totalPages = pagination(findKey);
			request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
			bookForm.setListOfBooksByFindKey(bookBO.getListOfBooksLimitByFindKey(first, last, findKey));
			bookForm.setTotalPages(totalPages);
		} else {
			bookForm.setListOfBooksByFindKey(new ArrayList<>());
		}
		return mapping.findForward("findBook");
	}

	private int pagination(String findKey) {
		int total = 0;
		first = 0;
		last = 8;
		if ((null == findKey || findKey.length() == 0)) {
			total = bookBO.countRows();
		} else if (null != findKey || 0 != findKey.length()) {
			total = bookBO.countRowsByFindKey(findKey);
		}

		if (total <= 8) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * 8;
			last = 8;
		}

		int totalPages = 0, num = 0;

		if ((total / 8) % 2 != 0) {
			num = total / 8;
		} else {
			num = (total + 1) / 8;
		}

		if (total % 2 == 0) {
			totalPages = (total / 8) + 1;
		} else {
			if (total < (num * 8) + 8 && total != num * 8) {
				totalPages = (total / 8) + 1;
			} else {
				totalPages = (total / 8);
			}
		}
		return totalPages;
	}
}
