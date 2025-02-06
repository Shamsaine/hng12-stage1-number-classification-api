FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the project's pom.xml and source code to the container
COPY pom.xml /app
COPY src /app/src

# Install Maven
RUN apt-get update && apt-get install -y maven

# Package the application
RUN mvn clean package

# Run the application
CMD ["java", "-jar", "target/numbers_classifier-0.0.1-SNAPSHOT.jar"]