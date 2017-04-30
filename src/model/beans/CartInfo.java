package model.beans;

import java.util.ArrayList;
import java.util.List;

import form.CustomerForm;
import model.bo.BookBO;

public class CartInfo {
	private String orderNum;
	private CustomerForm customerInfo;
	private final ArrayList<CartLineInfo> cartLines = new ArrayList<CartLineInfo>();
	private String userName;
	private ArrayList<Promotion> listOfPromotionCodes = new ArrayList<>();
	private double amountTotalAfterUsingPromotionCode;
	private boolean outOfStock;

	public CartInfo() {

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public CustomerForm getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerForm customerInfo) {
		this.customerInfo = customerInfo;
	}

	public ArrayList<CartLineInfo> getCartLines() {
		return cartLines;
	}

	public ArrayList<Promotion> getListOfPromotionCodes() {
		return listOfPromotionCodes;
	}

	public void setListOfPromotionCodes(ArrayList<Promotion> listOfPromotionCodes) {
		this.listOfPromotionCodes = listOfPromotionCodes;
	}

	public double getAmountTotalAfterUsingPromotionCode() {
		return amountTotalAfterUsingPromotionCode;
	}

	public void setAmountTotalAfterUsingPromotionCode(double amountTotalAfterUsingPromotionCode) {
		this.amountTotalAfterUsingPromotionCode = amountTotalAfterUsingPromotionCode;
	}

	public boolean isOutOfStock() {
		return outOfStock;
	}

	public void setOutOfStock(boolean outOfStock) {
		this.outOfStock = outOfStock;
	}

	public void setAmountTotal(double amountTotal) {
	}

	public CartLineInfo findLineByIsbn(String isbn) {
		for (CartLineInfo line : this.cartLines) {
			if (line.getBook().getIsbn().equals(isbn)) {
				return line;
			}
		}
		return null;
	}

	public void addBook(Book book, int quantity) {
		CartLineInfo line = this.findLineByIsbn(book.getIsbn());

		if (line == null) {
			line = new CartLineInfo();
			line.setBook(book);
			line.setQuantity(quantity);
			line.setInventoryQuantity(book.getQuantity());
			this.cartLines.add(line);
			return;
		}
		if (line.getQuantity() < 20) {
			int newQuantity = line.getQuantity() + quantity;
			if (newQuantity <= 0) {
				this.cartLines.remove(line);
			} else if (newQuantity <= 20 && book.getQuantity() >= newQuantity) {
				line.setQuantity(newQuantity);
			}
		}
	}

	public void updateBook(String isbn, int quantity) {
		CartLineInfo line = this.findLineByIsbn(isbn);
		BookBO bookBO = new BookBO();
		Book book = bookBO.findBookByIsbn(isbn);
		if (line != null) {
			line.setInventoryQuantity(book.getQuantity());
			if (quantity <= 0) {
				this.cartLines.remove(line);
			} else if (line.getQuantity() <= 20 && book.getQuantity() >= quantity) {
				line.setQuantity(quantity);
			}
		}
	}

	public void removeBook(Book book) {
		CartLineInfo line = this.findLineByIsbn(book.getIsbn());
		if (line != null) {
			this.cartLines.remove(line);
		}
	}

	public boolean isEmpty() {
		return this.cartLines.isEmpty();
	}

	public boolean isValidCustomer() {
		return this.customerInfo != null;
	}

	public int getQuantityTotal() {
		int quantityTotal = 0;
		for (CartLineInfo line : this.cartLines) {
			quantityTotal += line.getQuantity();
		}
		return quantityTotal;
	}

	public double getAmountTotal() {
		double total = 0;
		for (CartLineInfo line : this.cartLines) {
			total += line.getAmount();
		}
		this.setAmountTotalAfterUsingPromotionCode(total);
		return total;
	}

	public double getAmountTotalUsingPromotionCode() {
		double total = 0;
		for (CartLineInfo line : this.cartLines) {
			total += line.getAmount();
		}
		amountTotalAfterUsingPromotionCode = total;
		for (Promotion promotionCode : listOfPromotionCodes) {
			amountTotalAfterUsingPromotionCode = amountTotalAfterUsingPromotionCode
					- (amountTotalAfterUsingPromotionCode * promotionCode.getPhanTramKM()) / 100;
		}
		return this.getAmountTotalAfterUsingPromotionCode();
	}

	public void updateQuantity(CartInfo cartInfo) {
		if (cartInfo != null) {
			List<CartLineInfo> lines = cartInfo.getCartLines();
			for (CartLineInfo line : lines) {
				this.updateBook(line.getBook().getIsbn(), line.getQuantity());
			}
		}
	}

	public boolean checkPromotionCodeHasBeenUsed(Promotion promotionCode) {
		for (Promotion promotion : listOfPromotionCodes) {
			if (promotion.getMaKM().equalsIgnoreCase(promotionCode.getMaKM())) {
				return true;
			}
		}
		return false;
	}

	public void setPromotionCode(Promotion promotionCode) {
		if (listOfPromotionCodes.size() == 0) {
			listOfPromotionCodes.add(promotionCode);
		} else if (!listOfPromotionCodes.contains(promotionCode)) {
			listOfPromotionCodes.add(promotionCode);
		}
	}
}