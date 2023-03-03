package com.tripbill.services;

import java.util.List;
import java.util.Optional;

import com.tripbill.enums.BillTripEnum;

/**
 * @author nsoliveau
 *
 * Interface for interactions with BillTripEnum
 */
public interface IBillTripService {

	public Optional<BillTripEnum> findBillTripFromZones(List<Integer> zoneStart, List<Integer> zoneEnd);
}
