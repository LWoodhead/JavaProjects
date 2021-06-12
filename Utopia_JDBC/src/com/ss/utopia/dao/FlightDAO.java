/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Flight;

/**
 * @author lukej
 *
 */
public class FlightDAO extends TemplateDAO<Flight>{

	public FlightDAO(Connection conn) {
		super(conn);
	}

	public void addFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("insert into flight(id,route_id,airplane_id,departure_time,reserved_seats,seat_price) values (?,?,?,?,?,?)",
				new Object[] {flight.getFlightId(),flight.getRouteId(),flight.getAirplaneId(),flight.getDepartureTime(),
						flight.getReservedSeats(),flight.getSeatPrice()});
	}
	
	public void removeFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("delete from flight where id = ?",new Object[] {flight.getFlightId()});
		
	}
	
	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		save("update flight set route_id = ?, airplane_id = ?, departure_time = ?, reserved_seats = ?, seat_price = ? where id = ?",
				new Object[] {flight.getRouteId(),flight.getAirplaneId(),flight.getDepartureTime(),flight.getReservedSeats(),
						flight.getSeatPrice(),flight.getFlightId()});
	}
	
	public List<Flight> readAllFlights() throws ClassNotFoundException, SQLException {
		return read("select * from flight",null);
	}
	
	@Override
	public List<Flight> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Flight> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new Flight(rs.getInt("id"),rs.getInt("route_id"),rs.getInt("airplane_id"),rs.getDate("departure_time"),
					rs.getInt("reserved_seats"),rs.getFloat("seat_price")));
		}
		return all;
	}
}
