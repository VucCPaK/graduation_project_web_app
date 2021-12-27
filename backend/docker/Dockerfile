FROM openjdk:11
COPY ../build/libs/*.jar /app/backend.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "backend.jar"]