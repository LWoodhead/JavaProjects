/**
 * 
 */
package com.ss.march.lib;

/**
 * @author lukej
 *
 */
public class LibraryPublisher {

	private int publisherId;
	private String publisherName;
	private String publisherAddress;
	private String publisherPhone;

	public LibraryPublisher(int publisherId, String publisherName, String publisherAddress, String publisherPhone) {
		super();
		this.publisherId = publisherId;
		this.publisherName = publisherName;
		this.publisherAddress = publisherAddress;
		this.publisherPhone = publisherPhone;
	}

	public int getPublisherId() {
		return publisherId;
	}


	public void setPublisherId(int publisherId) {
		this.publisherId = publisherId;
	}


	public String getPublisherName() {
		return publisherName;
	}


	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}


	public String getPublisherAddress() {
		return publisherAddress;
	}


	public void setPublisherAddress(String publisherAddress) {
		this.publisherAddress = publisherAddress;
	}

	public String getPublisherPhone() {
		return publisherPhone;
	}

	public void setPublisherPhone(String publisherPhone) {
		this.publisherPhone = publisherPhone;
	}

	
	@Override
	public String toString() {
		return "LibraryPublisher [publisherId=" + publisherId + ", publisherName=" + publisherName
				+ ", publisherAddress=" + publisherAddress + ", publisherPhone=" + publisherPhone + "]";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
