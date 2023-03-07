package com.tripbill.services;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tripbill.entities.Tap;
import com.tripbill.entities.Taps;
import com.tripbill.entities.Trip;
import com.tripbill.enums.BillTripEnum;

/**
 * @author nsoliveau
 *
 * Service Class that provides mapping between valuable Classes 
 */
public class MapDataServiceImpl implements IMapDataService{

	static IStationsService stationsService = new StationsServiceImpl();
	static IBillTripService billTripService = new BillTripServiceImpl();
	
	/**
	 * Group all taps by a customer for all customers
	 * @param Taps theTaps
	 * @return Map<Integer, List<Tap>>
	 */
	@Override
	public Map<Integer, List<Tap>> orderTapByCustomerId(Taps theTaps) {
		
		return theTaps != null && theTaps.getTaps() != null ?
				theTaps.getTaps().stream()
				.sorted(Comparator.comparing(Tap::getCustomerId))
				.collect(Collectors.groupingBy(Tap::getCustomerId,
						Collectors.mapping(t -> t,Collectors.toList())))
				: new HashMap<Integer, List<Tap>>();
	}
	
	/**
	 * Generates all trips made by a customer from his taps
	 * @param Map<Integer, List<Tap>> theOrderedTaps
	 * @return Map<Integer, List<Trip>>
	 */
	@Override
	public Map<Integer, List<Trip>> generateValuableTrips(Map<Integer, List<Tap>> theOrderedTaps) {
		Map<Integer, List<Trip>> tripsByCustomer = new HashMap<Integer, List<Trip>>();
		
		for (Map.Entry<Integer, List<Tap>> set :
			theOrderedTaps.entrySet()) {
			
			tripsByCustomer.put(set.getKey(),new ArrayList<Trip>());
			
			List<Tap> listTapForCustomer = set.getValue().stream()
					.sorted(Comparator.comparing(Tap::getUnixTimestamp))
					.collect(Collectors.toList());
			
			

			for(int i = 0; i < listTapForCustomer.size();i+=2) {
				
				List<Trip> listTripTemp = tripsByCustomer.get(set.getKey());
				
				Tap secondTap = null;
				if(i+1 < listTapForCustomer.size()) {
					secondTap = listTapForCustomer.get(i+1);
				}else {
					System.out.println("No second Tap for customerId " + set.getKey() + " from first Tap at " + listTapForCustomer.get(i).getStation() + " station.");
				}
				listTripTemp.add(generateTrip(listTapForCustomer.get(i), secondTap));
				
				tripsByCustomer.put(set.getKey(),listTripTemp);
			}
		}
		return tripsByCustomer;
	}
	
	/**
	 * Generate a trip from two taps
	 * @param Tap theFirstTap, Tap theSecondeTap
	 */
	@Override
	public Trip generateTrip(Tap theFirstTap, Tap theSecondTap) {
		Trip trip = new Trip();

		trip.setStationStart(theFirstTap.getStation());
		trip.setStartedJourneyAt(theFirstTap.getUnixTimestamp());
		
		if(theSecondTap != null) {
			trip.setStationEnd(theSecondTap.getStation());
				
			List<Integer> zonesStart = stationsService.findZonesByStation(theFirstTap.getStation());
			List<Integer> zonesEnd = stationsService.findZonesByStation(theSecondTap.getStation());
			
			Optional<BillTripEnum> optLowestBillTrip = billTripService.findBillTripFromZones(zonesStart, zonesEnd);
			
			if(optLowestBillTrip.isPresent()){
				BillTripEnum lowestBillTrip = optLowestBillTrip.get();
				stationsService.identifyZoneByListZoneAndStation(lowestBillTrip.getIdZoneFrom(),theFirstTap.getStation());
				
				Optional<Integer> zoneFrom = stationsService.identifyZoneByListZoneAndStation(lowestBillTrip.getIdZoneFrom(),theFirstTap.getStation());
				Optional<Integer> zoneTo = stationsService.identifyZoneByListZoneAndStation(lowestBillTrip.getIdZoneTo(),theSecondTap.getStation());
				
				trip.setZoneFrom(zoneFrom.isPresent() ? zoneFrom.get() : 0);
				trip.setZoneTo(zoneTo.isPresent() ? zoneTo.get() : 0);
				trip.setCostInCents(lowestBillTrip.getPriceOfTrip());
			}
		}
		return trip;
	}
}
