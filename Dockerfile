FROM maven:3-jdk-11 AS builder

RUN cd /tmp && \
    git clone --depth 1 --branch v1.1.2 https://github.com/ndthuan/VnCoreNLP.git && \
    cd VnCoreNLP && \
    mvn -Plib install

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/

RUN mvn -Dmaven.test.skip clean package spring-boot:repackage

FROM openjdk:14-jdk-alpine

COPY --from=builder /app/target/vi-word-segmenter-*.jar /app/

WORKDIR /app

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar /app/vi-word-segmenter-*.jar
