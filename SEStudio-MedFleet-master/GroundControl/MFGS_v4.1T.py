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
from Queue import Queue




# connect to MC
print('Connecting to MC...')
# change this MC IP address and port when needed
host = '207.229.178.146'
port = 20005
size = 1024
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((host,port))
print('Connected to MC!!')

readyFlag = 0

data = 'ready:'+'573a7bbc0fc637e003570b76'
data_string = data+'\n'
time.sleep(1)
datta = 'ready:'+'573cf915913d5fc80ad7a22d'
datta_string = datta+'\n'

# new thread to send ready to MC
def send_ready():

    # Send 'ready' to MC
    while readyFlag == 0:
         #print('ready')
         s.send((data_string.encode('utf-8')))
         s.send((datta_string.encode('utf-8')))
         time.sleep(5)

print('Sending ready to MC!! \n')
try:
    thread01 = threading.Thread(target=send_ready, args=())
    thread01.start()
except:
    print "Error: unable to start thread"


	
	


def start_mission(conn, altd, droneID, TARGET_LATT, TARGET_LONN ):

    # Connect to Iris Plus or SITL
	
	# comment this if you are using real drones.
    vehicle = connect(conn, wait_ready=True)
	
	# uncomment this if you are using real drones.
    #vehicle = connect(port, wait_ready=True, baud=57600)

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

    isNotFlying = 0








    def arm_and_takeoff(aTargetAltitude):
        """
        Arms vehicle and fly to aTargetAltitude.
        """

        print "Basic pre-arm checks"
        # Wait until the autopilot finish from booting
        if vehicle.mode.name == "INITIALISING":
            print "Waiting for vehicle to initialise"
            time.sleep(2)
        while vehicle.gps_0.fix_type < 2:
            print "Waiting for GPS...:", vehicle.gps_0.fix_type
            time.sleep(2)


        print "Arming motors"
        # Switch to GUIDED mode to arm the Iris Plus
        vehicle.mode    = VehicleMode("GUIDED")

        while not vehicle.mode.name == "GUIDED":
            print " Mode: %s" % vehicle.mode
            print " GPS: %s", vehicle.gps_0.fix_type
            print " Waiting for guided..."
            time.sleep(2)

        vehicle.armed   = True

        while not vehicle.armed:
            vehicle.armed   = True
            print " Mode: %s" % vehicle.mode
            print " GPS: %s", vehicle.gps_0.fix_type
            print " Waiting for arming..."
            time.sleep(2)


            time.sleep(1)

        print "Taking off!"
        vehicle.simple_takeoff(aTargetAltitude) # Take off to target altitude

        # Wait until the Iris Plus reaches a safe height.
        while True:
            print "Drone ID", droneID
            print " Altitude: ", vehicle.location.global_relative_frame.alt

            print " Mode: %s" % vehicle.mode

            if vehicle.location.global_relative_frame.alt>=aTargetAltitude*0.80: #Just below target, in case of undershoot.
                print "Reached target altitude"
                break
            time.sleep(1)

    # New thread to send data to MC
    def send_attributes():
        print('sending updates to MC')
        while isNotFlying == 0:
                print('sending updates to MC...')
                lat=vehicle.location.global_relative_frame.lat
                lon=vehicle.location.global_relative_frame.lon
                alt=vehicle.location.global_relative_frame.alt
                v=vehicle.velocity[1]
                ss=vehicle.system_status.state
                bv=vehicle.battery.voltage
                bl=vehicle.battery.level

                data02 = { 'id':droneID, 'name':'String', 'latitude':lat, 'longitude':lon, 'altitude':alt, 'velocity':v, 'local_location':'%s'%vehicle.location.local_frame, 'gps_strength':'%s'%vehicle.gps_0, 'pitch_axis':vehicle.attitude.pitch, 'roll_axis':vehicle.attitude.roll, 'yaw_axis':vehicle.attitude.yaw, 'battery_voltage':bv, 'battery_percentage':bl, 'last_heartbeat':vehicle.last_heartbeat, 'heading':vehicle.heading, 'armed':vehicle.armed, 'mode':'%s'%vehicle.mode.name, 'system_status':'%s'%ss, 'air_speed':vehicle.airspeed, 'ground_speed':vehicle.groundspeed }
                data_string=json.dumps(data02)+'\n'
                s.send((data_string.encode('utf-8')))
                time.sleep(5)
                if isNotFlying == 1:
                     break
                     print('Sending updates to MC has ended')

    def do_guided_mission():

        # Arm and Takeoff
        arm_and_takeoff(10)
        time.sleep(10)



        # set Air Speed
        vehicle.airspeed=10

        print "Going to target point Lat: %s , Lon: %s" % (TARGET_LATT, TARGET_LONN)
        point1 = LocationGlobalRelative(TARGET_LATT, TARGET_LONN, 15)
        vehicle.simple_goto(point1)


        time.sleep(40)

        print "Landing at Target point"
        vehicle.mode    = VehicleMode("LAND")

        while vehicle.armed:
            print " Waiting for Disarming..."
            time.sleep(2)

        time.sleep(2)

        arm_and_takeoff(10)
        time.sleep(15)

        print "Going Home Lat: %s , Lon: %s" % (HomeVLAT, HomeLONG )
        point2 = LocationGlobalRelative(HomeVLAT, HomeLONG, 15)
        vehicle.simple_goto(point2)


        time.sleep(40)

        print "Landing at Home"
        vehicle.mode    = VehicleMode("LAND")

        while vehicle.armed:
            print " Waiting for Disarming..."
            time.sleep(2)

        time.sleep(2)
    def do_auto_mission():
        cmds = vehicle.commands
        print " Clear any existing commands"
        cmds.clear()


        cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_TAKEOFF, 0, 0, 0, 0, 0, 0, 0, 0, altd))
		# set Air Speed
        cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_DO_CHANGE_SPEED, 0, 0, 0, 50, 0, 0, 0, 0, 0))
		
        cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0, 0, 0, 0,TARGET_LATT, TARGET_LONN, altd))

        cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_LAND, 0, 0, 0, 0, 0, 0, 0, 0, 0))



        print " Upload new commands to Iris Plus"
        cmds.upload()
        print " Takeoff at Home"
        arm_and_takeoff(altd)
        time.sleep(10)






        #set to first (0) waypoint to Reset mission
        vehicle.commands.next=0
        print "switching to auto mode"
        vehicle.mode = VehicleMode("AUTO")


        while True:


            nextwaypoint=vehicle.commands.next


            if nextwaypoint==0:
                print "takeoff at Home.."

            if nextwaypoint==3:
                print "going to target location.."
            if nextwaypoint==4:
                print "landing at target location.."

                break;
            time.sleep(1)

        while vehicle.armed:
            print " Waiting for Disarming..."
            time.sleep(2)

        time.sleep(2)
    #----------------------
    try:
        thread02 = threading.Thread(target=send_attributes, args=())
        thread02.start()

    except:
        print "Error: unable to start thread"

    do_auto_mission()

    isNotFlying = 1


    print('Mission Completed')
    print('sending "completed" to MC')
    dataCom = 'completed:'+droneID
    dataCom_string = dataCom + '\n'
    s.send((dataCom_string.encode('utf-8')))


    thread01.join()
    thread02.join()
    print('closing connection to drone: ',droneID)
    vehicle.close()
# while true: get data from MC and assign missions to drones	
while True:

        # get data from MC
    print('waiting for MC...')
    recData = s.recv(size)
    print('information received!!')
    readyFlag = 1

	# decode data
    recData = recData.decode('utf-8')    
    json_dump = json.dumps(recData)
    data_loaded = json.loads(json_dump)
    print('this is DL: ',data_loaded)
    
    if data_loaded == '\n':
        print('Error! empty string 01')
        continue
    if data_loaded == 'Wrong Format':
        print('Error! Wrong Format')
        continue
    if data_loaded == '':
        print('Error! empty string 02')
        continue
    if data_loaded == ' ':
        print('Error! empty string 03')
        continue	
    
	# get mission data from JSON
    json_dict =ast.literal_eval(data_loaded)

    print('Received Target Location:',json_dict)
    print('Received Target latitude:',json_dict['latitude'])
    print('Received Target longitude:',json_dict['longitude'])
    print('Received Target Drone ID:',json_dict['droneId'])
	
    TARGET_LAT = json_dict['latitude']
    TARGET_LON = json_dict['longitude']
    DRONE_ID = json_dict['droneId']
    
    if DRONE_ID == '573a7bbc0fc637e003570b76':
        print(' sending mission to Drone 01 !!! ')
        pport = 'tcp:127.0.0.1:5763'
        port = 'com3'
        alt = 10
        try:
            thread03 = threading.Thread(target=start_mission, args=(pport,alt,DRONE_ID,TARGET_LAT, TARGET_LON))
            thread03.start()

        except:
            print "Error: unable to start thread"
        
    if DRONE_ID == '573cf915913d5fc80ad7a22d':
        print(' sending mission to Drone 02 !!! ')
        pport = 'tcp:127.0.0.1:5783'
        port = 'com5'
        alt = 15
        try:
            thread04 = threading.Thread(target=start_mission, args=(pport,alt,DRONE_ID,TARGET_LAT, TARGET_LON))
            thread04.start()

        except:
            print "Error: unable to start thread"
	
print('closing connection..')
s.close()