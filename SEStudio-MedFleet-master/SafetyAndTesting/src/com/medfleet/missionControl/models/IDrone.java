package com.medfleet.missionControl.models;

import java.util.Date;

public interface IDrone {
	String getId();
	String name();
	GPS gps();
	Double velocity();
	int battery();
	Date last_heartbeat();
	Double heading();
	boolean armed();
	String mode();	
}
