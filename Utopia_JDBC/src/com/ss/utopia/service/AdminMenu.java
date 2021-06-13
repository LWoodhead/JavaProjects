/**
 * 
 */
package com.ss.utopia.service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ss.utopia.domain.Airplane;
import com.ss.utopia.domain.AirplaneType;
import com.ss.utopia.domain.Airport;
import com.ss.utopia.domain.Flight;
import com.ss.utopia.domain.Route;

/**
 * @author lukej
 *
 */
public class AdminMenu {
	
	public void AdminMenuRunner() throws ClassNotFoundException, SQLException {
		Scanner scan = new Scanner(System.in);
		int input;
		try {
			while(true) {
				System.out.println("1) Add/Update/Remove/Read Flights\n"
						+ "2) Add/Update/Remove/Read Seats\n"
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
						System.out.println("Choice");
						break;
					case 3:
						System.out.println("Choice");
						break;
					case 4:
						while(airportCRUDMenu() != -1);
						break;
					case 5:
						System.out.println("Choice");
						break;
					case 6:
						System.out.println("Choice");
						break;
					case 7:
						System.out.println("Choice");
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
		System.out.println("1) Add \n2) Update \n3) Remove \n4) Read \n5) Quit to Previous");
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
		List<Airport> select = new ArrayList<>();
		AdminService as = new AdminService();
		List<Airport> airports = as.readAirport();
		int count = 0;
		for(Airport a: airports) {
			count++;
			System.out.println(count + ") IATA: " + a.getAirportCode() + " City: " + a.getCity());
		}
		try {
			System.out.println("Select Origin Airport");
			origin = Integer.parseInt(scan.nextLine());
			System.out.println("Select a Different Destination Airport");
			dest = Integer.parseInt(scan.nextLine());
			if(origin > 0 && origin <= count && dest > 0 && dest <= count && origin != dest) {
				select.add(airports.get(origin-1));
				select.add(airports.get(dest-1));
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
}

