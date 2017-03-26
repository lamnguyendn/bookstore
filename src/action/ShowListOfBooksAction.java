package action;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import common.FileProcess;
import common.HistoryLogLine;
import form.BookForm;
import model.beans.Account;
import model.beans.Book;
import model.beans.Category;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

public class ShowListOfBooksAction extends Action {
	int first = 0, last = 0, pages = 1, total, dataPerPage = 5;
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Account account = (Account) request.getSession().getAttribute("userName");
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				ArrayList<Book> listOfBooks = new ArrayList<>();
				request.setCharacterEncoding("UTF-8");

				BookForm bookForm = (BookForm) form;

				String categoryNum = bookForm.getCategoryNum();
				String findKey = bookForm.getFindKey();
				byte[] utf8 = findKey.getBytes(StandardCharsets.UTF_8);
				findKey = new String(utf8, StandardCharsets.UTF_8);
				bookForm.setFindKey(findKey);

				if (bookForm.getPage() != 0) {
					pages = bookForm.getPage();
				} else {
					pages = 1;
				}
				int totalPages = pagination(categoryNum, findKey);
				if (null == categoryNum || categoryNum.length() == 0 && (null == findKey || findKey.length() == 0)) {
					listOfBooks = bookBO.getListOfBooksLimit(first, last);
				} else if ((null != findKey || 0 != findKey.length())
						&& (null == categoryNum || categoryNum.length() == 0)) {
					listOfBooks = bookBO.getListOfBooksLimitByFindKey(first, last, findKey);
				} else if ((null != categoryNum || 0 != categoryNum.length())
						&& (null == findKey || findKey.length() == 0)) {
					listOfBooks = bookBO.getListOfBooksLimitByCategoryNum(first, last, categoryNum);
				}
				ArrayList<Category> listOfCategories = categoryBO.getListOfCategories();
				bookForm.setListOfBooks(listOfBooks);
				bookForm.setTotalPages(totalPages);
				bookForm.setListOfCategories(listOfCategories);

				// System.out.println("size listOfCategories = " +
				// listOfCategories.size());
				// System.out.println("size listOfBooks = " +
				// listOfBooks.size());
				// System.out.println("totalPages = " + totalPages);
				// System.out.println("first = " + first);
				// System.out.println("last = " + last);
				// System.out.println("total = " + total);

				return mapping.findForward("listBooks");
			}
			return mapping.findForward("login");
		}
		return mapping.findForward("login");
	}

	/**
	 * Phân trang theo categoryNum hoặc findKey
	 * 
	 * @param categoryNum
	 *            là mã Category
	 * @param findKey
	 *            là từ khóa tìm kiếm
	 * @return trả về tổng số trang tìm thấy được theo categoryNum hoặc findKey
	 */
	@SuppressWarnings("null")
	private int pagination(String categoryNum, String findKey) {
		total = 0;
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

	/**
	 * gợi ý sách sử dụng đọc file. Đọc dữ liệu tìm kiếm và chọn mã sách mà
	 * người dùng hay tìm kiếm
	 * 
	 * @param findKey
	 */
	@SuppressWarnings("null")
	private void listGoiYSach(String findKey, String idCat) {
		ArrayList<HistoryLogLine> historyLogLines = FileProcess.readFile();

		boolean check = false;
		if (historyLogLines != null) {
			int i = 0;
			for (HistoryLogLine line : historyLogLines) {
				if (line.getFindKey().equals(findKey) || line.getFindKey().equals(idCat)) {
					if ((null != findKey || 0 != findKey.length()) && (null == idCat || idCat.length() == 0)) {
						historyLogLines.set(i, new HistoryLogLine(findKey, line.getCounter() + 1));
					} else if ((null != idCat || 0 != idCat.length()) && (null == findKey || findKey.length() == 0)) {
						historyLogLines.set(i, new HistoryLogLine(idCat, line.getCounter() + 1));
					}

					check = true;
				}
				i++;
			}
		}
		if (!check) {
			if ((null != findKey || 0 != findKey.length()) && (null == idCat || idCat.length() == 0)) {
				historyLogLines.add(new HistoryLogLine(findKey, 1));
			} else if ((null != idCat || 0 != idCat.length()) && (null == findKey || findKey.length() == 0)) {
				historyLogLines.add(new HistoryLogLine(idCat, 1));
			}
		}
		FileProcess.writeFile(historyLogLines);
		ArrayList<HistoryLogLine> top3 = getTop3HistoryLogs();
		// working with BO to display data on index.html
	}

	/**
	 * in ra màn hình History Logs File
	 */
	private void printHistoryLogs() {
		ArrayList<HistoryLogLine> historyLogLines = FileProcess.readFile();
		if (historyLogLines != null) {
			System.out.println("------------------history logs------------------");
			for (HistoryLogLine line : historyLogLines) {
				System.out.println("findKey = " + line.getFindKey() + ", counter = " + line.getCounter());
			}
			System.out.println("------------------------------------------------------");
		}
	}

	private ArrayList<HistoryLogLine> getTop3HistoryLogs() {
		ArrayList<HistoryLogLine> historyLogLines = FileProcess.readFile();
		ArrayList<HistoryLogLine> tempArr = new ArrayList<>();
		sortByCounter(historyLogLines);
		for (int i = 0; i < 3; i++) {
			tempArr.add(historyLogLines.get(i));
		}
		return tempArr;
	}

	/**
	 * sắp xếp theo counter từ cao xuống thấp
	 * 
	 * @param historyLogLines
	 */
	private void sortByCounter(ArrayList<HistoryLogLine> historyLogLines) {
		Collections.sort(historyLogLines, new Comparator<HistoryLogLine>() {
			@Override
			public int compare(HistoryLogLine o1, HistoryLogLine o2) {
				return o1.getCounter() > o2.getCounter() ? -1 : 1;
			}
		});

	}

	// /**
	// * get book pagination
	// * @param page
	// * @return
	// */
	// public ArrayList<Book> getBooks(int page){
	// // simulate paging, you can replace by MySQL data retrieving for example.
	// ArrayList<Book> result = new ArrayList<>();
	// int start = (page - 1) * dataPerPage;
	// for (int i = start; i < start + dataPerPage; i++) {
	// result.add(bookEntityList.get(i));
	// }
	// return result;
	// }
}