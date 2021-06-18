/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Airplane;

/**
 * @author lukej
 *
 */
public class AirplaneDAO extends TemplateDAO<Airplane>{

	public AirplaneDAO(Connection conn) {
		super(conn);
	}
	
	public void addAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("insert into airplane(type_id) values (?)",new Object[] {airplane.getTypeId()});
	}
	
	public void removeAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("delete from airplane where id = ?",new Object[] {airplane.getAirplaneId()});
	}
	
	public void updateAirplane(Airplane airplane) throws ClassNotFoundException, SQLException {
		save("update airplane set type_id = ? where id = ?",new Object[] {airplane.getTypeId(),airplane.getAirplaneId()});
	}
	
	public List<Airplane> readAllAirplanes() throws ClassNotFoundException, SQLException {
		return read("select * from airplane",null);
	}
	
	@Override
	public List<Airplane> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Airplane> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new Airplane(rs.getInt("id"),rs.getInt("type_id")));
		}
		return all;
	}
}
