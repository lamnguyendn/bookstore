package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import common.DataAccess;
import model.beans.Book;

public class BookDAO {
	Connection con = DataAccess.connect();
	Statement stm;
	PreparedStatement pstm;
	ResultSet rs;

	public void addBook(Book book) {
		String sql = "INSERT INTO sach(isbn,tensach,dongia,soluong,ngayxuatban,hinhanh_1,mota,ma_tg,ma_tl,ma_nxb) "
				+ " VALUES(?,?,?,?,?,?,?,?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, book.getIsbn());
			pstm.setString(2, book.getName());
			pstm.setFloat(3, book.getPrice());
			pstm.setInt(4, book.getQuantity());
			pstm.setDate(5, new java.sql.Date(book.getPublishDate().getTime()));
			pstm.setBytes(6, book.getImage_1());
			pstm.setString(7, book.getDescription());
			pstm.setString(8, book.getAuthorNum());
			pstm.setString(9, book.getCategoryNum());
			pstm.setString(10, book.getPublisherNum());

			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Add new book has Error");
			e.printStackTrace();
		}
	}

	public void updateBook(Book book) {
		String sql = "UPDATE sach SET tensach = ?, dongia = ?, soluong = ?, ngayxuatban = ?,"
				+ " hinhanh_1 = ?, mota = ?, ma_tg = ?, ma_tl = ?, ma_nxb = ?" + " WHERE isbn = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, book.getName());
			pstm.setFloat(2, book.getPrice());
			pstm.setInt(3, book.getQuantity());
			pstm.setDate(4, new java.sql.Date(book.getPublishDate().getTime()));
			pstm.setBytes(5, book.getImage_1());
			pstm.setString(6, book.getDescription());
			pstm.setString(7, book.getAuthorNum());
			pstm.setString(8, book.getCategoryNum());
			pstm.setString(9, book.getPublisherNum());
			pstm.setString(10, book.getIsbn());

			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update book has Error");
			e.printStackTrace();
		}
	}

	public void deleteBook(String isbn) {
		String sql = "DELETE FROM sach WHERE isbn = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, isbn);
			pstm.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Delete book has Error");
			e.printStackTrace();
		}
	}

	public Book findBookByIsbn(String isbn) {
		Connection con = DataAccess.connect();
		String sql = "SELECT s.*, tg.ten_tg, tl.ten_tl, nxb.ten_nxb, tg.ten_tg" + " FROM sach s"
				+ " INNER JOIN theloai tl ON s.ma_tl = tl.ma_tl" + " INNER JOIN tacgia tg ON s.ma_tg = tg.ma_tg"
				+ " INNER JOIN nhaxuatban nxb ON s.ma_nxb = nxb.ma_nxb and s.isbn = ? ;";
		Book book = new Book();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, isbn);
			rs = pstm.executeQuery();
			while (rs.next()) {
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setPrice(rs.getFloat("dongia"));
				book.setQuantity(rs.getInt("soluong"));
				book.setPublishDate(rs.getDate("ngayxuatban"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setDescription(rs.getString("mota"));
				book.setAuthorNum(rs.getString("ma_tg"));
				book.setCategoryNum(rs.getString("ma_tl"));
				book.setPublisherNum(rs.getString("ma_nxb"));
				book.setAuthorName(rs.getString("ten_tg"));
				book.setCategoryName(rs.getString("ten_tl"));
				book.setPublisherName(rs.getString("ten_nxb"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return book;
	}

	public ArrayList<Book> getListOfBooksLimit(int first, int last) {
		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT s.*, c.ten_tl, tg.ten_tg, nxb.ten_nxb" + " FROM sach s"
				+ " INNER JOIN theloai c ON s.ma_tl = c.ma_tl" + " INNER JOIN tacgia tg ON s.ma_tg = tg.ma_tg"
				+ " INNER JOIN nhaxuatban nxb ON s.ma_nxb = nxb.ma_nxb" + " limit " + first + " , " + last + ";";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setPrice(rs.getInt("dongia"));
				book.setQuantity(rs.getInt("soluong"));
				book.setPublishDate(rs.getDate("ngayxuatban"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setDescription(rs.getString("mota"));
				book.setAuthorNum(rs.getString("ma_tg"));
				book.setCategoryNum(rs.getString("ma_tl"));
				book.setPublisherNum(rs.getString("ma_nxb"));
				book.setAuthorName(rs.getString("ten_tg"));
				book.setCategoryName(rs.getString("ten_tl"));
				book.setPublisherName(rs.getString("ten_nxb"));
				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	public ArrayList<Book> getListOfBooksLimitByCategoryNum(int first, int last, String categoryNum) {
		Connection con = DataAccess.connect();
		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT s.*, c.ten_tl, tg.ten_tg, nxb.ten_nxb" + " FROM sach s"
				+ " INNER JOIN theloai c ON s.ma_tl = c.ma_tl AND c.ma_tl = '" + categoryNum + "'"
				+ " INNER JOIN tacgia tg ON s.ma_tg = tg.ma_tg" + " INNER JOIN nhaxuatban nxb ON s.ma_nxb = nxb.ma_nxb"
				+ " limit " + first + " , " + last + ";";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setPrice(rs.getInt("dongia"));
				book.setQuantity(rs.getInt("soluong"));
				book.setPublishDate(rs.getDate("ngayxuatban"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setDescription(rs.getString("mota"));
				book.setAuthorNum(rs.getString("ma_tg"));
				book.setCategoryNum(rs.getString("ma_tl"));
				book.setPublisherNum(rs.getString("ma_nxb"));
				book.setAuthorName(rs.getString("ten_tg"));
				book.setCategoryName(rs.getString("ten_tl"));
				book.setPublisherName(rs.getString("ten_nxb"));

				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arr;
	}

	public int countRows() {
		String sql = "SELECT count(isbn) FROM sach;";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int countRowsByCategoryNum(String categoryNum) {
		String sql = "SELECT count(b.isbn) FROM sach b" + " INNER JOIN theloai c ON c.ma_tl = b.ma_tl AND b.ma_tl = '"
				+ categoryNum + "' ;";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public int countRowsByFindKey(String findKey) {
		String sql = "SELECT count(isbn)" + " FROM sach b"
				+ " INNER JOIN tacgia tg ON b.ma_tg = tg.ma_tg AND (b.isbn LIKE N'%" + findKey + "%'"
				+ " OR b.tensach LIKE N'%" + findKey + "%' OR tg.ten_tg LIKE N'%" + findKey + "%')";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int count = 0;
		try {
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public ArrayList<Book> getListOfBooksLimitByFindKey(int first, int last, String findKey) {
		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT b.*, tl.ten_tl, nxb.ten_nxb, tg.ten_tg" + " FROM sach b"
				+ " INNER JOIN theloai tl ON b.ma_tl = tl.ma_tl" + " INNER JOIN nhaxuatban nxb ON nxb.ma_nxb = b.ma_nxb"
				+ " INNER JOIN tacgia tg ON b.ma_tg = tg.ma_tg AND (b.isbn LIKE '%" + findKey + "%'"
				+ " OR b.tensach LIKE N'%" + findKey + "%' OR tg.ten_tg LIKE N'%" + findKey + "%') " + " limit " + first
				+ " , " + last + ";";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setPrice(rs.getInt("dongia"));
				book.setQuantity(rs.getInt("soluong"));
				book.setPublishDate(rs.getDate("ngayxuatban"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setDescription(rs.getString("mota"));
				book.setAuthorNum(rs.getString("ma_tg"));
				book.setCategoryNum(rs.getString("ma_tl"));
				book.setPublisherNum(rs.getString("ma_nxb"));
				book.setAuthorName(rs.getString("ten_tg"));
				book.setCategoryName(rs.getString("ten_tl"));
				book.setPublisherName(rs.getString("ten_nxb"));
				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

	public ArrayList<Book> getListOfSuggestedBook() {
		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT isbn, tensach, mota,hinhanh_1 FROM sach ORDER BY isbn DESC LIMIT 4";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setDescription(rs.getString("mota"));
				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return arr;
	}

}