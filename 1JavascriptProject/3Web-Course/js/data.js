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
        rating : 5.0,
        topics : randomTopic(topics),
        price : 9000000,
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
        price : 3900000,
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
        price : 3850000,
        supporterId : 2
    },
    {
        id : 4,
        title : "Learn AWS the Hard Way",
        description : "Bởi qua khoá học này, bạn sẽ được thực hành nhiều và đa dạng từng dịch vụ, sau đó làm những dự án kết hợp nhiều dịch vụ lại với nhau. Cuối cùng khi đã thực hành thành thạo thì mới luyện thi các câu hỏi bộ đề. Mục tiêu không chỉ là thi chứng chỉ mà là áp dụng vào dự án thật.",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/brbgh4451coepbqoch60/R6t2Z_Op",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 7000000,
        supporterId : 3
    },
    {
        id : 5,
        title : "DevOps 2022",
        description : "DevOps là một văn hóa làm việc kết hợp giữa kỹ sư phát triển phần mềm (dev) với bộ phận operator (kỹ sư hệ thống, nhân viên bảo mật, kỹ sư mạng, kỹ sư hạ tầng, …) nhằm mục đích rút ngắn vòng đời phát triển sản phẩm",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/93NQ8U6F",
        rating : 4.9,
        topics : randomTopic(topics),
        price : 4000000,
        supporterId : 1
    },
    {
        id : 6,
        title : "Web Frontend nâng cao với React",
        description : "React là một thư viện Javascript được phát triển bởi Facebook, dùng để xây dựng giao diện người dùng. Nó giúp xây dựng các ứng dụng lớn, có nhiều thao tác phức tạp từ người dùng. Rất nhiều trang web lớn đã áp dụng React vào việc xây dựng UI như: facebook.com, wordpress.com, airbnb.com, dropbox.com, …",
        type : "onlab",
        image : "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/xQSZ4zy3",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 5500000,
        supporterId : 2
    },
    {
        id : 7,
        title : "Java căn bản",
        description : "Khoá học “Java căn bản” 16 buổi dành cho người mới bắt đầu học lập trình. Nó được thiết kế dễ hiểu, trực quan, gồm nhiều ví dụ code minh hoạ ngắn phù hợp với mọi đối tượng học viên nhập môn lập trình.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/ZuedW7J1",
        rating : 4.4,
        topics : randomTopic(topics),
        price : 4500000,
        supporterId : 3
    },
    {
        id : 8,
        title : "Lập trình Nodejs (cập nhật 2022)",
        description : "NodeJS là một công nghệ web đang phát triển nhanh chóng và các lập trình viên NodeJS là một trong những người được trả lương cao nhất trong ngành hiện nay. Nắm vững NodeJS sẽ giúp bạn có một công việc tốt hoặc cải thiện công việc hiện tại bằng cách cho phép bạn xây dựng các ứng dụng web một cách nhanh chóng, mạnh mẽ và chất lượng.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/bub3enc51co7s932dsk0/6PyUoB-T",
        rating : 4.7,
        topics : randomTopic(topics),
        price : 300000,
        supporterId : 1
    },
    {
        id : 9,
        title : "Javascript căn bản - Tổng hợp 12 game huyền thoại",
        description : "Các dự án trong khóa học này được thiết kế để giúp bạn xây dựng mọi thứ bằng cách sử dụng HTML5, CSS & JavaScript mà cần sử dụng libraries hay frameworks.",
        type : "online",
        image : "https://media.techmaster.vn/api/static/10194/bv7f8ms51coduae6ij60",
        rating : 4.5,
        topics : randomTopic(topics),
        price : 500000,
        supporterId : 2
    },
    {
        id : 10,
        title : "Xây dựng và quản trị website bằng wordpress",
        description : "WordPress là một hệ quản trị nội dung (CMS - Content Management System) vượt trội giúp người dùng có thể tạo ra nhiều thể loại website khác nhau như blog cá nhân, website tin tức - giải trí, website công ty, website bán hàng - thương mại điện tử, thậm chí với các loại website có độ phức tạp cao như sàn thương mại điện tử, đăng dự án bất động sản,…",
        type : "online",
        image : "https://media.techmaster.vn/api/static/c2m5ou451cob24f6skeg/c6jfe1451cof74mrqo4g",
        rating : 4.8,
        price : 500000,
        topics : randomTopic(topics),
        supporterId : 3
    },
    {
        id : 11,
        title : "Khoá học SQL nâng cao",
        description : "<ul><li>Sử dụng EXPLAIN / EXPLAIN ANALYZE để phân tích câu query</li><li>Tìm hiểu các phương pháp truy xuất dữ liệu từ bảng: Sequential Scan, Index Scan, Bitmap Scan</li><li>Các phương pháp JOIN: Nested Loop Join, Sort Merge Join, Hash Join</li><li>Sử dụng Index để tăng tốc truy vấn: B-Tree, Hash, Covering, Function-based Index</li><li>Các lý do khiến cho Index bị mất tác dụng khi tiến hành truy vấn và các cách khắc phục</li><li>Một số phương pháp tuning khác</li></ul>",
        type : "online",
        image : "https://media.techmaster.vn/api/static/6734/bo5caps51coco9d0vu80",
        rating : 5,
        price : 500000,
        topics : randomTopic(topics),
        supporterId : 2
    },
    {
        id : 12,
        title : "Nhập môn lập trình - giải thuật cơ bản",
        description : "Khóa học trực tuyến này được thiết kế, biên tập lại từ khóa học cấu trúc dữ liệu giải thuật phòng lab rất thành công (nhiều học viên xin được việc và xin học bổng đi học chuyên ngành khóa học máy tính tại Mỹ đã học khóa này). Tại sao lại phải thiết kế lại, nếu khóa học phòng lab thành công?",
        type : "online",
        image : "https://media.techmaster.vn/api/fileman/Uploads/users/5463/giaithuat-thumnail.png",
        rating : 4.5,
        price : 500000,
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

