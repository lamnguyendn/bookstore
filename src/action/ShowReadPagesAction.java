package action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import form.BookForm;
import model.bo.BookBO;

public class ShowReadPagesAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BookForm bookForm = (BookForm) form;
		BookBO ebookBO = new BookBO();
		String isbn = bookForm.getIsbn();
		int totalPages = bookForm.getPagesNum();
	
		ArrayList<String> listPages = ebookBO.getListPages(isbn,totalPages);
		
		//ArrayList<String> listLargePages = ebookBO.listLargePages(isbn,totalPages);
		
		if(listPages.size()!=0){
			bookForm.setListPages(listPages);
			//bookForm.setListLargePages(listLargePages);
			return mapping.findForward("loadpages");
		}else{
			return mapping.findForward("can'tload");
		}
		
		
	}

}
