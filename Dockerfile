FROM openjdk:17-alpine

WORKDIR /app

COPY target/WhetherSpringBoot-0.0.1-SNAPSHOT.jar ./application.jar

ENTRYPOINT ["java",  "-jar", "./application.jar"]