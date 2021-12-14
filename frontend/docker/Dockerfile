FROM openjdk:11
COPY ../build/libs/*.jar /app/frontend.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "frontend.jar"]