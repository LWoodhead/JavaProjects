/**
 * 
 */
package com.ss.utopia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.utopia.domain.Route;

/**
 * @author lukej
 *
 */
public class RouteDAO extends TemplateDAO<Route>{

	public RouteDAO(Connection conn) {
		super(conn);
	}

	public void addRoute(Route route) throws ClassNotFoundException, SQLException {
		save("insert into route(origin_id,destination_id) values (?,?)",new Object[] {route.getOriginAirport(),route.getDestinationAirport()});
	}
	
	public void removeRoute(Route route) throws ClassNotFoundException, SQLException {
		delete("delete from route where id = ?",new Object[] {route.getRouteId()});
	}
	
	public void updateRoute(Route route) throws ClassNotFoundException, SQLException {
		update("update route set origin_id = ?, destination_id = where id = ?",new Object[] {route.getOriginAirport(),route.getDestinationAirport(),route.getRouteId()});
	}
	
	public List<Route> readAllRoutes() throws ClassNotFoundException, SQLException {
		return read("select * from airport",null);
	}
	
	@Override
	public List<Route> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
		List<Route> all = new ArrayList<>();
		while(rs.next()) {
			all.add(new Route(rs.getInt("id"),rs.getString("origin_id"),rs.getString("destination_id")));
		}
		return all;
	}
}
