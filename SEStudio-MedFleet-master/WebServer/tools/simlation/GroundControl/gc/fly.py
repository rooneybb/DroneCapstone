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

def get_home_location() {
    while not vehicle.home_location:
        print " Waiting for home location ..."

    print "\n Home location: %s" % vehicle.home_location
    HomeVLAT = vehicle.home_location.lat
    HomeLONG = vehicle.home_location.lon
}

def arm_and_takeoff(aTargetAltitude):
    """
    Arms vehicle and fly to aTargetAltitude.
    """
    print "...Pre-Arm Checks..."
    # Wait until the autopilot finish from booting
    print "INITIALISE VEHICLE"
    while vehicle.mode.name == "INITIALISING":
        print "\tWaiting for vehicle to initialise"
        time.sleep(2)
    print "\tSuccess!! vehicle initialised."
    print "INITIALISE GPS"
    while vehicle.gps_0.fix_type < 2:
        print "\tWaiting for GPS...:", vehicle.gps_0.fix_type
        time.sleep(2)
    print "\tSuccess!! vehicle initialised."
    print "...ARMING MOTORS..."
    # Switch to GUIDED mode to arm the Iris Plus
    vehicle.mode    = VehicleMode("GUIDED")
    print "Activating Guided Mode"
    while not vehicle.mode.name == "GUIDED":
        print "\tMode: %s" % vehicle.mode
        print "\tGPS: %s", vehicle.gps_0.fix_type
        print "\tWaiting for guided..."
        time.sleep(2)
    print "\tSuccess!! Guided Mode initialised."
    vehicle.armed   = True
    print "Arming Vehicle"
    while not vehicle.armed:
        vehicle.armed   = True
        print "\tMode: %s" % vehicle.mode
        print "\tGPS: %s", vehicle.gps_0.fix_type
        print "\tWaiting for arming..."
    print "\tSuccess!! Vehicle Armed."
    print "...Drone Ready!..."
    print "Taking off!"
    vehicle.simple_takeoff(aTargetAltitude) # Take off to target altitude
    # Wait until the Iris Plus reaches a safe height.
    while True:
        print "\tAltitude: %s", vehicle.location.global_relative_frame.alt
        print "\tMode: %s" % vehicle.mode
        if vehicle.location.global_relative_frame.alt>=aTargetAltitude*0.80: #Just below target, in case of undershoot.
            print "\tTarget altitude Reached!"
            break
        time.sleep(1)


def do_guided_mission():
    # Arm and Takeoff
    arm_and_takeoff(10)
    time.sleep(10)
    # set Air Speed
    vehicle.airspeed=10
    print "...Starting Guided Mission..."
    print "\tGoing to target point Lat: %s , Lon: %s" % (TARGET_LAT, TARGET_LON)
    point1 = LocationGlobalRelative(TARGET_LAT, TARGET_LON, 15)
    vehicle.simple_goto(point1)
    time.sleep(40)
    print "\tLanding at Target point!"
    vehicle.mode    = VehicleMode("LAND")
    print "\tLanded!"
    print "\tDisarming Drone"
    while vehicle.armed:
        print "\t\tWaiting for Disarming..."
        time.sleep(2)
    print "\tSuccess!! Vehicle Disarmed."
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
    Print "Finished Mission!!!"

def do_auto_mission():
    cmds = vehicle.commands
    print "...Starting Auto Mission..."
    print "\tClearing any existing commands"
    cmds.clear()
    print "\tAdding commands"
    ## todo get as stack and fill
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_TAKEOFF, 0, 0, 0, 0, 0, 0, 0, 0, 10))
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0, 0, 0, 0,TARGET_LAT, TARGET_LON, 11))
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_LAND, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_TAKEOFF, 0, 0, 0, 0, 0, 0, 0, 0, 10))
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_WAYPOINT, 0, 0, 0, 0, 0, 0,HomeVLAT, HomeLONG, 11))
    cmds.add(Command( 0, 0, 0, mavutil.mavlink.MAV_FRAME_GLOBAL_RELATIVE_ALT, mavutil.mavlink.MAV_CMD_NAV_LAND, 0, 0, 0, 0, 0, 0, 0, 0, 0))
    print "\tUpload new commands"
    cmds.upload()
    print "Takeoff at Home"
    arm_and_takeoff(10)
    time.sleep(10)
    vehicle.airspeed=10
    #set to first (0) waypoint to Reset mission
    vehicle.commands.next=0
    print "Switching to auto mode"
    vehicle.mode = VehicleMode("AUTO")
    # todo make this work
    while True:
        nextwaypoint=vehicle.commands.next
        if nextwaypoint==0:
            print "takeoff at Home.."
        if nextwaypoint==1:
            print "going to target location.."
        if nextwaypoint==2:
            print "landing at target location.."
        if nextwaypoint==3:
            print "takeoff at target location.."
        if nextwaypoint==4:
            print "going Home.."
        if nextwaypoint==5:
            print "landing at Home.."
            break;
        time.sleep(1)
    print "Disarming Drone"
    while vehicle.armed:
        print "\tWaiting for Disarming..."
        time.sleep(2)
    print "\tSuccess!! Vehicle Disarmed."
    time.sleep(2)
    Print "Finished Mission!!!"
