const emailEl = document.getElementById("email");
const passwordEl = document.getElementById("password");
const btnLogin = document.getElementById("btn-login");

var modalLogin = new bootstrap.Modal(document.getElementById('modal-login'), {
    keyboard: false
  })

  

btnLogin.addEventListener("click", async function(){
    let email = emailEl.value;
    let password = passwordEl.value;

    if(email == "" || password == "") {
        alert("Email hoặc password không được để trống");
        return;
    }

    try {
        let res = await axios.post(`http://localhost:8080/api/v1/login`, {
            email : email,
            password : password
        });

        addToLocalStorage("userLogin", res.data);
        checkUserLogin(res.data);
        modalLogin.hide();
    } catch (error) {
        console.log(error);
    }
})
