/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBook {

	private int bookId;
	private String bookTitle;
	private int publisherId;
	
	public LibraryBook(int bookId, String bookTitle, int publisherId) {
		this.bookId = bookId;
		this.bookTitle = bookTitle;
		this.publisherId = publisherId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getPublisherId() {
		return publisherId;
	}
	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}
	@Override
	public String toString() {
		return "LibraryBook [bookId=" + bookId + ", bookTitle=" + bookTitle + ", publisherId=" + publisherId + "]";
	}
	
}
