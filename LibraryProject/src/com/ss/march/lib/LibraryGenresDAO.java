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
public class LibraryGenresDAO implements LibraryDAO<LibraryGenres> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryGenresDAO d= new LibraryGenresDAO();
		for(LibraryGenres t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryGenres l = new LibraryGenres("Horror",5);
		String[] update = new String[2];
		update[0] = "Horror2";
		update[1] = "";
//		d.update(l, update);
		d.save(l);
//		d.delete(l);
		for(LibraryGenres t: d.getAll()) {
			System.out.println(t.toString());
		}

	}

	@Override
	public List<LibraryGenres> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_genre");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryGenres> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryGenres(rs.getString("genre_name"),rs.getInt("genre_id")));
		}
		return all;

	}

	@Override
	public void save(LibraryGenres t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_genre (genre_name) values (?)");
		pstmt.setString(1, t.getGenreName());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryGenres t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_genre set genre_name = ? where genre_id = ?");
		pstmt.setString(1,params[0]);
        pstmt.setInt(2, t.getGenreId());
		pstmt.executeUpdate(); 
	}

	@Override
	public void delete(LibraryGenres t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_genre where genre_id = ?");
		pstmt.setInt(1, t.getGenreId());
		pstmt.executeUpdate(); 
	}
	
	//Add a book_genres entry with an existing book and genre Id
	public void linkTables(int genreId,int bookId) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_genres (genre_id,bookId,) values (?,?)");
		pstmt.setInt(1, genreId);
		pstmt.setInt(2, bookId);
		pstmt.executeUpdate(); 
	}

}
