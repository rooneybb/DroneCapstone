package com.medfleet.missionControl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;

import org.json.JSONObject;
import org.json.JSONTokener;

import com.medfleet.missionControl.config.config;
import com.medfleet.missionControl.models.BaseStation;
import com.medfleet.missionControl.models.Drone;
import com.medfleet.missionControl.models.Mission;
import com.medfleet.missionControl.models.Ticket;



public class ServerThread implements Runnable 
{
    private Drone drone1 = new Drone(config.DroneIds.get(0), "Left", true);
    private Drone drone2 = new Drone(config.DroneIds.get(1), "Right", false);
	
    private Mission mission1;
    private Mission mission2;
    
    private Socket client = null;  
    public ServerThread(Socket client){  
        this.client = client;  
        
        PrintStream out;
		try {
			out = new PrintStream(client.getOutputStream());
			
	        Runnable r = new Runnable() 
	        {
	            public void run() 
	            {
	            	while(true)
	            	{
		                if (mission1 != null && mission2 != null && drone1.isCompleted && drone2.isCompleted)
		                {
		                	drone1.isCompleted = false;
		                	drone2.isCompleted = false;
		                	
		                	Ticket.UpdateMissionTickets(mission1);
		                	Ticket.UpdateMissionTickets(mission2);
	
		                	Ticket ticket1 = mission1.getCurTicket();
		                	Ticket ticket2 = mission2.getCurTicket();
		                	
		                	if (ticket1 != null)
		                	{
		                		mission1.updateFlightPaths();
		                		String missionStr = mission1.toAnotherLocation();
		            			
		        				out.println(missionStr);
		        				System.out.println("Send the request to base station...");
		        				System.out.println(missionStr);
		                	}
		                	else
		                	{
		                		drone1.isCompleted = true;
		                	}
		                	
		                	if (ticket2 != null)
		                	{
		                		mission2.updateFlightPaths();
		                		String missionStr = mission2.toAnotherLocation();
		            			
		        				out.println(missionStr);
		        				System.out.println("Send the request to base station...");
		        				System.out.println(missionStr);                    		
		                	}
		                	else
		                	{
		                		drone2.isCompleted = true;
		                	}
		             
		                }
		                
		                try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
		            }
	            }
	        };
		} catch (IOException e1) {
			e1.printStackTrace();
		}  

    }
      
    @Override  
    public void run() {  
        try
        {  
            // Get Socket output stream，send data to clients.
            PrintStream out = new PrintStream(client.getOutputStream());  
            // GET Socket input stream，receive data from clients.
            BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));  
            boolean flag =true;  
            BaseStation baseStation = new BaseStation(	config.BaseStationLatitude,
            											config.BaseStationLongitude,
            											config.BaseStationAltitude
            										 );
            Ticket.setBaseStation(baseStation);
            
            Ticket curTicket = null;            
            curTicket = new Ticket();
            
            mission1 = Ticket.getMissionForDrone(drone1);
            mission2 = Ticket.getMissionForDrone(drone2);
            

            
            while(flag)
            { 
                String str =  buf.readLine();  
 
        		try
        		{
                    if(str == null || "".equals(str)){  
                        flag = false;  
                    }
                    else
                    {  
                        if("bye".equals(str))
                        {  
                            flag = false;  
                        }
                        else if (("completed:" + drone1.getId()).equals(str) || ("completed:" + drone2.getId()).equals(str))
                        {
                        	//Find the second
                        	Drone drone = null;
                        	Mission mission = null;
                        	
                        	str = str.substring(10);
                        	
                        	if (drone1.getId().equals(str))	
                        	{
                        		drone = drone1;
                        		mission = mission1;
                        	}
                        	else							
                        	{
                        		drone = drone2;
                        		mission = mission2;
                        	}
                        	
                        	if (curTicket != null)
                        	{
                        		curTicket = mission.getCurTicket();
                        		
                        		if (curTicket == null)
                        		{
                        			drone.isCompleted = true;
                        		}
                        		else
                        		{
	                        		mission.CompleteTicket(curTicket);
	                        		
	                        		if (drone.getBatteryPercentage() < config.BatteryLimitation)
	                				{
	                					out.println(mission.toBaseStation());
	                					System.out.println("Finished all tasks! Return back home.");
	                				}
	                        		else
	                        		{
	                        			out.println(mission.toAnotherLocation());
	                        			System.out.println("Send the request to base station...");                       		
	                        		}
	                        		mission1.updateFlightPaths();
	                            	mission2.updateFlightPaths();
                            	}
                        	}
                        	else
                        	{
                        		
                        	}

                        }
                        else if (("ready:" + drone1.getId()).equals(str) || ("ready:" + drone2.getId()).equals(str))
                        {
                        	Drone drone = null;
                        	Mission mission = null;
                        	
                        	str = str.substring(6);
                        	
                        	if (drone1.getId().equals(str))	
                        	{
                        		drone = drone1;
                        		mission = mission1;
                        	}
                        	else							
                        	{
                        		drone = drone2;
                        		mission = mission2;
                        	}
                        	
                        	Ticket.UpdateMissionTickets(mission);
                        	
                        	curTicket = mission.getCurTicket();

                    		if (curTicket != null)
                    		{
                    			drone.isCompleted = false;
                    			mission.updateFlightPaths();
                    			
                    			String missionStr = mission.toAnotherLocation();
                    			
                				out.println(missionStr);
                				System.out.println("Send the request to base station...");
                				System.out.println(missionStr);               		
                     		}
                    		else
                    		{
                    			drone.isCompleted = true;
                    			System.out.println("No other tickets");
                    		}
                        }
                        else
                        {  
                    		//Update drone info into database
                        	System.out.println("Update drone info...");
                        	
                        	JSONTokener tokener = new JSONTokener(str);
                    		JSONObject curDroneJSON = new JSONObject(tokener);
                        	
                    		Drone drone = new Drone(curDroneJSON);
                    		
                    		if (drone.getId().equals(drone1.getId()))
                    		{
                    			drone1 = drone;
                    		}
                    		else
                    		{
                    			drone2 = drone;
                    		}
                    		
                    		System.out.println("Drone Id:" + drone.getId() + "    latitude:" + drone.getLatitude()  + "   longitude:" + drone.getLongitude());
                        	
                        	String url = Main.ipAddress + "drones/" + drone.getId();
  
                        	HttpURLConnection con = Main.getHttpURLConnection(url);
                        	OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            				wr.write(drone.toJson());
            				wr.flush(); 
            				int responseCode = con.getResponseCode(); 
            				
            				if (responseCode != 200)
            				{
            					System.out.println("Update Database Fail!");
            				}
                        }
                    }  
        		}
        		catch(Exception e)
        		{
        			e.printStackTrace();
                    if(str == null || "".equals(str)){  
                        flag = false;  
                    }else{  
                        if("bye".equals(str)){  
                            flag = false;  
                        }else{                          	
                        	out.println("Wrong Format");
                        }  
                    }  
        		}
            }  
            out.close();  
            client.close();  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    } 
  }
