# Order Management System

Order management system backend using Spring Boot and Postgres.

## ERD

```
+---------------------------------------+                   +--------------------------------+                   
| customer                              |                   | product                        |                   
+-------------------+--------------+----+                   +------------+--------------+----+                   
| id                | UUID         | PK |                   | id         | UUID         | PK |                   
| email             | VARCHAR(255) |    |                   | end_date   | TIMESTAMP    |    |                   
| full_name         | VARCHAR(255) |    |                   | name       | VARCHAR(255) |    |                   
| phone_number      | VARCHAR(255) |    |                   | sku_code   | VARCHAR(255) |    |                   
| registration_code | VARCHAR(255) |    |                   | start_date | TIMESTAMP    |    |                   
+-------------------+--------------+----+                   | unit_price | REAL         |    |                   
                                   *                        +------------+--------------+----+                   
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
                                   *                                                    *                        
+----------------------------------------------+            +---------------------------------------------------+
| orderr                                       |            | order_line                                        |
+----------------+------------+----------------+            +--------------------+--------------+---------------+
| id             | UUID       | PK             |************| id                 | UUID         | PK            |
| customer_id    | UUID?      | FK("customer") |            | order_id           | UUID         | FK("orderr")  |
| submitted_date | TIMESTAMP? |                |            | product_id         | UUID         | FK("product") |
+----------------+------------+----------------+            | productsku         | VARCHAR(255) |               |
                                                            | product_unit_price | REAL         |               |
                                                            | quantity           | INTEGER      |               |
                                                            +--------------------+--------------+---------------+
```

## Installation

### Running
Run application:
```bash
docker-compose up -d # Start host local Postgres container, run in docker directory - cd docker
./gradlew bootRun # Starts the application on a local network. 
```
Application should now be running at http://localhost:8080


### Build
To build application without running:
```bash
./gradlew build # Builds the application
```

### API documentation:
Springdoc - Swagger is available at http://localhost:8080/swagger-ui/index.html 

### Tests:

Run tests:
```bash
./gradlew clean test --info
```
Report will be generated at `./build/reports/tests/test/index.html`

