package com.medfleet.missionControl.models;

import org.json.JSONObject;

public class Copter {
	
	public String id;

	public GPS    gps;

	public double pitchAxis;
	public double rollAxis;
	public double yawAxis;
	public double airSpeed;
	public double heading;
	
	public String localLocation;
	public String GPSStrength;
	public double groundSpeed;
	
	public double batteryVoltage;
	public double batteryPercentage;
	public double lastHeartBeat;
	
	public String systemStatus;
	public String mode;
	public boolean armed;
 
	
	public Copter () {}
	public Copter (JSONObject copter) {
		id = copter.getString("_id");

		gps = new GPS(copter.getDouble("latitude"),
						copter.getDouble("longitude"), 
						copter.getDouble("altitude"));

		pitchAxis = copter.getDouble("pitch_axis");
		rollAxis = copter.getDouble("roll_axis");
		yawAxis = copter.getDouble("yaw_axis");
		airSpeed = copter.getDouble("air_speed");
		heading = copter.getDouble("heading");

		localLocation = copter.getString("local_location"); 
		GPSStrength = copter.getString("gps_strength");
		groundSpeed = copter.getDouble("ground_speed");

		batteryVoltage = copter.getDouble("battery_voltage");
		batteryPercentage = copter.getDouble("battery_percentage");
		lastHeartBeat = copter.getDouble("last_heartbeat");

		systemStatus = copter.getString("system_status");
		mode = copter.getString("mode");
		armed = copter.getBoolean("armed");
	}
	
	public String toJson() {
		JSONObject drone   = new JSONObject();
		drone.put("_id", this.id);
		drone.put("gps", gps.toJson());
		drone.put("local_location", this.localLocation);
		drone.put("gps_strength", this.GPSStrength);
		drone.put("pitch_axis", this.pitchAxis);
		drone.put("roll_axis", this.rollAxis);
		drone.put("yaw_axis", this.yawAxis);
		drone.put("battery_voltage", this.batteryVoltage);
		drone.put("battery_percentage", this.batteryPercentage);
		drone.put("last_heartbeat", this.lastHeartBeat);
		drone.put("heading", this.heading);
		drone.put("armed", this.armed);
		drone.put("mode", this.mode);
		drone.put("system_status", this.systemStatus);
		drone.put("ground_speed", this.groundSpeed);
		drone.put("air_speed", this.airSpeed);
		
		return drone.toString();
	}
}
