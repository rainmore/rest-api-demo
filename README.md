# REST API

REST API is a web base project to provide JOSN REST API to facilitate the communication between the internal services and 3rd parties (e.g. mobile application or web UI).
 

## Developer User Guide

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




