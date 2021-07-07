#!/bin/bash

postgres_host="db"
postgres_port=3306

# Wait for the postgres docker to be running
while ! nc $postgres_host $postgres_port; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done

java -jar app.jar