FROM amazoncorretto:21-alpine3.18-jdk@sha256:29263a8c3b3f6b7c31bfeb1642d2266bd23f30a4e25bd3747fe148a5ba803a57

RUN apk update && apk upgrade

WORKDIR /app

COPY target/backendCosmeticosBellezaInfinita-0.0.1-SNAPSHOT.jar /app/backendCosmeticosBellezaInfinita.jar

RUN adduser -D -s /bin/sh appuser

RUN chown -R appuser:appuser /app

USER appuser

CMD ["java", "-jar", "backendCosmeticosBellezaInfinita.jar"]