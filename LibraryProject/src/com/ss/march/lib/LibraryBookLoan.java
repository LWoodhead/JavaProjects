/**
 * 
 */
package com.ss.march.lib;

import java.sql.Date;

/**
 * @author lukej
 *
 */
public class LibraryBookLoan {

	/**
	 * @param args
	 */
	private int bookId;
	private int branchId;
	private int cardNo;
	private Date dateOut;
	private Date dueDate;
	private Date dateIn;
	
	public LibraryBookLoan(int bookId, int branchId, int cardNo, Date dateOut, Date dueDate, Date dateIn) {
		super();
		this.bookId = bookId;
		this.branchId = branchId;
		this.cardNo = cardNo;
		this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
//		this.dateOut = new Date(System.currentTimeMillis());
//		this.dueDate = new Date(System.currentTimeMillis() + 14 * 24 * 3600 * 1000);
//		this.dateIn = null;
		
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String toString() {
		return "LibraryBookLoan [bookId=" + bookId + ", branchId=" + branchId + ", cardNo=" + cardNo + ", dateOut="
				+ dateOut + ", dueDate=" + dueDate + ", dateIn=" + dateIn + "]";
	}
	
}
