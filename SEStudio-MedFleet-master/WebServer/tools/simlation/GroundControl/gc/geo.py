def distance_to_current_waypoint():
    """
    Gets distance in metres to the current waypoint.
    It returns None for the first waypoint (Home location).
    """
    nextwaypoint=vehicle.commands.next
    if nextwaypoint == 0:
        return None
    missionitem=vehicle.commands[nextwaypoint-1] #commands are zero indexed
    lat=missionitem.x
    lon=missionitem.y
    alt=missionitem.z
    targetWaypointLocation=LocationGlobalRelative(lat,lon,alt)
    distancetopoint = get_distance_metres(vehicle.location.global_frame, targetWaypointLocation)
    return distancetopoint


def printInfo():
    print "Autopilot Firmware version: %s" % vehicle.version
    print "Autopilot capabilities (supports ftp): %s" % vehicle.capabilities.ftp
    print "Global Location: %s" % vehicle.location.global_frame
    print "Global Location (relative altitude): %s" % vehicle.location.global_relative_frame
    print "Local Location: %s" % vehicle.location.local_frame    #NED
    print "Attitude: %s" % vehicle.attitude
    print "Velocity: %s" % vehicle.velocity
    print "GPS: %s" % vehicle.gps_0
    print "Groundspeed: %s" % vehicle.groundspeed
    print "Airspeed: %s" % vehicle.airspeed
    print "Gimbal status: %s" % vehicle.gimbal
    print "Battery: %s" % vehicle.battery
    print "EKF OK?: %s" % vehicle.ekf_ok
    print "Last Heartbeat: %s" % vehicle.last_heartbeat
    print "Rangefinder: %s" % vehicle.rangefinder
    print "Rangefinder distance: %s" % vehicle.rangefinder.distance
    print "Rangefinder voltage: %s" % vehicle.rangefinder.voltage
    print "Heading: %s" % vehicle.heading
    print "Is Armable?: %s" % vehicle.is_armable
    print "System status: %s" % vehicle.system_status.state
    print "Mode: %s" % vehicle.mode.name    # settable
    print "Armed: %s" % vehicle.armed    # settable


def jsonInfo():
    print('sending updates to MC...')
    lat=vehicle.location.global_relative_frame.lat
    lon=vehicle.location.global_relative_frame.lon
    alt=vehicle.location.global_relative_frame.alt
    v=vehicle.velocity[1]
    ss=vehicle.system_status.state
    bv=vehicle.battery.voltage
    bl=vehicle.battery.level

    drone_data = {
                'latitude':lat,
                'longitude':lon,
                'altitude':alt,
                'velocity':v,
                'local_location':'%s'%vehicle.location.local_frame,
                'gps_strength':'%s'%vehicle.gps_0,
                'pitch_axis':vehicle.attitude.pitch,
                'roll_axis':vehicle.attitude.roll,
                'yaw_axis':vehicle.attitude.yaw,
                'battery_voltage':bv,
                'battery_percentage':bl,
                'last_heartbeat':vehicle.last_heartbeat,
                'heading':vehicle.heading,
                'armed':vehicle.armed,
                'mode':'%s'%vehicle.mode.name,
                'system_status':'%s'%ss,
                'ground_speed':vehicle.airspeed
    }
    return (data_string=json.dumps(drone_data)+'\n')
