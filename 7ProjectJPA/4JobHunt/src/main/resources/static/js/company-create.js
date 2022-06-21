// Truy cập vào các thành phần
const nameEl = document.getElementById("company-name");
const websiteEl = document.getElementById("company-website");
const emailEl = document.getElementById("company-email");
const cityEl = document.getElementById("company-city");
const addressEl = document.getElementById("company-address");
const descriptionEl = document.getElementById("company-description");

const btnCreateCompany = document.querySelector(".btn-create-company");

// API tạo công ty mới
btnCreateCompany.addEventListener("click", async function () {
    try {
        let res = await axios.post(`/api/admin/companies`, {
            name: nameEl.value,
            website: websiteEl.value,
            email: emailEl.value,
            city: cityEl.value,
            address: addressEl.value,
            description : descriptionEl.value,
        });

        if(res.data) {
            alert("Tạo công ty thành công");
        }

        // Clear dữ liệu để nhập công ty mới
        nameEl.value= "";
        websiteEl.value = "";
        emailEl.value = "";
        addressEl.value = "";
        descriptionEl.value = "";
    } catch (error) {
        alert(error.response.data.message);
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
        cityEl.innerHTML += `<option value="${p.name}">${p.name}</option>`;
    }
}

getProvince();