#!/usr/bin/env bash

set -xe

# shellcheck disable=SC2046
SERVICE_DIR=$(cd $(dirname "$0") || exit; pwd)

cd "$SERVICE_DIR" || exit

SERVICE_NAME=$(./mvnw -Dexec.executable='echo' -Dexec.args='${project.artifactId}' --non-recursive exec:exec -q)
SERVICE_VERSION=$(./mvnw -Dexec.executable='echo' -Dexec.args='${project.version}' --non-recursive exec:exec -q)
DOCKER_IMAGE="ndthuan/$SERVICE_NAME:$SERVICE_VERSION"

./mvnw -Dmaven.test.skip clean package spring-boot:repackage

cat > Dockerfile.tmp <<EOF
FROM openjdk:14-jdk-alpine

COPY ./target/${SERVICE_NAME}-${SERVICE_VERSION}.jar /app/

WORKDIR /app

EXPOSE 8080

CMD java $JAVA_OPTS -jar ${SERVICE_NAME}-${SERVICE_VERSION}.jar

EOF

docker build -t "$DOCKER_IMAGE" -f Dockerfile.tmp .

if [ "$1" == "push" ]; then
  docker push "$DOCKER_IMAGE"
fi
