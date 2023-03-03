package com.tripbill.services;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.Taps;

/**
 * @author nsoliveau
 *
 * Service Class that manages accessing, reading and writing with files
 * 
 * Implements IFileDataService
 */
public class FileDataServiceImpl implements IFileDataService {

	ObjectMapper mapper = new ObjectMapper();
	ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();

	/**
	 * Access and read the file at the specific path then parse JSON format Data to Taps Class
	 * 
	 * @param String theInputPath
	 * @return Taps
	 * @throws JsonParseException 
	 */
	@Override
	public Taps importDataJSONFormat(String theIntputPath)
			throws JsonParseException,JsonMappingException,IOException {
		Taps taps = new Taps();
		try {
			taps = mapper.readValue(new File(theIntputPath), Taps.class);
		} catch (JsonParseException e) {
			System.err.println("An unexpected error occured while parsing the file !");
			throw(e);
		} catch (JsonMappingException e) {
			System.err.println("An unexpected error occured while trying to map data from the file !");
			throw(e);
		} catch (IOException e) {
			System.err.println("An unexpected error occured while trying to access the file at path "+theIntputPath+" !");
			throw(e);
		}
		return taps;
	}

	/**
	 * Access and create/replace the file at the specific path then write customerSummariese Data with a JSON format
	 * 
	 * @param CustomerSummaries theCustomerSummaries, String theOutputPath
	 */
	@Override
	public void exportDataJSONFormat(CustomerSummaries theCustomerSummaries, String theOutputPath)
			throws JsonGenerationException,JsonMappingException,IOException{
		try {
			writer.writeValue(new File(theOutputPath), theCustomerSummaries);
		} catch (JsonGenerationException e) {
			System.err.println("An unexpected error occured while generation the file !");
			throw(e);
		} catch (JsonMappingException e) {
			System.err.println("An unexpected error occured while trying to map data for the file !");
			throw(e);
		} catch (IOException e) {
			System.err.println("An unexpected error occured while trying to access the repository at path "+theOutputPath+" !");
			throw(e);
		}
	}
}
