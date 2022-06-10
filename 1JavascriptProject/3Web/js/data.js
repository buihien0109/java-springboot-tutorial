// Random topic cho khóa học
const randomTopic = arr => {
    // Tạo mảng rỗng
    let list = [];

    // Số lượng topic có trong mảng 1 -> 3
    let countTopic = Math.floor(Math.random() * (3) + 1)

    // Random topic và push vào mảng
    while(list.length != countTopic) {
        let index = Math.floor(Math.random() * arr.length);
        let topicValue = arr[index].value;
        if(!list.includes(topicValue)) {
            list.push(topicValue);
        }
    }

    return list;
}

// Danh sách topic
let topics = [
    {
        name : "Lập trình Backend",
        value : "backend"
    },
    {
        name : "Lập trình Frontend",
        value : "frontend"
    },
    {
        name : "Cơ sở dữ liệu",
        value : "database"
    },
    {
        name : "Di động",
        value : "mobile"
    },
    {
        name : "Devops",
        value : "devops"
    },
    {
        name : "Khóa học cơ bản",
        value : "basic"
    },
]

// Danh sách khóa học
let courses = [
    {
        id : 1,
        title : "Spring Boot - Web Back End",
        description : "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 1000000,
        supporterId : 3
    },
    {
        id : 2,
        title : "Lập trình iOS Swift căn bản cập nhật 2022",
        description : "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0",
        rating : 4.7,
        topics : randomTopic(topics),
        price : 2000000,
        supporterId : 1
    },
    {
        id : 3,
        title : "Khoá học Lập trình Arduino Scratch cho trẻ em",
        description : "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 1500000,
        supporterId : 2
    },
    {
        id : 4,
        title : "Spring Boot - Web Back End",
        description : "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 4000000,
        supporterId : 3
    },
    {
        id : 5,
        title : "Lập trình iOS Swift căn bản cập nhật 2022",
        description : "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0",
        rating : 4.7,
        topics : randomTopic(topics),
        price : 2000000,
        supporterId : 1
    },
    {
        id : 6,
        title : "Khoá học Lập trình Arduino Scratch cho trẻ em",
        description : "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 2000000,
        supporterId : 2
    },
    {
        id : 7,
        title : "Spring Boot - Web Back End",
        description : "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 6000000,
        supporterId : 3
    },
    {
        id : 8,
        title : "Lập trình iOS Swift căn bản cập nhật 2022",
        description : "Swift là một trong những ngôn ngữ đang phát triển mạnh mẽ nhất hiện nay",
        type : "online",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/bjxnxQi0",
        rating : 4.7,
        topics : ["Di động"],
        price : 2500000,
        supporterId : 1
    },
    {
        id : 9,
        title : "Khoá học Lập trình Arduino Scratch cho trẻ em",
        description : "Phù hợp với học sinh chưa bao giờ code. 3 buổi đầu sử dụng ngôn ngữ Scratch kéo thả trực quan để làm quen với lập trình.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/bt3miv451co41h2qcnr0",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 2000000,
        supporterId : 2
    },
    {
        id : 10,
        title : "Spring Boot - Web Back End",
        description : "Spring Boot là một module của Spring Framework giúp giảm tải các cấu hình cho phép xây dựng nhanh chóng một ứng dụng độc lập. Spring Boot cung cấp sẵn các Embedded HTTP servers (Tomcat, Jetty, …), các plugins để phát triển và test một cách dễ dàng.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/36/bu7v9ks51co41h2qcttg",
        rating : 4.5,
        price : 1000000,
        topics : randomTopic(topics),
        supporterId : 3
    }
]

// Danh sách tư vấn viên
let users = [
    {
        id : 1,
        name : "Phạm Thị Mẫn",
        email : "man@gmail.com",
        phone : "0988888888",
        avatar : "https://media.techmaster.vn/api/static/crop/bv9jp4k51co7nj2mhht0",
        password : "111"
    },
    {
        id : 2,
        name : "Nguyễn Đức Thịnh",
        email : "thinh@gmail.com",
        phone : "0977777777",
        avatar : "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/ccjlg0NC",
        password : "111"
    },
    {
        id : 3,
        name : "Nguyễn Thanh Hương",
        email : "huong@gmail.com",
        phone : "0966666666",
        avatar : "https://media.techmaster.vn/api/static/crop/brm3huc51co50mv77sag",
        password : "111"
    },
    {
        id : 4,
        name : "Bùi Hiên",
        email : "hien@gmail.com",
        phone : "0955555555",
        avatar : "https://media.techmaster.vn/api/static/crop/c61mglc51co49felaphg",
        password : "111"
    },
    {
        id : 5,
        name : "Lục Thanh Ngọc",
        email : "ngoc@gmail.com",
        phone : "0944444444",
        avatar : "https://media.techmaster.vn/api/static/crop/c10b0uk51coag9451f10",
        password : "111"
    },
    {
        id : 6,
        name : "Nguyễn Xuân Ba",
        email : "ba@gmail.com",
        phone : "0933333333",
        avatar : "https://media.techmaster.vn/api/static/crop/c6o8q3s51cof74mrqq70",
        password : "111"
    }
]

// Danh sách user

