FROM maven:3.9.6-eclipse-temurin-21-alpine as build
COPY /src /psique/src
COPY pom.xml /psique/pom.xml
WORKDIR /psique
RUN mvn clean package

FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /psique
COPY --from=build /psique/target/*.jar /posdata-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/posdata-api.jar"]

