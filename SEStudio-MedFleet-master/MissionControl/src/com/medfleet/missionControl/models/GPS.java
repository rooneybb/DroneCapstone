package com.medfleet.missionControl.models;

import java.util.Optional;

import org.json.JSONObject;

public class GPS {
	double latitude;
	double longitude;
	double altitude;
	
	public double getLatitude () 	{ return this.latitude; }
	public double getLongitude () 	{ return this.longitude;}
	public double getAltitude () 	{ return this.altitude; }
	
	GPS (double lat, double lon, double alt){
		this.latitude =  lat;
		this.longitude = lon;
		this.altitude =  (Optional.ofNullable(alt).orElse(0.0));
	}

	public JSONObject toJsonByClass() {
		return new JSONObject(this);
	}
}
