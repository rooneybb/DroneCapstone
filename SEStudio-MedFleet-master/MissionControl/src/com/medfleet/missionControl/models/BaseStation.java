package com.medfleet.missionControl.models;


import com.medfleet.missionControl.Calculation;



public class BaseStation 
{
	private double _latitude;
	private double _longitude;
	private double _altitude;
	
	public BaseStation(double latitude, double longitude, double altitude)
	{
		_latitude = latitude;
		_longitude = longitude;
		_altitude = altitude;
	}
	
	public boolean isSafe(double latitude, double longitude)
	{
		if (distanceFrom(latitude, longitude) < com.medfleet.missionControl.config.config.SafetyRadius)
			return true;
		else
			return false;
	}
	
	public double distanceFrom(double latitude, double longitude)
	{
		return Calculation.distFrom(this.getLatitude(), this.getLongitude(), latitude, longitude);

	}
	
	public double getLatitude()
	{
		return _latitude;
	}
	
	public double getLongitude()
	{
		return _longitude;
	}
	
	public double getAltitude()
	{
		return _altitude;
	}
}
