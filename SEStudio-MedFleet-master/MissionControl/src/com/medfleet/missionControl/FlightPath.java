package com.medfleet.missionControl;

public class FlightPath 
{
	private double _longitude;
	private double _latitude;
	
	public FlightPath(double longitude, double latitude)
	{
		_longitude = longitude;
		_latitude = latitude;
	}
	
	public double getLongitude()
	{
		return _longitude;
	}
	
	public double getLatitude()
	{
		return _latitude;
	}
	
	public String toJson()
	{
		return "[" + _longitude + "," + _latitude + "]";
	}
}
