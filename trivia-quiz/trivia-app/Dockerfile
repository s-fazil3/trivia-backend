FROM maven:3.8.5-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build /app/target/*.jar deploy.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "deploy.jar"]
