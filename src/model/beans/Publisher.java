package model.beans;

public class Publisher {
	private String publisherNum;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhoneNumber;

	public Publisher() {
	}

	public Publisher(String publisherNum, String publisherName, String publisherAddress, String publisherPhoneNumber) {
		super();
		this.publisherNum = publisherNum;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
		this.publisherPhoneNumber = publisherPhoneNumber;
	}

	public String getPublisherNum() {
		return publisherNum;
	}

	public void setPublisherNum(String publisherNum) {
		this.publisherNum = publisherNum;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getPublisherAddress() {
		return publisherAddress;
	}

	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhoneNumber() {
		return publisherPhoneNumber;
	}

	public void setPublisherPhoneNumber(String publisherPhoneNumber) {
		this.publisherPhoneNumber = publisherPhoneNumber;
	}

}