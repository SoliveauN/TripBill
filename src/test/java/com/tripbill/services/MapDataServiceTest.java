package com.tripbill.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.tripbill.entities.Tap;
import com.tripbill.entities.Taps;
import com.tripbill.entities.Trip;

public class MapDataServiceTest {
	
	Taps tapsData = new Taps();
	Taps tapsBadData = new Taps();

	String inputPath = "src/test/resources/testInputOK.txt";
	String inputMissingDataPath = "src/test/resources/testInputMissingData.txt";
	
	IMapDataService mapDataService = new MapDataServiceImpl();
	
	IFileDataService fileDataService = new FileDataServiceImpl();
	
	Map<Integer,List<Tap>> expectedOrderedTapByCustomer = new HashMap<Integer,List<Tap>>();
	
	Map<Integer, List<Trip>> expectedTripsByCustomer = new HashMap<Integer,List<Trip>>();
	Map<Integer, List<Trip>> expectedMissingDataTripsByCustomer = new HashMap<Integer,List<Trip>>();
	
	private void initTapsData() {
		try {
			tapsData = fileDataService.importDataJSONFormat(inputPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void initMissingTapsData() {
		try {
			tapsBadData = fileDataService.importDataJSONFormat(inputMissingDataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void initExpectedOrderedTapByCustomer() {
		List<Tap> orderedTapsForCustomer1 = new ArrayList<Tap>();
		orderedTapsForCustomer1.add(new Tap(1, 1, "A"));
		orderedTapsForCustomer1.add(new Tap(2, 1, "D"));
		
		List<Tap> orderedTapsForCustomer2 = new ArrayList<Tap>();
		orderedTapsForCustomer2.add(new Tap(2, 2, "B"));
		orderedTapsForCustomer2.add(new Tap(3, 2, "C"));
		orderedTapsForCustomer2.add(new Tap(3, 2, "H"));
		orderedTapsForCustomer2.add(new Tap(10, 2, "G"));
		
		List<Tap> orderedTapsForCustomer3 = new ArrayList<Tap>();
		orderedTapsForCustomer3.add(new Tap(3, 3, "H"));
		orderedTapsForCustomer3.add(new Tap(27, 3, "E"));
		
		List<Tap> orderedTapsForCustomer4 = new ArrayList<Tap>();
		orderedTapsForCustomer4.add(new Tap(41, 4, "A"));
		orderedTapsForCustomer4.add(new Tap(47, 4, "I"));
		
		expectedOrderedTapByCustomer.put(1, orderedTapsForCustomer1);
		expectedOrderedTapByCustomer.put(2, orderedTapsForCustomer2);
		expectedOrderedTapByCustomer.put(3, orderedTapsForCustomer3);
		expectedOrderedTapByCustomer.put(4, orderedTapsForCustomer4);
	}
	
	private void initExpectedTripsByCustomer() {
		List<Trip> tripsForCustomer1 = new ArrayList<Trip>();
		tripsForCustomer1.add(new Trip("A", "D", 1, 240, 1, 2));
		
		List<Trip> tripsForCustomer2 = new ArrayList<Trip>();
		tripsForCustomer2.add(new Trip("B", "C", 2, 240, 1, 2));
		tripsForCustomer2.add(new Trip("H", "G", 3, 200, 4, 4));
		
		List<Trip> tripsForCustomer3 = new ArrayList<Trip>();
		tripsForCustomer3.add(new Trip("H", "E", 3, 200, 4, 3));
		
		List<Trip> tripsForCustomer4 = new ArrayList<Trip>();
		tripsForCustomer4.add(new Trip("A", "I", 41, 300, 1, 4));
		
		expectedTripsByCustomer.put(1, tripsForCustomer1);
		expectedTripsByCustomer.put(2, tripsForCustomer2);
		expectedTripsByCustomer.put(3, tripsForCustomer3);
		expectedTripsByCustomer.put(4, tripsForCustomer4);
	}
	
	private void initExpectedMissingDataTripsByCustomer() {
		initExpectedTripsByCustomer();
		expectedMissingDataTripsByCustomer = expectedTripsByCustomer;
		List<Trip> tripMissingData = expectedMissingDataTripsByCustomer.get(2);
		tripMissingData.clear();
		tripMissingData.add(new Trip("B", "C", 2, 240, 1, 2));
		tripMissingData.add(new Trip("G",null,10,null,null,null));
		
		expectedMissingDataTripsByCustomer.put(2, tripMissingData);
	}

	@Test
	public void givenTaps_whenGenerateOrderedTapByCustomer_thenReturnTapOrderedByCustomer() {
		initTapsData();
		initExpectedOrderedTapByCustomer();
		
		Map<Integer,List<Tap>> testOrderedTap = mapDataService.orderTapByCustomerId(tapsData);
		
		assertTrue(testOrderedTap != null);
		assertTrue(!testOrderedTap.isEmpty());
		assertEquals(expectedOrderedTapByCustomer, testOrderedTap);
	}
	
	@Test
	public void givenNullTapsOrEmptyListTaps_whenGenerateOrderedTapByCustomer_thenReturnTapOrderedByCustomer() {
		initExpectedOrderedTapByCustomer();
		
		Map<Integer,List<Tap>> testOrderedTap = mapDataService.orderTapByCustomerId(tapsData);
		
		assertTrue(testOrderedTap != null);
		assertTrue(testOrderedTap.isEmpty());
		
		testOrderedTap = mapDataService.orderTapByCustomerId(null);
		
		assertTrue(testOrderedTap != null);
		assertTrue(testOrderedTap.isEmpty());
	}
	
//	@Test
//	public void givenOrderedTapByCustomer_whenGenerateTrips_theReturnTripsByCustomer() {
//		initTapsData();
//		initExpectedTripsByCustomer();
//		
//		Map<Integer,List<Tap>> testOrderedTap = mapDataService.orderTapByCustomerId(tapsData);
//		
//		Map<Integer,List<Trip>> testTripsByCustomer = mapDataService.generateValuableTrips(testOrderedTap);
//		
//		assertTrue(testTripsByCustomer != null);
//		assertTrue(!testTripsByCustomer.isEmpty());
//		assertEquals(expectedTripsByCustomer, testTripsByCustomer);
//	}
	
	@Test
	public void givenBadOrderedTapByCustomer_whenGenerateTrips_theReturnTripsByCustomer() {
		initMissingTapsData();
		initExpectedMissingDataTripsByCustomer();
		
		Map<Integer,List<Tap>> testOrderedTap = mapDataService.orderTapByCustomerId(tapsBadData);
		
		Map<Integer,List<Trip>> testMissingDataTripsByCustomer = mapDataService.generateValuableTrips(testOrderedTap);
		
		assertTrue(testMissingDataTripsByCustomer != null);
		assertTrue(!testMissingDataTripsByCustomer.isEmpty());
		assertEquals(expectedMissingDataTripsByCustomer, testMissingDataTripsByCustomer);
	}
}
