package com.tripbill.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.CustomerSummary;
import com.tripbill.entities.Trip;

/**
 * @author nsoliveau
 *
 * Service Class that has specific use for calculating the total bill for all customer's trips
 *
 * Implements ICalculateBillService
 */
public class CalculateBillServiceImpl implements ICalculateBillService{


	static IMapDataService mapDataService = new MapDataServiceImpl();
	
	/**
	 * Calculates all customer's bills and generates the summary for all customers
	 * 
	 * @param Map<Integer,List<Trip>> theListTripByCustomer
	 * @return CustomerSummaries
	 */
	@Override
	public CustomerSummaries calculateBillFromInput(Map<Integer,List<Trip>> theListTripByCustomer) {
		
		return new CustomerSummaries(theListTripByCustomer.entrySet()
			.stream()
			.map(s -> new CustomerSummary(s.getKey(),
						s.getValue().parallelStream().mapToInt(t -> t.getCostInCents()).sum(),
						s.getValue()))
			.collect(Collectors.toList()));
	}
}
