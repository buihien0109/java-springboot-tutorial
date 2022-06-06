const API_URL = "http://localhost:8080/admin/api/v1";

// Truy cập vào các thành phần
const companyCityEl = document.getElementById("company-city");
const btnCreateCompany = document.querySelector(".btn-create-company");

const nameEl = document.getElementById("company-name");
const websiteEl = document.getElementById("company-website");
const emailEl = document.getElementById("company-email");
const cityEl = document.getElementById("company-city");
const addressEl = document.getElementById("company-address");
const descriptionEl = document.getElementById("company-description");

// API tạo công ty mới
btnCreateCompany.addEventListener("click", async function () {
    try {
        console.log("abc");
        let res = await axios.post(`${API_URL}/companies`, {
            name: nameEl.value,
            website: websiteEl.value,
            email: emailEl.value,
            city: cityEl.value,
            address: addressEl.value,
            description : descriptionEl.value,
        });

        console.log(res);

        if (res.data) {
            window.location.href = "./company-list.html";
        }
    } catch (error) {
        console.log(error);
    }
});

// API lấy danh sách tỉnh, thành phố
async function getProvince() {
    try {
        // Gọi API lấy danh sách tỉnh thành phố
        let res = await axios.get("https://provinces.open-api.vn/api/p/");

        // Render tỉnh thành phố
        renderProvince(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Hiển thị tỉnh, thành phố ra ngoài giao diện
function renderProvince(arr) {
    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        companyCityEl.innerHTML += `<option value="${p.name}">${p.name}</option>`;
    }
}

getProvince();
