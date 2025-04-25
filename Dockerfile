# Use a lightweight OpenJDK image
FROM openjdk:25-ea-4-jdk-oraclelinux9

# Set working directory inside container
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar app.jar

# Expose the default Spring Boot port
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]