// Truy cập vào các thành phần
const userEmailEl = document.getElementById("user-email");
const userPasswordEl = document.getElementById("user-password");
const btnLogin = document.getElementById("btn-login");
const userEl = document.querySelector(".side-bar .user");

const modalLogindEl = document.getElementById('modal-login');
const modalLogindConfig = new bootstrap.Modal(modalLogindEl, {
    keyboard: false
})

// Lưu dữ liệu vào LocalStorage
const addToLocalStorage = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
}

// Lấy dữ liệu từ LocalStorage và hiển thị
const getFromLocalStorage = (key) => {
    const valueLocalStorage = localStorage.getItem(key);
    if (valueLocalStorage) {
        return JSON.parse(valueLocalStorage);
    }
    return null;
}

// Xóa dữ liệu từ LocalStorage
const removeFromLocalStorage = (key) => {
    localStorage.removeItem(key);
}

// Kiểm tra user đã đăng nhập chưa để chuyển avatar
const checkUserLogin = () => {
    let user = getFromLocalStorage("userLogin");
    if(user) {
        userEl.innerHTML = `
            <button onclick="logout()" class="border-0 bg-transparent">
                <img src="${user.avatar}" alt="avatar">
            </button>
        `;
    } else {
        userEl.innerHTML = `
            <button type="button" class="bg-transparent border-0 text-white" data-bs-toggle="modal" data-bs-target="#modal-login">
                <span class="fs-5"><i class="fa-solid fa-user"></i></span>
            </button>
        `;
    }
}

// Xử lý đăng nhập
btnLogin.addEventListener("click", function() {
    let email = userEmailEl.value;
    let password = userPasswordEl.value;

    // Validate thông tin
    if(email == "" || password == "") {
        alert("Tài khoản hoặc mật khẩu không được để trống");
        return;
    }

    // Tìm kiếm user
    let user = users.find(u => u.email == email && u.password == password);

    // Nếu không có user -> error
    if(!user) {
        alert("Tài khoản hoặc mật khẩu không chính xác");
        return;
    }

    // Lưu thông tin user vào localStorage
    addToLocalStorage("userLogin", user);

    // Ẩn modal
    modalLogindConfig.hide();

    // Hiển thị thông tin user
    checkUserLogin();

    // Render cart
    updateTotalCart();
    renderItem();
})

// Xử lý đăng xuất
const logout = () => {
    removeFromLocalStorage("userLogin");
    checkUserLogin();
    updateTotalCart();
    renderItem();
}

checkUserLogin();
