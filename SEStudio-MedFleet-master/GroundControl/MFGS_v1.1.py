'''

              Author: Turki Majdali
                     MFGS 
				    for SITL
                                               '''

import socket
import json
import ast
import time
import thread
from dronekit import connect, LocationGlobalRelative
from dronekit.lib import VehicleMode, LocationGlobal
from pymavlink import mavutil

# Connect to the vehicle

vehicle = connect('tcp:127.0.0.1:5760', wait_ready=True)

while not vehicle.home_location:
    cmds = vehicle.commands
    cmds.download()
    cmds.wait_ready()
    if not vehicle.home_location:
        print " Waiting for home location ..."
  # Home location is ready, so print it!        
print "\n Home location: %s" % vehicle.home_location
HomeVLAT = vehicle.home_location.lat
HomeLONG = vehicle.home_location.lon

isFlying = 0

print('Connecting to MC')
host = '40.117.32.22'
port = 20005
size = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host,port))

data = 'ready'
data_string = data+'\n'

while(True):
  print('Sending ready to MC \n')
  # Send 'ready' to MC
  s.send((data_string.encode('utf-8')))
  # get data from MC
  recData = s.recv(size)

  # if there is no data -> continue
  if recData == 0:
      continue
  else:
      break

  time.sleep(1)

recData = recData.decode('utf-8')
json_dump = json.dumps(recData)
data_loaded = json.loads(json_dump)
json_dict =ast.literal_eval(data_loaded)

print('Received Target Location:',json_dict)
print('Received Target latitude:',json_dict['latitude'])
print('Received Target longitude:',json_dict['longitude'])

TARGET_LAT = json_dict['latitude']
TARGET_LON = json_dict['longitude']

def arm_and_takeoff(aTargetAltitude):
    """
    Arms vehicle and fly to aTargetAltitude.
    """

    print "Basic pre-arm checks"
    # Don't let the user try to fly autopilot is booting
    if vehicle.mode.name == "INITIALISING":
        print "Waiting for vehicle to initialise"
        time.sleep(1)
    while vehicle.gps_0.fix_type < 2:
        print "Waiting for GPS...:", vehicle.gps_0.fix_type
        time.sleep(1)

        
    print "Arming motors"
    # Copter should arm in GUIDED mode
    vehicle.mode    = VehicleMode("GUIDED")
    
    while not vehicle.mode.name == "GUIDED":
        print " Mode: %s" % vehicle.mode
        print " GPS: %s", vehicle.gps_0.fix_type        
        print " Waiting for guided..."
    
    
    vehicle.armed   = True    

    while not vehicle.armed:
        vehicle.armed   = True    
        print " Mode: %s" % vehicle.mode
        print " GPS: %s", vehicle.gps_0.fix_type        
        print " Waiting for arming..."


        time.sleep(1)

    print "Taking off!"
    vehicle.simple_takeoff(aTargetAltitude) # Take off to target altitude

    # Wait until the vehicle reaches a safe height before processing the goto (otherwise the command 
    #  after Vehicle.commands.takeoff will execute immediately).
    while True:
        print " Altitude: ", vehicle.location.global_relative_frame.alt
        
        print " Mode: %s" % vehicle.mode          
        
        if vehicle.location.global_relative_frame.alt>=aTargetAltitude*0.80: #Just below target, in case of undershoot.
            print "Reached target altitude"
            break
        time.sleep(1)
		
# New thread to send data to MC		
def send_attributes( threadName, delay):
    print('sending updates to MC')
    while True:
            print('sending updates...')
            lat=vehicle.location.global_relative_frame.lat
            lon=vehicle.location.global_relative_frame.lon
            alt=vehicle.location.global_relative_frame.alt
            v=vehicle.velocity[1]
            ss=vehicle.system_status.state
            bv=vehicle.battery.voltage
            bl=vehicle.battery.level
            
            data02 = { 'name':'String', 'latitude':lat, 'longitude':lon, 'altitude':alt, 'velocity':v, 'local_location':'%s'%vehicle.location.local_frame, 'gps_strength':'%s'%vehicle.gps_0, 'pitch_axis':vehicle.attitude.pitch, 'roll_axis':vehicle.attitude.roll, 'yaw_axis':vehicle.attitude.yaw, 'battery_voltage':bv, 'battery_percentage':bl, 'last_heartbeat':vehicle.last_heartbeat, 'heading':vehicle.heading, 'armed':vehicle.armed, 'mode':'%s'%vehicle.mode.name, 'system_status':'%s'%ss, 'ground_speed':vehicle.airspeed }
            data_string=json.dumps(data02)+'\n'
            s.send((data_string.encode('utf-8')))
            time.sleep(5)
            if isFlying == 1:
                 break
                 print('Sending updates to MC has ended')
				 
# Arm and Takeoff			
arm_and_takeoff(10)
time.sleep(10)

try:
   thread.start_new_thread( send_attributes, ("Thread-1", 2, ) )
   
except:
   print "Error: unable to start thread"

# set Air Speed   
vehicle.airspeed=10

print "Going to target point Lat: %s , Lon: %s" % (TARGET_LAT, TARGET_LON)
point1 = LocationGlobalRelative(TARGET_LAT, TARGET_LON, 15)
vehicle.simple_goto(point1)


time.sleep(40)

print "Landing at Target point"
vehicle.mode    = VehicleMode("LAND")
time.sleep(45)

arm_and_takeoff(10)
time.sleep(15)

print "Going Home Lat: %s , Lon: %s" % (HomeVLAT, HomeLONG )
point2 = LocationGlobalRelative(HomeVLAT, HomeLONG, 15)
vehicle.simple_goto(point2)


time.sleep(30)

print "Landing at Home"
vehicle.mode    = VehicleMode("LAND")
time.sleep(30)

isFlying = 1

print "Close vehicle object"
vehicle.close()

print('finished')
s.close()