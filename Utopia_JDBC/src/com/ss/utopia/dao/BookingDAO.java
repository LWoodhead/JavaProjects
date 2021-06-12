/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Booking;

/**
 * @author lukej
 *
 */
public class BookingDAO extends TemplateDAO<Booking>{

	public BookingDAO(Connection conn) {
		super(conn);
	}
	
	public void addBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("insert into booking (is_active,confirmation_code) values (?,?)",new Object[] {booking.getIsActive(),booking.getConfirmationCode()});
	}
	
	public void removeBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("delete from booking where id = ?",new Object[] {booking.getBookingId()});
	}
	
	public void updateBooking(Booking booking) throws ClassNotFoundException, SQLException {
		save("update booking set is_active = ?, confirmation_code = ? where id = ?",new Object[] {booking.getIsActive(),booking.getConfirmationCode(),
				booking.getBookingId()});
	}
	
	public List<Booking> readAllBookings() throws ClassNotFoundException, SQLException {
		return read("select * from booking",null);
	}
	
	@Override
	public List<Booking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Booking> all = new ArrayList<>();
		while(rs.next()) {
		}
		return all;
	}
}
