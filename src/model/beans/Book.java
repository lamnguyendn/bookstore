package model.beans;

import java.util.Date;

public class Book {
	private String isbn;
	private String name;
	private float price;
	private int quantity;
	private Date publishDate;
	private byte[] image_1;
	private String description;

	private String authorNum;
	private String authorName;
	private String categoryNum;
	private String categoryName;
	private String publisherNum;
	private String publisherName;

	public Book() {

	}

	public Book(String isbn, String name, float price, int quantity, Date publishDate, byte[] image_1,
			String description, String authorNum, String categoryNum, String publisherNum) {
		super();
		this.isbn = isbn;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.publishDate = publishDate;
		this.image_1 = image_1;
		this.description = description;
		this.authorNum = authorNum;
		this.categoryNum = categoryNum;
		this.publisherNum = publisherNum;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getImage_1() {
		return image_1;
	}

	public void setImage_1(byte[] image_1) {
		this.image_1 = image_1;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAuthorNum() {
		return authorNum;
	}

	public void setAuthorNum(String authorNum) {
		this.authorNum = authorNum;
	}

	public String getCategoryNum() {
		return categoryNum;
	}

	public void setCategoryNum(String categoryNum) {
		this.categoryNum = categoryNum;
	}

	public String getPublisherNum() {
		return publisherNum;
	}

	public void setPublisherNum(String publisherNum) {
		this.publisherNum = publisherNum;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

}