# DummyBank Banking Application

A Spring Boot-based banking application with multiple user roles (Admin, Banker, Client) and basic account and transaction management.

## 1. Prerequisites
- Java 21
- Maven 3+
- MySQL (or compatible database)
- IDE (IntelliJ, Eclipse, VS Code)
- Postman (for API testing)
 
## 2. Project Structure

com.DummyBank.BankingApplication  
├─ config  
├─ controller  
├─ dto  
├─ entity  
├─ mapper  
├─ repository  
├─ security  
├─ service  
└─ BankingApplication.java  
 
## 3. Database Setup
1. Start your MySQL server.
2. Create the database:
```sql
CREATE DATABASE dummy_bank;
```
Create a dedicated user:

```sql
CREATE USER 'bank_user'@'localhost' IDENTIFIED BY 'password123';
```
```sql
GRANT ALL PRIVILEGES ON dummy_bank.* TO 'bank_user'@'localhost';
FLUSH PRIVILEGES;
```
```sql
ALTER USER 'bankuser'@'localhost' IDENTIFIED WITH mysql_native_password BY 'bankpass';
FLUSH PRIVILEGES;
```  
## 4. Configuration 
Configure src/main/resources/application.properties:

### MySQL Connection 
spring.datasource.url=jdbc:mysql://localhost:3306/banking_app?useSSL=false&serverTimezone=UTC
spring.datasource.username=bankuser
spring.datasource.password=bankpass
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

### Hibernate / JPA 
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

### Run data.sql at startup 
spring.sql.init.mode=always

### Server Port 
server.port=8081

## 5. Build and Run 
Open the project in your IDE.

Build the project:
```bash
mvn clean install
```
Run the application:
```bash
mvn spring-boot:run
```
Access the app at: http://localhost:8081

## 6. Testing 
Use a Postman collection found in this app resources to test endpoints.
