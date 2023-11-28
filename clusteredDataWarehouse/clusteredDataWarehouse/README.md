# Clustered Data Warehouse

This is a Spring Boot application for a clustered data warehouse system.

## Requirements

To run the application, ensure you have the following prerequisites installed:

- Java 17
- Maven
- Docker
- Docker Compose

## How to Run

1. Build the application using Maven:
   ```bash
   mvn clean install
2. Run the application
3. Access the APIs using tools like Postman.

## System Components

1. mysql
2. GitHub

## Code Structure and Implementation Details

- Project Initialization

The project is initialized using the Spring Initializer to set up Maven, specify the programming language, version, and add necessary dependencies.


1. FxDeal Class: A class named FxDeal is created to encapsulate the data to be added to the database. The table in the database is named after this class.
   ![Local Image](photos/c1.png)

2. Controller: The code begins with a controller where you can request insert and findAll APIs. For findAll, it retrieves the list of data from the database, and for insertion, it adds data to the database.
   ![Local Image](photos/c2.png)
   ![Local Image](photos/c3.png)
   ![Local Image](photos/c4.png)

3. Data Validation Steps:
   ![Local Image](photos/c5.png)
- First step: Validation for missing inputs to ensure correct input.
   ![Local Image](photos/c6.png)
- Second step: Validation for duplication.
- Third step: Validation for ISO code.
   ![Local Image](photos/c7.png)

4. Automatic Data Addition:

- The system automatically adds certain data such as time and ID during the processing.

5. Logging and Error Handling:

- Comprehensive logging is implemented to track system activities.
- Robust error handling is in place for better understanding and debugging.

6. Unit Testing:

- Unit tests are incorporated to validate the functionality of the code.

7. Docker Integration:

- Docker is integrated into the project for containerization.

## Run Simulation

In this section, the code and its functionality are explained, followed by a real example.


1. Database Connection:
- A local database is created and connected to the project.

2. Maven clean install
   ![Local Image](photos/0.png)

3. Run the project
   ![Local Image](photos/1.png)

4. call insert API
   ![Local Image](photos/2.png)
   ![Local Image](photos/3.png)

5. test some cases
- missing field
  ![Local Image](photos/4.png)
  ![Local Image](photos/5.png)

- duplicated ID
  ![Local Image](photos/6.png)
  ![Local Image](photos/7.png)

- Wrong ISO code
  ![Local Image](photos/8.png)
  ![Local Image](photos/9.png)

6. Insert new Data
   ![Local Image](photos/10.png)

7. Show the database in MySQL
   ![Local Image](photos/11.png)

8. call findAll API
   ![Local Image](photos/12.png)

9. Testing:

- Unit testing for controller
  ![Local Image](photos/13.png)

- Unit testing for DealServes
  ![Local Image](photos/14.png)

  
I hope everything is clear in the project