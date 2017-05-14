package common;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PayPal {
	OkHttpClient client = new OkHttpClient();

	String run(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}

	public static void main(String[] args) throws IOException {
		String userName = "user1";
		double soDu = 20000;
		OkHttpClient client2 = new OkHttpClient();

		MediaType mediaType2 = MediaType.parse("application/json");
		RequestBody body2 = RequestBody.create(mediaType2,
				"" + "{" + "\"userName\": \"" + userName + "\"," + "\"soDu\": " + soDu + "}");
		Request request2 = new Request.Builder().url("http://localhost:8083/payment").put(body2)
				.addHeader("content-type", "application/json").addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "b187ad36-04f1-e1ea-f644-d72c0c880611").build();

		Response response2 = client2.newCall(request2).execute();
		System.out.println(response2.body().string());
	}

	public static String payPal(String userName, String password, double soTien, HttpServletRequest request1)
			throws ThanhToanException {
		OkHttpClient client = new OkHttpClient();

		MediaType mediaType = MediaType.parse("application/json");
		RequestBody body = RequestBody.create(mediaType, "" + "{" + "\"userName\": \"" + userName + "\","
				+ "\"password\": \"" + password + "\"," + "\"soDu\": " + soTien + "}");
		Response response = null;
		Request request = new Request.Builder().url("http://localhost:8083/payment").put(body)
				.addHeader("content-type", "application/json").addHeader("cache-control", "no-cache")
				.addHeader("postman-token", "b187ad36-04f1-e1ea-f644-d72c0c880611").build();
		String result = "";
		try {
			response = client.newCall(request).execute();
			result = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
			response.close();
			throw new ThanhToanException(request1);
		} finally {
			response.close();
		}
		return result;
	}
}