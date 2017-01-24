package com.medfleet.missionControl.models;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.medfleet.missionControl.Calculation;
import com.medfleet.missionControl.Main;

public class Ticket
{

		private String _id; 
		private double _latitude;
		private double _longitude;
		private double _altitude;
		private int _urgency;
		private String _status;
		
		private JSONObject _jsonObject;
		
		private static ArrayList<Ticket> _tickets = new ArrayList<Ticket>();
		
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
						((Ticket) ticket).UpdateToServer("on_route");

						if (_baseStation.isSafe(ticket.getLatitude(), ticket.getLongitude()))
							_tickets.add(ticket);
						else
						{
							System.out.println("Ticket:" + ticket.getId() + "is too far away.");
						}
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("Get " + _tickets.size() + " tickets.");
		}
		
		public static Mission getMissionForDrone(Drone drone)
		{
			Mission mission = new Mission(drone, _baseStation);
			return mission;
		}
		
		public static void UpdateMissionTickets(Mission mission)
		{
			Ticket ticket = getNearestTicketToBaseStation(mission.getDrone().getIsOnLeft());
			
			while(ticket != null)
			{
				mission.getTickets().add(ticket);
				ticket = getNearestTicketToAnotherMission(ticket, mission.getDrone().getIsOnLeft());
			}
		}
		
		public static Ticket getNearestTicketToAnotherMission(Ticket ticket, boolean isOnLeft)
		{
			if (_tickets == null || _tickets.size() == 0)	updateTicketsFromServer();
			
			Ticket nearestTicket = null;
			double nearestDistance = Integer.MAX_VALUE;
			
			for (int i = 0; i < _tickets.size(); i++)
			{
				Ticket tempTicket = _tickets.get(i);
				
				if (ticket == tempTicket)	continue;
				
				double tempDistance = ticket.distanceFrom(tempTicket.getLatitude(),tempTicket.getLongitude());
				
				if ((isOnLeft && tempTicket.getLongitude() < _baseStation.getLongitude()) ||
					(!isOnLeft && tempTicket.getLongitude() >= _baseStation.getLongitude())	)
				{
					if (nearestDistance > tempDistance);
					{
						nearestTicket = tempTicket;
						nearestDistance = tempDistance;
					}
				}
			}
			_tickets.remove(nearestTicket);
			return nearestTicket;			
		}
		
		public static Ticket getNearestTicketToBaseStation(boolean isOnLeft)
		{
			if (_tickets == null || _tickets.size() == 0)	updateTicketsFromServer();
			
			Ticket nearestTicket = null;
			double nearestDistance = Integer.MAX_VALUE;
			
			for (int i = 0; i < _tickets.size(); i++)
			{
				Ticket tempTicket = _tickets.get(i);
				
				double tempDistance = _baseStation.distanceFrom(tempTicket.getLatitude(),tempTicket.getLongitude());
				
				if ((isOnLeft && tempTicket.getLatitude() < _baseStation.getLatitude()) ||
					(!isOnLeft && tempTicket.getLatitude() >= _baseStation.getLatitude())	)
				{
					if (nearestDistance > tempDistance);
					{
						nearestTicket = tempTicket;
						nearestDistance = tempDistance;
					}
				}
			}
			_tickets.remove(nearestTicket);
			return nearestTicket;
		}
		public Ticket() {}
		public Ticket(JSONObject jsonObject)
		{
			_id = jsonObject.getString("_id");
			_latitude = jsonObject.getDouble("latitude");
			_longitude = jsonObject.getDouble("longitude");
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
					e.printStackTrace();
				}
			}
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
		
		public int getUrgency()
		{
			return _urgency;
		}
		
		public String getStatus()
		{
			return _status;
		}
	}