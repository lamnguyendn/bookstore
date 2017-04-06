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
import model.beans.Book;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

/**
 * Quản lý sách - Phân trang
 * 
 * @author LamNX
 *
 */
public class PaginationBookManagementAction extends Action {
	int first = 0;
	int last = 0;
	int pages = 1;
	int totalPerPage = 5;
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
		String categoryNum = bookForm.getCategoryNum();
		String findKey = bookForm.getFindKey();
		byte[] utf8 = findKey.getBytes(StandardCharsets.ISO_8859_1);
		findKey = new String(utf8, StandardCharsets.UTF_8);
		bookForm.setFindKey(findKey);
		if (bookForm.getPage() != 0) {
			pages = bookForm.getPage();
		} else {
			pages = 1;
		}
		int totalPages = pagination(categoryNum, findKey);
		if (null == categoryNum || categoryNum.length() == 0 && (null == findKey || findKey.length() == 0)) {
			result = bookBO.getListOfBooksLimit(first, last);
		} else if ((null != findKey || 0 != findKey.length()) && (null == categoryNum || categoryNum.length() == 0)) {
			result = bookBO.getListOfBooksLimitByFindKey(first, last, findKey);
		} else if ((null != categoryNum || 0 != categoryNum.length()) && (null == findKey || findKey.length() == 0)) {
			result = bookBO.getListOfBooksLimitByCategoryNum(first, last, categoryNum);
		}
		StringBuffer strResult = new StringBuffer();
		for (Book book : result) {
			strResult.append("<tr>" + "<td>" + book.getIsbn() + "</td>" + "<td>" + book.getName() + "</td>" + "<td>"
					+ book.getAuthorName() + "</td>" + "<td>" + book.getCategoryName() + "</td>" + "<td>"
					+ book.getPublisherName() + "</td>" + "<td class=\"text-right\">" + book.getQuantity() + "</td>"
					+ "<td> <a href='/BookStore/deleteBook.do?isbn=" + book.getIsbn()
					+ "' class='glyphicon glyphicon-trash'></a> </td>" + "<td> <a href='/BookStore/updateBook.do?isbn="
					+ book.getIsbn() + "' class='glyphicon glyphicon-edit'></a> </td>" + "</tr>");
		}
		response.getWriter().println(strResult.toString());
		bookForm.setTotalPages(totalPages);
		return null;
	}

	@SuppressWarnings("null")
	private int pagination(String categoryNum, String findKey) {
		int total = 0;
		first = 0;
		last = 5;
		if ((null == categoryNum || categoryNum.length() == 0) && (null == findKey || findKey.length() == 0)) {
			total = bookBO.countRows();
		} else if ((null != findKey || 0 != findKey.length()) && (null == categoryNum || categoryNum.length() == 0)) {
			total = bookBO.countRowsByFindKey(findKey);
		} else if ((null != categoryNum || 0 != categoryNum.length()) && (null == findKey || findKey.length() == 0)) {
			total = bookBO.countRowsByCategoryNum(categoryNum);
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

		if (total % 2 == 0 && total < 5) {
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