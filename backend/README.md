# Smart Expense Splitter — Backend

This backend is a Spring Boot application using Spring Data JPA with MySQL.

## Quickstart
1. Install Java 17 and Maven.
2. Create a MySQL database and user (example):

   ```sql
   CREATE DATABASE expense_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   CREATE USER 'expense_user'@'localhost' IDENTIFIED BY 'your_password';
   GRANT ALL PRIVILEGES ON expense_db.* TO 'expense_user'@'localhost';
   FLUSH PRIVILEGES;
   ```

3. Update `src/main/resources/application-mysql.properties` with your DB credentials and URL.

4. Schema management (Flyway):
   - This project uses Flyway for migrations. I added `V1__init.sql` under `src/main/resources/db/migration/` which creates the initial schema.
   - Hibernate DDL auto is disabled for the `mysql` profile to ensure migrations are authoritative.

   To apply migrations automatically on app startup, simply start the app and Flyway will run the migrations against your MySQL database.

   ### Run with Docker Compose (recommended if you don't have MySQL installed)

   If you don't have MySQL locally, you can start one with Docker Compose (requires Docker):

   ```powershell
   cd "C:\Users\Hastik Pangi\Desktop\smart expense splitter\backend"
   docker-compose up -d
   ```

   This will start a MySQL 8.0 container with:
   - user: root
   - password: change_me
   - database: expense_db

   After the container is up, start the Spring Boot app and it will run Flyway migrations against the container.

5. Build the project:

   ```powershell
   cd "C:\Users\Hastik Pangi\Desktop\smart expense splitter\backend"
   mvn -DskipTests package
   ```

6. Run the app:

   ```powershell
   mvn spring-boot:run
   # or
   java -jar target/smart-expense-splitter-backend-0.0.1-SNAPSHOT.jar
   ```

## Notes
- The project uses Spring Boot 3.x and Java 17.
- If you prefer the database schema to be managed manually, set `spring.jpa.hibernate.ddl-auto=none` and run `schema.sql` yourself.
- I can add sample controllers, services, or DTOs next — tell me which area you'd like me to scaffold.
