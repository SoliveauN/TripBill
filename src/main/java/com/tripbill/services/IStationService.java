package com.tripbill.services;

import java.util.List;
import java.util.Optional;

/**
 * @author nsoliveau
 *
 * Interface for interactions with ZoneStationsEnum
 */
public interface IStationService {

	public List<Integer> findZonesByStation(String theStation);
	
	public List<String> findStationsByZone(Integer theIdZone);
	
	public Optional<Integer> identifyZoneByListZoneAndStation(List<Integer> theListZones, String theStation);
}
