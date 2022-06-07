// Định nghĩa API URL root
const API_URL = "http://localhost:8080/api/v1";

// Kiểm tra xem danh sách course thuộc thể loại nào (Tất cả, Phòng lab - Trực tuyến)
// Lấy thông tin type trên URL
let params = new URLSearchParams(window.location.search);
let type = params.get("type");

// Hiển thị title tương ứng với từng loại khóa học
if(type) {
    if(type == "onlab") {
        document.title = "Khóa học phòng lab"
    } else if(type == "online") {
        document.title = "Khóa học trực tuyến"
    } else {
        window.location.href = "./404.html"
    }
} else {
    document.title = "Danh sách khóa học"
}

// ************************* Danh sách API *************************
// Lấy danh sách khóa học
const getCoursesAPI = type => {
    if(type) {
        return axios.get(`${API_URL}/courses?type=${type}`);
    }
    return axios.get(`${API_URL}/courses`);
}

// Tìm kiếm khóa học theo chủ đề
const getCoursesByTopicAPI = topic => {
    if(type) {
        return axios.post(`${API_URL}/courses/filter`, { type, topic });
    }
    return axios.post(`${API_URL}/courses/filter`, { topic });
}

// Tìm kiếm khóa học theo tên
const getCoursesByTitleAPI = term => {
    if(type) {
        return axios.post(`${API_URL}/courses/search`, { type, term });
    }
    return axios.post(`${API_URL}/courses/search`, { term });
}


// ************************* Truy cập vào các thành phần *************************
const courseListEl = document.querySelector(".course-list");
const seachFormInputEl = document.querySelector(".seach-form-input");
const seachFormBtnEl = document.querySelector(".seach-form-button");
const topicItemsEl = document.querySelectorAll(".topic-item input");

// ************************* Các hàm xử lý *************************

// 1. Lấy danh sách khóa học
const getCourses = async (type) => {
    try {
        let res = await getCoursesAPI(type);
        renderCourse(res.data);
    } catch (error) {
        console.log(error);
    }
}

// 2. Tìm kiếm khóa học
const seachCourse = async (term) => {
    try {
        let res = await getCoursesByTitleAPI(term);
        renderCourse(res.data);
    } catch (error) {
        console.log(error);
    }   
};

// Tìm kiếm khóa học với ô input
seachFormInputEl.addEventListener("keydown", function (e) {
    if (e.keyCode == 13) {
        let term = e.target.value;
        if (term == "") {
            alert("Từ khóa không được để trống");
            return;
        }
        seachCourse(term);
    }
});

// Tìm kiếm khóa học với button
seachFormBtnEl.addEventListener("click", function () {
    let term = seachFormInputEl.value;
    if (term == "") {
        alert("Từ khóa không được để trống");
        return;
    }
    seachCourse(term);
});

// 3. Lọc khóa học
const filterCourse = async (topic) => {
    try {
        let res = await getCoursesByTopicAPI(topic);
        renderCourse(res.data);    
    } catch (error) {
        console.log(error);
    }
}

topicItemsEl.forEach(input => {
    input.addEventListener("change", function() {
        let topicValue = this.value;
        filterCourse(topicValue)
    })
})

// HELPER FUNCTION : Render khóa học ra ngoài giao diện
const renderCourse = (arr) => {
    courseListEl.innerHTML = "";

    if(arr.length == 0) {
        courseListEl.innerHTML = `
            <div class="com-md-12">
                <h2 class="fs-3 mt-3">Danh sách khóa học trống</h2>
            </div>
        `;
        return;
    }

    for (let i = 0; i < arr.length; i++) {
        const c = arr[i];
        courseListEl.innerHTML += `
            <div class="col-md-4">
                <a href="./detail.html?id=${c.id}">
                    <div class="course-item shadow-sm rounded mb-4">
                        <div class="course-item-image">
                            <img src="${c.image}"
                                alt="${c.title}">
                        </div>
                        <div class="course-item-info p-3">
                            <h2 class="fs-5 mb-4 text-dark">${c.title}</h2>
                            <div
                                class="d-flex justify-content-between align-items-center fw-light text-black-50">
                                <p class="type">${c.type}</p>
                                <p class="rating">
                                ${c.rating} <span class="text-warning"><i class="fa-solid fa-star"></i></span>
                                </p>
                            </div>
                        </div>
                    </div>
                </a>
            </div>
        `;
    }
};

getCourses(type);

