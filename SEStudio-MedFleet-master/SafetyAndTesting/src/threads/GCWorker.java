package threads;

import java.net.Socket;

public class GCWorker extends Thread {

	Socket socket; 
	GCWorker(Socket s) { socket = s;}
	
	public void run() {
//		PrintStream out = null;
//		BufferedReader in = null;
//		//String log = null;
//		try {
//			out = new PrintStream(sock.getOutputStream());
//			try {
//				String name;
//				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//
//				// gets the request
//				name = in.readLine();
//
//				// RequestHandeler handles the request. this is basically a factory for the Content Classes and
//				// returns a full doc with header
//                if (name != null) {
//                    RequestHandeler rh = new RequestHandeler(); // the instance
//    				String ret = rh.handleRequest(name); // the return
//
//    				// for debugging
//    				System.out.println("\n# New Request");
//    				System.out.println(name); // print to console the request from client
//    				//System.out.println(ret); // print to console data sent back to client
//
//    				// request is returned to user
//    				out.print(ret); // return to client
//                }
//
//				in.close(); out.close();  // cleanup
//			} catch (IOException x) { System.out.println("Server read error"); } // boo
//			sock.close(); // clean up
//		} catch (IOException ioe) { System.out.println(ioe); }
	}
}
