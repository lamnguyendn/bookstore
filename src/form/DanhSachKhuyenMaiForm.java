package form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import model.beans.Promotion;

@SuppressWarnings("serial")
public class DanhSachKhuyenMaiForm extends ActionForm {

	private ArrayList<Promotion> listKhuyenMai;

	public ArrayList<Promotion> getListKhuyenMai() {
		return listKhuyenMai;
	}

	public void setListKhuyenMai(ArrayList<Promotion> listKhuyenMai) {
		this.listKhuyenMai = listKhuyenMai;
	}

}
