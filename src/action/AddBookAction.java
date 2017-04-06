package action;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import common.StringProcess;
import form.BookForm;
import model.beans.Account;
import model.beans.Book;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

/**
 * Quản lý sách - Thêm sách
 * 
 * @author LamNX
 *
 */
public class AddBookAction extends Action {
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();
	AuthorBO authorBO = new AuthorBO();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		BookForm bookForm = (BookForm) form;
		Account account = (Account) request.getSession().getAttribute("userName");
		request.setAttribute("listOfCategories", categoryBO.getListOfCategories());
		if (null != account) {
			request.setAttribute("logged", true);
			if ("ROLE_ADMIN".equalsIgnoreCase(account.getRole())) {
				request.setAttribute("admin", true);
				bookForm.setListOfCategories(categoryBO.getListOfCategories());
				bookForm.setListOfPublishers(publisherBO.getListOfPublishers());
				bookForm.setListOfAuthors(authorBO.getListOfAuthors());

				bookForm.setActionName("Thêm sách");
				bookForm.setActionBook("/addBook");
				bookForm.setSubmitName("Thêm");

				if ("Thêm".equals(bookForm.getSubmit())) {
					ActionErrors actionErrors = new ActionErrors();

					if (StringProcess.notValid(bookForm.getIsbn())) {
						actionErrors.add("isbnError", new ActionMessage("error.isbnError"));
					} else if (StringProcess.duplicateIsbn(bookForm.getIsbn())) {
						actionErrors.add("isbnError", new ActionMessage("error.isbnExistsError"));
					}
					if (StringProcess.notValid(bookForm.getName())) {
						actionErrors.add("nameError", new ActionMessage("error.nameError"));
					}
					if (StringProcess.notValid(bookForm.getCategoryNum())) {
						actionErrors.add("categoryError", new ActionMessage("error.categoryError"));
					}
					if (StringProcess.notValid(bookForm.getAuthorNum())) {
						actionErrors.add("authorError", new ActionMessage("error.authorError"));
					}
					if (StringProcess.notValid(bookForm.getPublisherNum())) {
						actionErrors.add("publisherError", new ActionMessage("error.publisherError"));
					}
					if (StringProcess.notValid(bookForm.getQuantity())) {
						actionErrors.add("quantityError", new ActionMessage("error.quantityError"));
					} else if (StringProcess.notValidNumber(bookForm.getQuantity())) {
						actionErrors.add("quantityError", new ActionMessage("error.quantityDoesNotMatchFormat"));
					} else if (StringProcess.quantityGreaterThanZero(bookForm.getQuantity())) {
						actionErrors.add("quantityError", new ActionMessage("error.quantityDoesNotLeftThanZero"));
					} else if (StringProcess.quantityLessThanOneThousand(bookForm.getQuantity())) {
						actionErrors.add("quantityError", new ActionMessage("error.quantityLessThanOneThousand"));
					}
					if (StringProcess.notValid(bookForm.getPublishDate())) {
						actionErrors.add("publishDateError", new ActionMessage("error.publishDateError"));
					} else if (StringProcess.notValidDate(bookForm.getPublishDate())) {
						actionErrors.add("publishDateError", new ActionMessage("error.publishDateDoesNotMatchFormat"));
					}
					if (StringProcess.notValid(bookForm.getPrice())) {
						actionErrors.add("priceError", new ActionMessage("error.priceError"));
					} else if (StringProcess.notValidNumber(bookForm.getPrice())) {
						actionErrors.add("priceError", new ActionMessage("error.priceDoesNotMatchFormat"));
					} else if (StringProcess.priceGreaterThanZero(bookForm.getPrice())) {
						actionErrors.add("priceError", new ActionMessage("error.priceDoesNotLeftThanZero"));
					} else if (StringProcess.priceLessThanTenMillion(bookForm.getPrice())) {
						actionErrors.add("priceError", new ActionMessage("error.priceDoesNotLessThanTenMillion"));
					}
					if (StringProcess.notValid(bookForm.getImage_1().getFileName())) {
						actionErrors.add("image_1Error", new ActionMessage("error.image_1Error"));
					} else if (StringProcess.notValidImage(bookForm.getImage_1())) {
						actionErrors.add("image_1Error", new ActionMessage("error.imageDoesNotMatchFormat"));
					} else if (StringProcess.fileSizeNotValid(bookForm.getImage_1())) {
						actionErrors.add("image_1Error", new ActionMessage("error.fileSizeNotValid"));
					}
					if (StringProcess.notValid(bookForm.getDescription())) {
						actionErrors.add("descriptionError", new ActionMessage("error.descriptionError"));
					}

					saveErrors(request, actionErrors);
					if (actionErrors.size() > 0) {
						return mapping.findForward("addBook");
					}
				}
				if ("Thêm".equals(bookForm.getSubmit())) {
					String isbn = bookForm.getIsbn();
					String name = bookForm.getName();
					float price = Float.parseFloat(bookForm.getPrice());
					int quantity = Integer.parseInt(bookForm.getQuantity());
					Date publishDate = StringProcess.convertStringToDate(bookForm.getPublishDate());
					byte[] image_1 = bookForm.getImage_1().getFileData();
					String description = bookForm.getDescription();
					String categoryNum = bookForm.getCategoryNum();
					String authorNum = bookForm.getAuthorNum();
					String publisherNum = bookForm.getPublisherNum();

					Book book = new Book(isbn, name, price, quantity, publishDate, image_1, description, authorNum,
							categoryNum, publisherNum);
					bookBO.addBook(book);

					return mapping.findForward("addBookSuccess");
				} else {
					return mapping.findForward("addBook");
				}
			}
		}
		return mapping.findForward("404");
	}
}