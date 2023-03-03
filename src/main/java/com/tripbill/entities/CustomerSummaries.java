package com.tripbill.entities;

import java.util.List;

/**
 * @author nsoliveau
 * 
 * Valuable Class that represents all customers' summary
 */
public class CustomerSummaries {

	private List<CustomerSummary> customerSummaries;

	public CustomerSummaries(List<CustomerSummary> customerSummaries) {
		this.customerSummaries = customerSummaries;
	}

	public List<CustomerSummary> getCustomerSummaries() {
		return customerSummaries;
	}

	public void setCustomerSummaries(List<CustomerSummary> theCustomerSummaries) {
		this.customerSummaries = theCustomerSummaries;
	}
	
}
