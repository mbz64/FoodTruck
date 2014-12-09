package com.foodtruck.storage;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.UnknownHostException;

import com.foodtruck.domain.DataObject;

public interface Storage {
	public void init(BufferedReader reader) throws IOException, UnknownHostException;

	public void addData(DataObject obj);
}
