//package refactor;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.io.PrintStream;
//import java.net.HttpURLConnection;
//import java.net.Socket;
//
//import org.json.JSONObject;
//
//import com.medfleet.missionControl.helpers.HttpHelper;
//import com.medfleet.missionControl.helpers.HttpMethod;
//import com.medfleet.missionControl.models.Drone;
//
//public class ServerThreadRefactored implements Runnable {
//	public HttpHelper hh = HttpHelper.getInstance();
//	private Socket client;
//	boolean flag = true;
//	PrintStream out;
//	BufferedReader buf;
//	JSONObject currentRequestJson;
//	Drone drone;
//
//	public ServerThreadRefactored(Socket client) throws IOException {
//		this.client = client;
//		// Get Socket output stream，send data to clients.
//		PrintStream out = new PrintStream(client.getOutputStream());
//		// GET Socket input stream，receive data from clients.
//		BufferedReader buf = new BufferedReader(new InputStreamReader(client.getInputStream()));
//		// to get drone via call
//		Drone drone = new Drone("56c2170e02d65b8538064bc8", "OnlyOne");
//	}
//
//	@Override
//	public void run() {
//		while (flag) {
//			String str;
//			try {
//				str = buf.readLine();
//				System.out.println("From client:" + str);
//				if (!"ready".equals(str)) { flag = false; }
//				System.out.println("Get tickets...");
//				System.out.println("Update drone info...");
//				String url = hh.wlTxWIdUrl(drone.getId());
//				HttpURLConnection con = hh.getHttpURLConnection(url, HttpMethod.post);
//				OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
//				wr.write(str);
//				wr.flush();
//				int responseCode = con.getResponseCode();
//				if (responseCode != 200) { System.out.println("Update Database Fail!"); }
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//	}
//}
//			
