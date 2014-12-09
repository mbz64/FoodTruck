package com.foodtruck.parser;

import java.io.BufferedReader;
import java.io.IOException;

import com.foodtruck.domain.DataObject;

public interface FileParser {

	/**
	 *  Make the parser ready to read from data file.
	 */
	public void init(BufferedReader reader) throws IOException;

	/**
	 *  Gets the next element from the data file
	 * @return DataObject for valid data, null when no more data left
	 */
	public DataObject getNextFoodTruckInfo() throws IOException;

}
