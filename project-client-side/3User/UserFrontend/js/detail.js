const API_URL = "http://localhost:8080/api/v1/users";

let params = new URLSearchParams(window.location.search);
let id = params.get("id");

const getUser = async (id) => {
    try {
        let res = await axios.get(`${API_URL}/${id}`);

        renderUser(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
};

// Truy cập vào ô input
const fullnameEl = document.getElementById("fullname");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const addressEl = document.getElementById("address");
const avatarEl = document.getElementById("avatar-preview");

const renderUser = (user) => {
    fullnameEl.value = user.fullName;
    emailEl.value = user.email;
    phoneEl.value = user.phone;
    addressEl.value = user.address;

    if(user.avatar) {
        avatarEl.src = "http://localhost:8080" + user.avatar;
    } else {
        avatarEl.src = "https://via.placeholder.com/200"
    }

};

// Xử lý quay lại trang index
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Xử lý cập nhật thông tin user
const btnSave = document.getElementById("btn-save");
btnSave.addEventListener("click", async function () {
    try {
        // Tạo object với dữ liệu đã được cập nhật
        let userUpdate = {
            fullName: fullnameEl.value,
            phone: phoneEl.value,
            email: emailEl.value,
            address: addressEl.value,
            avatar: avatarEl.value,
        };

        // Gọi API để cập nhật
        let res = await axios({
            method: "put",
            url: `${API_URL}/${id}`,
            data: userUpdate,
        });

        if (res.data) {
            window.location.href = "/";
        }
    } catch (error) {
        alert(error.response.data.message);
    }
});

// Đổi mật khẩu
const oldPasswordEl = document.getElementById("old-password");
const newPasswordEl = document.getElementById("new-password");
const btnChangePassword = document.getElementById("btn-change-password");

const modalChangePasswordEl = document.getElementById('modal-change-password');
const modalChangePasswordConfig = new bootstrap.Modal(modalChangePasswordEl, {
    keyboard: false
})


// Thay đổi mật khẩu
btnChangePassword.addEventListener("click", async function () {
    try {
        let oldPasswordValue = oldPasswordEl.value;
        let newPasswordValue = newPasswordEl.value;

        await axios.put(
            `${API_URL}/${id}/update-password`,
            {
                oldPassword: oldPasswordValue,
                newPassword: newPasswordValue,
            }
        );

        modalChangePasswordConfig.hide();
        oldPasswordEl.value = "";
        newPasswordEl.value = ""
    } catch (error) {
        alert(error.response.data.message);
    }
});

// Quên mật khẩu
const btnForgotPassword = document.getElementById("btn-forgot-password");

btnForgotPassword.addEventListener("click", async function() {
    try {
        let res = await axios.post(`${API_URL}/${id}/forgot-password`)
        alert(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
})

// Hiển thị danh sách tỉnh - thành phố
async function getProvince() {
    try {
        // Gọi API lấy danh sách tỉnh thành phố
        let res = await axios.get("https://provinces.open-api.vn/api/p/");

        // Render tỉnh thành phố
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

function renderProvince(arr) {
    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        addressEl.innerHTML += `<option value="${p.name}">${p.name}</option>`
    }
}

// Đổi avatar
function uploadFileAPI(file) {
    let formData = new FormData();
    formData.append("file", file);

    return axios({
        method : "POST",
        url : `${API_URL}/${id}/upload-file`,
        data : formData,
        // headers: {'Content-Type': 'multipart/form-data' }
    })
}

const fileUploadEl = document.querySelector("#avatar");
fileUploadEl.addEventListener("change", async (event) => {
    try {
        const file = event.target.files[0];
        let res = await uploadFileAPI(file);
        
        avatarEl.src = "http://localhost:8080" + res.data
    } catch (error) {
        console.log(error);
    }
})


async function init() {
    await getProvince();
    await getUser(id);
}

init()


