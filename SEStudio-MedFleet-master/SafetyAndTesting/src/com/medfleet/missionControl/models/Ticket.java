package com.medfleet.missionControl.models;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.medfleet.missionControl.Calculation;
import com.medfleet.missionControl.Main;

public class Ticket 
{
	private String _id; 
	//-180 - 180 
	private double _latitude;
	private double _longitude;
	//0 - anythings
	private double _altitude;
	//1 - 5
	private int _urgency;
	private String _status;
	
	private JSONObject _jsonObject;
	
	private static ArrayList<Ticket> _tickets = null;
	
	public static ArrayList<Ticket> getTickets()
	{
		return _tickets;
	}
	
	private static BaseStation _baseStation = null;
	
	public static void setBaseStation(BaseStation baseStation)
	{
		_baseStation = baseStation;
	}
	
	public static void updateTicketsFromServer()
	{
		System.out.println("Get tickets...");
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		URI uri;
		try 
		{
			uri = new URI(Main.ipAddress + "tickets/status/received");

			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONArray allTickets = new JSONArray(tokener);

			for (int i = 0; i < allTickets.length(); i++)
			{
				
				JSONObject jsonObject =  allTickets.getJSONObject(i);
				
				if (jsonObject.has("latitude"))
				{
					Ticket ticket = new Ticket(jsonObject);
					ticket.UpdateToServer("on_route");

					if (_baseStation.isSafe(ticket.getLatitude(), ticket.getLongitude()))
						tickets.add(ticket);
					else
					{
						System.out.println("Ticket:" + ticket.getId() + "is too far away.");
					}
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		_tickets = tickets;
	}
	
	public Ticket getNearestTicketToAnotherMission()
	{
		if (_tickets == null || _tickets.size() == 0)	updateTicketsFromServer();
		
		Ticket nearestTicket = null;
		double nearestDistance = Integer.MAX_VALUE;
		
		for (int i = 0; i < _tickets.size(); i++)
		{
			Ticket tempTicket = _tickets.get(i);
			
			if (this == tempTicket)	continue;
			
			double tempDistance = this.distanceFrom(tempTicket.getLatitude(),tempTicket.getLongitude());
			
			if (nearestDistance > tempDistance);
			{
				nearestTicket = tempTicket;
				nearestDistance = tempDistance;
			}
		}
		
		return nearestTicket;
	}
	
	public static Ticket getNearestTicketToBaseStation(BaseStation baseStation)
	{
		if (_tickets == null || _tickets.size() == 0)	updateTicketsFromServer();
		
		Ticket nearestTicket = null;
		double nearestDistance = Integer.MAX_VALUE;
		
		for (int i = 0; i < _tickets.size(); i++)
		{
			Ticket tempTicket = _tickets.get(i);
			
			double tempDistance = baseStation.distanceFrom(tempTicket.getLatitude(),tempTicket.getLongitude());
			
			if (nearestDistance > tempDistance);
			{
				nearestTicket = tempTicket;
				nearestDistance = tempDistance;
			}
		}
		
		return nearestTicket;
	}
	
	public Ticket(JSONObject jsonObject)
	{
		_id = jsonObject.getString("_id");
		
		//if (!id.equals("56c290bc314fc94c03392c14")) continue;
		
		_latitude = jsonObject.getDouble("latitude");
		_longitude = jsonObject.getDouble("longitude");
		//_altitude = jsonObject.getDouble("altitude");
		_urgency = jsonObject.getInt("urgency");
		_status = jsonObject.getString("status");
		
		this._jsonObject = jsonObject;
	}

	
	public double distanceFrom(double latitude, double longitude)
	{
		return Calculation.distFrom(this.getLatitude(), this.getLongitude(), latitude, longitude);
	}
	
	public void CompleteTicket()
	{
		UpdateToServer("completed");
		_tickets.remove(this);
	}
	
	public void UpdateToServer(String status)
	{
		int responseCode = 0;
		
		this._status = status;
		_jsonObject.put("status", status);
		
		while(responseCode != 200)
		{
			System.out.println("Update the ticket...");
			String url = Main.ipAddress + "tickets/" + this.getId();
			
			HttpURLConnection con;
			try 
			{
				con = Main.getHttpURLConnection(url);

				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
				
				wr.write(_jsonObject.toString());
				System.out.println(_jsonObject.toString());
				
				wr.flush(); 
				responseCode = con.getResponseCode(); 
			} catch (Exception e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getCoordinate()
	{
		return "{'latitude':" + this.getLatitude() + 
			   ", 'longitude':"+this.getLongitude()+
			   ", 'altitude':" + this.getAltitude() + "}";
	}
	
	public JSONObject getJSONObject()
	{
		return _jsonObject;
	}
	
	public String getId()
	{
		return _id;
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
	
	public double getUrgency()
	{
		return _urgency;
	}
	
	public String getStatus()
	{
		return _status;
	}
}
