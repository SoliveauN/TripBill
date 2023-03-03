package com.tripbill.services;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

public class StationsServiceTest {
	
	IStationsService stationsService = new StationsServiceImpl();
	
	@Test
	public void givenStationAOrB_whenFindingZones_thenReturnZonesList() {
		String stationOK = "A";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(1));
		
		stationOK = "B";
		
		listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(1));
	}
	
	@Test
	public void givenStationD_whenFindingZones_thenReturnZonesList() {
		String stationOK = "D";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(2));
	}
	
	@Test
	public void givenStationCOrE_whenFindingZones_thenReturnZonesList() {
		String stationOK = "C";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 2);
		assertTrue(listZones.contains(2));
		assertTrue(listZones.contains(3));
		
		stationOK = "E";
		
		listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 2);
		assertTrue(listZones.contains(2));
		assertTrue(listZones.contains(3));
	}
	
	@Test
	public void givenStationF_whenFindingZones_thenReturnZonesList() {
		String stationOK = "F";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 2);
		assertTrue(listZones.contains(3));
		assertTrue(listZones.contains(4));
	}
	
	@Test
	public void givenStationGOrHOrI_whenFindingZones_thenReturnZonesList() {
		String stationOK = "G";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(4));
		
		stationOK = "H";
		
		listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(4));
		
		stationOK = "I";
		
		listZones = stationsService.findZonesByStation(stationOK);
		
		assertTrue(listZones != null);
		assertTrue(!listZones.isEmpty());
		assertTrue(listZones.size() == 1);
		assertTrue(listZones.contains(4));
	}
	
	@Test
	public void givenStationUnknown_whenFindingZones_thenReturnZonesList() {
		String stationKO = "J";
		
		List<Integer> listZones = stationsService.findZonesByStation(stationKO);
		
		assertTrue(listZones != null);
		assertTrue(listZones.isEmpty());
	}
	
	@Test
	public void givenListAnyZoneAndStationA_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = Arrays.asList(1,2,3,4);
		String stationOK = "A";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(zone.isPresent());
		assertTrue(zone.get() == 1);
	}
	
	@Test
	public void givenListAnyZoneAndStationC_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = Arrays.asList(1,2,3,4);
		String stationOK = "C";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(zone.isPresent());
		assertTrue(zone.get() == 2 || zone.get() == 3);
	}
	
	@Test
	public void givenListZoneLess3AndStationC_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = Arrays.asList(1,2,4);
		String stationOK = "C";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(zone.isPresent());
		assertTrue(zone.get() == 2);
	}
	
	@Test
	public void givenListAnyZoneAndStationF_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = Arrays.asList(1,2,3,4);
		String stationOK = "F";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(zone.isPresent());
		assertTrue(zone.get() == 3 || zone.get() == 4);
	}
	
	@Test
	public void givenListAnyZoneAndStationUnknown_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = Arrays.asList(1,2,3,4);
		String stationOK = "J";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(!zone.isPresent());
	}
	
	@Test
	public void givenListNoZoneAndAnyStation_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = null;
		String stationOK = "C";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(!zone.isPresent());
	}
	
	@Test
	public void givenListNoZoneAndStationUnknown_whenIdentifingStation_thenReturnOptionalInteger() {
		List<Integer> listZonesOK = null;
		String stationOK = "J";
		
		Optional<Integer> zone = stationsService.identifyZoneByListZoneAndStation(listZonesOK,stationOK);
		
		assertTrue(!zone.isPresent());
	}
}
