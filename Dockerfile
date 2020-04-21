FROM openjdk:11 as buildbase

COPY . /bench

WORKDIR /bench

RUN ./gradlew clean

FROM buildbase

RUN ["/bin/sh", "./gradlew", "dockerBuild"]

ENTRYPOINT ["java", "-jar", "build/libs/benchtool.jar"]
