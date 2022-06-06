// Kiểm tra xem danh sách course thuộc thể loại nào (Tất cả, Phòng lab - Trực tuyến)
// Lấy thông tin type trên URL
let params = new URLSearchParams(window.location.search);
let type = params.get("type");

// Lọc ra danh sách course tùy thuộc vào type
// Hiển thị title tương ứng với từng loại khóa học
let courseList = courses;
if(type) {
    if(type == "onlab") {
        courseList = courses.filter(c => c.type == type)
        document.title = "Khóa học phòng lab"
    } else if(type == "online") {
        courseList = courses.filter(c => c.type == type)
        document.title = "Khóa học trực tuyến"
    } else {
        window.location.href = "./404.html"
    }
} else {
    document.title = "Danh sách khóa học"
}

// Truy cập vào các thành phần
const courseListEl = document.querySelector(".course-list");
const seachFormInputEl = document.querySelector(".seach-form-input");
const seachFormBtnEl = document.querySelector(".seach-form-button");
const topicItemsEl = document.querySelectorAll(".topic-item input");

// Tìm kiếm khóa học
const seachCourse = (term) => {
    // Lọc khóa học theo title
    let courseFilter = courseList.filter((c) => c.title.toLowerCase().includes(term.toLowerCase()));
    renderCourse(courseFilter);
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

// Lọc khóa học
const filterCourse = topic => {
    // Lọc khóa học theo chủ đề
    let courseFilter = courseList.filter((c) => c.topics.includes(topic));
    renderCourse(courseFilter); 
}

topicItemsEl.forEach(input => {
    input.addEventListener("change", function() {
        let topicValue = this.value;
        filterCourse(topicValue)
    })
})

// Render khóa học ra ngoài giao diện
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

renderCourse(courseList);
