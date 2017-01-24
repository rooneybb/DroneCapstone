package threads;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import refactor.Bucket;

import com.medfleet.missionControl.helpers.HttpHelper;
import com.medfleet.missionControl.helpers.LogHelper;
import com.medfleet.missionControl.models.*;

import config.Config;

public class WLHelper 
{
	/*
	Bucket bucket = Bucket.getInstance(); // global vars
	LogHelper lh = LogHelper.getInstance(); //simple helper to print logs 
	Config c = Config.getInstance();
	HttpHelper hh = HttpHelper.getInstance();
	
	public WLHelper () {} 
	
	public void updateTicket(Ticket ticket) { }
	public void deleteTicket(Ticket ticket) { }
	public void getTicket(Ticket ticket) { }
	*/
	/* simple helper to 
	 * 		get tickets from url, 
	 * 		create the ticket object, 
	 * 		add to bucket
	*/ 		
	/*
	public void getTickets() {
		System.out.println("### Tickets, \n\tGET all from url, SET as Ticket object, ADD to bucket");
		
		// get the tickets from the server
		JSONArray tickets = getJson(c.wlTxUrl); System.out.println("SET / ADD Tickets");
		
		// convert json to ticket object add to bucket
		for (int i = 0; i < tickets.length(); i++) {
			JSONObject t = tickets.getJSONObject(i);
			bucket.addTicket(new MedTicket(t)); lh.printSuccess();
		}
		
		System.out.println("### Tickets Completed"); lh.printSuccess();
	}
	
	public void updateDrone(Drone drone) { }
	public void deleteDrone(Drone drone) { }
	public void getDrone(String id)  throws Exception{ 
		// get the tickets from the server
		String url = c.wlDroneUrl + id;
		String result = hh.GetRequest(url);
		
	}
	
	public void getDrones() { 
		System.out.println("### Drones, \n\tGET all from url, SET as Drone object, ADD to bucket");
		String url = "http://104.43.172.127:3000/drones/56c637561ad762bc0c725c67";
		// get the tickets from the server
		//JSONArray drones = getJson(c.wlDroneUrl); 
		JSONArray drones = getJson(url);
		System.out.println("SET / ADD Drones");
		
		// convert json to ticket object add to bucket
		for (int i = 0; i < drones.length(); i++) {
			JSONObject t = drones.getJSONObject(i);
			bucket.addDrone(new Copter(t)); lh.printSuccess();
		}
		System.out.println("### Drones Completed"); lh.printSuccess();
	}
	*/
	/* simeple helper method to 
	 * 		return the json object return from a url
	 */
	/*
	private JSONArray getJson(String url) {
		System.out.println("GET json object from " + url);
		URI uri;
		try {
			uri = new URI(url);
			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			lh.printSuccess();
			System.out.println(tokener.toString());
			return new JSONArray(tokener);
			
		} catch (URISyntaxException e) {
			e.printStackTrace();
			lh.printFail();
			return null;
			
		} catch (JSONException e) {
			e.printStackTrace();
			lh.printFail();
			return null;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			lh.printFail();
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			lh.printFail();
			return null;
			
		} finally {}		
	}*/
}

