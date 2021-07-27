FROM adoptopenjdk/openjdk11
COPY target/evadon-*.jar app.jar
COPY ./docker-entrypoint.sh docker-entrypoint.sh
RUN apt-get update && apt-get install netcat-openbsd -y
RUN chmod +x docker-entrypoint.sh
# ENTRYPOINT는 컨테이너가 시작되었을 때 스크립트 혹은 명령을 실행합니다. 즉 docker run 명령으로 컨테이너를 생성하거나, docker start 명령으로 정지된 컨테이너를 시작할 때 실행됩니다.
ENTRYPOINT ./docker-entrypoint.sh