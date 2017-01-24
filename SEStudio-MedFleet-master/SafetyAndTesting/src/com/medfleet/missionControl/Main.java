package com.medfleet.missionControl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.medfleet.missionControl.models.Ticket;


public class Main {

	public static String ipAddress = "http://40.77.67.165:3000/";
	public static void main(String[] args) throws IOException, URISyntaxException
	{ 
		/*
		URI uri;
		try 
		{
			uri = new URI(Main.ipAddress + "tickets/status/received");

			JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
			JSONArray allTickets = new JSONArray(tokener);

			for (int i = 0; i < allTickets.length(); i++)
			{
				JSONObject jsonObject =  allTickets.getJSONObject(i);
				String id = jsonObject.getString("_id");
				jsonObject.put("status", "on_route");
				System.out.println("Update the ticket...");
				String url = Main.ipAddress + "tickets/" + id;
				
				HttpURLConnection con;
				try 
				{
					con = Main.getHttpURLConnection(url);

					OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
					
					wr.write(jsonObject.toString());
					System.out.println(jsonObject.toString());
					
					wr.flush(); 
				 con.getResponseCode(); 
				} catch (Exception e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		/*
		String url=ipAddress + "drones";
		URL object=new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		*/
		/*
		JSONObject cred   = new JSONObject();
		JSONObject auth   = new JSONObject();
		JSONObject parent = new JSONObject();

		cred.put("username","adm");
		cred.put("password", "pwd");

		auth.put("tenantName", "adm");
		auth.put("passwordCredentials", cred.toString());
		
		parent.put("auth", auth.toString());
		*/
		/*
		OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
		Drone drone = new Drone("test01");

		wr.write(drone.toAddNewDroneJSON());
		wr.flush(); 
		int responseCode = con.getResponseCode(); 
		if (responseCode == 200)
		{
			InputStream is = con.getInputStream();
            String state = getStringFromInputStream(is);
            JSONObject droneInfo = new JSONObject(state);
		}
		*/
		
		//JSONObject aa = new JSONObject(con.getInputStream());
		
		
		//URI uri = new URI("http://52.73.89.184:3000/drones");
		//JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
		//JSONArray aa = new JSONArray(tokener);
		//JSONObject root = new JSONObject(tokener);
		
		
        //Port: 20005 listening from clients;
		ServerSocket server = null;
		try {
			server = new ServerSocket(20005);

			System.out.println("Mission Control: Start at Port:20005"); 

	        Socket client = null;  
	        boolean f = true; 

	        while(f)
	        {  
	        	
	            //waiting for connection from clients.
	            client = server.accept();  
	            System.out.println("Connection Success！");  
	            
	            //Open a new thread for each client.
	            new Thread(new ServerThread(client)).start();  
	        }  
	        server.close();
		}
		finally
		{
			server.close();
		}
  
	}
	
	public static HttpURLConnection getHttpURLConnection(String url) throws Exception
	{
		//String url="http://52.73.89.184:3000/drones/" + id;
		URL object=new URL(url);

		HttpURLConnection con = (HttpURLConnection) object.openConnection();
		con.setDoOutput(true);
		con.setDoInput(true);
		con.setRequestProperty("Content-Type", "application/json");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestMethod("POST");
		
		return con;
	}
	
    public static String getStringFromInputStream(InputStream is)
            throws IOException 
    {
	    ByteArrayOutputStream os = new ByteArrayOutputStream();
	    
	    byte[] buffer = new byte[1024];
	    int len = -1;
	
	    while ((len = is.read(buffer)) != -1) {
	            os.write(buffer, 0, len);
	    }
	    is.close();
	    String state = os.toString();
	    os.close();
	    return state;
    }

}
