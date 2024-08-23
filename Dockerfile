FROM ubuntu:latest
LABEL authors="defix"

FROM openjdk:22-slim

WORKDIR /app

COPY /target/FavordayApp-0.0.1-SNAPSHOT.jar /app/FavordayApp-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "FavordayApp-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]