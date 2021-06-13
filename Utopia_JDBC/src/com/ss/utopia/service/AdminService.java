package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;

public class AdminService {

	private ConnectionUtil cUtil = new ConnectionUtil();
	
	//Airport only functions
	public void addAirport(Airport airport) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.addAirport(airport);
			conn.commit();
			System.out.println("Airport Added");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Airport Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void removeAirport(Airport airport) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.removeAirport(airport);
			conn.commit();
			System.out.println("Airport Removed");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Airport Unable to be Removed");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void updateAirport(Airport airport) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			adao.updateAirport(airport);
			conn.commit();
			System.out.println("Airport Updated");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Airport Unable to be Updated");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public List<Airport> readAirport() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Airport> airports = null;
		try {
			conn = cUtil.getConnection();
			AirportDAO adao = new AirportDAO(conn);
			airports = adao.readAllAirports();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Airports");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return airports;
	}
	
	//Route only functions
	public List<Route> readRoute() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Route> routes = null;
		try {
			conn = cUtil.getConnection();
			RouteDAO rdao = new RouteDAO(conn);
			routes = rdao.readAllRoutes();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Routes");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return routes;
	}
	
	//Flight only functions
	public List<Flight> readFlight()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Flight> flights = null;
		try {
			conn = cUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			flights = fdao.readAllFlights();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Flights");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return flights;
	}

	public void updateFlight(Flight flight) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);;
			conn.commit();
			System.out.println("Flight Updated");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Flight Unable to be Updated");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void removeFlight(Flight flight) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			FlightDAO fdao = new FlightDAO(conn);
			fdao.removeFlight(flight);
			conn.commit();
			System.out.println("Flight Removed");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Flight Unable to be Removed");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	//Airplane only functions
	public List<Airplane> readAirplane()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Airplane> planes = null;
		try {
			conn = cUtil.getConnection();
			AirplaneDAO adao = new AirplaneDAO(conn);
			planes = adao.readAllAirplanes();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Airplanes");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return planes;
	}

	//AirplaneType only functions
	public List<AirplaneType> readAirplaneType()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<AirplaneType> types = null;
		try {
			conn = cUtil.getConnection();
			AirplaneTypeDAO adao = new AirplaneTypeDAO(conn);
			types = adao.readAllAirplaneTypes();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Airplane Types");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return types;
	}
	
	//Add a flight
	public void addFlight(Route route, List<Airport> ordest, Airplane airplane, Date departureTime, float seatPrice) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			int routeId = 0;
			if(route == null) {
			//Add the two airports, query won't allow duplicates
				AirportDAO adao = new AirportDAO(conn);
				adao.addAirport(ordest.get(0));
				adao.addAirport(ordest.get(1));
				//Create new route
				RouteDAO routeDAO = new RouteDAO(conn);
				Route newRoute = new Route(null,ordest.get(0).getAirportCode(),ordest.get(1).getAirportCode());
				routeId = routeDAO.addRouteWithPk(newRoute);
				List<Route> routes = readRoute();
			}else {
				routeId = route.getRouteId();
			}
			System.out.println("route id " + routeId);
			Flight flightToAdd = new Flight(null, routeId, airplane.getAirplaneId(), departureTime, 0, seatPrice);
			FlightDAO flightDAO = new FlightDAO(conn);
			flightDAO.addFlight(flightToAdd);
			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Flight Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
}
