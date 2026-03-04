# Use Eclipse Temurin (The standard production build for Java 25)
FROM eclipse-temurin:25-jdk

WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]