package com.medfleet.missionControl.helpers;

import config.*;

public enum LogHelper {
    INSTANCE;
    private LogHelper() {}
    public static LogHelper getInstance() { return INSTANCE; }

    Config config = Config.getInstance();
    String successStr 	= config.successStr;
    String failStr  	= config.failStr;
    
    public void printFail() 	{ System.out.println(failStr);  	}
    public void printSuccess() 	{ System.out.println(successStr);	}


}
