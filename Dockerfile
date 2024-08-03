FROM openjdk:22-jdk-buster

WORKDIR /dbh-query-service-app

COPY target/dbh-query-service-v1.jar dbh-query-service-app.jar

EXPOSE 8002

ENTRYPOINT ["java", "-jar", "dbh-query-service-app.jar"]