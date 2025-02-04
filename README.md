# Bono Backend Setup

## Getting Started

Navigate into the project directory:

```bash
cd bono-challenge
```

Install (download) dependencies:

```bash
mvn clean install
```

This will download all necessary libraries and build the project.

Run the application:

```bash
mvn spring-boot:run
```

Once the server starts, it should be available at http://localhost:8080/.

## Project Structure

- **src/main/java**: Contains your Java source code, separated by packages (e.g., controller, service, repository, entity, dto).
- **src/main/resources**: Houses application configuration files (e.g., application.properties or application.yml), along with data.sql if you want to seed a database.
- **pom.xml**: Maven configuration file listing project dependencies and build settings.

## CRUD Operations for Rules

This backend provides endpoints at `/api/rules` to manage Rule objects:

- **List Rules**
  - GET `/api/rules`
  - Returns a list of all rules.

- **Get a Single Rule**
  - GET `/api/rules/{id}`
  - Returns the rule with the specified id.

- **Create a Rule**
  - POST `/api/rules`
  - Expects a JSON body defining the rule data.

- **Update a Rule**
  - PUT `/api/rules/{id}`
  - Expects a JSON body with updated rule data.

- **Delete a Rule**
  - DELETE `/api/rules/{id}`
  - Removes the specified rule from the system.

## Build for Production

To generate a packaged .jar file for production deployment:

```bash
mvn clean package
```

The resulting .jar will be available in the `target/` directory. You can then run it with:

```bash
java -jar target/my-spring-boot-backend-1.0.0.jar
```

## Troubleshooting

- **Port conflicts**: If you already have a service running on port 8080, change the port in `application.properties` (for example, `server.port=9090`) or stop the other service.
- **Database issues**: If you are using an H2 in-memory database, check http://localhost:8080/h2-console (if enabled) to see tables and data. Make sure `spring.h2.console.enabled=true` in your properties.
- **CORS errors**: If you're accessing this API from a different origin (for example, a Vue frontend on another port), configure CORS in your Spring Boot app or use a proxy.
```
