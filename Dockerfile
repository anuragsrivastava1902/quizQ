# Use an official JDK runtime as a parent image
FROM eclipse-temurin:17-jdk

# Set the working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml first (for caching)
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Download dependencies (for faster builds)
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Now copy the rest of the source code
COPY src src

# Build the application (skip tests to save time)
RUN ./mvnw clean package -DskipTests

# Expose the port (Render uses PORT env var)
EXPOSE $PORT

# Run the jar file (use an explicit jar name)
CMD ["sh", "-c", "java -jar target/*.jar"]
