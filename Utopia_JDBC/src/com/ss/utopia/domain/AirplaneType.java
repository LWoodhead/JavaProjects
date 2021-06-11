/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class AirplaneType {
	private Integer AirplaneTypeId;
	private Integer maxCapacity;
	
	public AirplaneType(Integer airplaneTypeId, Integer max_capacity) {
		AirplaneTypeId = airplaneTypeId;
		this.maxCapacity = max_capacity;
	}
	public Integer getAirplaneTypeId() {
		return AirplaneTypeId;
	}
	public void setAirplaneTypeId(Integer airplaneTypeId) {
		AirplaneTypeId = airplaneTypeId;
	}
	public Integer getMaxCapacity() {
		return maxCapacity;
	}
	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}
	@Override
	public String toString() {
		return "AirplaneType [AirplaneTypeId=" + AirplaneTypeId + ", maxCapacity=" + maxCapacity + "]";
	}
	
	
}
