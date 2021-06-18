/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class BookingGuest {
	private Integer bookingId;
	private String email;
	private String phone;
	
	public BookingGuest(Integer bookingId, String email, String phone) {
		this.bookingId = bookingId;
		this.email = email;
		this.phone = phone;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "BookingGuest [bookingId=" + bookingId + ", email=" + email + ", phone=" + phone + "]";
	}
	
	
		
}
