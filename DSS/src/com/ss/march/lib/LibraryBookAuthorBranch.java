/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBookAuthorBranch {

	/**
	 * @param args
	 */
	private String authorName;
	private int authorId;
	private String title;
	private int bookId;
	private int branchId;
	private int copies;
	

	public LibraryBookAuthorBranch(String authorName, int authorId, String title, int bookId, int branchId,int copies) {
		this.authorName = authorName;
		this.authorId = authorId;
		this.title = title;
		this.bookId = bookId;
		this.branchId = branchId;
		this.copies = copies;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
	
	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	@Override
	public String toString() {
		return "LibraryBookAuthorBranch [authorName=" + authorName + ", authorId=" + authorId + ", title=" + title
				+ ", bookId=" + bookId + ", branchId=" + branchId + ", copies=" + copies + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
