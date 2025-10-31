# Use an official JDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy the project files
COPY . .

# Build the application (if using Maven)
RUN ./mvnw clean package -DskipTests

# Expose port (Render uses PORT env var)
EXPOSE 8080

# Run the jar file
CMD ["java", "-jar", "target/*.jar"]
