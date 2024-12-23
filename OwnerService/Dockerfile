FROM openjdk:8-alpine
VOLUME /opt/egbtm
#ADD data /
COPY target/OwnerService-0.0.1-SNAPSHOT.jar /app/

WORKDIR /app

ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "OwnerService-0.0.1-SNAPSHOT.jar"]
