// Truy cập vào các thành phần
const formEl = document.querySelector(".form");
const usernameInputEl = document.querySelector("#username");
const passwordInputEl = document.querySelector("#password")
const btnLogin = document.querySelector("#btn-login");

const infoEl = document.querySelector(".info");
const usernameEl = document.querySelector(".username span");
const emailEl = document.querySelector(".email span");
const avatarEl = document.querySelector(".avatar img");

// Xử lý login
btnLogin.addEventListener("click", async function () {
    try {
        // Lấy giá trị trong ô input
        let usernameValue = usernameInputEl.value;
        let passwordValue = passwordInputEl.value;

        // Kiểm tra ô input trống
        if(!usernameValue || !passwordValue) {
            alert("Username hoặc password không được để trống");
            return;
        }

        // Gọi API login
        let res = await axios.post("http://localhost:8080/login", {
            username: usernameValue,
            password: passwordValue
        })

        // Hiển thị thông tin user
        displayUserInfo(res.data)
    } catch (error) {
        alert(error.response.data);
    }
})

// Hiển thị thông tin của user khi đăng nhập thành công
function displayUserInfo(user) {
    infoEl.classList.remove("hide");
    formEl.classList.add("hide");

    usernameEl.innerText = user.username;
    emailEl.innerText = user.email;
    avatarEl.src = user.avatar;
}