# Stage 1: Build the application using Maven
FROM maven:3.8.6-openjdk-11-slim as builder

WORKDIR /app
COPY . .

# Build the WAR file using Maven
RUN mvn clean package -DskipTests

# Stage 2: Run the WAR file with Tomcat
FROM tomcat:9-jdk11-openjdk-slim

# Copy the WAR file from the builder stage to Tomcat's webapps directory
COPY --from=builder /app/target/app-1.0.war /usr/local/tomcat/webapps/

# Expose port 8080 (Tomcat default)
EXPOSE 8080

# Command to run Tomcat
CMD ["catalina.sh", "run"]



