/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.FlightBooking;

/**
 * @author lukej
 *
 */
public class FlightBookingDAO extends TemplateDAO<FlightBooking>{

	public FlightBookingDAO(Connection conn) {
		super(conn);
	}
	
	public void addFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException {
		save("insert into flight_bookings (flight_id,booking_id) values (?,?)",new Object[] {fb.getFlightId(),fb.getBookingId()});
	}
	
	public void removeFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException {
		save("delete from flight_bookings where flight_id = ? and booking_id = ?",new Object[] {fb.getFlightId(),fb.getBookingId()});
	}
	
	public void updateFlightBooking(FlightBooking fb) throws ClassNotFoundException, SQLException {
		save("update flight_bookings set flight_id = ? , booking_id = ? where flight_id = ? and booking_id = ?",
				new Object[] {fb.getFlightId(),fb.getBookingId(),fb.getFlightId(),fb.getBookingId()});
	}
	
	public List<FlightBooking> readAllFlightBookings() throws ClassNotFoundException, SQLException {
		return read("select * from flight_bookings",null);
	}
	
	@Override
	public List<FlightBooking> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<FlightBooking> all = new ArrayList<FlightBooking>();
		while(rs.next()) {
			all.add(new FlightBooking(rs.getInt("flight_id"),rs.getInt("booking_id")));
		}
		return all;
	}
}
