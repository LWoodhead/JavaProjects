/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class BookingUser {
	private Integer bookingId;
	private Integer userId;
	
	public BookingUser(Integer bookingId, Integer userId) {
		this.bookingId = bookingId;
		this.userId = userId;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "BookingUser [bookingId=" + bookingId + ", userId=" + userId + "]";
	}
	
	
}
