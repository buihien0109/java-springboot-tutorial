const messageEl = document.getElementById("message");

const getData = async () => {
    try {
        let res = await axios.get("http://localhost:8080/api/v1/admin/hasroleadmin");
        messageEl.innerHTML = res.data;
    } catch (error) {
        console.log(error);
    }
}

getData();