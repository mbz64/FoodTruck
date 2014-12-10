package com.foodtruck.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;

import com.foodtruck.domain.DataObject;
import com.foodtruck.parser.FileParser;
import com.foodtruck.parser.FoodTruckFileParser;
import com.foodtruck.storage.FoodTruckMongoDbStorage;
import com.foodtruck.storage.Storage;

public class FoodTruckDataLoader {

	private BufferedReader fileReader;

	private void cleanUp() {
		if (fileReader != null) {
			try {
				fileReader.close();
			} catch(IOException ex) {

			}finally {
				fileReader = null;
			}
		}
	}

	private Storage createStorageAccess(String filename) throws UnknownHostException, IOException {
		BufferedReader configFileReader = new BufferedReader(new FileReader(filename));
		FoodTruckMongoDbStorage db = new FoodTruckMongoDbStorage();
		try {
			db.init(configFileReader);
		}finally {
			configFileReader.close();
		}
		return db;
	}

	private FileParser createFileParser(String filename) throws IOException
	{
		fileReader = new BufferedReader(new FileReader(filename));
		FoodTruckFileParser fp = new FoodTruckFileParser();
		fp.init(fileReader);
		return fp;
	}

	private void loadData(FileParser fp, Storage st) throws IOException {
		DataObject dob;
		while ((dob = fp.getNextFoodTruckInfo()) != null) {
			if (dob.isValid()) {
				System.out.println("Adding data");
				st.addData(dob);
			}
		}
	}

	private void showError() {
		System.out.println("Error in run arguments. Expected:");
		System.out.println("arg[0] - datafile");
		System.out.println("arg[1] - dbconfigfile");

	}
	public static void main(String argv[]) {

		FoodTruckDataLoader dl = new FoodTruckDataLoader();
		if (argv.length < 2) {
			dl.showError();
			return;
		}
		try {
			FileParser fp = dl.createFileParser(argv[0]);
			Storage st = dl.createStorageAccess(argv[1]);
			dl.loadData(fp, st);
		} catch(Exception ex) {
			System.out.println("Exception happenned");
			System.out.println(ex.toString());
		}
		finally {
			dl.cleanUp();
		}

	}
}
