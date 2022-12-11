FROM maven:3-jdk-11 AS builder

RUN cd /tmp && \
    git clone --depth 1 --branch v1.1.2 https://github.com/ndthuan/VnCoreNLP.git && \
    cd VnCoreNLP && \
    mvn -Plib install

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src/ /app/src/

RUN mvn -Dmaven.test.skip clean package

FROM alpine:3.14

WORKDIR /app

RUN apk --no-cache add openjdk11 tzdata

COPY --from=builder /app/target/vi-word-segmenter-*.jar .

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar /app/vi-word-segmenter-*.jar

HEALTHCHECK --interval=20s --timeout=5s --start-period=20s --retries=3 CMD netstat -an | grep ':8080' > /dev/null; if [ 0 != $? ]; then exit 1; fi;
