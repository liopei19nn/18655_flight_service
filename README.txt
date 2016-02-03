18655 Homework 4 

Name : Li Pei
ID : lip

This REST Service is based on Jersey Framework. 
User Interface is based on Java Swing.
External service including : 1. Flight Information from FlightStats, 2.  Weather Information From FlightStats

This is a MAVEN project, so if you want to run the service, you have to check the version of Eclipse, and you must have Tomcat at least v7.0.

If you want to run the service, you have to configure the Jersey framework. The .jar files are all in WebContent/WEB-INF/lib, please add all .jar file when you configure build path and "Add External JARs".

After you configure the service, you have to run on server, then you could use browser and input 
"http://localhost:8080/lip_18655_homework4/rest/TravelInfoService/comCode/input1/number/input2"
In "input1", input the airline company code, like for American Airline, input "AA". In input2, you have to 
input the airline code, like if you want search "AA100", the URL is 
"http://localhost:8080/lip_18655_homework4/rest/TravelInfoService/comCode/AA/number/100"
and the browser will show a XML file.

If you want to use client UI, run TravelInfoUI.java, and you will see the UI, you could input and query flight.

To help grading, I will describe my work accroding Grading Criteria,

[85] Basic Requirements:

[10] Develop a GUI for users to input travel information (e.g., date, time, city, state);
[10] Develop a GUI to show  weather information and flight information;

	You can check the GUI in com.lip.info.ui package, there is a main frame with two seperate panel, one panel for input information, and one panel for showing weather and flight information.

[15] Develop a mashup service that composite external web services;

	The mashup service is published with TravelInfo.java, and the background implements are in com.lip.info.utilities package.

[30] Call external web services (e.g., weather, flight info)

	Check GetFlightInfo.java and GetWeatherInfo.java, they are two different External Web Service from FlightStats. And the GetInfoUtilities.java called them and combine the data from these service together.

[10] Use Eclipse to develop
[10] Turn in a zip file, with source code and a readme.txt file containing both developers' names. 

Bonus!!
[15] Think (all open): Historical Cache:

In TravelInfo.java, where REST service is published, there is a local static hashmap object used to cache the 100 recent query result. If you query the same key, the mashed up service will not call external service, but return the recent stored data to you, which will accelerate the query speed, especially when you only focus on the recent query results.








