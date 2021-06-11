/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class Booking {
	private Integer bookingId;
	private Integer isActive;
	private String confirmationCode;
	
	public Booking(Integer bookingId, Integer isActive, String confirmationCode) {
		this.bookingId = bookingId;
		this.isActive = isActive;
		this.confirmationCode = confirmationCode;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getIsActive() {
		return isActive;
	}
	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}
	public String getConfirmationCode() {
		return confirmationCode;
	}
	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", isActive=" + isActive + ", confirmationCode=" + confirmationCode
				+ "]";
	}
}
