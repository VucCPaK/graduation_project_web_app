FROM openjdk:11
COPY ../target/embedded-ldap-0.0.1-SNAPSHOT.jar /app/embedded-ldap.jar
WORKDIR /app
ENTRYPOINT ["java", "-jar", "embedded-ldap.jar"]