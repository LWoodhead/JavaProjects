/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airport;

/**
 * @author lukej
 *
 */
public class AirportDAO extends TemplateDAO<Airport> {

	public AirportDAO(Connection conn) {
		super(conn);
	}

	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("insert into airport (iata_id,city) values (?,?)",new Object[] {airport.getAiportCode(),airport.getCity()});
	}
	
	public void removeAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("delete from airport where iata_id = ?",new Object[] {airport.getAiportCode()});
	}
	
	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		save("update airport set iata_id = ?, city = ? where iata_id = ?",new Object[] {airport.getAiportCode(),airport.getCity()});
	}
	
	public List<Airport> readAllAirports() throws ClassNotFoundException, SQLException {
		return read("select * from airport",null);
	}
	
	@Override
	public List<Airport> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airport> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new Airport(rs.getString("iata_id"),rs.getString("city")));
		}
		return all;
	}
}
