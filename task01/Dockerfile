# syntax=docker/dockerfile:1

FROM openjdk:17-alpine
COPY task01/build/libs/task01*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/app.jar"]