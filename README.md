# Spring Boot PostgreSQL CRUD Application

This is a RESTful CRUD (Create, Read, Update, Delete) application built with Spring Boot that connects to a PostgreSQL database. The application manages employee data and demonstrates best practices for building Spring Boot applications with JPA/Hibernate and PostgreSQL.

## Prerequisites

- Java JDK 17 or higher
- Maven
- PostgreSQL installed locally
- pgAdmin 4 (for database management)

## Database Setup

1. Install PostgreSQL and pgAdmin 4 if you haven't already
2. Open pgAdmin 4
3. Create a new database called `employee_db`:
    - Right-click on "Databases" in the browser panel
    - Select "Create > Database"
    - Enter "employee_db" as the name
    - Click "Save"

## Application Configuration

The application is configured to connect to a local PostgreSQL database. You can modify the connection details in the `application.properties` file:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_db
spring.datasource.username=postgres
spring.datasource.password=postgres
```

Replace `postgres` with your PostgreSQL username and password if they are different.

## Building and Running the Application

1. Clone this repository
2. Navigate to the project root directory
3. Build the project:
   ```
   mvn clean install
   ```
4. Run the application:
   ```
   mvn spring-boot:run
   ```

The application will start on port 8080.

## API Endpoints

| HTTP Method | Endpoint | Description |
|-------------|----------|-------------|
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| GET | /api/employees/email/{email} | Get employee by email |
| GET | /api/employees/department/{department} | Get employees by department |
| GET | /api/employees/salary/{salary} | Get employees with salary greater than specified value |
| GET | /api/employees/search?firstName={firstName} | Search employees by first name |
| POST | /api/employees | Create a new employee |
| PUT | /api/employees/{id} | Update an employee |
| DELETE | /api/employees/{id} | Delete an employee |

## Example Request (Create Employee)

```bash
curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@example.com",
        "phoneNumber": "555-123-4567",
        "salary": 75000.00,
        "department": "Engineering"
      }'
```

## Project Structure

```
project-structure/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── postgrescrud/
│   │   │               ├── PostgresCrudApplication.java
│   │   │               ├── controller/
│   │   │               │   └── EmployeeController.java
│   │   │               ├── model/
│   │   │               │   └── Employee.java
│   │   │               ├── repository/
│   │   │               │   └── EmployeeRepository.java
│   │   │               └── service/
│   │   │                   ├── EmployeeService.java
│   │   │                   └── EmployeeServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml
└── README.md
```