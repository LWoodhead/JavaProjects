/**
 * 
 */
package com.ss.march.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author lukej
 *
 */
public class DBConnection {

	/**
	 * @param args
	 */
	private static final String driver = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/library";
	private static final String username = "root";
	private static final String password = "Zambia2010";
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException{
		// TODO Auto-generated method stub
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password); 
//		Statement stmt = conn.createStatement();
		PreparedStatement pstmt = conn.prepareStatement("select * from tbl_library_branch where branchId = ?"); 
//		String sql = "select * from tbl_library_branch where branchId = '1'";
//		ResultSet rs = stmt.executeQuery(sql);
		pstmt.setString(1, "5");
		ResultSet rs = pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString("branchId"));
			System.out.println(rs.getString("branchName"));
			System.out.println(rs.getString("branchAddress"));
		}
	}
	
}
//branchName branchAddress