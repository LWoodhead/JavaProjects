/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class FlightBooking {
	private Integer flightId;
	private Integer bookingId;
	public FlightBooking(Integer flightId, Integer bookingId) {
		super();
		this.flightId = flightId;
		this.bookingId = bookingId;
	}
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	@Override
	public String toString() {
		return "FlightBooking [flightId=" + flightId + ", bookingId=" + bookingId + "]";
	}
}
