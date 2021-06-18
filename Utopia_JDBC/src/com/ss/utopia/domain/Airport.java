/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class Airport {
	private String airportCode;
	private String city;
	public Airport(String airportCode, String city) {
		this.airportCode = airportCode;
		this.city = city;
	}
	public String getAirportCode() {
		return airportCode;
	}
	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Airport [airportCode=" + airportCode + ", city=" + city + "]";
	}
	
	
}
