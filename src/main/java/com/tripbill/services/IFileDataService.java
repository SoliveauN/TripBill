package com.tripbill.services;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.Taps;

/**
 * @author nsoliveau
 *
 * Interface for managing file access
 */
public interface IFileDataService{

	public Taps importDataJSONFormat(String theIntputPath) throws JsonParseException, JsonMappingException, IOException;
	
	public void exportDataJSONFormat(CustomerSummaries theCustomerSummaries,String theOutputPath) throws JsonGenerationException, JsonMappingException, IOException;
}
