package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import config.Config;

public class GCListenerThread extends Thread{

	/** convig vars */  		Config c = Config.getInstance();
	
	/** The Port.  */ 			int port = c.gcListenerPort;
	
	/** The q.  */ 				int q = 7;
	
	/** The socket.  */ 		public Socket socket;
	
	/** The server socket. */ 	public ServerSocket serverSocket;
	
	/** constructor. */			public GCListenerThread(){}
	
	/* (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	public void run(){
	  try {
	  	// creates the socket used to spin off a new thread
			serverSocket = new ServerSocket(port, q);
			System.out.println("### Ground Control Listener Started at Port: " + c.gcListenerPort);
		    try{ // please work
		    	while (c.mcRunning) {
		    		// wait for the next client connection:
		    		socket = serverSocket.accept();
		    		// spin off
		    		new Thread(new GCWorker(socket)).start();
		    	} // boo failed
		    } catch (IOException ioe) {System.out.println(ioe);}
	  } catch (IOException e) {
	  	// nice message to see failing....
			System.out.println("GC Thead setup Failed");
			e.printStackTrace();
		}
	}
	}