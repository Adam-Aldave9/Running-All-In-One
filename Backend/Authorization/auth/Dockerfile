FROM maven:3.8.3-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean install package -DskipTests

FROM openjdk:17-jdk-alpine
COPY --from=build /app/target/auth-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]