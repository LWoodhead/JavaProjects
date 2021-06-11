/**
 * 
 */
package com.ss.utopia.domain;

import java.sql.Date;

/**
 * @author lukej
 *
 */
public class Passenger {
	private Integer passengerId;
	private Integer bookingId;
	private String firstName;
	private String lastName;
	private Date dob;
	private String gender;
	private String address;
	
	public Passenger(Integer passengerId, Integer bookingId, String firstName, String lastName, Date dob, String gender,
			String address) {
		this.passengerId = passengerId;
		this.bookingId = bookingId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.gender = gender;
		this.address = address;
	}

	public Integer getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(Integer passengerId) {
		this.passengerId = passengerId;
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Passenger [passengerId=" + passengerId + ", bookingId=" + bookingId + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender + ", address=" + address + "]";
	}
	
	
}
