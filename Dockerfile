# Use an official OpenJDK base image to build the app
FROM eclipse-temurin:21-jdk-alpine as build

# Set the working directory inside the container
WORKDIR /workspace

# Copy the Maven wrapper and pom.xml
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Download all the dependencies (this layer is cached unless the pom.xml changes)
RUN ./mvnw dependency:go-offline

# Copy the source code
COPY src /workspace/src

# Build the app
RUN ./mvnw package -DskipTests

# Now use a JRE image to run the application (lighter image)
FROM eclipse-temurin:21-jre-alpine

# Set the working directory
WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /workspace/target/*-runner.jar app.jar

# Expose the default port for Quarkus applications
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
