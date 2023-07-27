# JashterAPI
## Requirements
- Spring Boot 3.1.2
- Java8
- application.properties is below
  ```
  spring.datasource.url=jdbc:sqlserver://[HOST];databaseName=[DB_NAME];trustServerCertificate=true;
  spring.datasource.username=[DB_USERNAME]
  spring.datasource.password=[DB_PASSWORD]
  spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
  security.key=[JWT_SECRET]
  ```
