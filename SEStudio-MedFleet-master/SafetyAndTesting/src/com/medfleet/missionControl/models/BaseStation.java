package com.medfleet.missionControl.models;

import com.medfleet.missionControl.Calculation;

public class BaseStation 
{
	static private double _saftyDistance = 0.002d;
	
	private double _latitude;
	private double _longitude;
	//0 - anything
	private double _altitude;
	
	public BaseStation(double latitude, double longitude, double altitude)
	{
		_latitude = latitude;
		_longitude = longitude;
		_altitude = altitude;
	}
	
	public boolean isSafe(double latitude, double longitude)
	{
		if (distanceFrom(latitude, longitude) < _saftyDistance)
			return true;
		else
			return false;
	}
	
	public double distanceFrom(double latitude, double longitude)
	{
		return Calculation.distFrom(this.getLatitude(), this.getLongitude(), latitude, longitude);
	}
	
	public String getCoordinate()
	{
		return "{'latitude':" + this.getLatitude() + 
			   ", 'longitude':"+this.getLongitude()+
			   ", 'altitude':" + this.getAltitude() + "}";
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
