# Shop (graduation project)

## Introduction

Shop is my graduation project. And also was developed for teaching new technologies and improving skills in known. The whole project consist from different microservices. Interaction between microservices going via gateway as separate microservice. Product that visible on the site and their description keep in PostgreSQL and their pictures keep in MongoDB. Application can interact with user data via 'keycloak' that connected to one of the microservices that keep users in Active Directory (embedded) and exchanged data via LDAP.


## Getting started
Before we start we need several servers such as PostgreSQL (13.5), Keycloak (16.0.1), MongoDB (5.0.5).

## Run microservices on localhost:
* Starts servers PostgreSQL, Keycloak, MongoDB.
