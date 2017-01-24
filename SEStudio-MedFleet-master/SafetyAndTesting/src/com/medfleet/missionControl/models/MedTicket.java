package com.medfleet.missionControl.models;

import java.util.Date;

import com.medfleet.missionControl.helpers.*;

import org.json.JSONObject;

//public class MedTicket implements Ticket {
public class MedTicket {	
	String id;
	GPS gps;
	int urgency; // todo make sure between 1-5
	ticketStatus status;
	

	public MedTicket () {}
	public MedTicket (JSONObject ticket) {
		id = ticket.getString("_id");
		gps = new GPS (ticket.getDouble("latitude"),
					ticket.getDouble("longitude"),
					ticket.getDouble("altitude") );
		urgency = ticket.getInt("urgency");
		status = ticketStatus.valueOf(ticket.getString("status"));
	}
	/*
	@Override
	public String id() { return id; }

	@Override
	public GPS gps() { return gps; }

	@Override
	public int urgency() { return urgency; }

	@Override
	public Date txReceived() { return null; }

	@Override
	public Date txCompleted() { return null; }
	
	@Override
	public String toJson() {
		JSONObject ticket   = new JSONObject();
		ticket.put("GPS", this.gps.toJson());
		ticket.put("urgency",this.urgency);
		ticket.put("status", this.status);

		return ticket.toString();
	}
	
	@Override
	public ticketStatus status() {
		return this.status;
	}
	*/
}
