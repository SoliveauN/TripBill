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

public class MapDataServiceTest {
	
	Taps tapsData = new Taps();
	IMapDataService mapDataService = new MapDataServiceImpl();
	
	String inputPath = "src/test/resources/testInputOK.txt";
	IFileDataService fileDataService = new FileDataServiceImpl();
	
	Map<Integer,List<Tap>> expectedOrderedTapByCustomer = new HashMap<Integer,List<Tap>>();
	
	public void initTapsData() {
		try {
			tapsData = fileDataService.importDataJSONFormat(inputPath);
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
	
	
}
