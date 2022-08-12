## Hướng dẫn đẩy ứng dụng SpringBoot lên trên Heroku

##### Tạo tài khoản heroku và đăng nhập

Truy cập vào trang web heroku.com, tiến hành đăng ký tài khoản và đăng nhập

#### Tạo heroku-app

**1. Tạo app trên heroku**

![](https://user-images.githubusercontent.com/49893899/182999719-5801710c-e6f3-47dc-bd5a-ec1e7d94f0a5.png)

Hoặc sử dụng teminal (đã cài heroku CLI - https://devcenter.heroku.com/articles/heroku-cli)

```bash
$ heroku create springboot-course-app
```

**2. Tạo database (nếu app không sử dụng database thì có thể bỏ qua bước này)**

Yêu cầu đăng ký thẻ visa trên heroku (free)

Vào phần https://dashboard.heroku.com/account/billing để đăng ký thẻ visa

Sau đó tìm kiếm Add-ons là : **ClearDB MySQL** và thêm vào

![](https://user-images.githubusercontent.com/49893899/182999697-d94a7373-64a9-47d7-91ca-92eb0d6225bc.png)

**3. Đẩy code lên trên heroku**

Nếu trong project của bạn đang sử dụng java 17 thì hãy bổ sung thêm file **system.properties** vào trong thư mục gốc chứa project với nội dung

```
java.runtime.version=17
```

Trong thư mục chứa project, bật terminal và thực hiện các câu lệnh sau

![](https://user-images.githubusercontent.com/49893899/182999740-d225dcc4-8265-46e1-9323-6d758c79f6ba.png)

```bash
1. Login to heroku (xác nhận ở trên web)

$ heroku login

2. Tạo commit và đẩy code lên

$ git init
$ git status
$ git add .
$ git commit -m "update"
$ git push heroku main (nếu main không được thì đổi thành master)
```

> Note :
> - Nếu project không có database thì đến bước này là xong, chỉ cần truy cập vào link deploy của hero là được
> - Nếu có database thì đến bước này mặc dù đã được tạo bảng nhưng dữ liệu trong các bảng không có gì
> - Nếu muốn restore database đang có sẵn cho app thì chuyển qua bước tiếp theo
>

**4. Restore database**

Sử dụng **phpMyAdmin** để tạo bản backup cho database, và có thể lưu vào thư mục chứa project để tiện cho việc copy vào container đang chạy mysql trên máy

**Kết nối với database trong heroku (sử dụng terminal - để kết nối với docker)**

1. Kiểm tra id của **container mysql** đang chạy

```
$ docker ps
```

![](https://user-images.githubusercontent.com/49893899/182999728-2ba135c3-453d-4c9f-8f79-f0801f812006.png)

2. Exec vào trong container mysql đang chạy để thực hiện các câu lệnh

```
$ docker exec -it [container_id] /bin/sh
```

![](https://user-images.githubusercontent.com/49893899/183000099-663e3c89-b18b-4e66-86b6-12caec27e77e.png)

3. Kiểm tra tham số của database

```
$ mysql -u [name] -p[password] -h [host]
```


> Trong đó những tham số name, password, host lấy thông số của app trên heroku

![](https://user-images.githubusercontent.com/49893899/183000502-235d3c25-dc63-457f-903a-037d175ef732.png)

![](https://user-images.githubusercontent.com/49893899/183000828-720398ba-1d9d-4024-b5a2-11a0701b6fe9.png)

Connect to database

```
$ show databases

$ connect to [database_name]

$ select @@character_set_database, @@collation_data_base

$ exit
```

**4. Sửa bản backup**

Trong bản backup database, sửa các thông số cho giống với thông số trong container

**5. Copy file backup vào trong container**

Ví dụ tạo folder database và cho file backup vào trong đó

```bash
$ mkdir database

$ docker cp ./[file_backup.sql] [container_id]:/database
```

**6. Restore database**

Xóa database đang có

```bash
$ mysql -u [name] -p[password] -h [host]

$ drop database [database_name]

$ exit
```

Restore database

```
$ mysql -u [name] -p[password] -h [host] < database/[file_backup.sql]
```
