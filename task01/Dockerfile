# syntax=docker/dockerfile:1

FROM bellsoft/liberica-openjdk-alpine-musl:17
RUN apk update && apk add curl
COPY /build/libs/task01*.jar /app/app.jar
EXPOSE 8000
CMD ["java", "-jar", "/app/app.jar"]