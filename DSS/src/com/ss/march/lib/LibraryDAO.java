package com.ss.march.lib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public interface LibraryDAO<T> {
	
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/library";
	static final String username = "root";
	static final String password = "Zambia2010";
	
	List<T> getAll() throws ClassNotFoundException, SQLException;
	
	void save(T t) throws ClassNotFoundException, SQLException;
	
	void update(T t, String[] params) throws ClassNotFoundException, SQLException;
	
	void delete(T t) throws ClassNotFoundException, SQLException;
	
	public default Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName(driver);
		Connection conn = DriverManager.getConnection(url,username,password);
		return conn;
	}
}
