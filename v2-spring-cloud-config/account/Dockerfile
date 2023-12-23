#Start with a base image containing Java Runtime
FROM openjdk:17-jdk-slim

MAINTAINER gbhardwaj

COPY target/account-0.0.1-SNAPSHOT.jar account-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","account-0.0.1-SNAPSHOT.jar"]