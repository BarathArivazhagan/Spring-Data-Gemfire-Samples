This sample project explains Client - Server architecture in Gemfire

Steps to run this sample Gemfire Client-Server-Example : 

Prerequisite :

Pivotal Gemfire - Go through this tutorial to download Gemfire and set up Gemfire locators

http://gemfire81.docs.pivotal.io/docs-gemfire/getting_started/15_minute_quickstart_gfsh.html


Step1 : Start the Gemfire locator 
start locator --name=testlocator

Step2: Start the Gemfire Cache Server 
start server --name=server1

Step3: Create a Replicated Book Region 
create region --name=BOOK --type=REPLICATE

Step4: Download Spring-Gemfire-BookStore1 and Spring-Gemfire-BookStore2 projects and import into IDE and run as Spring boot App

BookStore1 and BookStore2 are two Gemfire Cache Clients connecting to the Gemfire Locator[testlocator] running in localhost[10334] 

Step5: Following endpoints are exposed to perform basic CRUD operations:


http://localhost:8081 --> BookStore1
http://localhost:8082 --> BookStore2

http://localhost:8081/addBook?id=100&name=HARRYPORTER --> to add a book into BookStore1
http://localhost:8082/addBook?id=101&name=JAVA8 --> to add a book into BookStore2

As both the clients connected to the same locator and share regions in distributed region  : 

http://localhost:8081/findAllEntries -->BookStore1

you will see both the entries : 

HARRYPORTER -->entry made by BookStore1
JAVA8   -->entry made by BookStore2




This app uses Client server topology 
