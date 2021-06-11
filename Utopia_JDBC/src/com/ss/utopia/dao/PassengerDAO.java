/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Passenger;

/**
 * @author lukej
 *
 */
public class PassengerDAO extends TemplateDAO<Passenger>{

	public PassengerDAO(Connection conn) {
		super(conn);
	}
	public void addPassenger(Passenger p) throws ClassNotFoundException, SQLException {
		save("insert into passenger (booking_id,given_name,family_name,dob,gender,address) values (?,?,?,?,?,?)",
				new Object[] {p.getBookingId(),p.getFirstName(),p.getLastName(),p.getDob(),p.getGender(),p.getAddress()});
	}
	
	public void removePassenger(Passenger p) throws ClassNotFoundException, SQLException {
		delete("delete from passenger where id = ?",new Object[] {p.getPassengerId()});
	}
	
	public void updatePassenger(Passenger p) throws ClassNotFoundException, SQLException {
		update("update passenger set booking_id = ?,given_name = ?,family_name = ?,dob = ?,gender = ?,address = ? where id = ?",
				new Object[] {p.getBookingId(),p.getFirstName(),p.getLastName(),p.getDob(),p.getGender(),p.getAddress(),p.getPassengerId()});
	}
	
	public List<Passenger> readAllPassengers() throws ClassNotFoundException, SQLException {
		return read("select * from passenger",null);
	}
	
	@Override
	public List<Passenger> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Passenger> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new Passenger(rs.getInt("id"),rs.getInt("booking_id"),rs.getString("given_name"),rs.getString("family_name"),
					rs.getDate("dob"),rs.getString("gender"),rs.getString("address")));
		}
		return all;
	}
}
