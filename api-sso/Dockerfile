FROM maven:3.8.8 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM amazoncorretto:17-alpine
WORKDIR /sso
COPY --from=builder /app/target/*.jar sso.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "sso.jar"]