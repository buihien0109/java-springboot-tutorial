// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Tạo biến để lưu thông tin của khóa học + supporter
let course;
let supporter;

// Kiểm tra xem có tồn tại id trên url hay không?
if(id) {
    // Tìm kiếm khóa học
    course = courses.find(c => c.id == id);

    // Nếu không tìm thấy khóa học theo id -> lỗi
    if(!course) {
        window.location.href = "./404.html";
    }

    // Hiển thị tiêu đề khóa học
    document.title = course.title;

    // Tìm kiếm supporter theo khóa học
    supporter = supporters.find(s => s.id == course.supporterId);
} else {
    window.location.href = "./404.html"
}

// Truy cập vào các thành phần
const breadcrumbItemEl = document.querySelector(".breadcrumb-item.active");
const courseTitleEl = document.querySelector(".course-title");
const courseDescriptionEl = document.querySelector(".course-description");
const supporterEl = document.querySelector(".supporter");


// Hiển thị thông tin khóa học
const displayCourse = course => {
    breadcrumbItemEl.innerText = course.title;
    courseTitleEl.innerText = course.title;
    courseDescriptionEl.innerText = course.description;
}


// Hiển thị thông tin supporter
const displaySupporter = supporter => {
    supporterEl.innerHTML = `
        <div class="supporter-image">
            <img src="${supporter.avatar}" alt="${supporter.name}" class="rounded-circle w-75 h-75">
        </div>
        <div class="supporter-info">
            <p>
                <b>Tư vấn viên :</b>
                ${supporter.name}
            </p>
            <p>
                <b>Email :</b>
                ${supporter.email}
            </p>
            <p>
                <b>Số điện thoại :</b>
                ${supporter.phone}
            </p>
            <p>
                <button class="btn btn-primary shadow-sm">Nhận tư vấn</button>
            </p>
        </div>
    `
}

displayCourse(course);
displaySupporter(supporter);