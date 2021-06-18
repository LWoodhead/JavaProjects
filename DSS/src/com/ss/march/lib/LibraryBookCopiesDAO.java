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
public class LibraryBookCopiesDAO implements LibraryDAO<LibraryBookCopies> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBookCopiesDAO d= new LibraryBookCopiesDAO();
		for(LibraryBookCopies t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryBookCopies l = new LibraryBookCopies(1,3,5);
		String[] update = new String[1];
		update[0] = "11";
//		d.update(l, update);
//		d.save(l);
//		d.delete(l);
//		d.subtractCopy(3, 3);
		d.addCopy(3, 3);
		System.out.println();
		for(LibraryBookCopies t: d.getAll()) {
			System.out.println(t.toString());
		}
//		System.out.println(d.returnNumCopies(1, 2));
	}

	@Override
	public List<LibraryBookCopies> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book_copies");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBookCopies> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryBookCopies(rs.getInt("bookId"),rs.getInt("branchId"),rs.getInt("noOfCopies")));
		}
		return all;
	}

	@Override
	public void save(LibraryBookCopies t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_copies (bookId,branchId,noOfcopies) values (?,?,?)");
		pstmt.setInt(1, t.getBookId());
		pstmt.setInt(2, t.getBranchId());
		pstmt.setInt(3, t.getNumCopies());
		pstmt.executeUpdate(); 

		
	}

	@Override
	public void update(LibraryBookCopies t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_copies set noOfcopies = ? where bookid = ? and branchId = ?");
		pstmt.setInt(1,Integer.parseInt(params[0]));
        pstmt.setInt(2, t.getBookId());
        pstmt.setInt(3, t.getBranchId());
		pstmt.executeUpdate();
	}

	@Override
	public void delete(LibraryBookCopies t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book_copies where bookid = ? and branchId = ?");
		pstmt.setInt(1, t.getBookId());
        pstmt.setInt(2, t.getBranchId());
		pstmt.executeUpdate(); 
	}

	public int returnNumCopies(int bookId,int branchId) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = getConnection().prepareStatement("select noOfcopies from tbl_book_copies where bookId = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			return rs.getInt("noOfcopies");	
		}
		return -1;
	}
	public void subtractCopy(int bookId, int branchId) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_copies set noOfcopies = noOfcopies - 1 where bookid = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		pstmt.executeUpdate();
	}
	public void addCopy(int bookId, int branchId) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_copies set noOfcopies = noOfcopies + 1 where bookid = ? and branchId = ?");
		pstmt.setInt(1, bookId);
		pstmt.setInt(2, branchId);
		pstmt.executeUpdate();
	}
}
