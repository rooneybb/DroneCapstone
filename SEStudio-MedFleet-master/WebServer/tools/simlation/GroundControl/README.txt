
              Author: Turki Majdali
                     MFGS
             for SITL and Iris Plus

### todo
#add Vehicle.home_location
#add closesness of gps
    class dronekit.GPSInfo(eph, epv, fix_type, satellites_visible)
    Parameters:
        eph (Int) – GPS horizontal dilution of position (HDOP).
        epv (Int) – GPS vertical dilution of position (VDOP).
        fix_type (Int) – 0-1: no fix, 2: 2D fix, 3: 3D fix
        satellites_visible (Int) – Number of satellites visible.
#Callbacks
    in example code
#get inflight info
    in example code
#look into seneding new commands on mission
    Modifying missions
    While you can add new commands after downloading a mission it is not possible to directly modify and upload existing commands in Vehicle.commands (you can modify the commands but changes do not propagate to the vehicle).
    Instead you copy all the commands into another container (e.g. a list), modify them as needed, then clear Vehicle.commands and upload the list as a new mission:
    code in exapmes
#Flight Replay
Drone States vars:
    Vehicle.version
     Vehicle.location.capabilities
     Vehicle.location.global_frame
     Vehicle.location.global_relative_frame
     Vehicle.location.local_frame
     Vehicle.attitude
     Vehicle.velocity
     Vehicle.gps_0
     Vehicle.gimbal
     Vehicle.battery
     Vehicle.rangefinder
     Vehicle.ekf_ok
     Vehicle.last_heartbeat
     Vehicle.home_location
     Vehicle.system_status
     Vehicle.heading
     Vehicle.is_armable
     Vehicle.airspeed
     Vehicle.groundspeed
     Vehicle.armed
     Vehicle.mode.
