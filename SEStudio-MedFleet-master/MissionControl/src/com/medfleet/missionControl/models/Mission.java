package com.medfleet.missionControl.models;


import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import org.json.JSONObject;

import com.medfleet.missionControl.FlightPath;
import com.medfleet.missionControl.Main;

public class Mission 
{
	private ArrayList<Ticket> _tickets;
	private Drone _drone;
	private BaseStation _baseStation;
	
	public Mission(Drone drone, BaseStation baseStation)
	{
		_drone = drone;
		_baseStation = baseStation;
		_tickets = new ArrayList<Ticket>();
	}
	
	public void CompleteTicket(Ticket ticket)
	{
		if (ticket != null)
		{
			ticket.CompleteTicket();
			_tickets.remove(ticket);
		}
	}
	
	public Ticket getCurTicket()
	{
		if (_tickets.size() > 0)	return _tickets.get(0);
		return null;
	}
	
	public ArrayList<FlightPath> getFlightPaths()
	{
		ArrayList<FlightPath> _flightPaths = new ArrayList<FlightPath>();
		for (int i = 0; i < _tickets.size(); i++)
		{
			Ticket tempTicket = _tickets.get(i);
			FlightPath tempFlightPath = new FlightPath(tempTicket.getLongitude(), tempTicket.getLatitude());
			_flightPaths.add(tempFlightPath);
		}
		
		FlightPath lastFlightPath = new FlightPath(_baseStation.getLongitude(), _baseStation.getLatitude());

		_flightPaths.add(lastFlightPath);
		
		return _flightPaths;
	}
	
	public Drone getDrone()
	{
		return _drone;
	}
	
	public ArrayList<Ticket> getTickets()
	{
		return _tickets;
	}
	
//	public String toJson()
//	{
//		JSONObject mission   = new JSONObject();
//		mission.put("drone_id", _drone.getId());
//		mission.put("drone_id", _drone.getId());
//		
//		ArrayList<String> ticketIds = new ArrayList<String>();
//		for (int i = 0; i < this._tickets.size(); i++)
//		{
//			ticketIds.add(this._tickets.get(i).getId());
//		}
//		
//		mission.put("tickets", new JSONArray(ticketIds));
//		
//		ArrayList<String> flightPathsStrings = new ArrayList<String>();
//		for (int i = 0; i < this.getFlightPaths().size(); i++)
//		{
//			flightPathsStrings.add(getFlightPaths().get(i).toJson());
//		}
//		mission.put("flight_paths", new JSONArray(flightPathsStrings));
//		
//		return mission.toString();
//	}
	
	public String toBaseStation()
	{
		JSONObject flightInfo   = new JSONObject();
		flightInfo.put("droneId", _drone.getId());
		flightInfo.put("latitude", _baseStation.getLatitude());
		flightInfo.put("longitude", _baseStation.getLongitude());
		
		String flightInfoJson = flightInfo.toString();
		
		System.out.println(flightInfoJson);
		
		_tickets.clear();
		
		return flightInfoJson;
	}
	 
	public String toAnotherLocation()
	{
		ArrayList<FlightPath> flightPaths = getFlightPaths();
		
		if (flightPaths.size() == 1)
		{
			_drone.isCompleted = true;
		}
		
		if (flightPaths.size() > 0)
		{
			FlightPath flightPath = flightPaths.get(0);
			
			JSONObject flightInfo   = new JSONObject();
			flightInfo.put("droneId", _drone.getId());
			flightInfo.put("latitude", flightPath.getLatitude());
			flightInfo.put("longitude", flightPath.getLongitude());
			
			String flightInfoJson = flightInfo.toString();
			
			System.out.println(flightInfoJson);
			
			return flightInfoJson;
		}
		return "";
	}
	
	
	public void updateFlightPaths()
	{
		int responseCode = 0;
		
		System.out.println("Update Flight Paths for " + _drone.getId());
		String url = Main.ipAddress + "missions/drone/" + _drone.getId();
		
		HttpURLConnection con;
		try 
		{
			con = Main.getHttpURLConnection(url);

			OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
			
			wr.write(toJson());
			System.out.println(toJson());
			
			wr.flush(); 
			responseCode = con.getResponseCode(); 
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if (responseCode != 200)	
			System.out.println("Updated to database wrong for Mission: " + responseCode);
	}
	private String toJson() 
	{
		String result = "{ \"drone_id\": \""+_drone.getId()+"\", " +
		   " \"tickets\": [ ";
		
		for (int i = 0; i < _tickets.size() - 1; i++)
		{
			result += "\"" + _tickets.get(i).getId() + "\", "; 
		}
		
		if ( _tickets.size() > 1)
		{
			result += "\"" + _tickets.get(_tickets.size() - 1).getId() + "\""; 
		}
		
		result += "], ";
		result += "\"flight_paths\": [";
		
		ArrayList<FlightPath> flightPaths = this.getFlightPaths();
		
		for (int i = 0; i < flightPaths.size() - 1; i++)
		{
			result += flightPaths.get(i).toJson() + ","; 
		}
		
		if (flightPaths.size() > 1)
		{
			result += flightPaths.get(flightPaths.size() - 1).toJson();
		}
		
		result += "]}";
		
		return result;
		  	   
	}
}

/*
 * 
"flight_paths": [
      [41.7, -87.2],
      [41.6, -87.3],
      [41.5, -87.4]
  ]

[9:11] 
missions/updateflightpaths/missionid
 * 
 * */