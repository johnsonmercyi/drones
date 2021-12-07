# Drones Demo
Minimal [Spring Boot](http://projects.spring.io/spring-boot/) sample app.

### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

### Works Done
- Two main entities: `Drones` & `Medications` have been created to store Drones and and Medication details respectiely.
- `Drone` & `Medication` data is pre-loaded at project launch.
- A REST API has been created to carry the following task
  - registering a drone
  - loading a drone with medication items
  - checking loaded medication items for a given drone
  - checking available drones for loading
  - check drone battery level for a given drone
  - A periodic task scheduled to check Drone battery capacity and log the history (per minute) is implementated hence a log history entity is created to store history.

## Technologies Used
- Spring Boot - version 2.6.1
- Spring Data JPA
- Spring DevTool
- H2 Database (In Memory)
- Flyway DB (For Db Migrations)
- Lombok

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)


## Build and Run Spring Boot Application Locally
Before building a Spring Boot project, please ensure you've the steps below:
- Firstly ensure you have `maven` installed successfully. To install `maven`: [Download and Install from here](https://maven.apache.org/download.cgi)
- Secondly set maven classpath correctly.

## BUILD - Drone Demo
Please build using Spring Boot Maven command like so:
```shell
mvn package
```

## RUN - Drone Demo
There are two ways to run this project.
- 1. Executing the `main` method in the `com.soft.test.DroneDemoApplication.java` class from your IDE.
- 2. Using the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```


## API End Points
- Fetch Drones - GET: [localhost:8080/api/v1/drones](http://localhost:8080/api/v1/drones)
- Register Drones - POST: [localhost:8080/api/v1/drones/save](http://localhost:8080/api/v1/drones/save)
#### **Register Drone Payload format**
```shell
{
  "serialNo": "1002",
  "model": "Middleweight",
  "weightLimit": "230",
  "batteryCapacity": 100,
  "state": "IDLE"
}
```
- Load Drone - POST: [localhost:8080/api/v1/drones/load](http://localhost:8080/api/v1/drones/load)
#### **Load Drone Payload format**
```shell
{
  "id": "d21b34d1-2bf6-4cb0-9ff7-f02e20cab734",
  "medicationName": "Med_1"
}
```
- Fetch Drone Loaded Items - GET: [localhost:8080/api/v1/drones/loadedItems/1002](http://localhost:8080/api/v1/drones/loadedItems/1002)
- Check Drone Battery Status - GET: [localhost:8080/api/v1/drones/battery_level/1002](http://localhost:8080/api/v1/drones/battery_level/1002)
- Fetch Available Drones - GET: [localhost:8080/api/v1/drones/available](localhost:8080/api/v1/drones/available)


#### View Drone Battery Check History Log
I used integrated this project with H2 Embedded Database. I could create an API point to fetch these logs and that is okay. However i just chose to view these logs on H2 Browser UI through here: [http://localhost:8080/h2-ui](http://localhost:8080/h2-ui).

Paste this: 
> `jdbc:h2:mem:test_db` in the `JDBC URL` field
    
...and click connect to view Entity records.
