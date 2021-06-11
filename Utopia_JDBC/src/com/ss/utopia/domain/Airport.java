/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class Airport {
	private String aiportCode;
	private String city;
	
	public Airport(String aiportCode, String city) {
		this.aiportCode = aiportCode;
		this.city = city;
	}
	public String getAiportCode() {
		return aiportCode;
	}
	public void setAiportCode(String aiportCode) {
		this.aiportCode = aiportCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "Airporrt [aiportCode=" + aiportCode + ", city=" + city + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aiportCode == null) ? 0 : aiportCode.hashCode());
		result = prime * result + ((city == null) ? 0 : city.hashCode());
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
		Airport other = (Airport) obj;
		if (aiportCode == null) {
			if (other.aiportCode != null)
				return false;
		} else if (!aiportCode.equals(other.aiportCode))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		return true;
	}
	
}
