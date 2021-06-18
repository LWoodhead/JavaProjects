/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBookAuthor {

	/**
	 * @param args
	 */
	private String title;
	private String author;
	private int bookId;
	private int authorId;
	private int pubId;
	
	public LibraryBookAuthor(String title, String author, int bookId, int authorId,int pubId) {
		this.title = title;
		this.author = author;
		this.bookId = bookId;
		this.authorId = authorId;
		this.pubId = pubId;
	}


	public int getAuthorId() {
		return authorId;
	}


	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}


	public int getBookId() {
		return bookId;
	}


	public void setBookId(int bookId) {
		this.bookId = bookId;
	}


	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	



	@Override
	public String toString() {
		return "LibraryBookAuthor [title=" + title + ", author=" + author + ", authorId=" + authorId + ", bookId="
				+ bookId + ", pubId=" + pubId + "]";
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
