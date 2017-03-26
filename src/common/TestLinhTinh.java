package common;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestLinhTinh {

	public static void main(String[] args) {
		TestLinhTinh t = new TestLinhTinh();
		// System.out.println(t.convertDateSqlToDateUtil());
		t.getContextPath();

	}

	private void testThu() {
		String[] thu1 = { "ThuHai", "ThuBa", "ThuTu", "ThuNam", "ThuSau", "ThuBay", "ChuNhat" };
		String[] thu2 = { "ThuHai", "ThuBa", "ThuTu", "ThuNam", "ThuSau", "ThuBay", "ChuNhat" };
		String sql = "SELECT * FROM ... ";
		int count = 0;
		for (int i = 0; i < thu1.length - 1; i++) {
			for (int j = i + 1; j < thu1.length; j++) {
				System.out.println(thu1[i] + "," + thu2[j]);
				sql += "Thu LIKE '%" + thu1[i] + "%" + thu2[j] + "%'";
				if (i < thu1.length - 2 && j < thu1.length) {
					sql += " OR ";
				}
				sql += "\ni = " + i + ", j = " + j;
				sql += "\n-------------------";
			}
		}
		System.out.println(sql);
		System.out.println("thu1.length = " + thu1.length);
	}

	private void getContextPath() {
		// URL resource = getClass().getResource("/");
		// String path = resource.getPath().substring(1);
		// path = path.replace("build/classes/",
		// "WebContent\\\\images\\\\books\\\\");
		// path = path.replace("/", "\\\\");
		// System.out.println(path);
		// File currDir = new File(".");
		// String path = currDir.getAbsolutePath();
		// path = path.substring(0, path.length() - 1);
		// System.out.println(path);
		URL u = getClass().getProtectionDomain().getCodeSource().getLocation();
		File f = null;
		try {
			f = new File(u.toURI());
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String path = f.getParent().replace("build", "WebContent\\images\\books\\");
		System.out.println(path);
	}

	private String convertDateSqlToDateUtil() {
		Date date = new Date();
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(date);
	}
}
