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
public class LibraryBranchDAO implements LibraryDAO<LibraryBranch> {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LibraryBranchDAO d = new LibraryBranchDAO();
		for(LibraryBranch branch: d.getAll()) {
			System.out.println(branch.getBranchId() + " " + branch.getBranchName() +" "+branch.getBranchAddress());
		}
		LibraryBranch l = new LibraryBranch(5,"State Library","New York");
		String[] update = new String[2];
		update[0] = "State Library";
		update[1] = "California";
//		d.update(l, update);
//		d.save(l);
//		d.delete(l);
		for(LibraryBranch branch: d.getAll()) {
			System.out.println(branch.getBranchId() + " " + branch.getBranchName() +" "+branch.getBranchAddress());
		}
	}

	@Override
	public List<LibraryBranch> getAll() throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("select * from tbl_library_branch");
		ResultSet rs = pstmt.executeQuery(); 
		List<LibraryBranch> allBranches = new ArrayList<>();
		while(rs.next()) {
			allBranches.add(new LibraryBranch(rs.getInt("branchId"),rs.getString("branchName"),rs.getString("branchAddress")));
		}
		return allBranches;
	}

	@Override
	public void save(LibraryBranch branch) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("insert into tbl_library_branch (branchname,branchAddress) values (?,?)");
		pstmt.setString(1, branch.getBranchName());
		pstmt.setString(2, branch.getBranchAddress());
		pstmt.executeUpdate(); 
	}

	@Override
	public void update(LibraryBranch branch, String[] params) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("update tbl_library_branch set branchName = ?, branchAddress = ? where branchId = ?");
		pstmt.setString(1,params[0]);
		pstmt.setString(2,params[1]);
		pstmt.setInt(3, branch.getBranchId());
		pstmt.executeUpdate(); 
	}

	@Override
	public void delete(LibraryBranch branch) throws ClassNotFoundException, SQLException {
		PreparedStatement pstmt = getConnection().prepareStatement("delete from tbl_library_branch where branchId = ?");
		pstmt.setInt(1, branch.getBranchId());
		pstmt.executeUpdate(); 
	}

}
