# Spring Shop

## Introduction

Shop is my graduation project. And also was developed for teaching new technologies and improving skills in known. The whole project consist from different microservices. Interaction between microservices going via gateway. Product (entities) and their description keep in PostgreSQL and their pictures keep in MongoDB. Application can interact with user data via Keycloak that connected to one of the microservices and keep users in Active Directory (embedded in spring) and exchanged data via LDAP.


## Getting started
Before we start we need several servers such as PostgreSQL (13.5), Keycloak (16.0.1), MongoDB (5.0.5).

## Running for localhost:
* Starts servers with default port PostgreSQL, Keycloak, MongoDB.
* For PostgreSQL we use default user. Create database "project".
* For MongoDB we need to create a new user 'mongo' with password 'mongo'. Create database "pictures".
* For Keycloak download this archive [realm-export.zip](https://github.com/VucCPaK/graduation_project/files/7854396/realm-export.zip) then log into keycloak Admin account and import file.  
* ``` git clone https://github.com/vuccpak/graduation_project.git ```
* Run Frontend:
```
cd graduation_project\frontend
.\gradlew build
java -jar .\build\libs\frontend-0.0.1-SNAPSHOT.jar --spring.profile.active=localhost
```
* Run Backend:
```
cd graduation_project\backend
.\gradlew build
java -jar .\build\libs\backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=localhost 
```
* Run Embedded-ldap:
```
cd graduation_project\embedded-ldap
.\mvnw package
java -jar .\target\embedded-ldap-0.0.1-SNAPSHOT.jar --spring.profiles.active=localhost
```
* Run Gateway:
```
cd graduation_project\gateway
.\mvnw package
java -jar .\target\gateway-0.0.1-SNAPSHOT.jar --spring.profiles.active=localhost      
```
* Run Pictures:
```
cd graduation_project\pictures
.\mvnw package
java -jar .\target\pictures-0.0.1-SNAPSHOT.jar --spring.profiles.active=localhost      
```

## Note
Some features not completed and need revisions: docker, css (switch whole project on bootstrap).





