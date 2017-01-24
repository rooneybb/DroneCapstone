package com.medfleet.missionControl.models;

import java.util.Date;

import com.medfleet.missionControl.helpers.ticketStatus;

public interface BeforeTicket {
	String id();
	GPS gps();
	int urgency();
	Date txReceived();
	Date txCompleted();
	String toJson();
	ticketStatus status();

}
