/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.BookingPayment;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.User;

/**
 * @author lukej
 *
 */
public class AdminMenu {
	
	private final int empId = 3;
	private final int travId = 2;
	private final int adminId = 1;
	
	public void AdminMenuRunner() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		int input;
		try {
			while(true) {
				System.out.println("1) Add/Update/Remove/Read Flights\n"
						+ "2) Add/Remove/Read Reserved Seats\n"
						+ "3) Add/Update/Remove/Read Tickets and Passengers\n"
						+ "4) Add/Update/Remove/Read Airports\n"
						+ "5) Add/Update/Remove/Read Travelers\n"
						+ "6) Add/Update/Remove/Read Employees\n"
						+ "7) Over-ride Trip Cancellation for a ticket\n"
						+ "8) Quit to Previous");
				input = Integer.parseInt(scan.nextLine());
				switch(input) {
					case 1:
						while(flightCRUDMenu() != -1);
						break;
					case 2:
						while(reservedSeatCRUDMenu() != -1);
						break;
					case 3:
						while(ticketPassengerCRUDMenu() != -1);
						break;
					case 4:
						while(airportCRUDMenu() != -1);
						break;
					case 5:
						while(travelerCRUDMenu() != -1);
						break;
					case 6:
						while(employeeCRUDMenu() != -1);
						break;
					case 7:
						tripCancelOverride();
						break;
					case 8:
						return;
					default:
						System.out.println("Enter 1-8");
				}
			}
		}catch(NumberFormatException ex) {
			System.out.println("Enter A Number");
		}
	}
	
	public int airportCRUDMenu() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		AdminService adminService = new AdminService();
		Airport airport = null;
		int input;
		String iata, city;
		List<Airport> airports = null;
		System.out.println("1) Add Airport\n2) Update Airport\n3) Remove Airport\n4) Read Airports\n5) Quit to Previous");
		try {
			input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("Enter Airport IATA Code");
					iata = scan.nextLine();
					System.out.println("Enter Airport City");
					city = scan.nextLine();
					airport = new Airport(iata,city);
					adminService.addAirport(airport);
					break;
				case 2:
					//Print a list of airports to update
					airports = adminService.readAirport();
					System.out.println("Choose an Airport to Update");
					for(Airport a: airports) {
						System.out.println(a.getAirportCode() + " " + a.getCity());
					}
					System.out.println("Enter Airport IATA Code to Update");
					iata = scan.nextLine();
					System.out.println("Enter Airport City to Update");
					city = scan.nextLine();
					airport = new Airport(iata,city);
					adminService.updateAirport(airport);
					break;
				case 3:
					airports = adminService.readAirport();
					System.out.println("Choose an Airport to Remove");
					for(Airport a: airports) {
						System.out.println(a.getAirportCode() + " " + a.getCity());
					}
					System.out.println("Enter Airport IATA Code to Remove");
					iata = scan.nextLine();
					airport = new Airport(iata,null);
					adminService.removeAirport(airport);
					break;
				case 4:
					airports = adminService.readAirport();
					System.out.println("***All Airports***");
					for(Airport a: airports) {
						System.out.println("IATA: " + a.getAirportCode() + " City: " + a.getCity());
					}
					break;
				case 5:
					//-1 to move back a menu
					return -1;
				default:
					System.out.println("Enter 1-5");
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return 1;
	}
	
	public int flightCRUDMenu() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		AdminService adminService = new AdminService();
		Route route = null;
		Airplane plane = null;
		List<Flight> flights;
		int input;
		int routeId = 0,flightId = 0,planeId = 0,reservedSeats = 0;
		float seatPrice = 0;
		Date departureTime = null;
		List<Airport> airports = null;
		System.out.println("1) Add Booking and Passenger\n2) Update Booking and Passenger\n3) Remove Booking and Passenger\n4) Read Booking and Passenger\n5) Quit to Previous");
		try {
			input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					System.out.println("1) Select an Existing Route\n2) Create a New Route");
					//Acquire a route
					try {
						input = Integer.parseInt(scan.nextLine());
						if(input == 1) {
							route = selectExistingRoute();
						}else if(input == 2) {
							airports = selectNewRoute();
							if(airports == null) {
								System.out.println("Please enter a number corresponding to a prompt");
								break;
							}
						}else{
							System.out.println("Enter 1 or 2");
							break;
						}
					}catch(NumberFormatException ex) {
						System.out.println("Enter A Number");
					}
					//Acquire a plane
					plane = selectAirplane();
					//Acquire departure time
					try {
						System.out.println("Enter a Departure Time Format yyyy-mm-dd");
						departureTime = Date.valueOf(scan.nextLine());
					}catch(Exception e){
						System.out.println("Enter a Date");
					}
					//Acquire seat price
					try {
						System.out.println("Enter a Seat Price");
						seatPrice = Integer.parseInt(scan.nextLine());
					}catch(NumberFormatException ex) {
						System.out.println("Enter A Number");
					}
					adminService.addFlight(route, airports, plane, departureTime, seatPrice);
					break;
				case 2:
					flights = adminService.readFlight();
					for(Flight f: flights) {
						System.out.println(f.toString());
					}
					System.out.println("Choose a Flight to Update");
					try {
						System.out.println("Flight ID");
						flightId = Integer.parseInt(scan.nextLine());
						System.out.println("Route ID");
						routeId = Integer.parseInt(scan.nextLine());
						System.out.println("Plane ID");
						planeId = Integer.parseInt(scan.nextLine());
						System.out.println("Seat Price");
						seatPrice = Float.parseFloat(scan.nextLine());
						System.out.println("Reserved Seats");
						reservedSeats = Integer.parseInt(scan.nextLine());
						System.out.println("Enter a Departure Time Format yyyy-mm-dd");
						departureTime = Date.valueOf(scan.nextLine());
					}catch(NumberFormatException ex){
						System.out.println("Enter A Number");
					}catch(Exception e) {
						System.out.println("Enter a Date");
					}
					Flight toUpdate = new Flight(flightId, routeId, planeId, departureTime, reservedSeats, seatPrice);
					adminService.updateFlight(toUpdate);
					break;
				case 3:
					flights = adminService.readFlight();
					for(Flight f: flights) {
						System.out.println(f.toString());
					}
					System.out.println("Choose a Flight to Remove");
					try {
						System.out.println("Flight ID");
						flightId = Integer.parseInt(scan.nextLine());
						System.out.println("Route ID");
						routeId = Integer.parseInt(scan.nextLine());
						System.out.println("Plane ID");
						planeId = Integer.parseInt(scan.nextLine());
					}catch(NumberFormatException ex){
						System.out.println("Enter A Number");
					}catch(Exception e) {
						System.out.println("Enter a Date");
					}
					Flight toRemove = new Flight(flightId, routeId, planeId, null, null, null);
					adminService.removeFlight(toRemove);
					break;
				case 4:
					flights = adminService.readFlight();
					for(Flight f: flights) {
						System.out.println(f.toString());
					}
					break;
				case 5:
					return -1;
				default:
					System.out.println("Enter 1-5");
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return 1;
	}
	
	//Helper functions for flightCRUDMenu
	private Route selectExistingRoute() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		int input;
		Route select = null;
		AdminService as = new AdminService();
		List<Route> routes = as.readRoute();
		int count = 0;
		for(Route r: routes) {
			count++;
			System.out.println(count + ") ID: " + r.getRouteId() + " Origin: " + r.getOriginAirport() + " Destination: "
					+ r.getDestinationAirport());
		}
		System.out.println("Choose a Route");
		try {
			input = Integer.parseInt(scan.nextLine());
			if(input > 0 && input <= count) {
				select = routes.get(input-1);
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return select;
	}
	
	private List<Airport> selectNewRoute() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		int origin,dest;
		String orIATA, destIATA, orCity, destCity;
		List<Airport> select = new ArrayList<>();
		AdminService as = new AdminService();
		List<Airport> airports = as.readAirport();
		int count = 0;
		for(Airport a: airports) {
			count++;
			System.out.println(count + ") IATA: " + a.getAirportCode() + " City: " + a.getCity());
		}
		System.out.println((count +1) + ") Create an Airport");
		try {
			System.out.println("Select Origin Airport");
			origin = Integer.parseInt(scan.nextLine());
			if(origin > 0 && origin <= count) {
				select.add(airports.get(origin-1));
			}else if(origin == count +1){
				System.out.println("Select Origin IATA Code");
				orIATA = scan.nextLine();
				System.out.println("Select Origin City");
				orCity = scan.nextLine();
				select.add(new Airport(orIATA,orCity));
			}
			System.out.println("Select a Different Destination Airport");
			dest = Integer.parseInt(scan.nextLine());
			if(dest > 0 && dest <= count) {
				select.add(airports.get(dest-1));
			}else if(dest == count+1) {
				System.out.println("Select Origin IATA Code");
				destIATA = scan.nextLine();
				System.out.println("Select Origin City");
				destCity = scan.nextLine();
				select.add(new Airport(destIATA,destCity));
			}
			if(dest > count +1 || origin > count +1 || dest == origin) {
				return null;
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return select;
	}

	private Airplane selectAirplane() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		AdminService as = new AdminService();
		Airplane select = null;
		List<Airplane> planes = as.readAirplane();
		List<AirplaneType> types = as.readAirplaneType();
		int count = 0,input;
		for(Airplane p: planes) {
			int capacity = 0;
			for(AirplaneType t: types) {
				if(t.getAirplaneTypeId() == p.getTypeId()) {
					capacity = t.getMaxCapacity();
				}
			}
			count++;
			System.out.println(count + ") Flight ID: " + p.getAirplaneId() + " Capacity: " + capacity);
		}
		try {
			System.out.println("Select Airplane");
			input = Integer.parseInt(scan.nextLine());
			if(input > 0 && input <= count) {
				select = planes.get(input-1);
			}else {
				System.out.println("Enter 1-" + count);
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return select;
	}

	public int reservedSeatCRUDMenu() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		AdminService adminService = new AdminService();
		Flight selectedFlight = null;
		List<Flight> flights = null;
		List<Airplane> planes = null;
		List<AirplaneType> types = null;
		List<Integer> capacity = new ArrayList<>();
		int input,count,choosenFlight,seatChange,reservedSeats;
		System.out.println("1) Add \n2) Remove\n3) Read\n4) Quit to Previous");
		try {
			input = Integer.parseInt(scan.nextLine());
			switch(input) {
				case 1:
					flights = adminService.readFlight();
					types = adminService.readAirplaneType();
					planes = adminService.readAirplane();
					count = 0;
					//Find the seat capacity for the flight
					for(Flight f: flights) {
						for(Airplane p: planes) {
							if(f.getAirplaneId() == p.getAirplaneId()) {
								for(AirplaneType t: types) {
									if(t.getAirplaneTypeId() == p.getTypeId()) {
										capacity.add(t.getMaxCapacity());
									}
								}
							}
						}
						count++;
						System.out.println(count + ") FlightID: " + f.getFlightId() + " Seats Reserved: " + f.getReservedSeats()
						+ " Max Capacity: " + capacity.get(count-1));
					}
					System.out.println("Choose a Flight to Reserve Seats");
					choosenFlight = takeInputInt(count);
					System.out.println("Select the Number of Seats to Reserve");
					seatChange = takeInputInt(capacity.get(choosenFlight-1)-flights.get(choosenFlight-1).getReservedSeats());
					selectedFlight = flights.get(choosenFlight-1);
					reservedSeats = selectedFlight.getReservedSeats();
					selectedFlight.setReservedSeats(seatChange + reservedSeats);
					adminService.updateFlight(selectedFlight);
					break;
				case 2:
					flights = adminService.readFlight();
					types = adminService.readAirplaneType();
					planes = adminService.readAirplane();
					count = 0;
					//Find the seat capacity for the flight
					for(Flight f: flights) {
						for(Airplane p: planes) {
							if(f.getAirplaneId() == p.getAirplaneId()) {
								for(AirplaneType t: types) {
									if(t.getAirplaneTypeId() == p.getTypeId()) {
										capacity.add(t.getMaxCapacity());
									}
								}
							}
						}
						count++;
						System.out.println(count + ") FlightID: " + f.getFlightId() + " Seats Reserved: " + f.getReservedSeats()
						+ " Max Capacity: " + capacity.get(count-1));
					}
					System.out.println("Choose a Flight to Remove Reserved Seats");
					choosenFlight = takeInputInt(count);
					System.out.println("Select the Number of Seats to Unreserve");
					seatChange = takeInputInt(flights.get(choosenFlight-1).getReservedSeats());
					selectedFlight = flights.get(choosenFlight-1);
					reservedSeats = selectedFlight.getReservedSeats();
					selectedFlight.setReservedSeats(reservedSeats - seatChange);
					adminService.updateFlight(selectedFlight);
					break;
				case 3:
					flights = adminService.readFlight();
					types = adminService.readAirplaneType();
					planes = adminService.readAirplane();
					count = 0;
					//Find the seat capacity for the flight
					for(Flight f: flights) {
						for(Airplane p: planes) {
							if(f.getAirplaneId() == p.getAirplaneId()) {
								for(AirplaneType t: types) {
									if(t.getAirplaneTypeId() == p.getTypeId()) {
										capacity.add(t.getMaxCapacity());
									}
								}
							}
						}
						count++;
						System.out.println(count + ") FlightID: " + f.getFlightId() + " Seats Reserved: " + f.getReservedSeats()
						+ " Max Capacity: " + capacity.get(count-1));
					}
					break;
				case 4:
					return -1;
				default:
					return 1;
			}
		}catch(NumberFormatException ex){
			System.out.println("Enter A Number");
		}
		return 1;
	}
	
	public int ticketPassengerCRUDMenu() throws ClassNotFoundException, SQLException {
		int input,chooseBooking,flightIndex,bookingId,toChange;
		String fname,lname,dob,gender,address;
		Passenger passenger = null;
		int[] counts;
		AdminService as = new AdminService();
		List<Booking> bookings = as.readBooking();
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).getIsActive() == 0) {
				bookings.remove(i);
				i--;
			}
		}
		List<Passenger> pass = as.readPassenger();
		System.out.println("1) Add Passenger\n2) Update Passenger\n"
				+ "3) Remove Passenger\n4) Read Passengers\n5) Quit to Previous");
		input = takeInputInt(5);
		switch(input) {
			case 1:
				counts = printBookingPassengers(bookings,pass);
				System.out.println((counts[0]+1) + ") Create a Booking");
				System.out.println("Choose a Booking to Add a Passenger To");
				chooseBooking = takeInputInt(counts[0]+1);
				Booking newBooking = null;
				Flight flight = null;
				//To create a new booking and choose a flight
				if(chooseBooking == counts[0]+1) {
					newBooking = new Booking(null,1,randomCode(10));
					List<Flight> flights = as.readFlight();
					int flightCount = 0;
					for(Flight f: flights) {
						System.out.println((flightCount+1) + ") "+ f.toString());
						flightCount++;
					}
					System.out.println("Choose a Flight");
					flightIndex = takeInputInt(flightCount)-1;
					flight = flights.get(flightIndex);
					bookingId = -1;
				}else {
					bookingId = bookings.get(counts[0]-1).getBookingId();
				}
				System.out.println("Enter Passenger First Name");
				fname = takeInputString();
				System.out.println("Enter Passenger Last Name");
				lname = takeInputString();
				System.out.println("Enter Passenger DOB in Format yyyy-mm-dd");
				dob = takeInputString();
				System.out.println("Enter Passenger Gender");
				gender = takeInputString();
				System.out.println("Enter Passenger Address");
				address = takeInputString();
				passenger = new Passenger(null, bookingId, fname, lname, Date.valueOf(dob), gender, address);
				as.addBookingPassenger(newBooking, passenger, flight);
				break;
			case 2:
				counts= printBookingPassengers(bookings,pass);
				System.out.println("Enter A Passenger ID to Update Them");
				toChange = takeInputInt(1000);
				for(Passenger p: pass) {
					if(p.getPassengerId() == toChange) {
						passenger = p;
					}
				}
				if(passenger == null) {
					System.out.println("Enter A Valid ID");
					break;
				}
				System.out.println("Enter Passenger First Name or n/a");
				fname = takeInputString();
				System.out.println("Enter Passenger Last Name or n/a");
				lname = takeInputString();
				System.out.println("Enter Passenger DOB in Format yyyy-mm-dd or n/a");
				dob = takeInputString();
				System.out.println("Enter Passenger Gender or n/a");
				gender = takeInputString();
				System.out.println("Enter Passenger Address or n/a");
				address = takeInputString();
				if(!fname.equals("n/a")) {
					passenger.setFirstName(fname);
				}
				if(!lname.equals("n/a")) {
					passenger.setLastName(lname);
				}
				if(!dob.equals("n/a")) {
					passenger.setDob(Date.valueOf(dob));
				}
				if(!gender.equals("n/a")) {
					passenger.setGender(gender);
				}if(!address.equals("n/a")) {
					passenger.setAddress(address);
				}
				as.updatePassenger(passenger);
				break;
			case 3:
				counts = printBookingPassengers(bookings,pass);
				System.out.println("Enter A Passenger ID to Remove Them");
				toChange = takeInputInt(1000);
				for(Passenger p: pass) {
					if(p.getPassengerId() == toChange) {
						passenger = p;
					}
				}
				if(passenger == null) {
					System.out.println("Enter A Valid ID");
					break;
				}
				as.removePassenger(passenger);
				break;
			case 4:
				counts = printBookingPassengers(bookings,pass);
				break;
			case 5:
				return -1;
		}
		return 1;
	}
	
	public int travelerCRUDMenu() throws ClassNotFoundException, SQLException {
		int input,tCount,toDelete,toUpdate;
		User toChange = null;
		User current = null;
		String[] values = new String[6];
		String[] prompts = new String[] {"First Name","Last Name","Username","Email","Password","Phone"};
		AdminService as = new AdminService();
		List<User> travelers = as.readUserByRoleId(travId);
		System.out.println("1) Add Traveler\n2) Update Traveler\n"
				+ "3) Remove Traveler\n4) Read Traveler\n5) Quit to Previous");
		input = takeInputInt(5);
		switch(input) {
			case 1:
				for(int i=0;i<prompts.length;i++ ) {
					System.out.println("Enter " + prompts[i]);
					values[i] = takeInputString();
				}
				toChange = new User(null, travId, values[0], values[1], values[2], values[3], values[4], values[5]);
				//Can hash password here before storing
				as.addUser(toChange);
				break;
			case 2:
				tCount = printUsers(travelers);
				System.out.println("Choose A Traveler to Update");
				toUpdate = takeInputInt(tCount);
				current = travelers.get(toUpdate-1);
				System.out.println("Enter n/a for No Change");
				for(int i=0;i<prompts.length;i++ ) {
					System.out.println("Enter " + prompts[i]);
					values[i] = takeInputString();
				}
				toChange = new User(current.getUserId(),travId,
						values[0].equals("n/a") ? current.getFirstName() : values[0] , values[1].equals("n/a") ? current.getLastName() : values[1],
						values[2].equals("n/a") ? current.getUsername() : values[2], values[3].equals("n/a") ? current.getEmail() : values[3], 
						values[4].equals("n/a") ? current.getPassword() : values[4], values[5].equals("n/a") ? current.getPhone() : values[5]);
				//Can hash password here before storing
				as.updateUser(toChange);
				break;
			case 3:
				tCount = printUsers(travelers);
				System.out.println("Choose A Traveler to Remove");
				toDelete = takeInputInt(tCount);
				toChange = new User(travelers.get(toDelete-1).getUserId(), null, null, null, null, null, null, null);
				as.removeUser(toChange);
				break;
			case 4:
				tCount = printUsers(travelers);
				break;
			case 5:
				return -1;
		}
		return 1;
	}
	
	public int employeeCRUDMenu() throws ClassNotFoundException, SQLException {
		int input,eCount,toDelete,toUpdate;
		User toChange = null;
		User current = null;
		String[] values = new String[6];
		String[] prompts = new String[] {"First Name","Last Name","Username","Email","Password","Phone"};
		AdminService as = new AdminService();
		List<User> employees = as.readUserByRoleId(empId);
		System.out.println("1) Add Employee\n2) Update Employee\n"
				+ "3) Remove Employee\n4) Read Employee\n5) Quit to Previous");
		input = takeInputInt(5);
		switch(input) {
			case 1:
				for(int i=0;i<prompts.length;i++ ) {
					System.out.println("Enter " + prompts[i]);
					values[i] = takeInputString();
				}
				toChange = new User(null, empId, values[0], values[1], values[2], values[3], values[4], values[5]);
				//Can hash password here before storing
				as.addUser(toChange);
				break;
			case 2:
				eCount = printUsers(employees);
				System.out.println("Choose An Employee to Update");
				toUpdate = takeInputInt(eCount);
				current = employees.get(toUpdate-1);
				System.out.println("Enter n/a for No Change");
				for(int i=0;i<prompts.length;i++ ) {
					System.out.println("Enter " + prompts[i]);
					values[i] = takeInputString();
				}
				toChange = new User(current.getUserId(),empId,
						values[0].equals("n/a") ? current.getFirstName() : values[0] , values[1].equals("n/a") ? current.getLastName() : values[1],
						values[2].equals("n/a") ? current.getUsername() : values[2], values[3].equals("n/a") ? current.getEmail() : values[3], 
						values[4].equals("n/a") ? current.getPassword() : values[4], values[5].equals("n/a") ? current.getPhone() : values[5]);
				//Can hash password here before storing
				as.updateUser(toChange);
				break;
			case 3:
				eCount = printUsers(employees);
				System.out.println("Choose A Employee to Remove");
				toDelete = takeInputInt(eCount);
				toChange = new User(employees.get(toDelete-1).getUserId(), null, null, null, null, null, null, null);
				as.removeUser(toChange);
				break;
			case 4:
				eCount = printUsers(employees);
				break;
			case 5:
				return -1;
		}
		return 1;
	}
	
	public int tripCancelOverride() throws ClassNotFoundException, SQLException {
		int input,count;
		Booking booking = null;
		AdminService as = new AdminService();
		List<Booking> bookings= as.readBooking();
		for(int i=0;i<bookings.size();i++) {
			if(bookings.get(i).getIsActive() == 0) {
				bookings.remove(i);
				i--;
			}
		}
		count = printBookings(bookings);
		System.out.println((count+1) +") To Cancel");
		System.out.println("Enter A Booking to Refund");
		input = takeInputInt(count+1);
		if(input == count+1) {
			return -1;
		}
		booking = bookings.get(input-1);
		booking.setIsActive(0);
		as.cancelTrip(booking);
		return -1;
	}
	private int printBookings(List<Booking> bookings) {
		int count = 0;
		for(Booking b: bookings) {
				System.out.println((count+1) + ") " + b.toString());
				count++;
		}
		return count;
	}
	
	//helpers for users methods
	private int printUsers(List<User> users) {
		int count = 0;
		for(User u: users) {
			System.out.println((count+1) + ") " + u.toString());
			count++;
		}
		return count;
	}
	
	
	//Helpers for passenger tickets
	private int[] printBookingPassengers(List<Booking> bookings, List<Passenger> pass) {
		int[] counts = new int[] {0,0};
		for(Booking b : bookings) {
			System.out.println((counts[0] +1) + ") " +"For Active Booking ID: " + b.getBookingId() 
			+ " With Confirmation: " + b.getConfirmationCode());
			counts[0]++;
			for(Passenger p: pass) {
				if(p.getBookingId() == b.getBookingId()) {
					System.out.println("         " + (counts[1] +1) + ") " + p.toString());
					counts[1]++;
				}
			}
		}
		return counts;
	}
	
	//Function take from https://www.baeldung.com/java-random-string#apachecommons-alphanumeric
	private String randomCode(int targetStringLength) {
		int leftLimit = 48; 
	    int rightLimit = 122; 
	    Random random = new Random();
	    StringBuilder buffer = new StringBuilder(targetStringLength);
	    for (int i = 0; i < targetStringLength; i++) {
	        int randomLimitedInt = leftLimit + (int) 
	          (random.nextFloat() * (rightLimit - leftLimit + 1));
	        buffer.append((char) randomLimitedInt);
	    }
	    String generatedString = buffer.toString();
	    return generatedString;
	}
	
	//Helpers to handle data input
	private int takeInputInt(int max) {
		if(max == 0  || max < 0) {
			return 0;
		}
		Scanner scan = new Scanner(System.in);
		int input = 0;
		while(true) {
			try {
				input = Integer.parseInt(scan.nextLine());
				if(input > 0 && input <= max) {
					return input;
				}else {
					System.out.println("Enter 1-" + max);
				}
			}catch(NumberFormatException e){
				System.out.println("Please Enter a Number");
			}
		}
	}
	
	private String takeInputString(){
		Scanner scan = new Scanner(System.in);
		String input;
		input = scan.nextLine();
		return input;
	}
}

