// Truy cập vào các thành phần
const colorEl = document.querySelector(".color");
const btns = document.querySelectorAll(".btn");

btns.forEach(btn => {
    btn.addEventListener("click", async () => {
        try {
            // Lấy ra loại color
            let valueType = btn.dataset.type;

            // Gửi GET request với value type tương ứng
            let res = await axios.get(`http://localhost:8080/random-color?type=${valueType}`);

            // Hiển thị mã màu lên trên giao diện
            colorEl.innerText = res.data;

            // Thay đổi background cho body
            document.body.style.backgroundColor = res.data;
        } catch (error) {
            console.log(error.response.data.message);
        }
    })
})