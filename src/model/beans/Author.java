package model.beans;

public class Author {
	private String authorNum;
	private String authorName;
	private String authorInformation;

	public Author() {
	}

	public Author(String authorNum, String authorName, String authorInformation) {
		super();
		this.authorNum = authorNum;
		this.authorName = authorName;
		this.authorInformation = authorInformation;
	}

	public String getAuthorNum() {
		return authorNum;
	}

	public void setAuthorNum(String authorNum) {
		this.authorNum = authorNum;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getAuthorInformation() {
		return authorInformation;
	}

	public void setAuthorInformation(String authorInformation) {
		this.authorInformation = authorInformation;
	}

}