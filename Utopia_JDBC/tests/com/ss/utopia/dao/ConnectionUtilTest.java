/**
 * 
 */
package com.ss.utopia.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import com.ss.utopia.service.ConnectionUtil;

/**
 * @author lukej
 *
 */
public class ConnectionUtilTest {
	private ConnectionUtil util = new ConnectionUtil();
	
	@Test
	public void getConnectionTest() throws ClassNotFoundException, SQLException {
		Connection conn = util.getConnection();
		assertNotEquals(conn,null);
		assertEquals(conn.isValid(0),true);
		conn.close();
		assertEquals(conn.isClosed(),true);
	}
}
