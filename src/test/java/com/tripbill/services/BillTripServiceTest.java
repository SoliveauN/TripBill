package com.tripbill.services;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import com.tripbill.enums.BillTripEnum;

public class BillTripServiceTest {
	
	IBillTripService billTripService = new BillTripServiceImpl();
	
	@Test
	public void givenZonesListStart12AndEnd12_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(1,2);
		List<Integer> listEndOK = Arrays.asList(1,2);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELIN1OR2, billtrip.get());
	}
	
	@Test
	public void givenZonesListStart34AndEnd34_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(3,4);
		List<Integer> listEndOK = Arrays.asList(3,4);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELIN3OR4, billtrip.get());
	}

	@Test
	public void givenZonesListStart3AndEnd12_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(3);
		List<Integer> listEndOK = Arrays.asList(1,2);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELFROM3TO1OR2, billtrip.get());
	}
	
	@Test
	public void givenZonesListStart4AndEnd12_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(4);
		List<Integer> listEndOK = Arrays.asList(1,2);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELFROM4TO1OR2, billtrip.get());
	}
	
	@Test
	public void givenZonesListStart12AndEnd3_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(1,2);
		List<Integer> listEndOK = Arrays.asList(3);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELFROM1OR2TO3, billtrip.get());
	}
	
	@Test
	public void givenZonesListStart12AndEnd4_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(1,2);
		List<Integer> listEndOK = Arrays.asList(4);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndOK);
		
		assertTrue(billtrip.isPresent());
		assertEquals(BillTripEnum.TRAVELFROM1OR2TO4, billtrip.get());
	}
	
	@Test
	public void givenZonesListStartEmptyAndEndAny_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartKO = null;
		List<Integer> listEndOK = Arrays.asList(1,2,3,4);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartKO, listEndOK);
		
		assertTrue(billtrip.isEmpty());
		
		listStartKO = new ArrayList<Integer>();
		billtrip = billTripService.findBillTripFromZones(listStartKO, listEndOK);
		
		assertTrue(billtrip.isEmpty());
	}
	
	@Test
	public void givenZonesListStartAnyAndEndEmpty_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(1,2,3,4);
		List<Integer> listEndKO = null;
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndKO);
		
		assertTrue(billtrip.isEmpty());
		
		listEndKO = new ArrayList<Integer>();
		billtrip = billTripService.findBillTripFromZones(listStartOK, listEndKO);
		
		assertTrue(billtrip.isEmpty());
	}
	
	@Test
	public void givenZonesListStartUnknownAndEndAny_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartKO = Arrays.asList(7);
		List<Integer> listEndOK =Arrays.asList(1,2,3,4);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartKO, listEndOK);
		
		assertTrue(billtrip.isEmpty());
		
		listEndOK = new ArrayList<Integer>();
		billtrip = billTripService.findBillTripFromZones(listStartKO, listEndOK);
		
		assertTrue(billtrip.isEmpty());
	}
	
	@Test
	public void givenZonesListStartAnyAndEndUnknown_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartOK = Arrays.asList(1,2,3,4);
		List<Integer> listEndKO = Arrays.asList(0,6);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartOK, listEndKO);
		
		assertTrue(billtrip.isEmpty());
		
		listEndKO = new ArrayList<Integer>();
		billtrip = billTripService.findBillTripFromZones(listStartOK, listEndKO);
		
		assertTrue(billtrip.isEmpty());
	}
	
	@Test
	public void givenZonesListStartUnknownAndEndUnknown_whenFindBillTrip_thenReturnOptionalBillTrip() {
		List<Integer> listStartKO = Arrays.asList(5,8);
		List<Integer> listEndKO =Arrays.asList(0,9);
		
		Optional<BillTripEnum> billtrip = billTripService.findBillTripFromZones(listStartKO, listEndKO);
		
		assertTrue(billtrip.isEmpty());
		
		listEndKO = new ArrayList<Integer>();
		billtrip = billTripService.findBillTripFromZones(listStartKO, listEndKO);
		
		assertTrue(billtrip.isEmpty());
	}
}
