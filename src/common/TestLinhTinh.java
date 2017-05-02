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
		// t.getContextPath();
//		System.out.println("length = " + t.getLengthString());
		System.out.println(PasswordEncoder.createHash("123"));

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

	private int getLengthString() {
		String temp = "Lorem ipsum dolor sit amet, consectetur adipisicing elit. Neque aliquam quisquam odit architecto earum impedit nostrum debitis, unde totam optio, mollitia suscipit reiciendis tempore pariatur iusto aspernatur repellat ab laborum repellendus voluptatum sunt nisi. Quo omnis, deleniti officia dolorum porro molestiae ex quos, ipsum. Omnis facere repellendus porro ducimus aliquam voluptatem quisquam perferendis iure labore quia placeat, laudantium fugit doloremque eveniet. Cum quos quasi quidem facere rem ipsam, corrupti voluptate fugiat enim dolore suscipit, deleniti. Fugit quia sed nam facilis temporibus saepe aliquam ipsam quis delectus explicabo nostrum aperiam recusandae voluptate dignissimos eos harum neque fugiat fuga id dolorem, debitis. Quos eaque laboriosam eveniet, optio repellat quo quas expedita quaerat quibusdam quasi? Officiis odio dicta, praesentium officia doloribus reprehenderit eos cum quod animi architecto, voluptate ipsa laboriosam repellendus expedita veniam. Recusandae doloremque eos, ullam voluptatem sapiente ad similique laborum ratione illo magni. Pariatur sed deleniti excepturi tempore dignissimos, temporibus eius illo dolor magni fugiat dolores, necessitatibus provident aspernatur natus aperiam incidunt repellendus adipisci asperiores error assumenda. Delectus, nisi. Veniam tempore nulla soluta ipsum est, voluptatem culpa enim similique placeat repellat iste consequuntur, numquam. Deleniti culpa debitis corporis accusamus sit ad et quos doloribus veritatis, quod maiores reiciendis quas possimus ipsam aut, sapiente quaerat eveniet consequatur delectus officia mollitia soluta sed. Illo voluptatem perferendis magni velit placeat dolore quae molestiae assumenda voluptates earum reiciendis commodi ducimus autem porro sit obcae";
		String[] arr = temp.split(" ");
		return arr.length;
	}
}
