FROM openjdk:14

COPY . /bench

WORKDIR /bench

RUN ["/bin/sh", "./gradlew", "clean", "dockerBuild"]

ENTRYPOINT ["java", "-jar", "buid/libs/benchtool-java.jar"]