package com.tripbill.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.CustomerSummary;
import com.tripbill.entities.Trip;

public class CalculateBillServiceTest {

	private ICalculateBillService calculateBillService = new CalculateBillServiceImpl();
	
	private CustomerSummaries customerSummaries = new CustomerSummaries();
	
	private void expectedCustommerSummarieExceeded10Euros() {
		List<CustomerSummary> customerSummariesList = new ArrayList<CustomerSummary>();
		
		List<Trip> tripListCustomer = new ArrayList<Trip>();
		tripListCustomer.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer.add(new Trip("H", "G", 1, 200, 4, 4));
		tripListCustomer.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer.add(new Trip("H", "G", 1, 200, 4, 4));
		tripListCustomer.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer.add(new Trip("H", "G", 1, 200, 4, 4));
		
		CustomerSummary expectedCustomerSummary = new CustomerSummary(1, 1000, tripListCustomer);
		customerSummariesList.add(expectedCustomerSummary);
		
		customerSummaries.setCustomerSummaries(customerSummariesList);;
	}
	
	@Test
	public void givenCustumerSummaries_whenCheck10EurosGap_thenReturnCustomerSummaries() {
		expectedCustommerSummarieExceeded10Euros();
		
		List<CustomerSummary> customerSummaryList = new ArrayList<CustomerSummary>();
		
		List<Trip> tripListCustomer1 = new ArrayList<Trip>();
		tripListCustomer1.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer1.add(new Trip("H", "G", 1, 200, 4, 4));
		tripListCustomer1.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer1.add(new Trip("H", "G", 1, 200, 4, 4));
		tripListCustomer1.add(new Trip("B", "C", 1, 240, 1, 2));
		tripListCustomer1.add(new Trip("H", "G", 1, 200, 4, 4));
		
		CustomerSummary customerSummary1 = new CustomerSummary(1, 1320, tripListCustomer1);
		customerSummaryList.add(customerSummary1);
		
		CustomerSummaries customerSummariesTest = new CustomerSummaries(customerSummaryList);
		
		customerSummariesTest = calculateBillService.check10EurosGap(customerSummariesTest);
		
		assertEquals(customerSummaries, customerSummariesTest);
		
	}
}
