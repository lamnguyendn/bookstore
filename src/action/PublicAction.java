package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.PublicForm;
import model.beans.Account;
import model.beans.Book;
import model.beans.Category;
import model.bo.BookBO;
import model.bo.BooksSoldBO;
import model.bo.CategoryBO;

/**
 * Quản lý trang chủ
 * 
 * @author LamNX
 *
 */
public class PublicAction extends Action {
	BookBO bookBO = new BookBO();
	BooksSoldBO booksSoldBO = new BooksSoldBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		PublicForm publicForm = (PublicForm) form;
		if (null == request.getSession().getAttribute("dkx")) {
			request.getSession().setAttribute("dkx", "123");
		}
		ArrayList<Book> listSachDX;
		Account account = (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
			}
			if (!bookBO.checkXem(account.getUserName())) {
				listSachDX = bookBO.getListSachDX();
			} else {
				listSachDX = bookBO.getListSachDX(bookBO.getTacGia(account.getUserName()));
			}
		} else {
			listSachDX = bookBO.getListSachDX();
		}
		publicForm.setListOfSuggestedBook(listSachDX);
		ArrayList<Category> listOfHomeBooks = categoryBO.getListOfCategories();
		for (Category c : listOfHomeBooks) {
			c.setListOfBooksByCategory(bookBO.getListOfBooksByCategory(c.getCategoryNum()));
		}
		publicForm.setListOfBestBookSeller(booksSoldBO.getList4BooksThisMonth());
		publicForm.setListOfCategories(listOfHomeBooks);
		return mapping.findForward("index");
	}

}
