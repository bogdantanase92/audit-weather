# Audit weather

## Description
This application has the purpose to audit weather for a particular city.
The system consumes the data from a messaging system in order to create a history.
The application could help for looking at the past data and make forecasts.

## Technology stack
- Java 11
- Spring Boot
- MySQL (deployed in AWS)
- SQS (deployed in AWS)
- H2
- Liquibase

## Deployment
### Docker commands
```
mvn spring-boot:build-image
```
```
docker run -it -p9099:8080 audit-weather:0.0.1
```