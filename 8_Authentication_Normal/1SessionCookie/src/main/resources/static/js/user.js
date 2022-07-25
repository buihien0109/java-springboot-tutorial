const btnGetMessage = document.querySelector(".btn-get-message");
const btnGetMessageAdmin = document.querySelector(".btn-get-message-admin");
const messageEl = document.getElementById("message");

btnGetMessage.addEventListener("click", async function (){
    try {
        let res = await axios.get("/api/v1/message");

        messageEl.innerText = res.data;
    } catch (e) {
        console.log(e);
    }
})

btnGetMessageAdmin.addEventListener("click", async function (){
    try {
        let res = await axios.get("/api/v1/admin/message");

        messageEl.innerText = res.data;
    } catch (e) {
        console.log(e);
    }
})