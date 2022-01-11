# Spring Shop

## Introduction

Shop is my graduation project. And also was developed for teaching new technologies and improving skills in known. The whole project consist from different microservices. Interaction between microservices going via gateway as separate microservice. Product that visible on the site and their description keep in PostgreSQL and their pictures keep in MongoDB. Application can interact with user data via Keycloak that connected to one of the microservices and keep users in Active Directory (embedded in spring) and exchanged data via LDAP.


## Getting started
Before we start we need several servers such as PostgreSQL (13.5), Keycloak (16.0.1), MongoDB (5.0.5).

## Running for localhost:
* Starts servers with default port PostgreSQL, Keycloak, MongoDB.
* For PostgreSQL we use default user.
* For MongoDB we need to create a new user 'mongo' with password 'mongo'.
* For Keycloak first you need to create new realm with name 'my_realm'. Create new client with name 'my_client'.Another configuration ... . 
* ```
* 
* ```
