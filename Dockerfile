FROM openjdk:8u222-jdk-slim

COPY build/libs/bee-master-api-0.0.1-SNAPSHOT.jar /app/bee-master-api.jar

WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=default

CMD java -jar -Xmx1024m -Xms256m bee-master-api.jar
