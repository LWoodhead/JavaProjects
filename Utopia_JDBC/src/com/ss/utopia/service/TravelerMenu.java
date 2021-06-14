/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Booking;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.FlightBooking;
import com.ss.utopia.domain.Passenger;
import com.ss.utopia.domain.Route;
import com.ss.utopia.domain.User;

/**
 * @author lukej
 *
 */
public class TravelerMenu {
	
	private User currentUser = null;
	
	public void travelerMenuRunner() throws ClassNotFoundException, SQLException {
		int curMenu = 1, nextMenu = 1, valid = 0;
		valid = verifyUser();
		if(valid == -1) {
			System.out.println("Failed Login");
			return;
		}
		while(true) {
			switch(nextMenu) {
				case 1:
					nextMenu = travMenuOne();
					break;
				case -1:
					return;			
			}
		}
	}
	
	public int travMenuOne() throws ClassNotFoundException, SQLException {
		int input;
		System.out.println("1) Book a Ticket\n2) Cancel an Upcoming Trip\n3) Quit to Previous");
		input = takeInputInt(3);
		switch(input) {
			case 1:
				bookTicket();
				return 1;
			case 2:
				cancelTrip();
				return 1;
			case 3:
				return -1;
		}
		return 1;
	}
	
	public int verifyUser() throws ClassNotFoundException, SQLException {
		int response = -1;
		User temp = null;
		TravelerService ts = new TravelerService();
		System.out.println("Enter username");
		String username = takeInputString(); 
		while(response == -1) {
			System.out.println("Enter password or quit to exit");
			String password = takeInputString();
			if("quit".equals(password)) {
				return -1;
			}
			temp = ts.verifyUser(username, password);
			if(temp == null) {
				response = -1;
				System.out.println("Incorrect password or username try again");
			}else {
				currentUser = temp;
				break;
			}
		}
		return 1;
	}
	
	public void bookTicket() throws ClassNotFoundException, SQLException {
		int input = 0, pFlight;
		String[] values = new String[3];
		TravelerService ts = new TravelerService();
		List<Flight> flights = ts.readFlight();
		List<Route> routes = ts.readRoute();
		List<Airplane> planes = ts.readAirplane();
		List<AirplaneType> types = ts.readAirplaneType();
		int[] seatsLeft = printFlightPaths(flights,routes,planes,types);
		pFlight = takeInputInt(flights.size()+1);
		if(pFlight == flights.size()+1) {
			return;
		}else {
			Flight pickedFlight = flights.get(pFlight-1);
			input = 0;
			while(input != 2) {
				input = printFlightMenu();
				switch(input){
					case 1:
						System.out.println("Flight: " + pickedFlight.getFlightId() 
						+ " Seat Price: " + pickedFlight.getSeatPrice() + " Departure Date: " + pickedFlight.getDepartureTime()
						+ " Seats Available: " + seatsLeft[pFlight-1]);
						break;
					case 2:
						break;
					case 3:
						return;
				}
			}
			Booking booking = new Booking(null,1,randomCode(10));
			System.out.println("Enter your gender");
			values[0] = takeInputString();
			System.out.println("Enter your dob in format yyyy-mm-dd");
			values[1] = takeInputString();
			System.out.println("Enter your address");
			values[2] = takeInputString();		
			Passenger pass = new Passenger(null, null,currentUser.getFirstName() ,currentUser.getLastName(), Date.valueOf(values[1]), values[0], values[2]);
			ts.bookFlight(pickedFlight, currentUser, pass, booking);
		}
		
	}
	
	public void cancelTrip() throws ClassNotFoundException, SQLException {
		int count = 0,input;
		TravelerService ts = new TravelerService();
		List<Booking> bookings = ts.readUserBookings(currentUser);
		List<FlightBooking> fbooks = ts.readFlightBooking();
		List<Route> routes = ts.readRoute();
		List<Flight> flights = ts.readFlight();
		printBookedFlights(bookings,fbooks,flights,routes);
		System.out.println("Choose A Booking to Cancel");
		input = takeInputInt(bookings.size()+1);
		if(input == bookings.size()+1) {
			return;
		}else {
			ts.cancelBooking(bookings.get(input-1));
		}
	}
	
	//Helpers
	
	private void printBookedFlights(List<Booking> bookings,List<FlightBooking> fbooks, List<Flight> flights, List<Route> routes) {
		int count = 0;
		boolean found;
		for(Booking b: bookings) {
			found = false;
			for(FlightBooking fb: fbooks) {
				if(found == true) {
					break;
				}
				if(b.getBookingId() == fb.getBookingId()) {
					for(Flight f: flights) {
						if(found == true) {
							break;
						}
						if(fb.getFlightId() == f.getFlightId()) {
							for(Route r: routes) {
								if(r.getRouteId() == f.getRouteId()) {
									System.out.println((count+1) + ") Flight " + f.getFlightId() + " " + r.getOriginAirport() + " --> " + r.getDestinationAirport()
									+ " Departing " + f.getDepartureTime());
									count++;
									found = true;
									break;
								}
							}
						}
					}
				}
			}
		}
		System.out.println((count+1) + ") Quit to Previous");
	}
	
	private int[] printFlightPaths(List<Flight> flights, List<Route> routes, List<Airplane> planes, List<AirplaneType> types) {
		int count = 1;
		boolean found;
		int remainingSeats[] = new int[flights.size()];
		System.out.println("Pick the Flight you want to book a ticket for:");
		for(Flight f: flights) {
			found = false;
			for(Route r: routes) {
				if(found == true) {
					break;
				}
				if(f.getRouteId() == r.getRouteId()) {
					for(Airplane p: planes) {
						if(found == true) {
							break;
						}
						if(f.getAirplaneId() == p.getAirplaneId()) {
							for(AirplaneType t: types) {
								if(found == true) {
									break;
								}
								if(t.getAirplaneTypeId() == p.getTypeId() && f.getReservedSeats() < t.getMaxCapacity()) {
									System.out.println(count + ") " + r.getOriginAirport() + " --> " + r.getDestinationAirport());
									remainingSeats[count-1] = t.getMaxCapacity() - f.getReservedSeats();
									count++;
									found = true;
								}
							}
						}
					}
				}
			}
		}
		System.out.println(count + ") " + "Quit to Previous");
		return remainingSeats;
	}
	
	public int printFlightMenu() {
		int input;
		System.out.println("1) View Flight Details\n2) Confirm Booking\n3) Quit to Cancel Operation");
		input = takeInputInt(3);
		return input;
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
}
