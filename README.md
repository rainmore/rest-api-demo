# REST API

REST API is a web base project to provide JOSN REST API to facilitate the communication between the internal services and 3rd parties (e.g. mobile application or web UI).
 

## Developer Guide

### Prelimitary

The project is developed on top JVM. It requires following tools on the developer work station to develop/debug/test application.

* GIT
* JDK 8
* Gradle 4+

### Initialize Project
 
Clone the project code from github repository
```
git clone git@github.com:rainmore/rest-api-demo.git
```

Go to the project folder
```
cd rest-api-demo
```

Setup `gradle wrapper`
```
gradle wrapper --gradle-version 4.3.1 --distribution-type bin
```

### Run Project 
Run project in development mode
```
./gradlew bootRun
```

Run project as jar
```
./gradlew build

java -jar build/lib/application.jar

```

## Design

The project contains 5 layers

* Persistent
* Services
* Controllers
* Security 
* Others

### Persistent

Persistent layer is built on top Hibernate 5, Spring Data JPA and QueryDSL. It contains

#### Domains
The database connection is set at `src/main/resources/application.properties`. It can been customized to other type database. H2 memory database has been used for this project to avoid extra dependency.

All domain classes are located in `elements` gradle sub project for following benefits

* the entities can be reused for other project
* the generated QueryDSL classes are decoupled from the core codebase

The QueryDSL code are generated automatically in the gradle sub project 

NOTE: All domain classes are written in Java as Spring Data JPA only supports QueryDSL Q* class built from Java.

#### Repositories
Repositories are simplified by Spring Data JPA. All repositories are just interface and all CRUD methods have been implemented through Spring Data JPA.
Customized queries are also support but they won't be covered in this project.

### Services
Services layer contains the business logic. As there are no complicated business logic covered in this project, most services are just proxy to repository.     


### Controllers
Controllers provide REST JSON endpoints. It contains endpoints, validation and data conversion.

#### Endpoints

It is built with Spring MVC framework to handle HTTP request and response.
Also, it contains validation

#### Validation

#### Data Conversion    

### Security

http://localhost:8080/v2/api-docs

http://localhost:8080/swagger-ui.html



