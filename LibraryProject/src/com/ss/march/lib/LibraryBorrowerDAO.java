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
public class LibraryBorrowerDAO implements LibraryDAO<LibraryBorrower> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBorrowerDAO d= new LibraryBorrowerDAO();
		for(LibraryBorrower t: d.getAll()) {
			System.out.println(t.toString());
		}
		LibraryBorrower l = new LibraryBorrower(3,"Ben Brode","Los Angeles, CA","720-000-0000");
		String[] update = new String[3];
		update[0] = "Micheal Moore";
		update[1] = "Canada, On";
		update[2] = "820-782-8900";
//		d.update(l, update);
//		d.save(l);
//		d.delete(l);
//		d.checkCardno(1);
		for(LibraryBorrower t: d.getAll()) {
			System.out.println(t.toString());
		}
//		System.out.println(d.checkCardno(5));
//		for(LibraryBookAuthorBranch t: d.avaliableBooks(1)) {
//			System.out.println(t.toString());
//		}
//		System.out.println();
//		for(LibraryBranch t: d.checkedOutBookBranches(2)) {
//			System.out.println(t.toString());
//		}
		for(LibraryBookAuthorBranch t: d.checkedOutBookFor(1)) {
			System.out.println(t.toString());
		}
	}

	@Override
	public List<LibraryBorrower> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBorrower> allBorrowers = new ArrayList<>();
		while(rs.next()) {
			allBorrowers.add(new LibraryBorrower(rs.getInt("cardNo"),rs.getString("name"),rs.getString("address"),rs.getString("phone")));
		}
		return allBorrowers;

	}

	@Override
	public void save(LibraryBorrower t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_borrower (name,address,phone) values (?,?,?)");
		pstmt.setString(1, t.getName());
		pstmt.setString(2, t.getAddress());
		pstmt.setString(3, t.getPhone());
		pstmt.executeUpdate(); 

		
	}

	@Override
	public void update(LibraryBorrower t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_borrower set name = ?, address = ?, phone = ? where cardNo = ?");
		pstmt.setString(1,params[0]);
		pstmt.setString(2,params[1]);
		pstmt.setString(3,params[2]);
        pstmt.setInt(4, t.getCardNo());
		pstmt.executeUpdate(); 

		
	}

	@Override
	public void delete(LibraryBorrower t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_borrower where cardNo = ?");
		pstmt.setInt(1, t.getCardNo());
		pstmt.executeUpdate(); 
	}
	
	public int checkCardno(int cardNo) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_borrower where cardNo = ?");
		pstmt.setInt(1, cardNo);
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			if(rs.getInt("cardNo") == cardNo) {
				return 1;
			}
		}
		return -1;
	}
	
	//Returns all books that have at least one available copy in the given branch
	public List<LibraryBookAuthorBranch> avaliableBooks(int branchId) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = getConnection().prepareStatement("select b.bookId as bookId, b.title as title, l.branchId as branch, bc.noOfCopies as copies, "
				+ "a.authorName as author, a.authorId as authorId from tbl_author a "
				+ "join tbl_book_authors ba on a.authorId = ba.authorId "
				+ "join tbl_book b on b.bookId = ba.bookId "
				+ "join tbl_book_copies bc on bc.bookId = b.bookId "
				+ "join tbl_library_branch l on l.branchId = bc.branchId "
				+ "where noOfCopies > 0 and l.branchId = ?;");
		pstmt.setInt(1,branchId);
		ResultSet rs = pstmt.executeQuery();
		List<LibraryBookAuthorBranch> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryBookAuthorBranch(rs.getString("author"),rs.getInt("authorId"),rs.getString("title"),rs.getInt("bookId"),rs.getInt("branch"),rs.getInt("copies")));
		}
		return all;
	}
	
	//Returns every library branch the borrower has a book checked out from
	public List<LibraryBranch> checkedOutBookBranches(int cardNo) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = getConnection().prepareStatement("select lb.branchId as branchId, "
				+ "lb.branchName as branchName, lb.branchAddress as branchAddress "
				+ "from tbl_borrower bo "
				+ "join tbl_book_loans bl on bl.cardNo = bo.cardNo "
				+ "join tbl_library_branch lb on lb.branchId = bl.branchId "
				+ "where bo.cardno = ?");
		pstmt.setInt(1,cardNo);
		ResultSet rs = pstmt.executeQuery();
		List<LibraryBranch> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryBranch(rs.getInt("branchId"),rs.getString("branchName"),rs.getString("branchAddress")));
		}
		return all;
	}
	
	//List all books that the current cardno has checked out that have not been returned
	public List<LibraryBookAuthorBranch> checkedOutBookFor(int cardNo) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = getConnection().prepareStatement("select b.title as title, a.authorName as author, "
				+ "a.authorId as authorId, b.bookId as bookId, bl.branchId as branchId from tbl_borrower bo "
				+ "join tbl_book_loans bl on bl.cardNo = bo.cardNo "
				+ "join tbl_book b on b.bookId = bl .bookId "
				+ "join tbl_book_authors ba on ba.bookId = b.bookId "
				+ "join tbl_author a on a.authorId = ba.authorId "
				+ "where bl.cardNo = ? and bl.dateIn is null");
		pstmt.setInt(1,cardNo);
		ResultSet rs = pstmt.executeQuery();
		List<LibraryBookAuthorBranch> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryBookAuthorBranch(rs.getString("author"),rs.getInt("authorId"),rs.getString("title"),rs.getInt("bookId"),rs.getInt("branchId"),0));
		}
		return all;
	}
}
