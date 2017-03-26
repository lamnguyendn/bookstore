package common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.DateValidator;
import org.apache.struts.upload.FormFile;

/**
 * StringProcess.java
 *
 * Version 1.0
 *
 * Date: Jan 20, 2015
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * ----------------------------------------------------------------------- Jan
 * 20, 2015 DaiLV2 Create
 */

public class StringProcess {

	/**
	 * Ham tra ve gioi tinh: 1=Nam, 0=Nu
	 * 
	 * @param val
	 * @return String
	 */
	public static String gioiTinh(String val) {
		if ("0".equals(val)) {
			return "Nữ";
		}
		return "Nam";
	}

	/**
	 * Ham in ra mot xau, null in ra xau rong
	 * 
	 * @param s
	 * @return String
	 */
	public static String getVaildString(String s) {
		if (s == null)
			return "";
		return s;
	}

	/**
	 * Ham kiem tra xau rong hay khong
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean notValid(String s) {
		if (s == null || s.length() == 0)
			return true;
		return false;
	}

	/**
	 * Ham kiem tra xem xau co bao gom chi chu so hay khong
	 * 
	 * @param s
	 * @return boolean
	 */
	public static boolean notValidNumber(String s) {
		if (notValid(s))
			return true;
		String regex = "[0-9.]+";
		if (s.matches(regex))
			return false;
		return true;
	}

	public static boolean notValidDate(String publishDate) {
		if (DateValidator.getInstance().validate(publishDate, "dd-MM-yyyy") != null) {
			return false;
		}
		// try {
		// DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		// df.setLenient(false);
		// df.parse(publishDate);
		// } catch (ParseException e) {
		// return true;
		// }
		return true;
	}

	public static boolean notValidPrice(String strPrice) {
		try {
			double price = Double.parseDouble(strPrice);
			if (price < 0) {
				throw new Exception();
			}
			return false;
		} catch (Exception e) {
			System.out.println("Price is not valid!");
		}
		return true;
	}

	public static Date convertStringToDate(String publishDate) {
		Date date = DateValidator.getInstance().validate(publishDate, "dd-MM-yyyy");
		if (date != null) {
			return date;
		}
		// DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		// try {
		// df.setLenient(false);
		// return df.parse(publishDate);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		return null;
	}

	public static boolean nameDoesNotMatchFormat(String name) {
		String expression = "/[^\\W0-9_]+$/";
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(name);
		return matcher.matches();
	}

	public static boolean notValidImage(FormFile image) {
		String mimetype = image.getContentType();
		String type = mimetype.split("/")[0];
		if (type.equals("image"))
			return false;
		return true;
	}

	public static boolean fileSizeNotValid(FormFile image) {
		if (image.getFileSize() > 1048576)
			return true;
		return false;
	}

	public static boolean priceGreaterThanZero(String strPrice) {
		try {
			double price = Double.parseDouble(strPrice);
			if (price < 0) {
				throw new Exception();
			}
			return false;
		} catch (Exception e) {
		}
		return true;
	}

	public static boolean quantityGreaterThanZero(String strQuantity) {
		try {
			int quantity = Integer.parseInt(strQuantity);
			if (quantity < 0) {
				throw new Exception();
			}
			return false;
		} catch (Exception e) {
		}
		return true;
	}

	public static String convertDateSqlToDateUtil(Date publishDate) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String newPublishDate = formatter.format(publishDate);
		System.out.println("newPublishDate : " + newPublishDate);
		return newPublishDate;
	}

}
