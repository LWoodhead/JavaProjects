/**
 * 
 */
package com.ss.march.lib;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lukej
 *
 */
public class LibraryBookDAO implements LibraryDAO<LibraryBook>{

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBookDAO d = new LibraryBookDAO();
		for(LibraryBook book: d.getAll()) {
			System.out.println(book.toString());
		}
		LibraryBook test = new LibraryBook(7,"Dune",1);
		String[] update = new String[2];
		update[0] = "Micro Trends";
		update[1] = "1";
		
		
//		d.save(test);
//		d.update(test,update);
//		d.delete(test);
		for(LibraryBook book: d.getAll()) {
			System.out.println(book.toString());
		}
		
	}

	@Override
	public List<LibraryBook> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBook> allBooks = new ArrayList<>();
		while(rs.next()) {
			allBooks.add(new LibraryBook(rs.getInt("bookId"), rs.getString("title"), rs.getInt("pubId")));
		}
		return allBooks;
	}

	@Override
	public void save(LibraryBook book) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book (title,pubId) values (?,?)");
		pstmt.setString(1, book.getBookTitle());
		pstmt.setInt(2, book.getPublisherId());
		pstmt.executeUpdate(); 
		
	}

	@Override
	public void update(LibraryBook book, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book set title = ?, pubId = ? where bookId = ?");
		pstmt.setString(1,params[0]);
		pstmt.setInt(2,Integer.parseInt(params[1]));
		pstmt.setInt(3, book.getBookId());
		pstmt.executeUpdate(); 
		
	}

	@Override
	public void delete(LibraryBook book) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book where bookId = ?");
		pstmt.setInt(1, book.getBookId());
		pstmt.executeUpdate(); 
		
	}

}
