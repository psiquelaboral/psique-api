FROM maven:3.9.6-eclipse-temurin-21-alpine as build
COPY /src /psiquelaboral/src
COPY pom.xml /psiquelaboral/pom.xml
WORKDIR /psiquelaboral
RUN mvn clean package

FROM eclipse-temurin:21.0.2_13-jre-alpine
WORKDIR /psiquelaboral
COPY --from=build /psiquelaboral/target/*.jar /psiquelaboral-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/psiquelaboral-api.jar"]

