

This Project Spring-DATA-LocalRegion-Sample demonstrates how to create a simple LocalRegion of the application using Spring Data Gemfire and perform CRUD operations on it 


Prerequisite:

1. Start the Gemfire Locator :

gfsh> start locator --name=testlocator

2. Start the Cache Server : 

gfsh> start server --name=server1




It creates an Embedded Gemfire Local Cache region Movie .

Download the project and import into IDE and Run as Spring boot app

Make a post request to below end point 


ADD MOVIE:

http://localhost:8080/addMovie 

Body : 
{
  movieId:100,
  movieName:"AVENGERS"
}

GET MOVIE:

http://localhost:8080/getMovie?id=100


FIND ALL MOVIES :

http://localhost:8080/findAll
