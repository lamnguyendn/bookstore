package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.DataAccess;
import model.beans.Book;

public class BookDAO {

	public void addBook(Book book) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

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
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void updateBook(Book book) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

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
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteBook(String isbn) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

		String sql = "DELETE FROM sach WHERE isbn = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, isbn);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public Book findBookByIsbn(String isbn) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;

		String sql = "SELECT s.*, tg.ten_tg, tl.ten_tl, nxb.ten_nxb, tg.ten_tg" + " FROM sach s"
				+ " INNER JOIN theloai tl ON s.ma_tl = tl.ma_tl" + " INNER JOIN tacgia tg ON s.ma_tg = tg.ma_tg"
				+ " INNER JOIN nhaxuatban nxb ON s.ma_nxb = nxb.ma_nxb and s.isbn = ? ;";
		Book book = new Book();
		ResultSet rs = null;

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
				rs.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return book;
	}

	public ArrayList<Book> getListOfBooksLimit(int first, int last) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<Book> getListOfBooksLimitByCategoryNum(int first, int last, String categoryNum) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public int countRows() {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		int count = 0;
		String sql = "SELECT count(isbn) FROM sach;";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int countRowsByCategoryNum(String categoryNum) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		String sql = "SELECT count(b.isbn) FROM sach b" + " INNER JOIN theloai c ON c.ma_tl = b.ma_tl AND b.ma_tl = '"
				+ categoryNum + "' ;";
		int count = 0;
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public int countRowsByFindKey(String findKey) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		int count = 0;

		String sql = "SELECT count(isbn)" + " FROM sach b"
				+ " INNER JOIN tacgia tg ON b.ma_tg = tg.ma_tg AND (b.isbn LIKE N'%" + findKey + "%'"
				+ " OR b.tensach LIKE N'%" + findKey + "%' OR tg.ten_tg LIKE N'%" + findKey + "%')";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public ArrayList<Book> getListOfBooksLimitByFindKey(int first, int last, String findKey) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<Book> getListOfSuggestedBook() {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

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
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public ArrayList<Book> getListOfRelatedBooks(String isbn) {
		ArrayList<Book> arr = new ArrayList<>();
		ResultSet rs = null;
		Statement stm = null;

		String sql = "SELECT isbn, tensach, mota,hinhanh_1 FROM sach" + " WHERE isbn <> '" + isbn
				+ "' ORDER BY isbn LIMIT 4";
		Connection con = DataAccess.connect();
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
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public void updateQuantity(String isbn, int quantity) {
		String sql = "UPDATE sach SET soluong = soluong -" + quantity + "" + " WHERE isbn = '" + isbn + "'";
		Connection con = DataAccess.connect();
		Statement stm = null;

		try {
			stm = con.createStatement();
			stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean notExistsIsbn(String isbn) {
		String sql = "SELECT isbn FROM sach WHERE isbn ='" + isbn + "'";
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Book> getListOfBookByAuthor(String authorNum) {
		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT * FROM sach" + " WHERE ma_tg = '" + authorNum + "'";
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
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
				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}
	// LaNP

	/**
	 * top 3 quyen sach xep theo isbn
	 * 
	 * @return @
	 */
	public ArrayList<Book> getListSach() {
		Connection con = DataAccess.connect();
		String sql = "SELECT isbn, tensach, dongia, soluong, ngayxuatban, hinhanh_1, mota, ma_tg, ma_tl, ma_nxb"
				+ " FROM   sach ORDER BY isbn LIMIT 3";
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Book> list = new ArrayList<Book>();
		Book sach;
		try {
			while (rs.next()) {
				sach = new Book();
				sach.setPrice(rs.getFloat("dongia"));
				sach.setImage_1(rs.getBytes("hinhanh_1"));
				sach.setPublisherNum(rs.getString("ma_nxb"));
				sach.setAuthorNum(rs.getString("ma_tg"));
				sach.setCategoryNum(rs.getString("ma_tl"));
				sach.setIsbn(rs.getString("isbn"));
				sach.setDescription(rs.getString("mota"));
				sach.setPublishDate(rs.getDate("ngayxuatban"));
				sach.setQuantity(rs.getInt("soluong"));
				sach.setName(rs.getString("tensach"));
				list.add(sach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * Lay ra 1 tac gia username xem nhieu sach cua tac gia nay nhieu nhat
	 * 
	 * @param userName
	 * @param isbn
	 * @return @
	 */
	public String getTacGia(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT ma_tg FROM sach " + " WHERE isbn in (SELECT isbn FROM viewsach "
				+ "WHERE username = '%s' ORDER BY luotxem DESC) ORDER BY isbn DESC LIMIT 1", userName);
		ResultSet rs = null;
		String tacGia = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				String str = rs.getString("ma_tg");
				tacGia = str.toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tacGia;
	}

	/**
	 * Lay danh sach top 3 sach de xuat
	 * 
	 * @param userName
	 * @return @
	 */
	public ArrayList<Book> getListSachDX(String id_tacgia) {
		Connection con = DataAccess.connect();
		String sql = String
				.format("SELECT SUM(luotxem) as luotxem, sach.isbn, tensach, dongia, soluong, ngayxuatban, hinhanh_1, mota, ma_tg, ma_tl, ma_nxb "
						+ " FROM   viewsach, sach where sach.isbn = viewsach.isbn " + " and sach.ma_tg = '%s' "
						+ " GROUP BY sach.isbn, tensach, dongia, soluong, ngayxuatban, hinhanh_1, mota, ma_tg, ma_tl, ma_nxb "
						+ " ORDER BY luotxem DESC LIMIT 3", id_tacgia);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		ArrayList<Book> list = new ArrayList<Book>();
		Book sach;
		try {
			while (rs.next()) {
				sach = new Book();
				sach.setPrice(rs.getFloat("dongia"));
				sach.setImage_1(rs.getBytes("hinhanh_1"));
				sach.setPublisherNum(rs.getString("ma_nxb"));
				sach.setAuthorNum(rs.getString("ma_tg"));
				sach.setCategoryNum(rs.getString("ma_tl"));
				sach.setIsbn(rs.getString("isbn"));
				sach.setDescription(rs.getString("mota"));
				sach.setPublishDate(rs.getDate("ngayxuatban"));
				sach.setQuantity(rs.getInt("soluong"));
				sach.setName(rs.getString("tensach"));
				list.add(sach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * lay danh sach sach top 3 quyen sach dc xem nhieu nhat
	 * 
	 * @return @
	 */
	public ArrayList<Book> getListSachDX() {
		Connection con = DataAccess.connect();
		String sql = String
				.format("SELECT SUM(luotxem) as luotxem, sach.isbn, tensach, dongia, soluong, ngayxuatban, hinhanh_1, mota, ma_tg, ma_tl, ma_nxb "
						+ " FROM viewsach, sach where sach.isbn = viewsach.isbn "
						+ " GROUP BY sach.isbn, tensach, dongia, soluong, ngayxuatban, hinhanh_1, mota,  ma_tg, ma_tl, ma_nxb "
						+ " ORDER BY luotxem DESC LIMIT 3");
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		ArrayList<Book> list = new ArrayList<Book>();
		Book sach;
		try {
			while (rs.next()) {
				sach = new Book();
				sach.setPrice(rs.getFloat("dongia"));
				sach.setImage_1(rs.getBytes("hinhanh_1"));
				sach.setPublisherNum(rs.getString("ma_nxb"));
				sach.setAuthorNum(rs.getString("ma_tg"));
				sach.setCategoryNum(rs.getString("ma_tl"));
				sach.setIsbn(rs.getString("isbn"));
				sach.setDescription(rs.getString("mota"));
				sach.setPublishDate(rs.getDate("ngayxuatban"));
				sach.setQuantity(rs.getInt("soluong"));
				sach.setName(rs.getString("tensach"));
				list.add(sach);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * them luot xem sach
	 * 
	 * @param userName
	 * @param isbn
	 * @
	 */
	public void themLuotXem(String userName, String isbn) {
		Connection con = DataAccess.connect();
		PreparedStatement pstm = null;
		String sql = "INSERT INTO viewsach VALUES(?,?,?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, userName);
			pstm.setString(2, isbn);
			pstm.setInt(3, 1);
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * kiem tra da xem sach nay chua
	 * 
	 * @param userName
	 * @param isbn
	 * @return @
	 */
	public boolean checkXem(String userName, String isbn) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT luotxem FROM viewsach WHERE username = '%s' and isbn = '%s'", userName,
				isbn);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * kiem tra da xem sach lan nao chua
	 * 
	 * @param userName
	 * @param isbn
	 * @return @
	 */
	public boolean checkXem(String userName) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT luotxem FROM viewsach WHERE username = '%s'", userName);
		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * lay luot xem sach isbn
	 * 
	 * @param userName
	 * @param isbn
	 * @return @
	 */
	public int getLuotXem(String userName, String isbn) {
		Connection con = DataAccess.connect();
		String sql = String.format("SELECT luotxem FROM viewsach WHERE username = '%s' and isbn = '%s'", userName,
				isbn);
		ResultSet rs = null;
		int luotxem = 0;
		try {
			Statement stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			while (rs.next()) {
				String str = rs.getString("luotxem");
				luotxem = Integer.parseInt(str);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return luotxem;
	}

	/**
	 * cap nhat luot xem sach
	 * 
	 * @param userName
	 * @param isbn
	 * @param luotXem
	 * @
	 */
	public void updateLuotXem(String userName, String isbn, int luotXem) {
		Connection con = DataAccess.connect();
		String sql = String.format("UPDATE viewsach " + " SET luotxem ='%s'" + " WHERE username = '%s' and isbn = '%s'",
				luotXem, userName, isbn);
		try {
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<Book> getListOfBooksByCategory(String categoryNum) {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT isbn, tensach, dongia,hinhanh_1 FROM sach WHERE ma_tl = '" + categoryNum
				+ "' ORDER BY isbn DESC LIMIT 4";
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn(rs.getString("isbn"));
				book.setName(rs.getString("tensach"));
				book.setImage_1(rs.getBytes("hinhanh_1"));
				book.setPrice(rs.getFloat("dongia"));
				arr.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

	public boolean duplicateIsbn(String isbn) {
		Connection con = DataAccess.connect();
		String sql = "SELECT isbn FROM sach WHERE isbn = '" + isbn + "'";
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Book> getAllOfBooks() {
		Connection con = DataAccess.connect();
		ResultSet rs = null;
		Statement stm = null;

		ArrayList<Book> arr = new ArrayList<>();
		String sql = "SELECT s.*, c.ten_tl, tg.ten_tg, nxb.ten_nxb" + " FROM sach s"
				+ " INNER JOIN theloai c ON s.ma_tl = c.ma_tl" + " INNER JOIN tacgia tg ON s.ma_tg = tg.ma_tg"
				+ " INNER JOIN nhaxuatban nxb ON s.ma_nxb = nxb.ma_nxb" + ";";
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
				rs.close();
				stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return arr;
	}

}