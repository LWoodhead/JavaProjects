/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class BookingAgent {
	private Integer bookingId;
	private Integer agentId;
	
	public BookingAgent(Integer bookingId, Integer agentId) {
		this.bookingId = bookingId;
		this.agentId = agentId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getAgentId() {
		return agentId;
	}
	public void setAgentId(Integer agentId) {
		this.agentId = agentId;
	}
	@Override
	public String toString() {
		return "BookingAgent [bookingId=" + bookingId + ", agentId=" + agentId + "]";
	}
}
