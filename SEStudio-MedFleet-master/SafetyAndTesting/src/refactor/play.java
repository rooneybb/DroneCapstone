package refactor;

import com.medfleet.missionControl.helpers.HttpHelper;
import com.medfleet.missionControl.models.Copter;
import com.medfleet.missionControl.models.Drone;
//import com.medfleet.missionControl.models.Ticket;

import threads.GCListenerThread;
import threads.WLHelper;
import threads.WLListenerThread;
import config.Config;

public class play {

	/*
	public static void main(String[] args) {
		
		// get enviroment to import system vars and set them in helper classes
		//String targetEnvironment = System.getenv("TARGET_ENV");
		
		Bucket bucket = Bucket.getInstance();
		HttpHelper hh = HttpHelper.getInstance();
		
		//HttpHelper hh = HttpHelper.getInstance();
		Config c = Config.getInstance();
		WLHelper wl = new WLHelper();
		String url = "http://104.43.172.127:3000/drones/56c637561ad762bc0c725c67";
		String result;
		try {
			result = hh.GetRequest(url);
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		///////////////// test gettting Tickets
		// get tickets from web listener 

//		wl.getTickets();
//		// Test to see if working and turned into proper json obejects
//		for (Ticket t : bucket.tickets) {
//			System.out.println(t.toJson());
//		}
		////////////////////////////////////////////////////////////////
		
		///////////////// test gettting Drones ////////////////////////
		// get tickets from web listener 
//		wl.getDrones();
//		try {
//			wl.getDrone("56c637561ad762bc0c725c67");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		// Test to see if working and turned into proper json obejects
//		for (Copter d : bucket.drones) {
//			System.out.println(d.toJson());
//		}
//		////////////////////////////////////////////////////////////////
		
		
		
		
		/// start the weblistener thread ///
//		Thread wlThread = new Thread(new WLListenerThread());
//		wlThread.start();
//		
//		/// start the gc listener thread /// 
//		Thread gcThread = new Thread(new GCListenerThread());
//		gcThread.start();
//		
//		c.mcRunning = false;
		

		
	}*/

}
