#FROM openjdk:latest
#
#COPY target/Course-0.0.1-SNAPSHOT.jar app.jar
#
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM maven:3.8.4-openjdk-17

WORKDIR /app

COPY . .

CMD ["mvn", "spring-boot:run"]