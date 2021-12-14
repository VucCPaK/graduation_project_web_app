FROM openjdk:11
COPY ../build/libs/backend-0.0.1-SNAPSHOT.jar /app/backend.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "backend.jar"]