## Dockerize ứng dụng SpringBoot với docker

Cần chạy : `mvn install:install` để build file jar (file này sau khi build xong sẽ nằm trong folder target)

#### Tạo `Dockerfile`

```bash:Dockerfile
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
```

#### Tạo file `docker-compose.yml`

```bash:docker-compose.yml

```
