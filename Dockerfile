FROM ghcr.io/graalvm/graalvm-ce:ol7 as buildbase

WORKDIR /bench

RUN gu install native-image

COPY gradle/ ./gradle
COPY gradlew .
COPY *.gradle ./

RUN chmod +x gradlew

RUN ["/bin/sh", "./gradlew", "clean", "--no-daemon"]

COPY src/ ./src
RUN ["/bin/sh", "./gradlew", "dockerBuild", "--no-daemon"]

RUN native-image --static -jar /bench/build/libs/benchtool.jar benchtool_java

FROM scratch

COPY --from=buildbase /bench/benchtool_java /bench/benchtool_java

ENTRYPOINT [ "/bench/benchtool_java" ]