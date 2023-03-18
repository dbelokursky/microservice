FROM bellsoft/liberica-openjdk-alpine-musl:17
RUN apk update && apk add curl
COPY /build/libs/task06*.jar /app/app.jar
EXPOSE 8000
CMD ["java", \
"-jar", \
"/app/app.jar", \
"--spring.config.location=/config/application.yml"]