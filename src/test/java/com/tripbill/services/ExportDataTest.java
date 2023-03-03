package com.tripbill.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tripbill.entities.Taps;
import com.tripbill.services.FileDataServiceImpl;
import com.tripbill.services.IFileDataService;

public class ExportDataTest {
	
	private String ressourcesPath = "src/test/resources/";
	private String inputPathOK = ressourcesPath+"testInputOK.txt";
	private String inputPathKO = ressourcesPath+"emptyPath.mty";
	private String inputPathMapDataKO = ressourcesPath+"testDataMapInputKO.txt";
	private String inputPathParseDataKO = ressourcesPath+"testDataParseInputKO.txt";
	
	private IFileDataService fileDataService = new FileDataServiceImpl();
	
	@Test
	public void givenCustomerSummaries_whenWritingFil_then() {
		
	}
}
