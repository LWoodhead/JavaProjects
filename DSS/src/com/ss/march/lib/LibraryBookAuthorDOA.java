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
public class LibraryBookAuthorDOA implements LibraryDAO<LibraryBookAuthor> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBookAuthorDOA d= new LibraryBookAuthorDOA();
		for(LibraryBookAuthor t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryBookAuthor l = new LibraryBookAuthor("Dune","Frank Herbert",6,5,1);
		String[] update = new String[2];
		update[0] = "";
		update[1] = "";
//		d.update(l, update);
		d.save(l);
//		d.delete(l);
		for(LibraryBookAuthor t: d.getAll()) {
			System.out.println(t.toString());
		}
	}

	@Override
	public List<LibraryBookAuthor> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("SELECT distinct b.title as 'title', a.authorName as 'author', b.bookId as 'bookId'," 
				+ "a.authorId as 'authorId', b.pubId as 'pubId' from tbl_book b "
				+ "join tbl_book_authors ba on ba.bookId = b.bookId "
				+ "join tbl_author a on ba.authorId = a.authorId;");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBookAuthor> allBookAuthor = new ArrayList<>();
		while(rs.next()) {
			allBookAuthor.add(new LibraryBookAuthor(rs.getString("title"),rs.getString("author"),rs.getInt("bookId"),rs.getInt("authorId"),rs.getInt("pubId")));
		}
		return allBookAuthor;

	}

	@Override
	public void save(LibraryBookAuthor ba) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_authors (bookId,authorId) values (?,?)");
		pstmt.setInt(1, ba.getBookId());
		pstmt.setInt(2, ba.getAuthorId());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryBookAuthor ba, String[] params) throws ClassNotFoundException, SQLException {
		//Can't update without cascade
	}

	@Override
	public void delete(LibraryBookAuthor ba) throws ClassNotFoundException, SQLException {
		//Can't delete without cascade
	}

}
