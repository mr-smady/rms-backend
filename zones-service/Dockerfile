FROM eclipse-temurin:17-jdk-alpine
MAINTAINER zones-service
COPY build/libs/zones-service.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]