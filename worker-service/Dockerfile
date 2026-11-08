FROM maven:3.9.16-eclipse-temurin-25-alpine AS build

WORKDIR /workspace

COPY . .

RUN mvn --batch-mode --projects worker-service --also-make clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /workspace/worker-service/target/*.jar worker.jar

ENTRYPOINT ["java", "-jar", "worker.jar"]

