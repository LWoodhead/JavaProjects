/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.BookingGuest;

/**
 * @author lukej
 *
 */
public class BookingGuestDAO extends TemplateDAO<BookingGuest>{

	public BookingGuestDAO(Connection conn) {
		super(conn);
	}

	public void addBookingGuest(BookingGuest bg) throws ClassNotFoundException, SQLException {
		save("insert into booking_guest (booking_id,contact_email,contact_phone) values (?,?,?)",
				new Object[] {bg.getBookingId(),bg.getEmail(),bg.getPhone()});
	}
	
	public void removeBookingGuest(BookingGuest bg) throws ClassNotFoundException, SQLException {
		save("delete from booking_guest where booking_id = ?",new Object[] {bg.getBookingId()});
	}
	
	public void updateBookingGuest(BookingGuest bg) throws ClassNotFoundException, SQLException {
		save("update booking_guest set booking_id = ?, contact_email = ?, contact_phone = ? where booking_id = ?",
				new Object[] {bg.getBookingId(),bg.getEmail(),bg.getPhone(),bg.getBookingId()});
	}
	
	public List<BookingGuest> readAllBookingGuests() throws ClassNotFoundException, SQLException {
		return read("select * from booking_guest",null);
	}
	
	@Override
	public List<BookingGuest> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<BookingGuest> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new BookingGuest(rs.getInt("booking_id"), rs.getString("contact_email"), rs.getString("contact_phone")));
		}
		return all;
	}
}
