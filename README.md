# product-catalog

The Product Catalog microservice is a Java Spring Boot application that provides an API to manage
and retrieve information about products in an e-commerce system. It serves as a central repository
for product data and allows clients to query and retrieve product details.

## Features

- Scalable design allows easy addition of new types of components.
- Input validation ensures stability and correctness of the application.
- Custom exception handling improves the clarity of error messages.
- Separate application.yml for different environments (dev, production).

## API Documentation

The application exposes a RESTful API with the following endpoint:

### GET /prices

Endpoint to retrieve prices from the product catalog.

``` bash
curl --location 'http://localhost:8080/prices?applicationDate=2020-06-15T16:10:00&productId=35455&brandId=1'
```

#### Request Parameters

- applicationDate (String, required): The application date in the format 'yyyy-MM-ddTHH:mm:ss'.
- productId (Long, required): The ID of the product.
- brandId (Long, required): The ID of the brand.

#### Response

The API returns a JSON response with the price information.
e.g: 
```json
{
"productId": 35455,
"brandId": 1,
"priceList": 1,
"startDate": "2020-06-14T00:00:00",
"endDate": "2020-12-31T23:59:59",
"price": 35.50
}
```

## Testing

We employ two strategies for unit testing:

### 1. Unit Testing with SpringBootTest and MockMvc

We use SpringBootTest to test the overall application context and ensure that the custom exception
handling and input validation work as expected. This allows us to test the application in a
simulated environment.

### 2. Unit Testing with JUnit and Mockito

For unit testing individual components and services, we use standard unit testing frameworks such as
JUnit and Mockito. This allows us to isolate and test specific parts of the application.

And We employ one strategy for unit testing:

### System tests with bash script
Run the script by executing the command: 
```bash
./system-test.sh
```
Then You will get the results from command line, this can be included as part of CI/CD pipeline:
```
Start System Tests
Test 0 passed: Response {"productId":35455,"brandId":1,"priceList":1,"startDate":"2020-06-14T00:00:00","endDate":"2020-12-31T23:59:59","price":35.5} matches expected result for parameter: http://localhost:8080/prices?applicationDate=2020-06-14T00:00:00&productId=35455&brandId=1
Test 1 passed: Response {"productId":35455,"brandId":1,"priceList":2,"startDate":"2020-06-14T15:00:00","endDate":"2020-06-14T18:30:00","price":25.45} matches expected result for parameter: http://localhost:8080/prices?applicationDate=2020-06-14T15:20:00&productId=35455&brandId=1
Test 2 passed: Response {"productId":35455,"brandId":1,"priceList":3,"startDate":"2020-06-15T00:00:00","endDate":"2020-06-15T11:00:00","price":30.5} matches expected result for parameter: http://localhost:8080/prices?applicationDate=2020-06-15T00:10:00&productId=35455&brandId=1
Test 3 passed: Response {"productId":35455,"brandId":1,"priceList":4,"startDate":"2020-06-15T16:00:00","endDate":"2020-12-31T23:59:59","price":38.95} matches expected result for parameter: http://localhost:8080/prices?applicationDate=2020-06-15T16:10:00&productId=35455&brandId=1
End System Tests
```




## Prerequisites

- Java 11
- Maven

## Getting Started

1. Clone the repository.
2. Build the project using Maven: `mvn clean install`
3. Run the application: `mvn spring-boot:run`
4. To run the application on Linux or macOS server, use the following command: 
```bash 
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```
5. To run the application on Windows server, use the following
   command: 
```shell
mvnw.cmd spring-boot:run -Dspring-boot.run.profiles=dev
```

## Configuration

The application uses separate configuration files for different environments:

- `application-dev.yml` for the development environment.
- `application-prod.yml` for the production environment.

You can specify the active profile by setting the `SPRING_PROFILES_ACTIVE` environment variable.

## Database

By default, the application uses the dev profile, which loads an embedded H2 database. However, if
you want to use the prod profile, the application requires a MySQL 8 instance. You can set up a
MySQL instance using Docker by running the following command:
`docker-compose up -d`

This command will start a Docker container with MySQL 8 up and running, allowing the application to connect to it. Please make sure you have Docker installed before running this command.
## Database Migration

To add a new migration script to Flyway, follow these steps:

Create a new SQL migration script in the src/main/resources/db/migration directory. Use the
following naming convention: V<VERSION>__<DESCRIPTION>.sql. Replace <VERSION> with a unique version
number (e.g., 1.0.0, 2.1.0, etc.), and <DESCRIPTION> with a brief description of the migration.

Write your SQL migration script in the newly created file. Ensure that the script contains the
necessary DDL or DML statements to update the database schema or data.

Build and run the application using Maven: `mvn clean install`
Flyway will automatically detect the new migration script and apply it to the database during
application startup.

## Contributing

Contributions are welcome! If you have any suggestions or find any issues, please create a new issue
or submit a pull request.

## License

This project is licensed under the Apache License 2.0.



