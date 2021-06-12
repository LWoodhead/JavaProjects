/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.BookingUser;

/**
 * @author lukej
 *
 */
public class BookingUserDAO extends TemplateDAO<BookingUser>{

	public BookingUserDAO(Connection conn) {
		super(conn);
	}
	
	public void addBookingUser(BookingUser ba) throws ClassNotFoundException, SQLException {
		save("insert into booking_user (booking_id,user_id) values (?,?)",new Object[] {ba.getBookingId(),ba.getUserId()});
	}
	
	public void removeBookingUser(BookingUser ba) throws ClassNotFoundException, SQLException {
		save("delete from booking_user where booking_id = ? and user_id = ?",new Object[] {ba.getBookingId(),ba.getUserId()});
	}
	
	public void updateBookingUser(BookingUser ba) throws ClassNotFoundException, SQLException {
		save("update booking_user set booking_id = ?, user_id = ? where booking_id = ? and user_id= ?",
				new Object[] {ba.getBookingId(),ba.getUserId(),ba.getBookingId(),ba.getUserId()});
	}
	
	public List<BookingUser> readAllBookingUsers() throws ClassNotFoundException, SQLException {
		return read("select * from booking_user",null);
	}
	
	@Override
	public List<BookingUser> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingUser> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new BookingUser(rs.getInt("booking_id"), rs.getInt("user_id")));
		}
		return all;
	}
}
