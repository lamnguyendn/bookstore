package action;

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

public class ListEbookBoughtAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//HttpSession session = request.getSession(true);
		BookForm bookForm = (BookForm)form;
		Account account =  (Account) request.getSession().getAttribute("userName");
		CategoryBO categoryBO = new CategoryBO();
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		String userName = account.getUserName();
		BookBO bookBO = new BookBO();
		ArrayList<Book> listEbook= bookBO.getListEbookByUserName(userName);
		if(listEbook.size()!=0){
			bookForm.setListEbook(listEbook);
			
			System.out.println("Get listbook name size:" + bookForm.getListEbook().size());
			return mapping.findForward("loadlist");
		}else{
			return mapping.findForward("error");
		}
		
	}

}
