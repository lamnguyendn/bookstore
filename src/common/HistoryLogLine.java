package common;

import java.io.Serializable;

public class HistoryLogLine implements Serializable {

	private String findKey;
	private int counter;

	public HistoryLogLine() {
		super();
	}

	public HistoryLogLine(String findKey, int counter) {
		super();
		this.findKey = findKey;
		this.counter = counter;
	}

	public String getFindKey() {
		return findKey;
	}

	public void setFindKey(String findKey) {
		this.findKey = findKey;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

}