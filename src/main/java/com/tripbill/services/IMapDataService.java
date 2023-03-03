package com.tripbill.services;

import java.util.List;
import java.util.Map;

import com.tripbill.entities.Tap;
import com.tripbill.entities.Taps;
import com.tripbill.entities.Trip;

/**
 * @author nsoliveau
 *
 * Interface that provides mapping beetween valuable classes
 */
public interface IMapDataService {

	public Map<Integer, List<Tap>> orderTapByCustomerId(Taps theTaps);
	
	public Map<Integer, List<Trip>> generateValuableTrips(Map<Integer, List<Tap>> theOrderedTaps);
	
	public Trip generateTrip(Tap firstTap, Tap secondTap);
}
