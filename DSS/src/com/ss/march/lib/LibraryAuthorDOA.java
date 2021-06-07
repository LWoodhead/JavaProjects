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
public class LibraryAuthorDOA implements LibraryDAO<LibraryAuthor> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryAuthorDOA d = new LibraryAuthorDOA();
		for(LibraryAuthor t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryAuthor l = new LibraryAuthor(4,"Frank Herbert");
		String[] update = new String[1];
		update[0] = "Frank Herbert";
//		d.update(l, update);
//		d.save(l);
//		d.delete(l);
		for(LibraryAuthor t: d.getAll()) {
			System.out.println(t.toString());
		}
	}

	@Override
	public List<LibraryAuthor> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_author");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryAuthor> allAuthors = new ArrayList<>();
		while(rs.next()) {
			allAuthors.add(new LibraryAuthor(rs.getInt("authorId"),rs.getString("authorName")));
		}
		return allAuthors;

	}

	@Override
	public void save(LibraryAuthor t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_author (authorName) values (?)");
		pstmt.setString(1,t.getAuthorName());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryAuthor t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_author set authorName = ? where authorId = ?");
		pstmt.setString(1,params[0]);
        pstmt.setInt(2, t.getAuthorId());
		pstmt.executeUpdate(); 
	}

	@Override
	public void delete(LibraryAuthor t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_author where authorId = ?");
		pstmt.setInt(1, t.getAuthorId());
		pstmt.executeUpdate(); 		
	}

}
