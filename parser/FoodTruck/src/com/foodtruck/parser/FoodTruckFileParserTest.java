package com.foodtruck.parser;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.junit.Test;

import com.foodtruck.domain.DataObject;

public class FoodTruckFileParserTest {
	private final double precision = 0.000001;
	@Test
	public void testGetNextFoodTruckInfo() throws IOException {
		String data = "DummyLine\n1, ABC Inc,	Truck,, DESC, XYZ st, 10, 10, 10, 10, Approved, GEF, 10, 11, 37.790, -122.397, 10:30 am,  ,  ,  , 0, 3/15/2015, ";
		Reader reader = new StringReader(data);
		FoodTruckFileParser ftParser = new FoodTruckFileParser();
		ftParser.init(new BufferedReader(reader));
		DataObject obj = ftParser.getNextFoodTruckInfo();
		assertEquals(1, obj.getLocationID());
		assertEquals("ABC Inc", obj.getName());
		assertEquals("Truck", obj.getFacilityType());
		assertEquals("XYZ st", obj.getAddress());
		assertEquals("Approved", obj.getStatus());
		assertEquals("GEF", obj.getFoodItems());
		assertEquals(37.790, obj.getLatitude(), 0.000001);
		assertEquals(-122.397, obj.getLongitude(), 0.000001);
		assertEquals("3/15/2015", obj.getExpirationDate());
    }

	@Test
	public void testGetNextFoodTruckInfoMultiple() throws IOException {

		long ID[] = { 1,  2 ,  3};
		Double Latitude[] = {30.90006, -15.3005 , 80.50004};
		Double Longitude[] = {90.90006, 70.3005 , -20.50004};
		String data = "DummyLine\n" +
				ID[0] + ", ABC Inc,	Truck, 10, DESC, XYZ st, 10, 10, 10, 10, Approved, GEF, 10, 11, "+ String.valueOf(Latitude[0]) + ", " + String.valueOf(Longitude[0]) +", 10:30 am,  ,  ,  , 0, 3/15/2015, \n" +
				ID[1] + ", ABC Inc,	Truck, 10, DESC, XYZ st, 10, 10, 10, 10, Approved, GEF, 10, 11, "+ String.valueOf(Latitude[1]) + ", " + String.valueOf(Longitude[1]) +", 10:30 am,  ,  ,  , 0, 3/15/2015, \n" +
				ID[2] + ", ABC Inc,	Truck, 10, DESC, XYZ st, 10, 10, 10, 10, Approved, GEF, 10, 11, "+ String.valueOf(Latitude[2]) + ", " + String.valueOf(Longitude[2]) +", 10:30 am,  ,  ,  , 0, 3/15/2015, \n";

		Reader reader = new StringReader(data);
		FoodTruckFileParser ftParser = new FoodTruckFileParser();
		ftParser.init(new BufferedReader(reader));
		for (int i = 0; i < ID.length ;i++) {
			DataObject obj = ftParser.getNextFoodTruckInfo();
			assertEquals(ID[i], obj.getLocationID());
			assertEquals(Latitude[i], obj.getLatitude(), precision);
			assertEquals(Longitude[i], obj.getLongitude(), precision);
		}
    }
}
