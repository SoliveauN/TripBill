package com.tripbill.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author nsoliveau
 *
 * Data Enum that represents all zones and which station is in which zone
 */
public enum ZoneStationsEnum {
	
	ZONE1(1,Arrays.asList("A","B")),
	ZONE2(2,Arrays.asList("C","D","E")),
	ZONE3(3,Arrays.asList("C","E","F")),
	ZONE4(4,Arrays.asList("F","G","H","I"));
	
	private Integer idZone;
	private List<String> stationList;
	
	private ZoneStationsEnum(Integer idZone,List<String> stationList) {
		this.idZone = idZone;
		this.stationList = stationList;
	}

	public Integer getIdZone() {
		return idZone;
	}

	public List<String> getStationList() {
		return stationList;
	}
}
