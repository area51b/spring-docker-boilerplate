# Quick Boilerplate for Beginners

    Java + Spring Boot + Mybatis + Docker + OpenAPI 3.0 + Swagger UI
    Maven + OpenJDK 11

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

* Maven
* Docker Desktop

### Installing

##### First time & if there is additional POM dependencies, run below command to add library to m2 folder
    mvn -Dmaven.repo.local=./m2 clean package

##### Docker Image Build
    docker image build -t boilerplate .

##### Docker Run
    docker container run --rm --name boilerplate -p 7080:7080 -d boilerplate

##### Docker Log
    docker logs --follow boilerplate

##### Docker Stop
    docker stop boilerplate

## Swagger UI

##### Swagger API Local Editor
    docker pull swaggerapi/swagger-editor
    docker run -d -p 80:8080 swaggerapi/swagger-editor

This will run Swagger Editor (in detached mode) on port 80 on your machine, so you can open it by navigating to http://localhost in your browser.

##### API Testing with Swagger UI - Localhost
    http://localhost:7080/doc/swagger-ui.html

## Built With

##### [Maven](https://maven.apache.org/) - Dependency Management

## Contributing

NIL

## Versioning

NIL

## Authors

**Balakumar Manickam** - *Initial work*

## License

This project is licensed under the Apache License, Version 2.0 - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments

https://github.com/aws-samples/kubernetes-for-java-developers