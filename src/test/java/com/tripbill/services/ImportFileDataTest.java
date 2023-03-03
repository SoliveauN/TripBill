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

public class ImportFileDataTest {
	
	private String ressourcesPath = "src/test/resources/";
	private String inputPathOK = ressourcesPath+"testInputOK.txt";
	private String inputPathKO = ressourcesPath+"emptyPath.mty";
	private String inputPathMapDataKO = ressourcesPath+"testDataMapInputKO.txt";
	private String inputPathParseDataKO = ressourcesPath+"testDataParseInputKO.txt";
	
	private IFileDataService fileDataService = new FileDataServiceImpl();
	
	@Test
	public void givenPath_whenReadFile_thenReturnTaps() {
		try {
			Taps taps = fileDataService.importDataJSONFormat(inputPathOK);
			assertNotNull(taps);
		}catch (JsonParseException e) {
			fail();
		} catch (JsonMappingException e) {
			fail();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test()
	public void givenWrongPath_whenReadFile_thenCatchException() {
		try {
			fileDataService.importDataJSONFormat(inputPathKO);
			fail();
		} catch (JsonParseException e) {
			fail();
		} catch (JsonMappingException e) {
			fail();
		} catch (IOException e) {
			assertNotNull(e); // We Actually Catching this one (precisely FileNotFoundException)
		}
	}
	
	@Test()
	public void givenWrongMapData_whenReadFile_thenCatchException() {
		try {
			fileDataService.importDataJSONFormat(inputPathMapDataKO);
			fail();
		} catch (JsonParseException e) {
			fail();
		} catch (JsonMappingException e) {
			assertNotNull(e);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void givenWrongParseData_whenReadFile_thenCatchException() {
		try {
			fileDataService.importDataJSONFormat(inputPathParseDataKO);
			fail();
		} catch (JsonParseException e) {
			assertNotNull(e);
		} catch (JsonMappingException e) {
			fail();
		} catch (IOException e) {
			fail();
		}
	}
}
