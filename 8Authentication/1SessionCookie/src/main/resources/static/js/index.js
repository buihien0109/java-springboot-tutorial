const emailEl = document.getElementById("email");
const passwordEl = document.getElementById("password");
const btnLogin = document.getElementById("btn-login");

const modalLogin = new bootstrap.Modal(document.getElementById('modal-login'), {
    keyboard: false
})

btnLogin.addEventListener("click", async function(){
    let email = emailEl.value;
    let password = passwordEl.value;

    if(email === "" || password === "") {
        alert("Email hoặc password không được để trống");
        return;
    }

    try {
        let res = await axios.post(`/api/v1/login`, {
            email : email,
            password : password
        });

        setTimeout(function (){
            window.location.href = "/"
        },1500)
    } catch (error) {
        console.log(error);
    }
})
