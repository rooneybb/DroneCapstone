package com.medfleet.missionControl.models;

public class FlightPath {
	double latitude;
	double longitude;
	
	public FlightPath(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public double[] getFlightPath() {
		return new double[] { latitude, longitude };
	}
}
