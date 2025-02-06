# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files from the subdirectory
COPY numbers_classifier/pom.xml /app
COPY numbers_classifier/src /app/src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Build the application
RUN mvn clean package -DskipTests

# Expose the application port
EXPOSE 9090

# Run the Spring Boot application
CMD ["java", "-jar", "target/numbers_classifier-0.0.1-SNAPSHOT.jar"]
