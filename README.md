# EmployeeDatabase (Emp-Backend)

A small Spring Boot REST API that manages employee records. The project includes JPA repositories, a service layer, and a simple controller exposing CRUD endpoints. It can run with a MySQL datasource (default) or an in-memory H2 database for local development.

## Tech

- Java 17
- Spring Boot 3.4.x
- Spring Data JPA + H2 / MySQL
- Maven (wrapper provided)
- Docker (Dockerfile included)

## Quick start (recommended for development)

- From project root (uses the Maven wrapper):

```powershell
.\mvnw.cmd spring-boot:run
```

This runs the app with the default profile (reads `src/main/resources/application.properties`), which expects a MySQL database. To use the in-memory H2 DB (no local MySQL required):

```powershell
# Run with H2 profile
.\mvnw.cmd "-Dspring-boot.run.arguments=--spring.profiles.active=h2 --server.port=8081" spring-boot:run
```

Or build a jar and run it:

```powershell
.\mvnw.cmd -DskipTests package
# run packaged jar with H2 profile on port 8082
java -jar target/employee-poject-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2 --server.port=8082
```

## Configuration

- Default (MySQL) config: `src/main/resources/application.properties`
  - Uses environment variables when available: `MYSQLHOST`, `MYSQLPORT`, `MYSQLDATABASE`, `MYSQLUSER`, `MYSQLPASSWORD`.
- H2 profile config: `src/main/resources/application-h2.properties` (use `--spring.profiles.active=h2`).

Common env vars used by the project:

```text
MYSQLHOST (default: localhost)
MYSQLPORT (default: 3306)
MYSQLDATABASE (default: employee_db)
MYSQLUSER (default: root)
MYSQLPASSWORD (default: password)
```

## Endpoints

- GET  /employees            — list employees
- GET  /employees/{id}       — get employee by id
- POST /employees            — create employee (JSON body)
- PUT  /employees/{id}       — update employee
- DELETE /employees/{id}    — delete employee

Controller is implemented in `src/main/java/com/springProject/employee_poject/EmpController.java`.

## Docker & Render

- A `Dockerfile` is provided. I replaced an unavailable runtime tag with `eclipse-temurin:17-jdk` to ensure building on Render.
- To build locally:

```powershell
docker build -t emp-backend:latest .
docker run -p 8082:8080 emp-backend:latest
```

- On Render: push your branch (or merge to main); Render will build the image using the `Dockerfile`. Ensure environment variables for MySQL are set in the Render service, or set the service to use the `h2` profile for a quick deploy.

## Troubleshooting

- "Access denied for user 'root'@'localhost'": app used the default MySQL profile. Provide correct `MYSQLUSER`/`MYSQLPASSWORD` or run with `h2` profile.
- "Port 8080 already in use": start on another port with `--server.port=8081` or free the port.
- If Docker build fails due to base image not found, update the `FROM` line in the `Dockerfile` (already set to `eclipse-temurin:17-jdk` in this repo).

## Next steps / Useful commands

```powershell
# Run with H2 (dev)
.\mvnw.cmd "-Dspring-boot.run.arguments=--spring.profiles.active=h2 --server.port=8081" spring-boot:run

# Build jar
.\mvnw.cmd -DskipTests package

# Run jar with profile
java -jar target/employee-poject-0.0.1-SNAPSHOT.jar --spring.profiles.active=h2 --server.port=8082
```

---
If you want, I can: run a quick smoke test against the running server, build and run the Docker image locally, or push a small deploy configuration for Render. Tell me which you'd like next.
