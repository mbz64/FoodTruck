package com.foodtruck.domain;

public class DataObject {

	 private long LocationID;
	 private String Name;
	 private String FacilityType;
	 private String Address;
	 private String Status;
	 private String FoodItems;
	 private double Latitude;
	 private double Longitude;
	 private String Schedule;
	 private String ExpirationDate;
	 private boolean Valid;

	 public DataObject() {
		 Valid = true;
	 }

	 public long getLocationID() {
		return LocationID;
	}
	public void setLocationID(long locationID) {
		LocationID = locationID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getFacilityType() {
		return FacilityType;
	}
	public void setFacilityType(String facilityType) {
		FacilityType = facilityType;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getFoodItems() {
		return FoodItems;
	}
	public void setFoodItems(String foodItems) {
		FoodItems = foodItems;
	}
	public Double getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		try {
			Latitude = Double.parseDouble(latitude);
		} catch(NumberFormatException ex) {
			setValid(false);
		}
	}
	public Double getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		try {
			Longitude = Double.parseDouble(longitude);
		}catch(NumberFormatException ex) {
			setValid(false);
		}
	}
	public String getSchedule() {
		return Schedule;
	}
	public void setSchedule(String schedule) {
		Schedule = schedule;
	}
	public String getExpirationDate() {
		return ExpirationDate;
	}
	public void setExpirationDate(String expirationDate) {
		ExpirationDate = expirationDate;
	}

	public boolean isValid() {
		return Valid;
	}

	public void setValid(boolean valid) {
		Valid = valid;
	}

}
