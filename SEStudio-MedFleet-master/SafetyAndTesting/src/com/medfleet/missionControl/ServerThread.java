package com.medfleet.missionControl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URI;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.medfleet.missionControl.models.BaseStation;
import com.medfleet.missionControl.models.Drone;
import com.medfleet.missionControl.models.Ticket;

public class ServerThread implements Runnable 
{  
    private Socket client = null;  
    public ServerThread(Socket client){  
        this.client = client;  
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
            
            Drone drone = new Drone("56c2170e02d65b8538064bc8", "OnlyOne");
            BaseStation baseStation = new BaseStation(-35.363298,149.165215,0);
            Ticket.setBaseStation(baseStation);
            
            Ticket curTicket = null;
            
            while(flag)
            { 
            	// Receive the data from the clients.
            	//"{'latitude':1.111, 'longitude':1.1, 'altitude':1.123}";
                String str =  buf.readLine();  
 
        		try
        		{
        			System.out.println("From client:" + str);
                    if(str == null || "".equals(str)){  
                        flag = false;  
                    }else
                    {  
                        if("bye".equals(str)){  
                            flag = false;  
                        }
                        else if ("completed".equals(str))
                        {
                        	//Find the second
                        	if (curTicket != null)
                        	{
                        		curTicket.CompleteTicket();
                        		curTicket = curTicket.getNearestTicketToAnotherMission();
                        		
                        		if (drone.getBatteryPercentage() < 80 && Ticket.getTickets() == null ||  Ticket.getTickets().size() == 0)
                				{
                					out.println(baseStation.getCoordinate());
                					System.out.println("Finished all tasks! Return back home.");
                				}
                        		else
                        		{
                        			out.println(curTicket.getCoordinate());
                        			System.out.println("Send the request to base station...");                       		
                        		}
                				
                        	}
                        	else
                        	{
                        		out.println(baseStation.getCoordinate());
            					System.out.println("Finished all tasks! Return back home.");
                        	}
                        	//Ticket nearestTicket = getNearestTicketToAnotherMission();
            
                        }
                        else if ("ready".equals(str))
                        {
                        	curTicket = Ticket.getNearestTicketToBaseStation(baseStation);
                    		
                    		if (curTicket != null)
                    		{
                				out.println(curTicket.getCoordinate());
                				System.out.println("Send the request to base station...");
                     		}
                    		else
                    		{
                    			System.out.println("No other tickets");
                    		}
                        }
                        else
                        {  
                    		//Update drone info into database
                        	System.out.println("Update drone info...");
                        	String url = Main.ipAddress + "drones/" + drone.getId();
  
                        	HttpURLConnection con = Main.getHttpURLConnection(url);
                        	OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            				wr.write(str);
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
        			//System.out.println("'" + str + "' is not a valid JSON string.");
        			e.printStackTrace();
                    if(str == null || "".equals(str)){  
                        flag = false;  
                    }else{  
                        if("bye".equals(str)){  
                            flag = false;  
                        }else{  
                            //the data sent to clients
                            //out.println("From Server:" + str); 
                        	
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
