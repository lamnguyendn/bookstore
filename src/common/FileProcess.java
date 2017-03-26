package common;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileProcess {
	private final static String FILE_NAME = "C:\\history_logs.txt";

	@SuppressWarnings("unchecked")
	public static ArrayList<HistoryLogLine> readFile() {
		ArrayList<HistoryLogLine> arr = null;
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ObjectInputStream ois = new ObjectInputStream(fis);
			arr = (ArrayList<HistoryLogLine>) ois.readObject();
			ois.close();
			fis.close();
			return arr;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	public static void writeFile(ArrayList<HistoryLogLine> arr) {
		try {
			FileOutputStream fos = new FileOutputStream(FILE_NAME);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(arr);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}