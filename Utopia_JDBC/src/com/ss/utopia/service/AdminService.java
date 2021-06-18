package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.ss.utopia.dao.AirplaneDAO;
import com.ss.utopia.dao.AirplaneTypeDAO;
import com.ss.utopia.dao.AirportDAO;
import com.ss.utopia.dao.BookingDAO;
import com.ss.utopia.dao.BookingPaymentDAO;
import com.ss.utopia.dao.FlightBookingDAO;
import com.ss.utopia.dao.FlightDAO;
import com.ss.utopia.dao.PassengerDAO;
import com.ss.utopia.dao.RouteDAO;
import com.ss.utopia.dao.UserDAO;
import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.BookingPayment;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.FlightBooking;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.User;

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

	//Booking only functions
	public List<Booking> readBooking()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Booking> bookings = null;
		try {
			conn = cUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bookings = bdao.readAllBookings();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Bookings");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return bookings;
	}
	
	public void addBooking(Booking b) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			BookingDAO bdao = new BookingDAO(conn);
			bdao.addBooking(b);
			conn.commit();
			System.out.println("Booking Added");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Booking Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	//Passenger only functions
	public List<Passenger> readPassenger()throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<Passenger> pass = null;
		try {
			conn = cUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pass = pdao.readAllPassengers();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read Passengers");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return pass;
	}
	
	public void updatePassenger(Passenger p) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.updatePassenger(p);
			conn.commit();
			System.out.println("Passenger Updated");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Passenger Unable to be Updated");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void removePassenger(Passenger p) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.removePassenger(p);
			conn.commit();
			System.out.println("Passenger Removed");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Passenger Unable to be Removed");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void addPassenger(Passenger p) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			PassengerDAO pdao = new PassengerDAO(conn);
			pdao.addPassenger(p);
			conn.commit();
			System.out.println("Passenger Added");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Passenger Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
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
			Flight flightToAdd = new Flight(null, routeId, airplane.getAirplaneId(), departureTime, 0, seatPrice);
			FlightDAO flightDAO = new FlightDAO(conn);
			flightDAO.addFlight(flightToAdd);
			conn.commit();
			System.out.println("Flight Added");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Flight Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}

	public void addBookingPassenger(Booking booking, Passenger pass, Flight flight)throws ClassNotFoundException, SQLException {
		Connection conn = null;
		int bookingId = 0;
		try {
			conn = cUtil.getConnection();
			//If booking is null we need to add a new booking
			if(booking != null) {
				//Save booking
				BookingDAO bdao = new BookingDAO(conn);
				bookingId = bdao.addBookingWithPK(booking);
				//Save flight booking
				FlightBookingDAO fbdao = new FlightBookingDAO(conn);
				FlightBooking fb = new FlightBooking(flight.getFlightId(), bookingId);
				fbdao.addFlightBooking(fb);
				//Save passenger
				pass.setBookingId(bookingId);
				PassengerDAO pdao = new PassengerDAO(conn);
				pdao.addPassenger(pass);
				//Save booking payment
				BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
				bpdao.addBookingPayment(new BookingPayment(bookingId,"stripe id",0));
			//Otherwise all that is needed is to save the passenger
			}else{
				PassengerDAO pdao = new PassengerDAO(conn);
				pdao.addPassenger(pass);
			}
			//Either Way Increase Reserved Seats by One
			flight.setReservedSeats(flight.getReservedSeats()+1);
			FlightDAO fdao = new FlightDAO(conn);
			fdao.updateFlight(flight);
			conn.commit();
			System.out.println("Added Passenger to Booking");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Failed to Add Passenger to Booking");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	//User functions
	public void addUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.addUser(user);
			conn.commit();
			System.out.println("User Added");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("User Unable to be Added");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void removeUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.removeUser(user);
			conn.commit();
			System.out.println("User Removed");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("User Unable to be Removed");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			udao.updateUser(user);
			conn.commit();
			System.out.println("User Updated");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("User Unable to be Updated");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
	
	public List<User> readUser() throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<User> users = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			users = udao.readAllUsers();
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read users");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return users;
	}
	
	public List<User> readUserByRoleId(int roleId) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		List<User> users = null;
		try {
			conn = cUtil.getConnection();
			UserDAO udao = new UserDAO(conn);
			users = udao.readAllUsers();
			for(int i=0;i<users.size();i++) {
				if(users.get(i).getRoleId() != roleId) {
					users.remove(i);
					i--;
				}
			}
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Read users");
			e.printStackTrace();
		}finally {
			conn.close();
		}
		return users;
	}

	public void cancelTrip(Booking booking) throws SQLException {
		Connection conn = null;
		try {
			conn = cUtil.getConnection();
			BookingPaymentDAO bpdao = new BookingPaymentDAO(conn);
			BookingPayment paymentUpdate = null;
			List<BookingPayment> bookPay = bpdao.readAllBookingPayments();
			for(BookingPayment bp : bookPay) {
				if(bp.getBookingId() == booking.getBookingId());
				paymentUpdate = bp;
			}
			if(paymentUpdate != null) {
				paymentUpdate.setRefunded(1);
				bpdao.updateBookingPayment(paymentUpdate);
			}
			BookingDAO bdao = new BookingDAO(conn);
			bdao.updateBooking(booking);
			conn.commit();
			System.out.println("Booking Canceled");
		}catch(Exception e) {
			conn.rollback();
			System.out.println("Unable to Cancel");
			e.printStackTrace();
		}finally {
			conn.close();
		}
	}
}

