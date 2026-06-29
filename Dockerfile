# Stage 1 Build
FROM maven3.9.9-eclipse-temurin-21 AS builder

WORKDIR app

COPY pom.xml .

COPY src .src

RUN mvn clean package -DskipTests

# Stage 2 Run
FROM eclipse-temurin21-jre

WORKDIR app

COPY --from=builder apptarget.jar app.jar

EXPOSE 8080

ENTRYPOINT [java,-jar,app.jar]