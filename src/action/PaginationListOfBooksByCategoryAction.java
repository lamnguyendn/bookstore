package action;

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
 * Quản lý thể loại
 * 
 * @author LamNX
 *
 */
public class PaginationListOfBooksByCategoryAction extends Action {
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
		String categoryNum = bookForm.getCategoryNum();
		if (bookForm.getPage() != 0) {
			pages = bookForm.getPage();
		} else {
			pages = 1;
		}
		int start = (pages - 1) * dataPerPage;
		int end = start + dataPerPage;
		int totalPages = pagination(categoryNum);
		result = bookBO.getListOfBooksLimitByCategoryNum(start, end, categoryNum);

		StringBuffer strResult = new StringBuffer();
		for (Book book : result) {
			strResult.append("<div class=\"col-md-3 col-lg-3 col-sm-4 col-xs-6 book-info\" style=\"margin-top:50px;\">"
							+ "<img src=\"/BookStore/viewBookImage.do?isbn="+book.getIsbn()+"\" "
								+ "style=\"height: 250px; width: 200px; margin-bottom: 20px;\"></img>"
							+ " <h2 class=\"title-book\">"
								+ book.getName()
							+ " </h2>"
							+ " <p class=\"description\">"
								+ book.getDescription()
							+ " </p>"
							+ " <p style=\"margin-top: 20px;\">"
								+ " <a class=\"btn btn-default\" href=\"/BookStore/detailBook.do?isbn="+book.getIsbn()+"\">"
									+ " Xem thêm &raquo;"
								+ " </a>"
							+ " </p>"
						+ " </div>");
		}
		response.getWriter().println(strResult.toString());
		bookForm.setTotalPages(totalPages);
		bookForm.setCategoryName(categoryBO.findCategoryByCategoryNum(categoryNum));
		return null;
	}

	private int pagination(String categoryNum) {
		int total = 0;
		first = 0;
		last = 8;
		total = bookBO.countRowsByCategoryNum(categoryNum);

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