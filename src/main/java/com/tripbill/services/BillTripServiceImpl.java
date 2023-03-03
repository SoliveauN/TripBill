package com.tripbill.services;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.tripbill.enums.BillTripEnum;

/**
 * @author nsoliveau
 * 
 * Service Class that has specific use with BillTripEnum
 * 
 * Implements IBillTripService
 * 
 */
public class BillTripServiceImpl implements IBillTripService{

	/**
	 * Find the lowest price for a trip
	 * 
	 * @param List<Integer> theZoneStart, List<Integer> theZoneEnd
	 * @return Optional<BillTripEnum>
	 */
	@Override
	public Optional<BillTripEnum> findBillTripFromZones(List<Integer> theZoneStart, List<Integer> theZoneEnd) {

		return Arrays.stream(BillTripEnum.values())
			.filter(bl -> theZoneStart.stream()
			      .anyMatch(zs -> bl.getIdZoneFrom().contains(zs)))
			.filter(bl -> theZoneEnd.stream()
				   .anyMatch(ze -> bl.getIdZoneTo().contains(ze)))
			.min(Comparator.comparing(BillTripEnum::getPriceOfTrip));
		
	}

}
