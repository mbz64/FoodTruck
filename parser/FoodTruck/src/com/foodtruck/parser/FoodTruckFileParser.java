package com.foodtruck.parser;

import java.io.BufferedReader;
import java.io.IOException;

import com.foodtruck.domain.DataObject;

public class FoodTruckFileParser implements FileParser {
	private BufferedReader fileReader;

	@Override
	public void init(BufferedReader reader) throws  IOException {

		fileReader = reader;

		// skip first line
		fileReader.readLine();

	}


	@Override
	public DataObject getNextFoodTruckInfo() throws IOException {
		DataObject dob = null;
		if (fileReader != null) {
			String line = fileReader.readLine();

			if (line != null) {

				String []elements = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
				dob = new DataObject();
				for (int count = 0; count < elements.length; count++){


					String data = elements[count].trim();
					switch(count) {
						case 0: {
							// locationID
							try {
								dob.setLocationID(Long.parseLong(data));
							} catch(NumberFormatException ex) {
								dob.setValid(false);
							}
							break;
						}
						case 1: {
							// Applicant Name
							dob.setName(data);
							break;
						}
						case 2: {
							// FaciltyType
							dob.setFacilityType(data);
							break;
						}
						case 5: {
							// Address
							dob.setAddress(data);
							break;
						}
						case 10: {
							// Status
							dob.setStatus(data);
							break;
						}
						case 11: {
							// FoodItems
							dob.setFoodItems(data);
							break;
						}
						case 14: {
							// Latitude
							dob.setLatitude(data);
							break;
						}
						case 15: {
							// Longitude
							dob.setLongitude(data);
							break;
						}
						case 16: {
							// Schedule
							dob.setSchedule(data);
							break;
						}
						case 21: {
							// ExpirationDate
							dob.setExpirationDate(data);
							break;
						}
					}
				}
			}
		}

		return dob;
	}

}
