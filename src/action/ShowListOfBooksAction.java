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
import model.beans.Category;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

/**
 * Quản lý sách - Hiển thị danh sách sách
 * 
 * @author LamNX
 *
 */
public class ShowListOfBooksAction extends Action {
	int first = 0, last = 0, pages = 1, total, dataPerPage = 5;
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();

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

				ArrayList<Book> listOfBooks = new ArrayList<>();
				request.setCharacterEncoding("UTF-8");
				request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
				BookForm bookForm = (BookForm) form;

				int isSearch = bookForm.getIsSearch();
				String findKey = bookForm.getFindKey();
				byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
				findKey = new String(utf8, StandardCharsets.UTF_8);
				bookForm.setFindKey(findKey);

				if (bookForm.getPage() != 0) {
					pages = bookForm.getPage();
				} else {
					pages = 1;
				}
				int totalPages = pagination(findKey, isSearch);
				/*if ((null == findKey || findKey.length() == 0 || "".equalsIgnoreCase(findKey)) && isSearch == 0) {
					listOfBooks = bookBO.getListOfBooksLimit(first, last);
				} else if (((0 != findKey.length() || !"".equalsIgnoreCase(findKey)) && isSearch == 1)) {
					listOfBooks = bookBO.getListOfBooksLimitByFindKey(first, last, findKey);
				} else if (((null == findKey || 0 == findKey.length() || "".equalsIgnoreCase(findKey))
						&& isSearch == 1)) {
					listOfBooks = new ArrayList<>();
				}*/
				listOfBooks = bookBO.getAllOfBooks();
				ArrayList<Category> listOfCategories = categoryBO.getListOfCategories();
				bookForm.setListOfBooks(listOfBooks);
				bookForm.setTotalPages(totalPages);
				bookForm.setListOfCategories(listOfCategories);

				return mapping.findForward("listBooks");
			}
		}
		return mapping.findForward("404");
	}

	private int pagination(String findKey, int isSearch) {
		total = 0;
		first = 0;
		last = 5;
		if ((null == findKey || findKey.length() == 0 || "".equalsIgnoreCase(findKey)) && isSearch == 0) {
			total = bookBO.countRows();
		} else if (((0 != findKey.length() || !"".equalsIgnoreCase(findKey)) && isSearch == 1)) {
			total = bookBO.countRowsByFindKey(findKey);
		} else if (((null == findKey || 0 == findKey.length() || "".equalsIgnoreCase(findKey)) && isSearch == 1)) {
			return 0;
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

		if (total % 2 == 0 && total <= 5) {
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