# Pet Store Service

A modern Spring Boot microservice implementing a Pet Store API using **[Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture)**
(Ports & Adapters) and **Domain-Driven Design** (DDD) principles.

## 🏗️ Architecture Overview

This application follows **[Hexagonal Architecture](https://alistair.cockburn.us/hexagonal-architecture)** with
**Domain-Driven Design** patterns, ensuring clean separation of concerns and maintainable code structure.

```
┌─────────────────────────────────────────────────────────────┐
│                        Bootstrap                            │
│                   (Configuration & Wiring)                  │
└─────────────────────────────────────────────────────────────┘
           │                                    │
           ▼                                    ▼
┌─────────────────────┐                ┌─────────────────────┐
│   Driving Adapters  │                │   Driven Adapters   │
│    (REST API)       │                │   (JPA Repository)  │
└─────────────────────┘                └─────────────────────┘
           │                                    │
           ▼                                    ▼
┌─────────────────────────────────────────────────────────────┐
│                    Application Layer                        │
│                   (Use Cases & Ports)                       │
└─────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────┐
│                     Domain Layer                            │
│           (Entities, Value Objects, Services)               │
└─────────────────────────────────────────────────────────────┘
```

## Architecture Layers

- **Driving Layer**:
- **[Application Layer](./application/README.md)**: Orchestrates business workflows and coordinates domain objects to fulfill specific use cases.
  This layer defines **Ports** (interfaces) for both inbound operations (driving ports like `CreatePetTagPort`) and
  outbound dependencies (driven ports like `PetTagRepositoryPort`). It contains **Use Cases** that implement business
  workflows by coordinating domain services and entities, **Commands/Queries** following CQS principles for input
  validation and data transfer, and **Application Services** that handle cross-cutting concerns like transactions and
  security. The application layer remains technology-agnostic, focusing on business workflow orchestration while
  delegating domain logic to the domain layer and infrastructure concerns to adapters.
- **[Domain Layer](./domain/README.md)**: The heart of the application containing pure business logic, isolated from external concerns. It
  includes:
  - **Entities**: Business objects with identity like PetTag, Category, and User that have a lifecycle and can change over time while maintaining their identity
  - **Value Objects**: Immutable objects like PetTagId, CategoryName, UserEmail, and UserUsername that represent domain concepts without identity
  - **Domain Services**: Business logic that doesn't naturally belong to a single entity, such as PetTagService and CategoryService that coordinate complex business operations
  - **Repository Interfaces**: Contracts for data persistence like PetTagRepository that define how domain objects are stored and retrieved without coupling to specific technologies
  - **Domain Events**: Business-significant occurrences that can trigger side effects or notify other parts of the system
  - **Aggregates**: Consistency boundaries that group related entities and value objects, ensuring business invariants are maintained
- **Driven Layer**:

## 📋 Features

- **Tag Management**: Create, read, update, and delete pet tags
- **Category Management**: Manage pet categories with descriptions
- **Cursor-based Pagination**: Efficient pagination for large datasets
- **RESTful API**: OpenAPI 3.0.3 compliant REST endpoints
- **Quality Assurance**: Comprehensive testing and static analysis
- **Production Ready**: Health checks, metrics, and monitoring

## 🛠️ Technology Stack

- **Java 21** - Latest LTS version with modern language features
- **Spring Boot 3.5.5** - Enterprise-grade framework
- **Maven** - Multi-module project management
- **JPA/Hibernate** - Object-relational mapping
- **H2/PostgreSQL** - Database support
- **MapStruct** - Type-safe bean mapping
- **Lombok** - Boilerplate code reduction
- **ArchUnit** - Architecture testing
- **SpotBugs** - Static code analysis

## 🏢 Project Structure

```
petstore-service/
├── pom.xml                           # Parent Maven configuration
├── domain/                           # 🎯 Core business logic
│   └── src/main/java/.../domain/
│       ├── entity/                   # Domain entities (PetTag, Category)
│       ├── valueobject/              # Value objects (IDs, values)
│       ├── repository/               # Repository interfaces
│       ├── service/                  # Domain service interfaces
│       └── shared/                   # Shared domain concepts
├── application/                      # 🔄 Use cases and orchestration
│   └── src/main/java/.../application/
│       ├── usecase/                  # Use case implementations
│       ├── service/                  # Domain service implementations
│       ├── port/                     # Port interfaces
│       │   ├── driving/              # Inbound ports
│       │   └── driven/               # Outbound ports
│       └── dto/                      # Data transfer objects
├── driving/                          # 📥 Inbound adapters
│   └── rest-api/                     # REST API implementation
│       ├── contracts/                # OpenAPI specification
│       └── src/main/java/.../rest/
│           ├── controller/           # REST controllers
│           ├── dto/                  # REST DTOs
│           └── mapper/               # DTO mappers
├── driven/                           # 📤 Outbound adapters
│   └── jpa-repository/               # Database persistence
│       └── src/main/java/.../jpa/
│           ├── entity/               # JPA entities
│           ├── repository/           # JPA repositories
│           └── mapper/               # Entity mappers
└── bootstrap/                        # 🚀 Application startup
    └── src/main/java/.../
        ├── PetstoreServiceApplication.java
        └── config/                   # Configuration classes
```

## 🚀 Quick Start

### Prerequisites

- **Java 21** or higher
- **Maven 3.8+**
- **Docker** (optional, for database)

### Running the Application

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd petstore-service
   ```

2. **Build the project**
   ```bash
   ./mvnw clean install
   ```

3. **Run the application**
   ```bash
   ./mvnw spring-boot:run -pl bootstrap
   ```

4. **Access the API**
  - API Base URL: `http://localhost:8080`
  - Health Check: `http://localhost:8080/actuator/health`
  - OpenAPI Docs: `http://localhost:8080/swagger-ui.html`

### Development Mode

```bash
# Run with development profile
./mvnw spring-boot:run -pl bootstrap -Dspring-boot.run.profiles=dev

# Run with debug mode
./mvnw spring-boot:run -pl bootstrap -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
```

## 📚 API Documentation

The service provides a RESTful API for managing pet tags and categories:

### Tag Endpoints

- `GET /tag` - List all tags (paginated)
- `POST /tag` - Create a new tag
- `GET /tag/{tagId}` - Get tag by ID
- `PUT /tag/{tagId}` - Update tag by ID
- `DELETE /tag/{tagId}` - Delete tag by ID

### Category Endpoints

- `GET /category` - List all categories (paginated)
- `POST /category` - Create a new category
- `GET /category/{categoryId}` - Get category by ID
- `PUT /category/{categoryId}` - Update category by ID
- `DELETE /category/{categoryId}` - Delete category by ID

### Example Usage

```bash
# Create a new tag
curl -X POST http://localhost:8080/tag \
  -H "Content-Type: application/json" \
  -d '{"value": "friendly"}'

# Get paginated tags
curl "http://localhost:8080/tag?pageSize=10&q=friend"

# Create a category
curl -X POST http://localhost:8080/category \
  -H "Content-Type: application/json" \
  -d '{"name": "Dogs", "description": "All dog-related pets"}'
```

## 🧪 Testing

### Running Tests

```bash
# Run all tests
./mvnw test

# Run tests for specific module
./mvnw test -pl domain
./mvnw test -pl application

# Run architecture tests
./mvnw test -pl bootstrap -Dtest="HexagonalArchitectureTest"

# Run ErrorProne validation tests
./mvnw test -pl bootstrap -Dtest="*ErrorProne*,*CodeQuality*"
```

### Test Categories

- **Unit Tests**: Domain and application logic testing
- **Integration Tests**: Adapter and end-to-end testing
- **Architecture Tests**: Hexagonal architecture validation
- **Quality Tests**: ErrorProne-style code quality validation

### Coverage Requirements

- **Domain Layer**: 100% line coverage
- **Application Layer**: 95% line coverage
- **Adapter Layer**: 80% line coverage

## 🔍 Code Quality

### Static Analysis

```bash
# Run SpotBugs analysis
./mvnw spotbugs:check

# Run SonarQube analysis
./mvnw sonar:sonar

# Run with ErrorProne profile
./mvnw compile -Perrorprone
```

### Quality Gates

- All tests must pass
- Code coverage thresholds met
- No critical security vulnerabilities
- Architecture tests pass
- SpotBugs analysis clean

## 🏗️ Architecture Principles

### Hexagonal Architecture

1. **Domain Independence**: Core business logic isolated from external concerns
2. **Ports & Adapters**: Clear interfaces between layers
3. **Dependency Inversion**: Dependencies point inward toward the domain
4. **Testability**: Each layer can be tested independently

### Domain-Driven Design

1. **Ubiquitous Language**: Consistent domain terminology
2. **Bounded Contexts**: Clear module boundaries
3. **Value Objects**: Type-safe, immutable domain concepts
4. **Aggregates**: Consistency boundaries within domain
5. **Domain Services**: Business logic coordination

### Dependency Rules

```
Bootstrap → Driving Adapters → Application → Domain
Bootstrap → Driven Adapters → Application → Domain
```

**Never:**
- Domain depends on Application or Adapters
- Application depends on Adapters
- Adapters depend on each other directly

## 🔧 Configuration

### Application Properties

```yaml
# application.yml
spring:
  profiles:
    active: dev
  datasource:
    url: jdbc:h2:mem:petstore
    driver-class-name: org.h2.Driver
  jpa:
    hibernate:
      ddl-auto: create-drop
    show-sql: true

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
```

### Environment Profiles

- **dev**: Development with H2 in-memory database
- **test**: Testing configuration
- **prod**: Production with PostgreSQL

## 📊 Monitoring & Observability

### Health Checks

- Application health: `/actuator/health`
- Database connectivity
- Custom business health indicators

### Metrics

- Business metrics (tags created, categories managed)
- Technical metrics (response times, error rates)
- JVM metrics (memory, garbage collection)

### Logging

- Structured JSON logging
- Correlation IDs for request tracing
- Appropriate log levels for different environments

## 🚀 Deployment

### Docker

```bash
# Build Docker image
docker build -t petstore-service .

# Run with Docker Compose
docker-compose up -d
```

### Production Considerations

- Use PostgreSQL for production database
- Configure proper connection pooling
- Set up monitoring and alerting
- Implement proper secret management
- Use HTTPS for all communications

## 🤝 Development Guidelines

### Adding New Features

1. **Start with Domain**: Define entities, value objects, and repository interfaces
2. **Define Ports**: Create interfaces in the application layer
3. **Implement Use Cases**: Business logic orchestration
4. **Create Adapters**: External system integration
5. **Wire in Bootstrap**: Dependency injection configuration

### Code Standards

- Follow hexagonal architecture principles
- Use Java 21 features appropriately
- Implement proper error handling
- Write comprehensive tests
- Document public APIs

### Git Workflow

- Feature branches from `main`
- Pull request reviews required
- All quality gates must pass
- Squash commits before merge

## 📖 Additional Documentation

- [Architecture Guidelines](.github/copilot-instructions.md)
- [ErrorProne Setup](docs/ERRORPRONE_SETUP.md)
- [Development Guidelines](WARP.md)
- [Code Style Rules](.windsurf/rules/general.md)

## 🐛 Troubleshooting

### Common Issues

1. **Build Failures**
   ```bash
   # Clean and rebuild
   ./mvnw clean install
   
   # Check Java version
   java -version
   ```

2. **Architecture Violations**
   ```bash
   # Run architecture tests
   ./mvnw test -pl bootstrap -Dtest="HexagonalArchitectureTest"
   ```

3. **Database Connection Issues**
  - Check H2 console: `http://localhost:8080/h2-console`
  - Verify database configuration in `application.yml`

## 📄 License

This project is licensed under the Apache License 2.0 - see the [LICENSE](LICENSE) file for details.

## 👥 Contributing

1. Fork the repository
2. Create a feature branch
3. Follow coding standards
4. Write tests for new functionality
5. Ensure all quality gates pass
6. Submit a pull request

---

**Built with ❤️ using Hexagonal Architecture and Domain-Driven Design**
