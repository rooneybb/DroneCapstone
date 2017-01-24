#Web Listner Routes
The following are all the valid http requests for the WebLister the list is by route, then a list of request types 

##Conifguaration 
###/config
* get - gets all config files
* post- create a config file
* delete - delete all config files

###/config:configid
* get - get a config object based on id
* post - update a config object based on id
* delete - delete a config object based on id

###/config/name/:configname
* get - get a config based on the name field
* post - update a config object based on the name field 
* delete - delete a config object based on name feild

## Drones
###/drone
* get - lists all drones in json format
* post - create drone
* delete - deletes all drones

###/drone/:droneid
* get - gets drone by the id
* post - updates the drone by the id 
* delete - deletes the drone by the id

##Medfleet Monitor
###/
* get - starts the Med-fleet Monitor website
        
##Missions
###/mission
* get - gets all missions
* post - create a mission
* delete - deletes all missions

###/mission/:missionid
* get - list a mission by id in json format
* post - update a mission based on the id
* delete - delte a drone based on id 
    
##Tickets 
###/tickets
   * get - lists the tickets in json
   * post - creates a object in db by passing a json object in the html body
   * delete - delete ALL tickets
   
###tickets/:ticketid
   * get - gets tickets by id
   * post - update a tickets by id
   * delete - delete a tickets by id
   
###tickets/:lat?/:lat_dec?/:long?/:long_dec?/:urgency?
   * post - creates a tickets by the url
   * get - creates a tickets by the url

