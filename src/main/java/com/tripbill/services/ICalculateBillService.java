package com.tripbill.services;

import java.util.List;
import java.util.Map;

import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.Trip;

/**
 * @author nsoliveau
 *
 * Interface for calculating bills and produces CustomerSummaries
 */
public interface ICalculateBillService {
	
	public CustomerSummaries calculateBillFromInput(Map<Integer,List<Trip>> listTripByCustomer);
	
	public void check10EurosGap(CustomerSummaries customerSummaries);
	
}
