/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryBranch {

	/**
	 * @param args
	 */
	private int branchId;
	private String branchName;
	private String branchAddress;
	
	public LibraryBranch(int id,String name,String address) {
		this.branchId = id;
		this.branchName = name;
		this.branchAddress = address;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getBranchAddress() {
		return branchAddress;
	}
	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + branchId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LibraryBranch other = (LibraryBranch) obj;
		if (branchId != other.branchId)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return branchName + ", " + branchAddress;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
 