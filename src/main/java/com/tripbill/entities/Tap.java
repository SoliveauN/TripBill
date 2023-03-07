package com.tripbill.entities;

/**
 * @author nsoliveau
 *
 * Valuable Class that represents a customer's access to a station
 */
public class Tap {
	
	private Integer unixTimestamp;
	private Integer customerId;
	private String station;
	
	public Tap() {
		super();
	}

	public Tap(Integer unixTimestamp, Integer customerId, String station) {
		this.unixTimestamp = unixTimestamp;
		this.customerId = customerId;
		this.station = station;
	}
	
	public Integer getUnixTimestamp() {
		return unixTimestamp;
	}
	public void setLocalDateTime(Integer theUnixTimestamp) {
		this.unixTimestamp = theUnixTimestamp;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer theCustomerId) {
		this.customerId = theCustomerId;
	}
	public String getStation() {
		return station;
	}
	public void setStation(String theStation) {
		this.station = theStation;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Tap) {
			Tap tapObject = (Tap) o;
			if(tapObject.getUnixTimestamp() != null && !tapObject.getUnixTimestamp().equals(this.unixTimestamp))
				return false;
			if(tapObject.getCustomerId() != null && !tapObject.getCustomerId().equals(this.customerId))
				return false;
			if(tapObject.getStation() != null && !tapObject.getStation().equals(this.station))
				return false;
		}
		return true;
	}
}
