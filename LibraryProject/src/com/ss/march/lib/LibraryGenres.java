/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryGenres {

	/**
	 * @param args
	 */
	private String genreName;
	private int genreId;
	
	public LibraryGenres(String genreName, int genreId) {
		this.genreName = genreName;
		this.genreId = genreId;
	}

	public String getGenreName() {
		return genreName;
	}

	public void setGenreName(String genreName) {
		this.genreName = genreName;
	}

	public int getGenreId() {
		return genreId;
	}

	public void setGenreId(int genreId) {
		this.genreId = genreId;
	}

	
	@Override
	public String toString() {
		return "LibraryGenres [genreName=" + genreName + ", genreId=" + genreId + "]";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
