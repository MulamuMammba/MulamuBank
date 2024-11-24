FROM eclipse-temurin:23-jdk-alpine

WORKDIR /app
COPY target/mulamu-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "/app/mulamu-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
