/**
 * 
 */
package com.ss.march.lib;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lukej
 *
 */
public class LibraryBookLoanDAO implements LibraryDAO<LibraryBookLoan> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBookLoanDAO d= new LibraryBookLoanDAO();
		LibraryBookLoan l = new LibraryBookLoan(2,1,1,null,null,null);
//		d.save(l);
		for(LibraryBookLoan t: d.getAll()) {
			System.out.println(t.toString());
		}
		String[] update = new String[1];
		update[0] = "2021-06-30";
//		d.update(l, update);
//		d.returnBook(l);
		d.delete(l);
		for(LibraryBookLoan t: d.getAll()) {
			System.out.println(t.toString());
		}

	}

	@Override
	public List<LibraryBookLoan> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_book_loans");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBookLoan> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new LibraryBookLoan(rs.getInt("bookId"),rs.getInt("branchId"),rs.getInt("cardNo"),rs.getDate("dateOut"), rs.getDate("dueDate"), rs.getDate("dateIn")));
		}
		return all;

	}

	@Override
	public void save(LibraryBookLoan t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_book_loans (bookId,branchId,cardNo,dateOut,dueDate) values (?,?,?,curdate(),date_add(now(),interval 2 week))");
		pstmt.setInt(1, t.getBookId());
		pstmt.setInt(2, t.getBranchId());
		pstmt.setInt(3, t.getCardNo());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryBookLoan t, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_loans set dueDate = ? where bookId = ? and branchId = ? and cardNo = ?");
		pstmt.setDate(1,Date.valueOf(params[0]));
//		pstmt.setString(1,params[0]);
		pstmt.setInt(2, t.getBookId());
		pstmt.setInt(3, t.getBranchId());
        pstmt.setInt(4, t.getCardNo());
		pstmt.executeUpdate(); 
	}

	@Override
	public void delete(LibraryBookLoan t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_book_loans where bookId = ? and branchId = ? and cardNo = ?");
		pstmt.setInt(1, t.getBookId());
		pstmt.setInt(2, t.getBranchId());
        pstmt.setInt(3, t.getCardNo());
		pstmt.executeUpdate(); 
	}
	
	public void returnBook(LibraryBookLoan t) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_book_loans set dateIn = curdate() where bookId = ? and branchId = ? and cardNo = ?");
		pstmt.setInt(1, t.getBookId());
		pstmt.setInt(2, t.getBranchId());
        pstmt.setInt(3, t.getCardNo());
        pstmt.executeUpdate();
	}

}
