#Mobile Application
The Med-Fleet process starts when a user requests assistance using the Android mobile application. The user is required to specify the severity of their emergency as well as their current location. When the user presses ‘submit’ the mobile app sends a JSON Post request to the ticketing system (the post request contains the users GPS coordinates as well as the severity of their emergency). The application was built using Android Studio and SDK Version 23 (Marshmallow).

### Prerequisities

* Andriod Studio
* Andriod Device with Marshmallow or Greater 

### Installing
1. Open Andriod Sudio
2. Click - Open a Project
3. Naviage to the SEStudio-MedFleet/PhoneApp/Andriod folder
4. Click OK
5. Naviage App > res > values > strings > strings.xml and change the value of urlPost to the IP of the WebServer
6. To Run on simulator click the play icon.



## Add App to a Device
1. To build for device go to Build > Build Apk
2. Copy the Apk onto an andriod device and open the file

## Built With

* Andriod Studio

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details
