// Định nghĩa API URL root
const API_URL = "http://localhost:8080/api/v1";

// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Kiểm tra xem có tồn tại id trên url hay không?
if(!id) {
    window.location.href = "./404.html"
}

// ************************* Truy cập vào các thành phần *************************
const breadcrumbItemEl = document.querySelector(".breadcrumb-item.active");
const courseTitleEl = document.querySelector(".course-title");
const courseDescriptionEl = document.querySelector(".course-description");
const supporterEl = document.querySelector(".supporter");


// ************************* Các hàm xử lý *************************
// Lấy thông tin khóa học
const getCourse = async (id) => {
    try {
        // Gọi API
        let res = await axios.get(`${API_URL}/courses/${id}`);

        // Hiển thị thông tin
        displayCourse(res.data);
        displaySupporter(res.data.supporter);
    } catch (error) {
        window.location.href = "./404.html"
    }
}

// Hiển thị thông tin khóa học
const displayCourse = course => {
    document.title = course.title;
    breadcrumbItemEl.innerText = course.title;
    courseTitleEl.innerText = course.title;
    courseDescriptionEl.innerText = course.description;
}


// Hiển thị thông tin supporter
const displaySupporter = supporter => {
    supporterEl.innerHTML = `
        <div class="supporter-image">
            <img src="${supporter.avatar}" alt="${supporter.name}" class="rounded-circle">
        </div>
        <div class="supporter-info ms-4">
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

getCourse(id);