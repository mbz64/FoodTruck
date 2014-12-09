package com.foodtruck.storage;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.foodtruck.domain.DataObject;
import com.mongodb.BasicDBObject;


public class FoodTruckMongoDbStorageTest {
	private final double precision = 0.000001;

	@Test
	public void testBasicDataObject() throws IOException {

		DataObject obj = new DataObject();
		obj.setLocationID(10);
		obj.setName("XYZ");
		obj.setFacilityType("Cart");
		obj.setAddress("ABC");
		obj.setStatus("approved");
		obj.setFoodItems("DEF");
		obj.setLatitude("30.79500");
		obj.setLongitude("10.79500");
		obj.setSchedule("4:30PM- 9:30PM");
		obj.setExpirationDate("3/15/2015");
		FoodTruckMongoDbStorage fs = new FoodTruckMongoDbStorage();
		BasicDBObject db = fs.toBasicDBObject(obj);
		long id  = (Long) db.get(FoodTruckMongoDbStorage.LOCATION_COLUMN);
		assertEquals(10, id);
		String data = (String) db.get(FoodTruckMongoDbStorage.NAME_COLUMN);
		assertEquals("XYZ", data);
		data = (String) db.get(FoodTruckMongoDbStorage.FACILITY_COLUMN);
		assertEquals("Cart", data);
		data = (String) db.get(FoodTruckMongoDbStorage.ADDRESS_COLUMN);
		assertEquals("ABC", data);
		data = (String) db.get(FoodTruckMongoDbStorage.STATUS_COLUMN);
		assertEquals("approved", data);
		data = (String) db.get(FoodTruckMongoDbStorage.FOODITEM_COLUMN);
		assertEquals("DEF", data);
		data = (String) db.get(FoodTruckMongoDbStorage.SCHEDULE_COLUMN);
		assertEquals("4:30PM- 9:30PM", data);
		data = (String) db.get(FoodTruckMongoDbStorage.EXPIRATIONDATE_COLUMN);
		assertEquals("3/15/2015", data);
		BasicDBObject dbObj = (BasicDBObject) db.get(FoodTruckMongoDbStorage.GEOLOCATION_COLUMN);
		Object ob =dbObj.get(FoodTruckMongoDbStorage.CORODINATES_COLUMN);
		Double []ll = (Double[]) dbObj.get(FoodTruckMongoDbStorage.CORODINATES_COLUMN);
		assertEquals(30.79500, ll[1], precision);
		assertEquals(10.79500, ll[0], precision);

    }

}
