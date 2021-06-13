/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 * @author lukej
 *
 */
public class ConnectionUtil {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/utopia";
	static final String username = "luke";
	static final String password = "password";
		
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password);
		conn.setAutoCommit(Boolean.FALSE);//Why not just false?
		return conn;
	}
}
