/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBorrower {

	private int cardNo;
	private String name;
	private String Address;
	private String phone;
	
	
	public LibraryBorrower(int cardNo, String name, String address, String phone) {
		this.cardNo = cardNo;
		this.name = name;
		Address = address;
		this.phone = phone;
	}


	public int getCardNo() {
		return cardNo;
	}


	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return Address;
	}


	public void setAddress(String address) {
		Address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	@Override
	public String toString() {
		return "LibraryBorrower [cardNo=" + cardNo + ", name=" + name + ", Address=" + Address + ", phone=" + phone
				+ "]";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
