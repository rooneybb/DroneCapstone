'''

              Author: Turki Majdali
                     MFGS
             for SITL and Iris Plus
                                               '''

import socket
import json
import ast
import time
import thread
import threading
from dronekit import connect, LocationGlobalRelative, Command
from dronekit.lib import VehicleMode, LocationGlobal
from pymavlink import mavutil

#### vars
vehicle = connect('tcp:127.0.0.1:5760', wait_ready=True)
#vehicle = connect('com3', wait_ready=True, baud=57600)
HomeVLAT
HomeLONG;
cmds[];


#### order of what to do
# 1. get home location
    ## what are command in it?
# 2. connect to MC to get mission
    # own thread
    # send ready
    # get mission from mc
# 3. do_auto_mission()
    # in new thread?
# 4. clean up
