/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class Airplane {
	private Integer airplaneId;
	private Integer typeId;
	public Airplane(Integer airplaneId, Integer typeId) {
		this.airplaneId = airplaneId;
		this.typeId = typeId;
	}
	public Integer getAirplaneId() {
		return airplaneId;
	}
	public void setAirplaneId(Integer airplaneId) {
		this.airplaneId = airplaneId;
	}
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	@Override
	public String toString() {
		return "Airplane [airplaneId=" + airplaneId + ", typeId=" + typeId + "]";
	}
	
}
