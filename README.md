# Employee-Management
A Spring Boot REST API for managing employee records using a layered architecture. The application supports creating, retrieving, updating, and deleting employees, with request validation, DTO-based responses, centralized exception handling, PostgreSQL persistence, and Swagger UI documentation.
Features
- Create, retrieve, update, and delete employee records
- Layered structure: Controller, Service, Repository, Entity, DTO, and Exception Handler
- Request validation using Jakarta Bean Validation
- Request and response DTOs; API responses do not expose JPA entities
- Centralized error responses using @RestControllerAdvice
- Duplicate email checks
- PostgreSQL database integration with Spring Data JPA / Hibernate
- Transaction management in the service layer
- Swagger UI / OpenAPI documentation
Tech Stack
- Java (use the version configured in pom.xml)
- Spring Boot
- Spring Web
- Spring Data JPA / Hibernate
- PostgreSQL
- Jakarta Bean Validation
- Springdoc OpenAPI / Swagger UI
- Maven
Prerequisites
Install the following before running the application:
- Java version compatible with the project configuration in pom.xml
- PostgreSQL
- Maven
- Git
Getting Started
1. Clone the repository
Replace the placeholder with your GitHub repository URL:
git clone https://github.com/Faheema-shaikh/Employee-Management.git)
cd Employee-Management
2. Create or select a PostgreSQL database
This project is currently configured to use the local PostgreSQL database named postgres on port 5432.

