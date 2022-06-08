// Truy cập vào ô input
const nameEl = document.getElementById("name");
const emailEl = document.getElementById("email");
const phoneEl = document.getElementById("phone");
const addressEl = document.getElementById("address");
const avatarEl = document.getElementById("avatar");
const passwordEl = document.getElementById("password");

const btnSave = document.getElementById("btn-save");

// Xử lý quay lại trang index
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Xử lý thêm user
btnSave.addEventListener("click", async function () {
    try {
        // Tạo object với dữ liệu đã được cập nhật
        let userNew = {
            name: nameEl.value,
            phone: phoneEl.value,
            email: emailEl.value,
            address: addressEl.value,
            password : passwordEl.value
        };

        // Gọi API để tạo
        let res = await axios.post("http://localhost:8080/api/v1/users", userNew);

        if (res.data) {
            window.location.href = "/";
        }
    } catch (error) {
        alert(error.response.data.message);
    }
});

getProvince(addressEl);


