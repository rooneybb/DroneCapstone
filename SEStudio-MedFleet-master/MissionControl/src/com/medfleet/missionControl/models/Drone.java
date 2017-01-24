package com.medfleet.missionControl.models;

import org.json.JSONObject;

public class Drone 
{
	private String _id;
	private String _name;

	private double _latitude;
	private double _longitude;
	private double _altitude;
	
	private double _pitchAxis;
	private double _rollAxis;
	private double _yawAxis;
	
	private String _localLocation;
	private String _GPSStrength;
	private double _groundSpeed;
	private double _batteryVoltage;
	private double _batteryPercentage = 100.0;
	private double _lastHeartBeat;
	private double _heading;
	private String _systemStatus;
	private String _mode;
	private double _airSpeed;
	private boolean _armed;
	
	private boolean _isOnLeft;
	
	public boolean isCompleted = true;
	
	public Drone(String id, String name, boolean isOnLeft)
	{
		this._id = id;
		this._name = name;
		this._isOnLeft = isOnLeft;
	}
	
	public Drone(String name)
	{
		this._name = name;
	}

	
	public String toAddNewDroneJSON()
	{
		JSONObject name   = new JSONObject();
		name.put("name", this.getName());
	
		return name.toString();
	}
	
	public String toJson()
	{
		JSONObject drone   = new JSONObject();
		drone.put("name", this.getName());
		drone.put("latitude", this.getLatitude());
		drone.put("longitude", this.getLongitude());
		drone.put("altitude", this.getAltitude());
		drone.put("local_location", this.getLocalLocation());
		drone.put("gps_strength", this.getGPSStrength());
		drone.put("pitch_axis", this.getPitchAxis());
		drone.put("roll_axis", this.getRollAxis());
		drone.put("yaw_axis", this.getYawAxis());
		drone.put("battery_voltage", this.getBatteryVoltage());
		drone.put("battery_percentage", this.getBatteryPercentage());
		drone.put("last_heartbeat", this.getLastHeartBeat());
		drone.put("heading", this.getHeading());
		drone.put("armed", this.getArmed());
		drone.put("mode", this.getMode());
		drone.put("system_status", this.getSystemStatus());
		drone.put("ground_speed", this.getGroundSpeed());
		drone.put("air_speed", this.getAirSpeed());
		
		return drone.toString();
	}
	
	public Drone(JSONObject droneJson)
	{
		this._id = droneJson.getString("id");
		this._name = droneJson.getString("name");
		this._latitude = droneJson.getDouble("latitude");
		this._longitude = droneJson.getDouble("longitude");
		this._altitude = droneJson.getDouble("altitude");
		
		this._pitchAxis = droneJson.getDouble("pitch_axis");
		this._rollAxis  = droneJson.getDouble("roll_axis");
		this._yawAxis   = droneJson.getDouble("yaw_axis");
		
		this._localLocation = droneJson.getString("local_location");
		this._GPSStrength = droneJson.getString("gps_strength");
		this._groundSpeed  = droneJson.getDouble("ground_speed");
		
		this._batteryVoltage = droneJson.getDouble("battery_voltage");
		
		this._lastHeartBeat = droneJson.getDouble("last_heartbeat");
		this._heading = droneJson.getDouble("heading");
		
		this._systemStatus = droneJson.getString("system_status");
		this._mode = droneJson.getString("mode");
		
		this._airSpeed = droneJson.getDouble("air_speed");
		this._armed = droneJson.getBoolean("armed");
	}
	
	public String getId()
	{
		return _id;
	}
	
	public String getName()
	{
		return _name;
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
	
	public double getPitchAxis()
	{
		return _pitchAxis;
	}
	
	public double getRollAxis()
	{
		return _rollAxis;
	}
	
	public double getYawAxis()
	{
		return _yawAxis;
	}
	
	public String getLocalLocation()
	{
		return _localLocation;
	}
	
	public String getGPSStrength()
	{
		return _GPSStrength;
	}
	
	public double getGroundSpeed()
	{
		return _groundSpeed;
	}
	
	public double getBatteryVoltage()
	{
		return _batteryVoltage;
	}
	
	public double getBatteryPercentage()
	{
		return _batteryPercentage;
	}
	
	public double getLastHeartBeat()
	{
		return _lastHeartBeat;
	}
	
	public double getHeading()
	{
		return _heading;
	}
	
	public String getSystemStatus()
	{
		return _systemStatus;
	}
	
	public String getMode()
	{
		return _mode;
	}
	
	public double getAirSpeed()
	{
		return _airSpeed;
	}
	
	public boolean getArmed()
	{
		return _armed;
	}
	
	public boolean getIsOnLeft()
	{
		return _isOnLeft;
	}	
}
