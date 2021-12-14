FROM openjdk:11
COPY ../target/pictures-0.0.1-SNAPSHOT.jar /app/pictures.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "pictures.jar"]