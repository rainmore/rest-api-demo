# REST API DEMO

REST API is a web base project to provide JOSN REST API to facilitate the communication between the internal services and 3rd parties (e.g. mobile application or web UI). Custom profile has been defined as account, address and email in this project to demonstrate the JSON REST API.

## Developer Guide

### Prelimitary

The project is developed on top JVM with combine Java 8 code and Scala code. It requires following tools on the developer work station to develop/debug/test application.

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

The project can be accessed to from http://localhost:8080/api/v1/accounts

Run project in development mode
```
./gradlew bootRun
```

Run project as jar
```
./gradlew build

java -jar build/lib/application.jar
```

Run Test
```
./gradlew test
```

## Design

The project contains 5 layers

* Persistent
* Services
* Controllers
* Security 
* Others

### Persistent

Persistent layer is built on top Hibernate 5, Spring Data JPA and QueryDSL. It contains Domains and Repositories.

**Domains**

The database connection is set at `src/main/resources/application.properties`. It can been customised to other type database. H2 memory database has been used for this project to avoid extra dependency.

All domain classes are located in `elements` gradle sub project for following benefits

* the domains can be reused for other project
* the generated QueryDSL Q classes are decoupled from the core codebase

The QueryDSL code are generated automatically in the gradle sub project 

Java Persistent Validation has been applied to the domain to ensure the data persist correctly.

NOTE: All domain classes are written in Java as Spring Data JPA only supports QueryDSL Q* class built from Java.

**Repositories**

Repositories are simplified by Spring Data JPA. All repositories are just interface and all CRUD methods have been implemented through Spring Data JPA.
Customised queries are also support but they won't be covered in this project.

### Services
Services layer contains the business logic. As there are no complicated business logic covered in this project, most services are just proxy to repository.

### Controllers
Controllers provide REST JSON endpoints. It contains endpoints, validation and data conversion. To provide an extensible API, versioning has been introduced.  

Current version is `v1`. There could be 2 solutions when more versions are required. The first one is to introduce `v2` package, duplicate all existing end point from `v1`, and, then, modify logic in `v2`. 

The second solution is to split persistent and service layer from controllers as independent micro services. Service discovery should be introduced to automatically connect the service layer with each controller versions. 

Also, DTOs and data conversion have been introduced to avoid direct accessing domain entities. The domain entity modification won't have side effect to API, vice versa.

**Endpoints**
It is built with Spring MVC framework to handle HTTP request and response. Versioning has been applied to each API endpoint through `RequestMapping`.

**Validation**

Strict validation has been applied to the API access. 

* `@PathVariable` instead of `@RequestParam` is used to specific required API request parameters.
* Spring Web MVC build in type check for the request avoid extra parsing logic 
* `org.springframework.validation.Validator` has been bound to the controller to evaluate each POST/PUT request data.

**Data Conversion**

Data Conversion has been introduced to convert between domain entity and DTO. An independent converter can be introduced to hold advanced logic. At the moment, the conversion logic is simplified in each DTO.

**Cache**

Cache should be introduced to avoid heavy request to the service layer. E-Tag, Last-Modified and Cache-Control headers can be used to specific in the response header. The project has not implemented any caching logic at the moment.

### Security
The project is designed as a pure OAuth2 Resource server as such there is no need of an authentication manager. The authentication manager can be implemented as an independent micro server. The authorised application, eg, mobile application, can access the api through OAuth resources token. While, a web application can access the api through access token. 

At the moment, strict access has been commented in `ResourceServerConfig.configure`. 

**_TODO_**

* `ResourceServerConfig.configure` requires further improvement 
* `UserDetails` a customised version of `UserDetails` should be introduced to provide more logic
* `GrantedAuthority` should be applied to provided role and permission check to the API

### Swagger
Swagger module has been built in the project. It can be accessed from

* http://localhost:8080/v2/api-docs
* http://localhost:8080/swagger-ui.html

**_TODO_**

* Swagger has been configured with basic settings. 
* Security access to Swagger can be configured separated from API
* Swagger can be relocated to an independent micro service  

### Others

#### Test

Testing infrastructure has been introduced in the project. However, during the limited time, no any tests are implemented 

**_TODO_**

To implement unit tests and integration tests

#### Build 

**_TBD_**

#### I18N 

**_TBD_**



