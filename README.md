# Weather Status Application

# Project description:

The Application provides mainly three functional interfaces 
1. To register a sensor. 
   A sensor can be registered with its details like name, city , country 
2. Once Registerd a Sensor can send in its weather readings to the applciation. These include details like 
   humidity, windspeed, temperature etc.
3. User can retrieve the details based on various parameters like  average temperature and humidity for sensor 1 
   in the last 'N' number of days or based on a date range.


# How to run this project

  ## Pre-requisities

  Java SE Development Kit 11 or newer   
  && 
  Maven 3.0 or newer

  Check for the versions 

  javac -version
  mvn -version

## Building the Project

  * You can navigate to the root folder of the application and run : 'mvn clean install'

## Running the Application
 
  ** You can navigate to the root folder of the application and run : 'mvn spring-boot:run'


## Access the REST APIs

   ### <u>Sensor Registration API:</u>

      POST: localhost:5000/Sensor
      Description: One Sensor or Multiple Sensors can be registered here.
	
	* Sensor                                                       
	[{"country":"ireland","city":"athlone","name":"sensor 1"},{"country":"ireland","city":"athlone","name":"sensor 2"}]

	

   ### <u>Sensor Data API for a Sensor:</u>

      POST: localhost:8080/Sensor/SensorData
      Description: Any Registered Sensor can send in its live updates for the weather.

    	{
          "humidity": 569.1,
          "temp": 93.55,
          "windSpeed": 53.5,
          "sensor_id": "1"
		}

   ### <u>Retrieve average values of a sensor from 'N' days:</u>

      GET: localhost:5000/Sensor/{sensorID}/SensorData?days={no of days}
      Description: User can retrieve values of a particular sensor based on details provided. SensorID and the no of days need to be mentioned.
      Example: localhost:5000/Sensor/1/SensorData?days=7
      
   ### <u>Retrieve average values of a sensor from Date Range:</u>

      GET: localhost:5000/Sensor/{sensorID}/SensorData?To={To Date Range}&From={From Date Range}
      Description: User can retrieve values of a particular sensor based on details provided. SensorID and the Date Range to be mentioned.
	  Example: localhost:5000/Sensor/1/SensorData?To=2022-12-09&From=2022-12-02
 
 #Two ways to Access the Application
 
 		* In the Project Folder 
 	  	  mvn spring-boot:run
 	      
 	      Access the Apis on http://localhost:5000/
 	      
 	      Swagger Link: http://localhost:5000/swagger-ui/index.html
 	   
 	    * Run the Project as Docker Container using
 	      docker-compose up --build  (This will build a image on JDK 11 and Containerize it) 
 	      
 	      Access the Apis on http://localhost:8887/
 	      
 	      Swagger Link : http://localhost:8887/swagger-ui/index.html
 	   
 	   
# GitHubLink

		https://github.com/hemanthDogiparthi/Weather-Application


        
# Key Features:
     * Containarized the Application with Docker. Further improvements and Automation 
       can be done by adding Kubernetes and helm integration
     * Test Coverage with Jacaco. 
     * End to End Integration Testing enabled.
     * Swagger UI for showing the APIs provided
     * A runtime in memory H2 Database
     * Centralized and Uniform Error Response Mechanism
     * Tried to adhere to the SOLID principles where ever possible.
     * spring-cloud encryption for storing passwords for Database connectivity
       
     
    

# Scope for Improvements:

    * Front end to register sensors and sending in its readings.
    * Integrate the Application with Front End UI to make the app interactive
    * Test Code Coverage percentage for the Application is Around 75-80% . 
      Further tests can be included 
    * Add Authentication for restapis. Add seperate authorization for registering the sensors (An admin can only register the sensors)
      and seperate for Reading the sensor values API (Typically APi used by the customer)
    

