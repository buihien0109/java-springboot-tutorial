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
    supporter = users.find(s => s.id == course.supporterId);
} else {
    window.location.href = "./404.html"
}

// Truy cập vào các thành phần
const breadcrumbItemEl = document.querySelector(".breadcrumb-item.active");
const courseTitleEl = document.querySelector(".course-title");
const courseDescriptionEl = document.querySelector(".course-description");
const coursePriceEl = document.querySelector(".course-price");
const courseTypeEl = document.querySelector(".course-type");
const supporterEl = document.querySelector(".supporter");

const btnAddToCart = document.getElementById("btn-add-to-cart");


// Hiển thị thông tin khóa học
const displayCourse = course => {
    breadcrumbItemEl.innerText = course.title;
    courseTitleEl.innerText = course.title;
    courseDescriptionEl.innerText = course.description;
    coursePriceEl.innerText = formatMoney(course.price);
    courseTypeEl.innerText = course.type;
}


// Hiển thị thông tin supporter
const displaySupporter = supporter => {
    supporterEl.innerHTML = `
        <div class="supporter-image">
            <img src="${supporter.avatar}" alt="${supporter.name}" class="rounded-circle">
        </div>
        <div class="ms-4 supporter-info">
            <p>
                <b>Tư vấn viên :</b>
                ${supporter.name}
            </p>
            <p>
                <b>Email :</b>
                ${supporter.email}
            </p>
            <p class="mb-0">
                <b>Số điện thoại :</b>
                ${supporter.phone}
            </p>
        </div>
    `
}
// Thêm item vào cart
btnAddToCart.addEventListener("click", function() {
    let item = courses.find(c => c.id == id);
    let isAddSuccess = addItemToCart(item);

    if(isAddSuccess) {
        alert("Thêm vào giỏ hàng thành công");
    }
})

// Format tiền
const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}

displayCourse(course);
displaySupporter(supporter);