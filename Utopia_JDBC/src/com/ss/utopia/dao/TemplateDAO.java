package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class TemplateDAO<T> {
	static final String driver = "com.mysql.cj.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/library";
	static final String username = "root";
	static final String password = "Zambia2010";
	
	public static Connection conn = null;
	
	public TemplateDAO(Connection conn) {
		this.conn = conn;
	}
	
	//Either object array or string array
	//Pass in the query and the array of strings to set, input null if nothing needs to be changed
	public List<T> read(String query,Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		//Set all members that need to be set
		if(vals != null) {
			int count = 1;
			for(Object val : vals) {
				pstmt.setObject(count, val);
				count++;
			}
		}
		ResultSet rs = pstmt.executeQuery();
		return extractData(rs);
	}

	public void save(String query,Object[] vals) throws ClassNotFoundException, SQLException{
		PreparedStatement pstmt = conn.prepareStatement(query);
		//Set all members that need to be set
		if(vals != null) {
			int count = 1;
			for(Object val : vals) {
				pstmt.setObject(count, val);
				count++;
			}
		}
		pstmt.executeQuery();
	}
	
	public abstract List<T> extractData(ResultSet rs) throws ClassNotFoundException, SQLException;		
}
