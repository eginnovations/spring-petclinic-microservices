FROM openjdk:8-alpine
VOLUME /opt/egbtm
#ADD data /
COPY target/PetService-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "PetService-0.0.1-SNAPSHOT.jar"]
