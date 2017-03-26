package common;

import javax.servlet.http.HttpServletRequest;

import model.beans.Book;
import model.beans.CartInfo;
import model.beans.CartLineInfo;
import model.bo.BookBO;

public class CartProcess {
	// Products in Cart, stored in Session.
	public static CartInfo getCartInSession(HttpServletRequest request) {

		// Get Cart from Session.
		CartInfo cartInfo = (CartInfo) request.getSession().getAttribute("myCart");

		// If null, create it.
		if (cartInfo == null) {
			cartInfo = new CartInfo();
			// And store to Session.
			request.getSession().setAttribute("myCart", cartInfo);
			request.getSession().setMaxInactiveInterval(7200);
		}
		return cartInfo;
	}

	public static void removeCartInSession(HttpServletRequest request) {
		request.getSession().removeAttribute("myCart");
	}

	public static void storeLastOrderedCartInSession(HttpServletRequest request, CartInfo cartInfo) {
		request.getSession().setAttribute("lastOrderedCart", cartInfo);
	}

	public static CartInfo getLastOrderedCartInSession(HttpServletRequest request) {
		return (CartInfo) request.getSession().getAttribute("lastOrderedCart");
	}
	
	public static void checkQuantityOutOfStock(CartInfo myCart) {
		BookBO bookBO = new BookBO();
		
		for (CartLineInfo cartLineInfo : myCart.getCartLines()) {
			Book bookCart = cartLineInfo.getBook();
			String isbn = bookCart.getIsbn();
			Book book = bookBO.findBookByIsbn(isbn);
			if (book.getQuantity() <= 0) {
				cartLineInfo.setOutOfStock(true);
				myCart.setOutOfStock(true);
				return;
			}
		}
	}
}