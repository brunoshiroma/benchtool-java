FROM alpine:edge as buildbase

WORKDIR /bench

RUN echo "http://dl-cdn.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories
RUN apk update
run apk add openjdk14-jdk

COPY gradle/ ./gradle
COPY gradlew .
COPY *.gradle ./

RUN ./gradlew clean

COPY src/ ./src
RUN ["/bin/sh", "./gradlew", "dockerBuild"]

FROM alpine:edge as runtime

WORKDIR /bench

RUN echo "http://dl-cdn.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories
RUN apk update
run apk add openjdk14-jre-headless

COPY --from=buildbase /bench/build/libs/benchtool.jar .

ENTRYPOINT ["java", "-jar", "benchtool.jar"]
