FROM openjdk:11
COPY ../target/gateway-0.0.1-SNAPSHOT.jar /app/gateway.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "gateway.jar"]