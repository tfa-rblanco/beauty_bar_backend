# BeautyBar Management System

A professional Spring Boot application for managing beauty bar operations.

## Features

- Customer management with CRUD operations
- RESTful API design
- Input validation and error handling
- Database integration with JPA/Hibernate
- Professional project structure following Spring Boot best practices

## Technology Stack

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Data JPA**
- **Spring Security** with OAuth2
- **Keycloak** (authentication/authorization)
- **H2 Database** (development)
- **Maven** (build tool)
- **Bean Validation** (input validation)

## Project Structure

```
src/main/java/com/beautybar/
├── BeautyBarApplication.java          # Main application class
├── config/                            # Configuration classes
│   └── WebConfig.java
├── controller/                        # REST controllers
│   ├── CustomerController.java
│   └── HealthController.java
├── dto/                              # Data Transfer Objects
│   └── CustomerDto.java
├── exception/                        # Exception handling
│   ├── GlobalExceptionHandler.java
│   └── ResourceNotFoundException.java
├── model/                           # JPA entities
│   └── Customer.java
├── repository/                      # Data access layer
│   └── CustomerRepository.java
└── service/                        # Business logic layer
    └── CustomerService.java
```

## API Endpoints

### Customer Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customers` | Get all customers |
| GET | `/api/customers/{id}` | Get customer by ID |
| POST | `/api/customers` | Create new customer |
| PUT | `/api/customers/{id}` | Update customer |
| DELETE | `/api/customers/{id}` | Delete customer |
| GET | `/api/customers/search?name={name}` | Search customers by name |

### User Management

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/users` | Create new user (Admin only) |
| GET | `/api/users` | Get all users (Admin only) |
| GET | `/api/users/{userId}` | Get user by ID (Admin only) |
| GET | `/api/users/search?username={username}` | Search users by username (Admin only) |
| POST | `/api/users/{userId}/reset-password` | Trigger password reset email (Admin only) |

### Authentication

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/auth/user-info` | Get current user information and roles |

### Health Check

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/health` | Application health status |

## Running the Application

### Prerequisites
- Java 21
- Maven 3.6+
- Docker (for Keycloak)

### Start Keycloak
```bash
docker-compose up -d
```

### Configure Keycloak
See [KEYCLOAK_SETUP.md](KEYCLOAK_SETUP.md) for detailed setup instructions.

### Development Mode
```bash
./mvnw spring-boot:run
```

### Production Mode
```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

## Configuration

### Development
- Server runs on port 8080
- Context path: `/beautybar`
- H2 in-memory database
- H2 Console available at: `http://localhost:8080/beautybar/h2-console`

### Production
- Configure PostgreSQL database
- Set environment variables: `DB_USERNAME`, `DB_PASSWORD`
- Logging to file enabled

## Sample API Usage

### Get JWT Token (Admin)
```bash
curl -X POST http://localhost:8180/realms/beaty-bar-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=beaty-bar-client-admin" \
  -d "client_secret=ZPzvufTbOqS1w4dtBGP0KEzFM0KWMKIx" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

### Get JWT Token (User)
```bash
curl -X POST http://localhost:8180/realms/beaty-bar-realm/protocol/openid-connect/token \
  -H "Content-Type: application/x-www-form-urlencoded" \
  -d "grant_type=password" \
  -d "client_id=beaty-bar-client-users" \
  -d "client_secret=oCMwn2FXE6VcC7WKXxaeNeWm3IECPZ2d" \
  -d "username=YOUR_USERNAME" \
  -d "password=YOUR_PASSWORD"
```

### Create Customer (Admin/Manager only)
```bash
curl -X POST http://localhost:8080/beautybar/api/customers \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "phone": "1234567890"
  }'
```

### Get All Customers (Authenticated users)
```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/beautybar/api/customers
```

### Get User Info
```bash
curl -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  http://localhost:8080/beautybar/api/auth/user-info
```

### Create User (Admin only)
```bash
curl -X POST http://localhost:8080/beautybar/api/users \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_ADMIN_JWT_TOKEN" \
  -d '{
    "username": "testuser1",
    "email": "testuser1@example.com",
    "firstName": "Test",
    "lastName": "User",
    "password": "Test@123"
  }'
```

### Get All Users (Admin only)
```bash
curl -H "Authorization: Bearer YOUR_ADMIN_JWT_TOKEN" \
  http://localhost:8080/beautybar/api/users
```

## Best Practices Implemented

1. **Layered Architecture**: Clear separation of concerns with controller, service, repository layers
2. **DTO Pattern**: Separate DTOs for data transfer to avoid exposing internal entities
3. **Exception Handling**: Global exception handler with proper HTTP status codes
4. **Validation**: Input validation using Bean Validation annotations
5. **Transaction Management**: Proper transaction boundaries in service layer
6. **Configuration Management**: Environment-specific configurations
7. **RESTful Design**: Following REST principles with proper HTTP methods
8. **Database Design**: JPA entities with proper relationships and constraints
9. **Logging**: Structured logging configuration
10. **Health Monitoring**: Health check endpoints for monitoring