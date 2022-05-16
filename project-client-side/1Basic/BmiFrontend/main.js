// Truy cập vào các thành phần
let weightEl = document.querySelector("#weight");
let heightEl = document.querySelector("#height");

let btnGet = document.querySelector("#btn-get");
let btnPost = document.querySelector("#btn-post");

let resultEl = document.querySelector("h2 span");
let descriptionEl = document.querySelector("p span");

// Xử lý tính BMI bằng GET Request
btnGet.addEventListener("click", async () => {
    try {
        // Lấy giá trị trong ô input
        let weightValue = weightEl.value;
        let heightValue = heightEl.value;

        // Kiểm tra giá trị có trống hay không
        if (!weightValue || !heightValue) {
            alert("Cân năng hoặc chiều cao không được để trống!");
            return;
        }

        // Gọi API để tính toán chỉ số BMI
        let res = await axios.get(
            `http://localhost:8080/bmi?weight=${weightValue}&height=${heightValue}`
        );

        displayInfo(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
});

// Xử lý tính BMI bằng POST Request
btnPost.addEventListener("click", async () => {
    try {
        // Lấy giá trị trong ô input
        let weightValue = weightEl.value;
        let heightValue = heightEl.value;

        // Kiểm tra giá trị có trống hay không
        if (!weightValue || !heightValue) {
            alert("Cân năng hoặc chiều cao không được để trống!");
            return;
        }

        // Gọi API để tính toán chỉ số BMI
        let res = await axios.post("http://localhost:8080/bmi", {
            weight: weightValue,
            height: heightValue,
        });

        displayInfo(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
});

// Hiển thị thông tin với chỉ số BMI tương ứng
function displayInfo(bmi) {
    resultEl.innerText = bmi;

    if (bmi < 18.5) {
        descriptionEl.innerText = "Thiếu cân";
    } else if (bmi >= 18.5 && bmi <= 24.9) {
        descriptionEl.innerText = "Bình thường";
    } else if (bmi >= 25 && bmi <= 29.9) {
        descriptionEl.innerText = "Thừa cân";
    } else {
        descriptionEl.innerText = "Béo phì";
    }
}
