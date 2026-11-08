FROM maven:3.9.16-eclipse-temurin-25-alpine as build

WORKDIR /workspace

COPY . .

RUN mvn -B --projects monitor-service --also-make clean package -DskipTests

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /workspace/monitor-service/target/*.jar monitor.jar

ENTRYPOINT ["java", "-jar", "monitor.jar"]


