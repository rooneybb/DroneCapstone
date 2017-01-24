#Ground Control
Ground Control is a python based application whose primary responsibility is communicating with and scheduling the drones. The application interfaces with the drones via the [DRONE KIT API](http://dronekit.io/)

The Ground Control system starts by opening up a socket with Mission Control and listening for new missions. When a new mission comes in, Ground Control dispatches the drone to the GPS coordinates provided by Mission Control. Ground Control receives real time flight data from the drone; including, latitude, longitude, altitude, speed, etc. This information is received and passed back to Mission Control for entry into the database.

# changes i found 
python:
24 - host
32 - drone 1
35 - drone 2
330 - drone id 1
342 drone id 2
344  port 
332 port

## Getting Started

###Installation nstructions for Med-Fleet Ground Station (MFGS) on Windows:


####Install Python
- **[Install WinPython 2.7 64bit](http://sourceforge.net/projects/winpython/files/WinPython_2.7/)**

- **Register your WinPython distribution to Windows.**

    - Go to WinPython 2.7 64bit folder  
    - open WinPython Control Panel 
    - click Advanced 
    - click Register distribution

####Install DroneKit and SITL.
- Go to WinPython 2.7 command prompt  
- enter these commands:

```
> pip install dronekit
> pip install dronekit-sitl -UI
```

####[Install MavProxy.](http://firmware.diydrones.com/Tools/MAVProxy/) (Install MAVProxySetup-1.4.18.exe)

##Running Instructions using a simulator runing SITL and MavProxy.
- Open two WinPython terminals. For first terminal write:  > dronekit-sitl copter
- For second terminal write: > dronekit-sitl copter --instance 2 
- Run two MavProxy instances: first MavProxy terminal write: > link add tcp:127.0.0.1:5760
- Second MavProxy terminal write: > link add tcp:127.0.0.1:5780


##Running Instructions using drones
- Connect the telemetry antenna to PC using the USB cable.
- Turn on the drones and wait for Green LED.
- Open MFGS in a text editor and change Mission Control IP address to current one.
- Run MFGS in a new WinPython terminal : > python MFGS.py


## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
