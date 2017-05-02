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

public class PaginationFindBookAction extends Action {
	int first = 0;
	int last = 0;
	int pages = 1;
	int dataPerPage = 8;
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();
	ArrayList<Book> result = new ArrayList<>();
	int totalPages = 0;
	int rdSearch = -1;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BookForm bookForm = (BookForm) form;
		bookForm.setListOfCategories(categoryBO.getListOfCategories());
		if (bookForm.getPage() != 0) {
			pages = bookForm.getPage();
		} else {
			pages = 1;
		}
		String findKey = bookForm.getFindKey();
		if (null != findKey && !"".equalsIgnoreCase(findKey) && findKey.length() != 0) {
			byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
			findKey = new String(utf8, StandardCharsets.UTF_8);
			bookForm.setFindKey(findKey);
			totalPages = pagination(findKey);

			rdSearch = bookForm.getRdSearch();
			if (rdSearch == 0) {
				// find Author
				System.out.println("rdSearch 1 ");
				bookForm.setListOfBooksLimitByAuthorNameOrBookName(new ArrayList<>());
				bookForm.setListOfBooks(new ArrayList<>());
			} else if (rdSearch == 1) {
				// find Book
				System.out.println("rdSearch 2 ");
				result = bookBO.getListOfBooksLimitByFindKey(first, last, findKey);
				bookForm.setListOfBooksLimitByAuthorNameOrBookName(new ArrayList<>());
			} else {
				// find All
				System.out.println("rdSearch 3 ");
				result = bookBO.getListOfBooksLimitByAuthorNameOrBookName(first, last, findKey);
				bookForm.setListOfBooks(new ArrayList<>());
			}
			System.out.println("findKey : " + findKey);
			System.out.println("size : " + result.size());
			bookForm.setListOfAuthors(new ArrayList<>());

			bookForm.setTotalPages(totalPages);
		} else {
			bookForm.setListOfBooksLimitByAuthorNameOrBookName(new ArrayList<>());
			bookForm.setListOfAuthors(new ArrayList<>());
			bookForm.setListOfBooks(new ArrayList<>());
		}

		StringBuffer strResult = new StringBuffer();
		for (Book book : result) {
			strResult.append("<div class=\"col-md-3 col-lg-3 col-sm-4 col-xs-6 book-info\" style=\"margin-top:50px;\">"
					+ "<img src=\"/BookStore/viewBookImage.do?isbn=" + book.getIsbn() + "\" "
					+ "style=\"height: 250px; width: 200px; margin-bottom: 20px;\"></img>"
					+ " <h2 class=\"title-book\">" + book.getName() + " </h2>" + " <p class=\"description\">"
					+ book.getDescription() + " </p>" + " <p style=\"margin-top: 20px;\">"
					+ " <a class=\"btn btn-default\" href=\"/BookStore/detailBook.do?isbn=" + book.getIsbn() + "\">"
					+ " Xem thêm &raquo;" + " </a>" + " </p>" + " </div>");
		}
		response.getWriter().println(strResult.toString());
		bookForm.setTotalPages(totalPages);
		return null;
	}

	private int pagination(String findKey) {
		int total = 0;
		first = 0;
		last = 8;
		if (rdSearch == 0) {
			total = 0;
		} else if (rdSearch == 1) {
			total = bookBO.countRowsByFindKeyOnlyBook(findKey);
		} else {
			total = bookBO.countRowsByFindKey(findKey);
		}
		if (total <= 8) {
			first = 0;
			last = total;
		} else {
			first = (pages - 1) * 8;
			last = 8;
		}
		if (total <= 8) {
			return 1;
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
