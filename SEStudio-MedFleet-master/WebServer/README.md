#Webserver - DB Schema Web Listener, Med-Fleet Monitor. 
Med-Fleet’s Webserver contains the following modules
DB Schema 
Web Listener, 
Med-Fleet Monitor. 

##TO RUN
1. make sure npm, node, mongodb, typings, typescript compiler are installed 
2. make sure the DB is running on default ports
4. open the command prompt and change to the web listener folder
5. type: 

```bash
# make sure db is running 
mongod
# make sure global packages are installed
npm install -g gulp typescript ts-node
# install packages for server
npm install 
#copy static files to bin directory
gulp copy
#install typings files
typings install 
# compile the code
tsc
# start the server
npm start
```

6. test to see if working [127.0.0.1:3000](127.0.0.1:300)
7. if the server is running without errors you can have it run forever with 'forever'
```bash
forever start ./server.js 
```

## Prerequisities
To run the Websever you must have the following: 
* MongoDB open and running on the default port.
* nodejs / npm 

## Modules
* DB Schemas - Schema used for all objects. For more details see its [readme](README_db_schema.md)
* Web Listener - Listener that handels all changes to the objecs. For more details see its [readme](README_web_listener.md)
* Med-Fleet Monitor - Web client to monitor all infomation. For more details see its [readme](README_README_medfleet_monitor.md)

## Built With
* Mongo DB
* Express 
* Angular 2 
* npmjs 

## Versioning
see [ChangeLog](ChangeLog.md)

## License

This project is licensed under the MIT License 

