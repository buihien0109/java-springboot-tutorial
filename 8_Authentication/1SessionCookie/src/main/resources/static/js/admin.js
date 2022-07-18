const btnGetMessage = document.querySelector(".btn-get-message");
const messageEl = document.getElementById("message");

btnGetMessage.addEventListener("click", async function (){
    try {
        let res = await axios.get("/api/v1/admin/message");

        messageEl.innerText = res.data;
    } catch (e) {
        console.log(e);
    }
})