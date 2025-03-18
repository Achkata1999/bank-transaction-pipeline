# Use the official OpenJDK 17 image as a base
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy your compiled JAR file into the container
COPY ./target/pipeline-0.0.1-SNAPSHOT.jar /app/app.jar

# Run the application with remote debugging enabled on port 5005
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=0.0.0.0:5005", "-jar", "/app/app.jar"]
