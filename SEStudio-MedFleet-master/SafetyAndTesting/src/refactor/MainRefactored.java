//package refactor;
//
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.net.URISyntaxException;
//
//
//public class MainRefactored {
//
//	public static void main(String[] args) throws Throwable, URISyntaxException{
//		run();
//	}
//	
//	public static void run()  throws IOException,
//	URISyntaxException {
//		ServerSocket server = null;
//		
//		try {
//			//server = new ServerSocket(c.wlListenerPort);
//			//System.out.println("Mission Control: Start at Port: " + hh.wlListenerPort);
//
//			Socket client;
//
//			while (true) { // waiting for connection from clients.
//				client = server.accept(); // get one
//				System.out.println("Connection Success");
//				// Open a new thread for each client.
//				new Thread(new ServerThreadRefactored(client)).start();
//			}
//		} finally {
//			server.close(); // clean up
//		}
//	}
//
//}
