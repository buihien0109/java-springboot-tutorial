const messageEl = document.getElementById("message");

const transport = axios.create({
    withCredentials: true
  })

const getData = async () => {
    try {
        let res = await transport.get("http://localhost:8080/api/v1/hasroleuser");
        messageEl.innerHTML = res.data;
    } catch (error) {
        console.log(error);
    }
}

getData()