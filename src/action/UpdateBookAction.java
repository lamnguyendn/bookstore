package action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import common.StringProcess;
import form.BookForm;
import model.beans.Book;
import model.bo.AuthorBO;
import model.bo.BookBO;
import model.bo.CategoryBO;
import model.bo.PublisherBO;

public class UpdateBookAction extends Action {
	BookBO bookBO = new BookBO();
	PublisherBO publisherBO = new PublisherBO();
	CategoryBO categoryBO = new CategoryBO();
	AuthorBO authorBO = new AuthorBO();
	ServletContext servletContext;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");

		BookForm bookForm = (BookForm) form;
		bookForm.setListOfCategories(categoryBO.getListOfCategories());
		bookForm.setListOfAuthors(authorBO.getListOfAuthors());
		bookForm.setListOfPublishers(publisherBO.getListOfPublishers());

		bookForm.setActionBook("/updateBook");
		bookForm.setSubmitName("Sửa");
		bookForm.setActionName("Sửa sách");

		String isbn = bookForm.getIsbn();
		Book book = bookBO.findBookByIsbn(isbn);
		bookForm.setBook(book);

		if ("Sửa".equals(bookForm.getSubmit())) {
			ActionErrors actionErrors = new ActionErrors();

			// required
			// convert to UTF-8
			if (StringProcess.notValid(bookForm.getIsbn())) {
				actionErrors.add("isbnError", new ActionMessage("error.isbnError"));
			}
			if (StringProcess.notValid(bookForm.getName())) {
				actionErrors.add("nameError", new ActionMessage("error.nameError"));
			} else if (StringProcess.nameDoesNotMatchFormat(bookForm.getName())) {
				actionErrors.add("nameError", new ActionMessage("<br>" + "error.nameDoesNotMatchFormat"));
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
			}
			if (bookForm.getImage_1().getFileName().length() != 0) {
				if (StringProcess.notValid(bookForm.getImage_1().getFileName())) {
					actionErrors.add("image_1Error", new ActionMessage("error.image_1Error"));
				} else if (StringProcess.notValidImage(bookForm.getImage_1())) {
					actionErrors.add("image_1Error", new ActionMessage("error.imageDoesNotMatchFormat"));
				} else if (StringProcess.fileSizeNotValid(bookForm.getImage_1())) {
					actionErrors.add("image_1Error", new ActionMessage("error.fileSizeNotValid"));
				}
			}
			if (StringProcess.notValid(bookForm.getDescription())) {
				actionErrors.add("descriptionError", new ActionMessage("error.descriptionError"));
			}

			saveErrors(request, actionErrors);
			if (actionErrors.size() > 0) {
				return mapping.findForward("updateBook");
			}
		}
		if ("Sửa".equals(bookForm.getSubmit())) {
			String name = bookForm.getName();
			float price = Float.parseFloat(bookForm.getPrice());
			int quantity = Integer.parseInt(bookForm.getQuantity());
			Date publishDate = StringProcess.convertStringToDate(bookForm.getPublishDate());

			String description = bookForm.getDescription();
			String categoryNum = bookForm.getCategoryNum();
			String authorNum = bookForm.getAuthorNum();
			String publisherNum = bookForm.getPublisherNum();

			FormFile image1 = bookForm.getImage_1();
			int n = image1.getFileName().length();
			byte[] image_1;
			if (n < 1) {
				image_1 = book.getImage_1();
			} else {
				image_1 = image1.getFileData();
			}

			Book bookTemp = new Book(isbn, name, price, quantity, publishDate, image_1, description, authorNum,
					categoryNum, publisherNum);
			bookBO.updateBook(bookTemp);

			return mapping.findForward("updateBookSuccess");
		} else {
			bookForm.setName(book.getName());
			bookForm.setPrice(book.getPrice() + "");
			bookForm.setQuantity(book.getQuantity() + "");
			bookForm.setPublishDate(StringProcess.convertDateSqlToDateUtil(book.getPublishDate()));
			bookForm.setDescription(book.getDescription());
			bookForm.setCategoryNum(book.getCategoryNum());
			bookForm.setAuthorNum(book.getAuthorNum());
			bookForm.setPublisherNum(book.getPublisherNum());
			
			return mapping.findForward("updateBook");
		}

	}

}