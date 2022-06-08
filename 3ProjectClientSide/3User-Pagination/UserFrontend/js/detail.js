// Truy cập vào các thành phần
const addressEl = document.getElementById("address");
const nameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const passwordEl = document.getElementById("password");
const btnSave = document.getElementById("btn-save");
const btnBack = document.querySelector(".btn-back");

// Xử lý nút quay lại
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Lấy id trên URL
const params = new URLSearchParams(window.location.search);
const id = params.get("id");

// Kiểm tra có id hay không?
const checkUserExist = () => {
    if(!id) {
        window.location.href = "/404.html";
    }
}

// Gọi API
const getUser = async (id) => {
    try {
        let res = await axios.get(`${API_URL}/users/${id}`);
        renderUser(res.data);
    } catch (error) {
        console.log(error);
    }
};

// Render user
const renderUser = (user) => {
    nameEl.value = user.name;
    emailEl.value = user.email;
    phoneEl.value = user.phone;
    addressEl.value = user.address;

    // Avatar
    if (!user.avatar) {
        avatarPreviewEl.src = "https://via.placeholder.com/200";
    } else {
        avatarPreviewEl.src = `http://localhost:8080${user.avatar}`;
    }
};


// Chạy lấy tỉnh thành phố trước, sau đó mới lấy thông tin user
const init = async () => {
    checkUserExist();

    await getProvince(addressEl);
    await getUser(id);
};

init();