# ================================
# Stage 1: Build the application
# ================================
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy pom.xml first and download dependencies (cache optimization)
COPY pom.xml .
RUN mvn dependency:go-offline

# Now copy source and build
COPY src ./src
RUN mvn clean package -DskipTests

# ================================
# Stage 2: Run the application
# ================================
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the final fat jar (exclude .original)
COPY --from=build /app/target/mycart-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
