**Assignment_3 Object Oriented Design $ Program**

Design a basic Online Store using RMI registry and having basic operations.This code's skeleton is divided into 3 basic
packages namely client, server and common.

### Assignment_2/

    server/
        |   ItemManagerImpl.java
        |   PersonFactoryImpl.java
	|   ServerApp.java
    client/
        |   ClientApp.java
	|   UserActions.java
	|   CustomerActions.java
	|   AdminActions.java
	|   MenuOptions.java
    common/
        |   Person.java
        |   PersonFactory.java
	|   Item.java
	|   ItemManager.java
	|   Customer.java
	|   Admin.java
	|   ItemInventory.java
	|   ShoppingCart.java
    Makefile
    MyAssign.jar
    README.md
    
## Requirements
Java Runtime Environment (JRE) and Java Development Kit (JDK) version 8 or higher.
Access to two separate servers for the client and server if running in a distributed manner.

### Usage
1. Compile all java files
   - Use this command to create .class files
   - Command:
	> 'make compile'

2. Server
##### To run the server part of the application:
Ensure that the RMI registry is running before trying to connect with the client
* Navigate to the project directory: > cd Assignment_2 
* Run the server: > make run_server

#### Client
##### To run the client part of the application:
* Navigate to the project directory: > cd Assignment_1
* Run the cliennt: > make run_client

### Cleaning Up
##### To clean up compiled .class files:
> make clean