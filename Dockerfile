FROM maven:3.8.5-openjdk-17-slim AS imageonly

WORKDIR .
COPY settings.xml /usr/share/maven/ref/settings.xml
RUN mkdir /tmp/build
COPY pom.xml /tmp/build/pom.xml
COPY src /tmp/build/src
RUN mvn -f /tmp/build/pom.xml -Pdocker -s /usr/share/maven/ref/settings.xml clean package

FROM openjdk:17-slim

RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY application.yaml /opt/app/
COPY --from=imageonly /tmp/build/target/pun-0.0.1-SNAPSHOT.jar ./app.jar

ENV MAVEN_OPTS "-Xmx512m"

CMD ["java", "-Dspring.config.location=/opt/app/application.yaml","-jar", "/opt/app/app.jar"]

