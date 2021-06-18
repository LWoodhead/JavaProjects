/**
 * 
 */
package com.ss.utopia.domain;

/**
 * @author lukej
 *
 */
public class Route{
	private Integer routeId;
	private String originAirport;
	private String destinationAirport;
	
	public Route(Integer routeId, String originAirport, String destinationAirport) {
		this.routeId = routeId;
		this.originAirport = originAirport;
		this.destinationAirport = destinationAirport;
	}
	public Integer getRouteId() {
		return routeId;
	}
	public void setRouteId(Integer routeId) {
		this.routeId = routeId;
	}
	public String getOriginAirport() {
		return originAirport;
	}
	public void setOriginAirport(String originAirport) {
		this.originAirport = originAirport;
	}
	public String getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(String destinationAirport) {
		this.destinationAirport = destinationAirport;
	}
	@Override
	public String toString() {
		return "Route [routeId=" + routeId + ", originAirport=" + originAirport + ", destinationAirport="
				+ destinationAirport + "]";
	}
	
	
}
