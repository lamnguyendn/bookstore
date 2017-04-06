package common;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import model.beans.ThongKe;
/**
 * 
 * @author DatTQ
 *
 */
public class ConvertArrayListToJson {
	@SuppressWarnings("unchecked")
	
	public String convertToJson(ArrayList<ThongKe> arr) {
	JSONArray arrJson = new JSONArray();
	//phải khai báo biến n=arr.size(). không được đặt arr.size() vào trong vòng for
	for (int i = 0, n=arr.size(); i < n; i++) {
		JSONObject obj = new JSONObject();
		obj.put("label", arr.get(i).getLabel());
		obj.put("value", arr.get(i).getValue());
		arrJson.add(i, obj);
	}
	return arrJson.toJSONString();
}

//public String convertToJsonDoanhThu(ArrayList<ThongKe> arr) throws JSONException {
//	Gson gson = new Gson();
//	JSONObject obj = new JSONObject();
//	String jsonInString = "";
//	JSONArray arrJson = new JSONArray();
//	for (int i = 0; i < arr.size(); i++) {
//		String str = gson.toJson(arr.get(i));
//		jsonInString = jsonInString + str + ",";
//
//	}
//	return jsonInString;
//}
}
