package config;


public enum Config {
    INSTANCE;

    private Config() {}
    
    public static Config getInstance() { return INSTANCE; }
	
    /////////  wl vars ///////// 
	public final String wlUrl 			= "http://104.43.172.127:3000/";
	public final String wlTxUrl 		= "http://104.43.172.127:3000/tickets/";
	public final String wlTxStatusUrl 	= "http://104.43.172.127:3000/tickets/status/";
	public final String wlDroneUrl 		= "http://104.43.172.127:3000/drones/";
    ////////  wl Listener vars  ///////// 
    public final int wlListenerPort = 2570;
    
    ////////gs listener vars  ///////// 
    public final int gcListenerPort = 20005;
    
    //// log vars
    public final String successStr = "\t --> SUCCESS";
    public final String failStr  = "\t --> FAIL";
    
    //// bools for running
    public boolean mcRunning = true;
}
