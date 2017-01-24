![logo](./public/med_fleet_logo.png "Med-Fleet Logo") 
#SEStudio-MedFleet
Medical Fleet of Drones
Studio Team at DePaul
January - June 2016

The Med Fleet project uses a fleet of drones to deliver medical kits to users that request assistance. The requests originate from a mobile application that uses GPS to identify the current location of the user that needs help. The incoming requests are then prioritized, scheduled, and assigned to one of the drones in the fleet. The drone is then dispatched to deliver the medical kit to the GPS coordinates. Example applications include:

Natural Disaster - Victims of an earthquake can request assistance when emergency services in the area are fully utilized.
State Park or Ski Mountain - Hikers, Campers, or Skiers that have a serious injury in a remote location can request medical assistance.
The technical considerations for the project were substantial and the architectural decisions were left entirely up to the team members (more on this later). Another domain that warranted serious consideration were the saftey concerns associated to this project. Not only are users relying on the application for time sensitive medical support, flying drones opens up many saftey concerns to anything in the area (personal injury, property damage, etc). The challenge lied in finding a balance of resources to build out the technology while emphasizing saftey in all layers of the application.

It's worth noting that our implementation of this application uses inexpensive quadcopters that are capable of flying for about 20 minutes (our drones also don't support carrying a payload). Our implementation was primarily to simulate how more robust drones would be programed, organized, and dispatched.

##TO RUN Please refer to each modules README
* [Web Server (Med-Fleet Monitor / WebListener)](./WebServer/README.md)
* [Mission Control](./MissionControl/README.md)
* [Ground Control](./GroundControl/README.md)
* [Phone App](./PhoneApp/README.md)
* [SafetyAndTesting](./SafetyAndTesting/README.md)

###Then 
1. Open the command prompt and CD to the SEStudio-MedFleet/public
2. make sure you have newman installed: 
```
npm install -g newman
```
3. add Mock Data to the project: 
```
newman -c MedFleetMockDataSetup.json
```

### Notes 
if you ever need to reset the ticket status :
1. open a comand prompt
2. type mongo
3. type use medfleet
4. type db.tickets.update( {}, { $set: { status : "received" } }, { multi: true } );

## Built With
* Mongo DB
* Express 
* Angular 2 
* npmjs 
* Maven
* Andriod Developer 
* DroneKit
* MavProxie 
* Jenkins


## License
This project is licensed under the MIT License 