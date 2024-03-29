package com.tripbill.entities;

import java.util.List;

/**
 * @author nsoliveau
 *
 * Valuable Class that represents customer's summary of trips and total bill
 */
public class CustomerSummary {
	
	private Integer customerId;
	private Integer totalCostInCents;
	private List<Trip> trips;
	
	
	public CustomerSummary(Integer customerId, Integer totalCostInCents, List<Trip> trips) {
		super();
		this.customerId = customerId;
		this.totalCostInCents = totalCostInCents;
		this.trips = trips;
	}
	
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer theCustomerId) {
		this.customerId = theCustomerId;
	}
	public Integer getTotalCostInCents() {
		return totalCostInCents;
	}
	public void setTotalCostInCents(Integer theTotalCostInCents) {
		this.totalCostInCents = theTotalCostInCents;
	}
	public List<Trip> getTrips() {
		return trips;
	}
	public void setTrips(List<Trip> theTrips) {
		this.trips = theTrips;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof CustomerSummary) {
			CustomerSummary object = (CustomerSummary) o;
			if(!object.getCustomerId().equals(this.getCustomerId())) {
				return false;
			}
			if(!object.getTotalCostInCents().equals(this.totalCostInCents)) {
				return false;
			}
			if(!object.getTrips().equals(this.getTrips())) {
				return false;
			}
		}
		return true;
	}
}
