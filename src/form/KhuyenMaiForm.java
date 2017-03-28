package form;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

@SuppressWarnings("serial")
public class KhuyenMaiForm extends ActionForm {

	private String maKm;
	private String tenKm;
	private float phanTramKm;
	private int trangThai;
	private String submit;

	public String getMaKm() {
		return maKm;
	}

	public void setMaKm(String maKm) {
		this.maKm = maKm;
	}

	public String getTenKm() {
		return tenKm;
	}

	public void setTenKm(String tenKm) {
		this.tenKm = tenKm;
	}

	public float getPhanTramKm() {
		return phanTramKm;
	}

	public void setPhanTramKm(float phanTramKm) {
		this.phanTramKm = phanTramKm;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getSubmit() {
		return submit;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
}
