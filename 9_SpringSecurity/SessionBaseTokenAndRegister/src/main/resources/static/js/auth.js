// Truy cập vào các thành phần
let emailEl = document.getElementById("email");
let passwordEl = document.getElementById("password");
let btnLogin = document.getElementById("btn-login");

let r_nameEl = document.getElementById("r-name");
let r_emailEl = document.getElementById("r-email");
let r_passwordEl = document.getElementById("password");
let btnRegister = document.getElementById("btn-register");

// Xử lý đăng nhập
btnLogin.addEventListener("click", async function() {
    try {
        let email = emailEl.value;
        let password = passwordEl.value;

        await axios.post("/api/auth/login", {
            email : email,
            password : password
        })

        alert("Login Successfully")

        setTimeout(function () {
            window.location.reload();
        }, 1500)
    } catch (e) {
        alert(e.response.data.message);
    }
})

// Xử lý đăng ký
btnRegister.addEventListener("click", async function (){
    try {
        let name = r_nameEl.value;
        let email = r_emailEl.value;
        let password = r_passwordEl.value;

        let res = await axios.post("/api/auth/register", {
            name : name,
            email : email,
            password : password
        })

        if(res.data) {
            toastr.success("Email kích hoạt đã được gửi đến hòm thư của bạn. Vui lòng kích hoạt");
        }

    } catch (e) {
        alert(e.response.data.message);
    }
})