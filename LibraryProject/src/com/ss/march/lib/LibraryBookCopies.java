/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBookCopies {

	/**
	 * @param args
	 */
	private int bookId;
	private int branchId;
	private int numCopies;
	
	public LibraryBookCopies(int bookId, int branchId, int numCopies) {
		this.bookId = bookId;
		this.branchId = branchId;
		this.numCopies = numCopies;
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

	public int getNumCopies() {
		return numCopies;
	}

	public void setNumCopies(int numCopies) {
		this.numCopies = numCopies;
	}

	
	@Override
	public String toString() {
		return "LibraryBookCopies [bookId=" + bookId + ", branchId=" + branchId + ", numCopies=" + numCopies + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
