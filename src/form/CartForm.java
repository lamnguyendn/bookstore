package form;

import org.apache.struts.action.ActionForm;

import model.beans.CartInfo;

@SuppressWarnings("serial")
public class CartForm extends ActionForm {
	private CartInfo cart;
	private double amountTotal;
	private double amountTotalAfterUsingPromotionCode;
	private String isbn;
	private int quantity;
	private String promotionCode;

	public void setCart(CartInfo myCart) {
		cart = myCart;
	}

	public CartInfo getCart() {
		return cart;
	}

	public double getAmountTotal() {
		return amountTotal;
	}

	public void setAmountTotal(double amountTotal) {
		this.amountTotal = amountTotal;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public double getAmountTotalAfterUsingPromotionCode() {
		return amountTotalAfterUsingPromotionCode;
	}

	public void setAmountTotalAfterUsingPromotionCode(double amountTotalAfterUsingPromotionCode) {
		this.amountTotalAfterUsingPromotionCode = amountTotalAfterUsingPromotionCode;
	}

}