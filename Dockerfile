FROM eclipse-temurin:latest as build

WORKDIR /bench

COPY . .

RUN ./gradlew build

# Create a custom Java runtime
RUN $JAVA_HOME/bin/jlink \
         --add-modules java.base \
         --strip-debug \
         --no-man-pages \
         --no-header-files \
         --compress=2 \
         --output /javaruntime

# Define your base image
FROM debian:buster-slim as runtime
WORKDIR /bench
ENV JAVA_HOME=/opt/java/openjdk
ENV PATH "${JAVA_HOME}/bin:${PATH}"
COPY --from=build /javaruntime $JAVA_HOME

COPY --from=build /bench/build/libs/benchtool-java*.jar /bench/benchtool-java.jar

ENTRYPOINT [ "java", "-jar", "benchtool-java.jar" ]