package com.tripbill.entities;

/**
 * @author nsoliveau
 *
 * Valuable Class that represents a customer's trip beetween two stations
 */
public class Trip {
	
	private String stationStart;
	private String stationEnd;
	private Integer startedJourneyAt;
	private Integer costInCents;
	private Integer zoneFrom;
	private Integer zoneTo;
	
	public Trip() {
		super();
	}

	public Trip(String stationStart, String stationEnd, Integer startedJourneyAt, Integer costInCents, Integer zoneFrom,
			Integer zoneTo) {
		this.stationStart = stationStart;
		this.stationEnd = stationEnd;
		this.startedJourneyAt = startedJourneyAt;
		this.costInCents = costInCents;
		this.zoneFrom = zoneFrom;
		this.zoneTo = zoneTo;
	}

	public String getStationStart() {
		return stationStart;
	}

	public void setStationStart(String theStationStart) {
		this.stationStart = theStationStart;
	}

	public String getStationEnd() {
		return stationEnd;
	}

	public void setStationEnd(String theStationEnd) {
		this.stationEnd = theStationEnd;
	}

	public Integer getStartedJourneyAt() {
		return startedJourneyAt;
	}

	public void setStartedJourneyAt(Integer theStartedJourneyAt) {
		this.startedJourneyAt = theStartedJourneyAt;
	}

	public Integer getCostInCents() {
		return costInCents;
	}

	public void setCostInCents(Integer theCostInCents) {
		this.costInCents = theCostInCents;
	}

	public Integer getZoneFrom() {
		return zoneFrom;
	}

	public void setZoneFrom(Integer theZoneFrom) {
		this.zoneFrom = theZoneFrom;
	}

	public Integer getZoneTo() {
		return zoneTo;
	}

	public void setZoneTo(Integer zoneTo) {
		this.zoneTo = zoneTo;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof Trip) {
			Trip tripObject = (Trip) o;
			if(tripObject.getStationStart() != null && !tripObject.getStationStart().equals(this.stationStart))
				return false;
			if(tripObject.getStationEnd() != null && !tripObject.getStationEnd().equals(this.stationEnd))
				return false;
			if(tripObject.getStartedJourneyAt() != this.startedJourneyAt)
				return false;
			if(tripObject.getCostInCents() != null && !tripObject.getCostInCents().equals(this.costInCents))
				return false;
			if(tripObject.getZoneFrom() != null && !tripObject.getZoneFrom().equals(this.zoneFrom))
				return false;
			if(tripObject.getZoneTo() != null && !tripObject.getZoneTo().equals(this.zoneTo))
				return false;
		}
		return true;
	}
}
