FROM maven:3.8.8 as builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn package

FROM amazoncorretto:17-alpine
WORKDIR /projects-backend
COPY --from=builder /app/target/*.jar projects-backend.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "projects-backend.jar"]