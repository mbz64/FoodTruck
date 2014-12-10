Food Truck
--------------

The goal of this service is to provide a list of food  trucks available around user's location. The service is composed of 2 parts - a) A front end through which user selects a location on the map and then press the Get Food Truck button to send the request(latitude,longitude,hard coded radius) to the server. b) The backend provides REST API which given the user's selection location(latitude, longitude) and radius, send back a JSON array of food truck list back to the user.

More focus is given on the backend than the fron end of the solution.

Back End
----------
On the backend side, technology stack used is nginx, nodeJS and mongoDB. Reasoning for selecting nginx and nodeJS is their ability to support asynchronous model easily and hence serving large number of concurrent clients. Also my beginner level familiarity with nodeJS played a role in selecting it. The reasoning behind MongoDB's selection is its geolocation search api, high performance and ease of use.
There is also a dataloader component written in java (since i'm more familiar with it) which is responsible for loading the datafiles into MongoDB to be used by the service.

Code layout : The layout of the backend code is composed of 2 directories : Parser and server_side_scripts.
The Parser code is composed of 2 main interfaces FileParser and Storage. 

FileParser--->DataLoaderMain----> Storage.

The DataLoaderMain above gets the data from a concrete class implementing the FileParser interface and stores that information through a concrete implementation fo Storage. The Domain data structure DataObject contains the intermediate data. The design allows diff future implementation of FileParser and Storage. Appropriate unit tests are provided for each objects. The Parser receives 2 arguments (datafile and mongodb_config_file) for setup of the db  and reading data.

The server_side_scripts is composed of 3 scripts: foodtruckserver.js, foodtruck_apihandlers.js, dbutil_mongo.js. 

foodtruckserver.js provides the entry point to the server and accepts/sends data to remote server. It implements 2 REST APIs:
	1) GET ./list - it receives latitude, longitude, radius in query string and sends JSON array of food truck information (only id, name, foodtruck type and address 	is sent now). The reason behind receiving parameter in query string is I'm yet to find out processing of JSON object inputs(body_parser etc) in nodeJS.
        2) GET ./getDetail - it receives an id of the food truck in query string and sends detail information as json object for that food truck. The reason for using query string input is same as above.

foodtruck_apihandlers.js provides the api interface that the foodtruckserver talk to and responsible for interacting with the model(i.e. database storage).

dbutil_mongo.js provides inetrafce to talk to the mongodb database and gets appropriate ifnormation requested by the server. 

Some configurations are hardcoded in these scripts for now.

Unit tests for the server side scripts not yet implemented due to time constraint on my part.


Front End
---------
The minimal front end consists of an index.html which shows a google map. It allows user to select a location of the map and press a button which gets a list of food truck available around 300 meter of that location. The list of the food trucks is shown in  table. The table gets updated everytime user sends a new request.

Link to the Application
-----------------------
The app is hosted in amazon aws here: http://ec2-54-67-62-55.us-west-1.compute.amazonaws.com/ 




