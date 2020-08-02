# YouDude
An API to play with Spring and Java.

To run it:

Build the YouDude application:  
`mvn clean install -U`

Build the YouDude Admin:
`mvn clean install -U`

Then, from the "youdude" folder, you can run the Docker Compose command:    
`docker-compose -f compose.yml up -d`

Generate authorization token doing HTTP POST request to this endpoint:    
`http://localhost:8081/oauth/token`

Adds this token in your HTTP header requests. 

## Technologies
    - Java 11
    - Spring Boot
    - Spring JPA
    - Spring HATEOAS
    - Spring Cache
    - Spring Actuator
    - Spring Admin
    - Spring Security OAuth2
    - Lombok
    - Flyway
    - PostgreSQL
    - H2 - tests
    - JUnit5
    - Mockito

## Requests example

### User
REST with Page endpoint: `http://localhost:8080/youdude/rest/users`

RESTFul endpoint: `http://localhost:8080/youdude/hateoas/users`

## Actuator metrics
All metrics are exposed via the Spring Boot Admin. Just access the URL:
`http://localhost:8090/`

## Security
The security validation is done via YouDude-Auth service. 
It simulates a third-party service dedicated to generate and validate all security tokens.

All YouDude HTTP requests are validated by doing a request to YouDude-Auth, passing the token as a parameter.
The YouDude-Auth has all logic to validate the token.
