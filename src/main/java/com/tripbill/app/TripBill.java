package com.tripbill.app;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.tripbill.entities.CustomerSummaries;
import com.tripbill.entities.Tap;
import com.tripbill.entities.Taps;
import com.tripbill.entities.Trip;
import com.tripbill.services.CalculateBillServiceImpl;
import com.tripbill.services.FileDataServiceImpl;
import com.tripbill.services.ICalculateBillService;
import com.tripbill.services.IFileDataService;
import com.tripbill.services.IMapDataService;
import com.tripbill.services.MapDataServiceImpl;

public class TripBill {

	static IFileDataService fileDataService = new FileDataServiceImpl();
	static IMapDataService mapDataService = new MapDataServiceImpl();
	static ICalculateBillService calculateBillService = new CalculateBillServiceImpl();

	public static void main(String... args) {
		/*   
		 * 2 String param : 1 Path input, 1 Path output
		 * Map input JSONdata to calculate bills for each customer
		 * Then write in output file to JSONformat
		 */
		if(args.length == 2) {
			System.out.println("----- Accessing and importing data from "+args[0]+" -----");
			Taps taps;
			try {
				taps = fileDataService.importDataJSONFormat(args[0]);
				System.out.println("----- Mapping Data -----");
				Map<Integer, List<Tap>> orderedTaps = mapDataService.orderTapByCustomerId(taps);
				if(orderedTaps == null || orderedTaps.isEmpty()) {
					System.err.println("----- No Data Provided -----");
					exitingProgramDueToError();
				}
				System.out.println("----- Generate trips for each customer -----");
				Map<Integer, List<Trip>> listTripByCustomer = mapDataService.generateValuableTrips(orderedTaps);
				if(listTripByCustomer == null || listTripByCustomer.isEmpty()) {
					System.err.println("----- No Trip To Bill -----");
					exitingProgramDueToError();
				}
				System.out.println("----- Calculating bills for customers -----");
				CustomerSummaries customerSummaries = calculateBillService.calculateBillFromInput(listTripByCustomer);
				if(customerSummaries == null) {
					System.err.println("----- No Summaries to export -----");
					exitingProgramDueToError();
				}
				calculateBillService.check10EurosGap(customerSummaries);
				
				System.out.println("----- Exporting data at "+args[1]+" -----");
				fileDataService.exportDataJSONFormat(customerSummaries,args[1]);

				System.out.println("----- Program proceed correctly -----");
				System.out.println("----- Exiting the program ! -----");
				System.exit(0);
			} catch (JsonParseException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (JsonMappingException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
				System.exit(1);
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
		else{
			if(args.length > 2) {
				System.err.println("More than 2 Paths given. You have to give only 2 Paths.");
			}
			else {
				System.err.println("Less than 2 Paths given. You have to give only 2 Paths.");
			}
			exitingProgramDueToError();
		}
	}
	private static void exitingProgramDueToError() {
		System.out.println("----- Exiting the program ! -----");
		System.exit(1);
	}

}
