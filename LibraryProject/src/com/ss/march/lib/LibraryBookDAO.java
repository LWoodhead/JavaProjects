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
		d.printIdGenreTitleAuthor();
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
	
	public void linkTables(int bookId,int authorId) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_authors (bookId,authorId) values (?,?)");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, authorId);
		pstmt.executeUpdate(); 
	}
	
	public void printIdGenreTitleAuthor() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select a.authorId as authorId, bo.bookId as bookId, bo.title as title, \r\n"
				+ "pu.publisherName as publisher, g.genre_name as genre from tbl_author a\r\n"
				+ "join tbl_book_authors ba on ba.authorId = a.authorId\r\n"
				+ "join tbl_book bo on bo.bookId = ba.bookId\r\n"
				+ "join tbl_publisher pu on pu.publisherId = bo.pubId\r\n"
				+ "join tbl_book_genres bg on bg.bookId = bo.bookId\r\n"
				+ "join tbl_genre g on g.genre_id = bg.genre_id;");
		ResultSet rs = pstmt.executeQuery(); 
		while(rs.next()) {
			System.out.println("Author ID: " + rs.getInt("authorId") + " Book ID: " + rs.getInt("bookId") 
			+ " Title: " + rs.getString("title")+ " Publisher: " + rs.getString("publisher") + " Genre: " +  rs.getString("genre"));
		}
	}

}
