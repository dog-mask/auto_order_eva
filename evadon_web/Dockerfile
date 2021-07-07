FROM adoptopenjdk/openjdk11
COPY target/evadon-*.jar app.jar
COPY ./docker-entrypoint.sh docker-entrypoint.sh
RUN apt-get update && apt-get install netcat-openbsd -y
RUN chmod +x docker-entrypoint.sh
ENTRYPOINT ./docker-entrypoint.sh