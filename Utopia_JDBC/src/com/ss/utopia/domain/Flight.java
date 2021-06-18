/**
 * 
 */
package com.ss.utopia.domain;

import java.sql.Date;

/**
 * @author lukej
 *
 */
public class Flight {
	private Integer flightId;
	private Integer routeId;
	private Integer airplaneId;
	private Date departureTime;
	private Integer reservedSeats;
	private Float seatPrice;
	
	public Flight(Integer flightId, Integer routeId, Integer airplaneId, Date departureTime, Integer reservedSeats,Float seatPrice) {
		this.flightId = flightId;
		this.routeId = routeId;
		this.airplaneId = airplaneId;
		this.departureTime = departureTime;
		this.reservedSeats = reservedSeats;
		this.seatPrice = seatPrice;
	}

	public Integer getFlightId() {
		return flightId;
	}

	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}

	public Integer getRouteId() {
		return routeId;
	}

	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}

	public Integer getAirplaneId() {
		return airplaneId;
	}

	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Integer getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(Integer reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public Float getSeatPrice() {
		return seatPrice;
	}

	public void setSeatPrice(Float seatPrice) {
		this.seatPrice = seatPrice;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", routeId=" + routeId + ", airplaneId=" + airplaneId
				+ ", departureTime=" + departureTime + ", reservedSeats=" + reservedSeats + ", seatPrice=" + seatPrice
				+ "]";
	}
	
}
