/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingPaymentDAO;
import com.ss.utopia.dao.BookingUserDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.BookingPayment;
import com.ss.utopia.domain.BookingUser;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.User;

/**
 * @author lukej
 *
 */
public class TravelerService {

	private ConnectionUtil cUtil = new ConnectionUtil();
	
	public User verifyUser(String user, String pass) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			List<User> users = udao.readAllUsers();
			for(User u: users) {
				if(u.getPassword().equals(pass) && u.getUsername().equals(user)) {
					return u;
				}
			}
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Failed to check user");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return null;
	}
	
	public List<Booking> readUserBookings(User user) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Failed to check user");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return null;
	}
	
	public void bookFlight(Flight flight,User user, Passenger pass, Booking booking) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			int bookingId;
			conn = cUtil.getConnection();
			//Add Booking
			BookingDAO bdao = new BookingDAO(conn);
			bookingId = bdao.addBookingWithPK(booking);
			//Add booking user
			BookingUserDAO budao = new BookingUserDAO(conn);
			BookingUser buAdd = new BookingUser(bookingId,user.getUserId());
			//Add Booking payment
			BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
			BookingPayment bpAdd = new BookingPayment(bookingId,"stripe id",0);
			//Add passenger
			PassengerDAO pdao = new PassengerDAO(conn);
			pass.setBookingId(bookingId);
			pdao.addPassenger(pass);
			//Reserve Seat
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			conn.commit();
			System.out.println("Flight Booked");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Booking Failed");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public List<Flight> readFlight() throws ClassNotFoundException, SQLException {
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

	public List<AirplaneType> readAirplaneType() throws ClassNotFoundException, SQLException {
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

	public List<Airplane> readAirplane() throws ClassNotFoundException, SQLException {
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
}
