spring.application.name=quizQ
server.port=8082

# DataSource Configuration
spring.datasource.url=jdbc:mysql://gondola.proxy.rlwy.net:37538/qz?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
# Replace with your MySQL username
spring.datasource.password=dseDQglzynmwXpBxBRoYqFuYHtBcWjaD
# Replace with your MySQL password

# Hibernate DDL Mode
spring.jpa.hibernate.ddl-auto=update
# Use 'create', 'update', or 'validate' as needed

# Show SQL in the logs
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Enable JPA repositories
spring.data.jpa.repositories.enabled=true

----------------------------------------------------------------


✅ 2. How to Use Environment Variables in Spring Boot

In your application.properties, replace the hardcoded values like this:

spring.application.name=quizQ
server.port=${PORT:8080}

# DataSource Configuration
spring.datasource.url=${DB_URL}
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


The ${PORT:8080} part makes Spring Boot use Render’s assigned port automatically.

🧱 3. On Render: Add Environment Variables

When creating your Web Service on Render, scroll to the Environment section and add:

Key	Value
PORT	8080 (Render may set this automatically)
DB_URL	jdbc:mysql://gondola.proxy.rlwy.net:37538/qz?useSSL=true&serverTimezone=UTC&allowPublicKeyRetrieval=true
DB_USERNAME	root
DB_PASSWORD	dseDQglzynmwXpBxBRoYqFuYHtBcWjaD

(Replace these with your actual Railway credentials — I just copied your example.)

