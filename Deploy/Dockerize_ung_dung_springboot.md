## Dockerize ứng dụng SpringBoot với docker

Cần chạy : `mvn install:install` để build file jar (file này sau khi build xong sẽ nằm trong folder target)

#### Tạo `Dockerfile`

```bash
FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
```

#### Tạo file `docker-compose.yml`

```yml
version: "3.8"

services:
    # MySQL
    mysql:
        image: mysql:latest
        volumes:
            - db_data_course:/var/lib/mysql
            - ./course.sql:/docker-entrypoint-initdb.d/init.sql
        restart: always
        ports:
            - "3306:3306"
        environment:
            MYSQL_ROOT_PASSWORD: 123 
            MYSQL_DATABASE : db-course 
        networks:
            - course_network
    # web
    web :
        image: springboot_course_app:latest
        depends_on: 
            - mysql
        ports: 
            - "8089:8080"
        restart: always
        networks:
            - course_network
networks:
    course_network:
volumes:
    db_data_course:

```
