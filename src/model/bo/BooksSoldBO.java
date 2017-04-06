package model.bo;

import java.util.ArrayList;

import model.beans.BooksSold;
import model.beans.Nam;
import model.beans.Thang;
import model.beans.ThongKe;
import model.dao.BooksSoldDAO;

/**
 * 
 * @author DatTQ
 *
 */
public class BooksSoldBO {
	BooksSoldDAO booksSoldDAO = new BooksSoldDAO();

	public ArrayList<ThongKe> getListDoanhThuByYear(int nam) {

		return booksSoldDAO.getListDoanhThuByYear(nam);
	}

	public ArrayList<Nam> getListNam() {
		return booksSoldDAO.getListNam();
	}

	public ArrayList<Thang> getListThang() {

		return booksSoldDAO.getListThang();
	}

	public ArrayList<BooksSold> getListBooksSoldByLimit(int first, int last) {

		return booksSoldDAO.getListBooksSoldByLimit(first, last);
	}

	public ArrayList<BooksSold> getListBooksSoldLimitByDate(int first, int last, String startDate, String endDate) {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListBooksSoldLimitByDate(first, last, startDate, endDate);
	}

	public int countRowsLimit() {
		// TODO Auto-generated method stub
		return booksSoldDAO.countRowByLimit();
	}

	public int countRowByLimitDate(String startDate, String endDate) {
		// TODO Auto-generated method stub
		return booksSoldDAO.countRowByLimitDate(startDate, endDate);
	}

	public ArrayList<ThongKe> getListBooksSoldAuthorByDate(int cbNam, int cbThang) {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListBooksSoldAuthorByDate(cbNam, cbThang);
	}

	public ArrayList<ThongKe> getListBooksSoldCateByDate(int cbNam, int cbThang) {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListBooksSoldCateByDate(cbNam, cbThang);
	}

	public ArrayList<BooksSold> getList4BooksByDate(int nam, int thang) {
		// TODO Auto-generated method stub
		return booksSoldDAO.getList4BooksByDate(nam, thang);
	}

	public ArrayList<ThongKe> getListBooksSoldCateThisMonth() {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListBooksSoldCateThisMonth();
	}

	public ArrayList<ThongKe> getListBooksSoldAuthorThisMonth() {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListBooksSoldAuthorThisMonth();
	}

	public ArrayList<ThongKe> getListDoanhThuThisYear() {
		// TODO Auto-generated method stub
		return booksSoldDAO.getListDoanhThuThisYear();
	}

	public ArrayList<BooksSold> getList4BooksThisMonth() {
		// TODO Auto-generated method stub
		return booksSoldDAO.getList4BooksThisMonth();
	}

}
