package com.medfleet.missionControl;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URISyntaxException;
import java.net.URL;

import com.medfleet.missionControl.config.config;

public class Main 
{

	public static String ipAddress = "http://127.0.0.1:3000/";
	
	
	public static void main(String[] args) throws IOException, URISyntaxException
	{ 
		config.DroneIds.add("5745b71eb8998fe93d4b16d7");
		config.DroneIds.add("5745b72db8998fe93d4b16d8");
		
		
		ServerSocket server = null;
		try 
		{
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
