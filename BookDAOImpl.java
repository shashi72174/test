package com.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import cm.entities.Book;

import com.dao.IBookDAO;
import com.dbconnection.DBConnection;

public class BookDAOImpl implements IBookDAO {

	private DBConnection dbConnection = null;
	private String selectAllBooks = "select * from book";
	private String selectBooks = "SELECT * FROM (SELECT a.*, rownum r FROM (SELECT * FROM BOOK) a WHERE rownum < ((? * ?) + 1)) WHERE r >= (((? - 1) * ?) + 1)";
	private String selectBook = "select * from book where book_id=?";
	private String deleteBook = "delete from book where book_id=?";
	private String insertBook = "insert into book(book_id,isbn,book_Name,book_Price) values (?,?,?,?)";
	private String seq_bookId = "select Bookid_Seq.Nextval from dual";
	private Connection con = null;
	private PreparedStatement pstmt = null;

	@Override
	public List<Book> findAll(Integer pageNum, Integer recordsPerPage) {
		// TODO Auto-generated method stub
		List<Book> list = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBConnection().getConnection();
			pstmt = con.prepareStatement(selectBooks);
			pstmt.setInt(1, pageNum);
			pstmt.setInt(2, recordsPerPage);
			pstmt.setInt(3, pageNum);
			pstmt.setInt(4, recordsPerPage);
			rs = pstmt.executeQuery();
			if (rs != null) {
				list = new ArrayList<Book>();
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt(1));
					book.setIsbn(rs.getInt(2));
					book.setBookName(rs.getString(3));
					book.setBookPrice(rs.getDouble(4));
					list.add(book);
					book = null;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public List<Book> findAll() {
		// TODO Auto-generated method stub
		List<Book> list = null;
		ResultSet rs = null;
		try {
			con = DBConnection.getDBConnection().getConnection();
			pstmt = con.prepareStatement(selectAllBooks);
			rs = pstmt.executeQuery();
			if (rs != null) {
				list = new ArrayList<Book>();
				while (rs.next()) {
					Book book = new Book();
					book.setBookId(rs.getInt(1));
					book.setIsbn(rs.getInt(2));
					book.setBookName(rs.getString(3));
					book.setBookPrice(rs.getDouble(4));
					list.add(book);
					book = null;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
				rs.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return list;
	}

	@Override
	public Book getBook(Integer bookId) throws Exception {
		// TODO Auto-generated method stub
		Book book = null;
		try {
			con = dbConnection.getConnection();
			pstmt = con.prepareStatement(selectBook);
			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs != null && rs.next()) {
				book = new Book();
				book.setBookId(rs.getInt(1));
				book.setIsbn(rs.getInt(2));
				book.setBookName(rs.getString(3));
				book.setBookPrice(rs.getDouble(4));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return book;
	}

	@Override
	public boolean deleteBook(Integer bookId) throws Exception {
		// TODO Auto-generated method stub
		try {
			Connection con = dbConnection.getConnection();
			System.out.println(con.getAutoCommit());
			PreparedStatement pstmt = con.prepareStatement(deleteBook);
			pstmt.setInt(1, bookId);
			if (pstmt.executeUpdate() > 0)
				return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean save(Book book) {
		// TODO Auto-generated method stub
		Integer bookId = 0;
		try {
			con = dbConnection.getConnection();
			System.out.println(con.getAutoCommit());
			pstmt = con.prepareStatement(seq_bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next())
				bookId = rs.getInt(1);
			rs.close();
			pstmt = null;
			pstmt = con.prepareStatement(insertBook);
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, book.getIsbn());
			pstmt.setString(3, book.getBookName());
			pstmt.setDouble(4, book.getBookPrice());
			if (pstmt.executeUpdate() > 0) {
				con.commit();
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				con.close();
				pstmt.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return false;

	}
}
