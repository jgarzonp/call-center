# Java Backend Test - Call Center

This is a Java Project with Spring Boot 2 as a base structure.

The Project is build with Apache Maven 3 using the Spring Boot parent project to define the Spring Framework dependencies.

## Covered Points

- Should exists a **Dispatcher** class, it should manage the calls and it should have a method named **_dispatchCall_** to assign the available employees
- The **_dispatchCall_** method can be invoked by many threads.
- The Dispatcher class can have the skill to manage 10 call concurrently.
- Each call could have a duration time between 5 and 10 seconds.
- It must exist a unit test to send 10 calls concurrently.

## Extra Covered Points

- Give any solution when a call arrive and there is no available employees
- Give any solution when there are more than 10 concurrent calls

The solution to cover these 2 points was given, setting a waiting time for the new calls, and the available employees were defined by a recusive ProrityBlockingQueue, the employee is assigned to a call, and it is removed from the queue, but when the employee es free, it is returned to the queue, the queue with priority orders the object depending by the role defined into the object, thus the assignation conserves the order by OPERATOR, SUPERVISOR AND MANAGER.

- Add Other Tests

It added an extra unit test to manage the behavior for first 2 extra points.

- Add Code Documentation

All the source code is documented by JavaDoc

## Run The Project

To run the project you should execute the next command on terminal:

```
mvn
``` 
or
```
mvn spring-boot:run
``` 

The project is open to set a new configuration to manage more agents, to do that, you should change the parameters in the _`application.yml`_ file.