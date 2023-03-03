package com.tripbill.enums;

import java.util.Arrays;
import java.util.List;

/**
 * @author nsoliveau
 *
 * Data Enum that represents all billing pattern available
 */
public enum BillTripEnum {
	
	TRAVELIN1OR2(Arrays.asList(1,2),Arrays.asList(1,2),240),
	TRAVELIN3OR4(Arrays.asList(3,4),Arrays.asList(3,4),200),
	TRAVELFROM3TO1OR2(Arrays.asList(3),Arrays.asList(1,2),280),
	TRAVELFROM4TO1OR2(Arrays.asList(4),Arrays.asList(1,2),300),
	TRAVELFROM1OR2TO3(Arrays.asList(1,2),Arrays.asList(3),280),
	TRAVELFROM1OR2TO4(Arrays.asList(1,2),Arrays.asList(4),300);
	
	private List<Integer> idZoneFrom;
	private List<Integer> idZoneTo;
	private Integer priceOfTrip;
	
	private BillTripEnum(List<Integer> idZoneFrom, List<Integer> idZoneTo,Integer priceOfTrip) {
		this.idZoneFrom = idZoneFrom;
		this.idZoneTo = idZoneTo;
		this.priceOfTrip = priceOfTrip;
	}

	public List<Integer> getIdZoneFrom() {
		return idZoneFrom;
	}

	public List<Integer> getIdZoneTo() {
		return idZoneTo;
	}

	public Integer getPriceOfTrip() {
		return priceOfTrip;
	}
}