FROM alpine:edge as buildbase

WORKDIR /bench

RUN echo "http://dl-cdn.alpinelinux.org/alpine/edge/testing" >> /etc/apk/repositories
RUN apk update
RUN apk add openjdk14-jdk
RUN apk add openjdk14-jmods

RUN /usr/lib/jvm/java-14-openjdk/bin/jlink \
        --add-modules java.base \
        --verbose \
        --strip-java-debug-attributes \
        --compress 2 \
        --no-header-files \
        --no-man-pages \
        --output /opt/jre-minimal

COPY gradle/ ./gradle
COPY gradlew .
COPY *.gradle ./

RUN ./gradlew clean --no-daemon

COPY src/ ./src
RUN ["/bin/sh", "./gradlew", "dockerBuild", "--no-daemon"]

FROM alpine:edge as runtime

WORKDIR /bench

#https://medium.com/criciumadev/create-a-cloud-native-image-using-java-modules-a670be616b29
COPY --from=buildbase /opt/jre-minimal /opt/jre-minimal
ENV LANG=C.UTF-8 \
    PATH=${PATH}:/opt/jre-minimal/bin

COPY --from=buildbase /bench/build/libs/benchtool.jar .

ENTRYPOINT ["java", "-jar", "benchtool.jar"]
