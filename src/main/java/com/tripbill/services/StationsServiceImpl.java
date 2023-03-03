package com.tripbill.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.tripbill.enums.ZoneStationsEnum;

/**
 * @author nsoliveau
 *
 * Service Class that has specific use with ZoneStationsEnum
 */
public class StationsServiceImpl implements IStationsService{

	/**
	 * Find the zones that the given station is in
	 * @param String theStation
	 * @return List<Integer>
	 */
	@Override
	public List<Integer> findZonesByStation(String theStation) {
		return Arrays.stream(ZoneStationsEnum.values())
				.filter(zs -> zs.getStationList().contains(theStation))
				.map(zs -> zs.getIdZone())
				.collect(Collectors.toList());
	}
	
	
	/**
	 * Identify the zone based on the given station and zones(From BillTripEnum)
	 * @param List<Integer> theListZones, String theStation
	 * @return Optional<Integer>
	 */
	@Override
	public Optional<Integer> identifyZoneByListZoneAndStation(List<Integer> theListZones, String theStation) {
		return theListZones != null ?
				theListZones.stream()
			.filter(lz -> findZonesByStation(theStation).stream()
			      .anyMatch(zs -> lz == zs))
			.findAny()
			:Optional.empty();
	}

}