here is the basic info about this project:

java
    all java classes are located in :
        SEStudio-MedFleet\PhoneApp\Andriod\Med-Fleet\app\src\main\java\com\example\medfleet\med_fleet
    Classes (Actions):
    - MainActivity: The class loaded at runtime.  The method that is run is onCreate.  
	When the user selects their severity and clicks next, we go to OpenMap
    - OpenMap: The method that is called is on create.  This method load a Google Map and drops a pin on the users current location.
	The pin that is dropped supports "is Draggable" and updates the users lat & long depending on where the pin was dropped.  When
	the user selects "Request Drone" they are forwarded to the finish Action
    - Finish: This Class presents a message to the user letting them know if there request was successful or not.