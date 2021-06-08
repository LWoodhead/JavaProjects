/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryAuthor {

	private int authorId;
	private String authorName;
	
	public LibraryAuthor(int authorId, String authorName) {
		this.authorId = authorId;
		this.authorName = authorName;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	@Override
	public String toString() {
		return "LibraryAuthor [authorId=" + authorId + ", authorName=" + authorName + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
