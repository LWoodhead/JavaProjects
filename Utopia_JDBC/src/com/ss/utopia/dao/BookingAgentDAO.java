/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.BookingAgent;

/**
 * @author lukej
 *
 */
public class BookingAgentDAO extends TemplateDAO<BookingAgent>{

	public BookingAgentDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookingAgent(BookingAgent ba) throws ClassNotFoundException, SQLException {
		save("insert into booking_agent (booking_id,agent_id) values (?,?)",
				new Object[] {ba.getBookingId(),ba.getAgentId()});
	}
	
	public void removeBookingAgent(BookingAgent ba) throws ClassNotFoundException, SQLException {
		save("delete from booking_agent where booking_id = ? and agent_id = ?",
				new Object[] {ba.getBookingId(),ba.getAgentId()});
	}
	
	public void updateBookingAgent(BookingAgent ba) throws ClassNotFoundException, SQLException {
		save("update booking_agent set booking_id = ? , agent_id = ? where booking_id = ? and agent_id = ?",
				new Object[] {ba.getBookingId(),ba.getAgentId(),ba.getBookingId(),ba.getAgentId()});
	}
	
	public List<BookingAgent> readAllBookingAgents() throws ClassNotFoundException, SQLException {
		return read("select * from booking_agent",null);
	}
	
	@Override
	public List<BookingAgent> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingAgent> all = new ArrayList<BookingAgent>();
		while(rs.next()) {
			all.add(new BookingAgent(rs.getInt("booking_id"),rs.getInt("agent_id")));
		}
		return all;
	}
}
