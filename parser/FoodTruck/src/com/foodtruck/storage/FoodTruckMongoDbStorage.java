package com.foodtruck.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import com.foodtruck.domain.DataObject;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class FoodTruckMongoDbStorage implements Storage {

	private static String HOSTNAME_TITLE = "hostname";
	private static String PORT_TITLE = "port";
	private static String DBANME_TITLE = "dbname";
	private static String COLLECTIONNAME_TITLE = "collectionname";

	private String HostName;
	private int Port;
	private String DBName;
	private String CollectionName;
	private MongoClient Client;
	private DB	DBClient;
	private DBCollection StorageCollection;

	public static String LOCATION_COLUMN = "locationid";
	public static String NAME_COLUMN = "name";
	public static String ADDRESS_COLUMN = "address";
	public static String FACILITY_COLUMN = "facilitytype";
	public static String STATUS_COLUMN = "status";
	public static String FOODITEM_COLUMN = "fooditems";
	public static String SCHEDULE_COLUMN = "schedule";
	public static String EXPIRATIONDATE_COLUMN = "expirationdate";
	public static String GEOLOCATION_COLUMN = "geolocation";
	public static String CORODINATES_COLUMN = "coordinates";


	@Override
	public void init(BufferedReader reader) throws IOException, UnknownHostException{

		readConfig(reader);
		Client = new MongoClient(HostName , Port);
		DBClient = Client.getDB(DBName);
		StorageCollection = DBClient.getCollection(CollectionName);

	}

	private void readConfig(BufferedReader fileReader) throws IOException {

		try {
			String line;
			while ((line = fileReader.readLine()) != null)
			{

				StringTokenizer st = new StringTokenizer(line, ":");
				while (st.hasMoreElements()) {
					String data = st.nextToken().trim();
					String value = "";
					if (st.hasMoreElements()) {
						value = st.nextToken().trim();
					}
					if (data.equalsIgnoreCase(HOSTNAME_TITLE)) {
						HostName = value;
					} else if (data.equalsIgnoreCase(PORT_TITLE)) {
						Port = Integer.parseInt(value);
					} else if (data.equalsIgnoreCase(DBANME_TITLE)) {
						DBName = value;
					} else if (data.equalsIgnoreCase(COLLECTIONNAME_TITLE)) {
						CollectionName = value;
					}
				}
			}
		} finally {

		}

	}

	@Override
	public void addData(DataObject obj) {
		BasicDBObject dbo = toBasicDBObject(obj);
		BasicDBObject queryObj = new BasicDBObject(LOCATION_COLUMN, obj.getLocationID());
		StorageCollection.update(queryObj, dbo, true, false);
	}

	public BasicDBObject toBasicDBObject(DataObject ob) {
		if (ob == null) {
			return null;
		}
		BasicDBObject dbo = new BasicDBObject(LOCATION_COLUMN, ob.getLocationID())
					.append(NAME_COLUMN, ob.getName())
					.append(FACILITY_COLUMN, ob.getFacilityType())
					.append(ADDRESS_COLUMN, ob.getAddress())
					.append(STATUS_COLUMN, ob.getStatus().toUpperCase())
					.append(FOODITEM_COLUMN, ob.getFoodItems())
					.append(SCHEDULE_COLUMN, ob.getSchedule())
					.append(EXPIRATIONDATE_COLUMN, ob.getExpirationDate())
					.append(GEOLOCATION_COLUMN, new BasicDBObject("type", "Point")
						.append(CORODINATES_COLUMN, new Double[]{ob.getLongitude(), ob.getLatitude()})

					);
	return dbo;
	}
}
