## Cài đặt MySQL và phpMyAdmin sử dụng docker

**Cài đặt MySQL**

```bash
// 1. Tạo volume
docker volume create mysql-volume

// 2. Tạo container
docker run -d --name mysql-container -p 3306:3306 -v mysql-volume:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=123 mysql:latest
```

**Account truy cập**

-   username : root
-   password : 123

**Cài đặt phpAdmin**

```bash
// 1. Tạo volume
docker volume create phpmyadmin-volume

// 2. Tạo container
docker run -d --name phpmyadmin-container -v phpmyadmin-volume:/etc/phpmyadmin/config.usr.inc.php --link mysql-container:db -p 82:80 phpmyadmin/phpmyadmin:latest
```

> Chú ý khi khởi tạo container phpMyAdmin cần link đến container MySQL đang chạy thông qua tên của container

**Docker image tham khảo**

-   MySQL : https://hub.docker.com/_/mysql
-   phpMyAdmin : https://hub.docker.com/r/phpmyadmin/phpmyadmin/

## Cài đặt MySQL và phpMyAdmin sử dụng docker compose

Tạo network

```bash
docker network create mysql
```

File **docker-compose.yml**

```bash:docker-compose.yml
version: "3.8"

services:
    # MySQL
    db:
        image: mysql:latest
        volumes:
            - db_data:/var/lib/mysql
        restart: always
        ports:
            - "3306:3306"
        environment:
            MYSQL_ROOT_PASSWORD: 123
        networks:
            - mysql
    # phpmyadmin
    phpmyadmin:
        depends_on:
            - db
        image: phpmyadmin/phpmyadmin:latest
        restart: always
        ports:
            - "82:80"
        environment:
            PMA_HOST: db
            MYSQL_ROOT_PASSWORD: 123
        networks:
            - mysql
networks:
    mysql:
volumes:
    db_data:
```
