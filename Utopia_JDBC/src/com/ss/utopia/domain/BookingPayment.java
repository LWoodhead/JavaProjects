/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class BookingPayment{
	private Integer bookingId;
	private String stripeId;
	private Integer refunded;
	
	public BookingPayment(Integer bookingId, String stripeId, Integer refunded) {
		this.bookingId = bookingId;
		this.stripeId = stripeId;
		this.refunded = refunded;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public String getStripeId() {
		return stripeId;
	}
	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}
	public Integer getRefunded() {
		return refunded;
	}
	public void setRefunded(Integer refunded) {
		this.refunded = refunded;
	}
	@Override
	public String toString() {
		return "BookingPayment [bookingId=" + bookingId + ", stripeId=" + stripeId + ", refunded=" + refunded + "]";
	}
	
	
}
