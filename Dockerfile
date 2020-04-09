FROM openjdk:14

COPY . /bench

WORKDIR /bench

RUN ["/bin/sh", "./gradlew", "clean", "build"]

COPY ./build/libs/*.jar ./benchtool-java.jar

ENTRYPOINT ["java", "-jar", "benchtool-java.jar"]