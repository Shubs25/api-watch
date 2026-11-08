FROM maven:3.9.16-eclipse-temurin-25-alpine AS build

WORKDIR /workspace

COPY . .

RUN mvn --batch-mode --projects analytics-service --also-make clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /workspace/analytics-service/target/*.jar analytics.jar

ENTRYPOINT ["java", "-jar", "analytics.jar"]

