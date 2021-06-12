/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.AirplaneType;

/**
 * @author lukej
 *
 */
public class AirplaneTypeDAO extends TemplateDAO<AirplaneType>{

	public AirplaneTypeDAO(Connection conn) {
		super(conn);
	}
	
	public void addAirplaneType(AirplaneType type) throws ClassNotFoundException, SQLException {
		save("insert into airplane_type(max_capacity) values (?)",new Object[] {type.getMaxCapacity()});
	}
	
	public void removeAirplaneType(AirplaneType type) throws ClassNotFoundException, SQLException {
		save("delete from airplane_type where id = ?",new Object[] {type.getAirplaneTypeId()});
	}
	
	public void updateAirplaneType(AirplaneType type) throws ClassNotFoundException, SQLException {
		save("update airplane_type set max_capacity = ? where id = ?",new Object[] {type.getMaxCapacity(),type.getAirplaneTypeId()});
	}
	
	public List<AirplaneType> readAllAirplaneTypes() throws ClassNotFoundException, SQLException {
		return read("select * from airplane_type",null);
	}
	
	@Override
	public List<AirplaneType> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<AirplaneType> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new AirplaneType(rs.getInt("id"),rs.getInt("max_capacity")));
		}
		return all;
	}
}
