package com.example.coursebackend.database;

import com.example.coursebackend.model.Course;
import com.example.coursebackend.model.Supporter;

import java.util.ArrayList;
import java.util.List;

public class FakeDB {
    // Danh sách course
    public static List<Course> courses = new ArrayList<>(List.of(
            new Course(1, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(2, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new ArrayList<>(List.of("Di động")), 1),
            new Course(3, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(4, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "onlab", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(5, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "onlab", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new ArrayList<>(List.of("Backend")), 1),
            new Course(6, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "onlab", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(7, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new ArrayList<>(List.of("Backend")), 3),
            new Course(8, "Lập trình iOS Swift căn bản cập nhật 2022", "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay", "online", "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0", 4.7, new ArrayList<>(List.of("Backend")), 1),
            new Course(9, "Khoá học Lập trình Arduino Scratch cho trẻ em", "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.", "online", "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0", 4.3, new ArrayList<>(List.of("Database")), 2),
            new Course(10, "Spring Boot - Web Back End", "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.", "online", "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg", 4.5, new ArrayList<>(List.of("Backend")), 3)
    ));

    // Danh sách user
    public static List<Supporter> supporters = new ArrayList<>(List.of(
            new Supporter(1, "Phạm Thị Mẫn", "man@gmail.com", "0988888888", "https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0"),
            new Supporter(2, "Nguyễn Đức Thịnh", "thinh@gmail.com", "0977777777", "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC"),
            new Supporter(3, "Nguyễn Thanh Hương", "huong@gmail.com", "0966666666", "https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag")

    ));
}
