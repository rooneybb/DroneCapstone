package com.medfleet.missionControl.models;

import org.json.JSONObject;

public class GPS {
	Double latitude;
	Double longitude;
	Double altitude;
	
	GPS (double lat, double lon, double alt){
		this.latitude = lat;
		this.longitude = lon;
		this.altitude = alt;
	}
	public String toJson () {
		JSONObject gps   = new JSONObject();
		gps.put("latitude",  this.latitude);
		gps.put("longitude", this.longitude);
		gps.put("altitude",  this.altitude);
		
		return gps.toString();
		
	}
}
