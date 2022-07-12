// Truy cập vào các thành phần
const nameEl = document.getElementById("company-name");
const websiteEl = document.getElementById("company-website");
const emailEl = document.getElementById("company-email");
const cityEl = document.getElementById("company-city");
const descriptionEl = document.getElementById("company-description");
const logoInputEl = document.getElementById("company-logo-input");
const logoEl = document.getElementById("company-logo-preview");

const btnUpdateCompany = document.querySelector(".btn-update-company");
const btnDeleteCompany = document.querySelector(".btn-delete-company");

// Cập nhật thông tin công ty
btnUpdateCompany.addEventListener("click", async () => {
    try {
        // Gọi API
        let res = await axios.put(`/api/admin/companies/${company.id}`, {
            name: nameEl.value,
            website: websiteEl.value,
            email: emailEl.value,
            city: cityEl.value,
            description: descriptionEl.value,
        });

        if (res.data) {
            toastr.success("Cập nhật thông tin nhà tuyển dụng thành công");
        }
    } catch (e) {
        console.log(e.response.data.message);
    }
})

// Xóa công ty
btnDeleteCompany.addEventListener("click", async () => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
        if (isConfirm) {
            await axios.delete(`/api/admin/companies/${company.id}`);

            toastr.success("Xóa nhà tuyển dụng thành công");

            setTimeout(() => {
                window.location.href = "/admin/companies";
            }, 1500)
        }
    } catch (e) {
        alert(e.response.data.message);
    }
})

// Đổi logo
logoInputEl.addEventListener("change", async (e) => {
    try {
        let file = e.target.files[0];
        let formData = new FormData();
        formData.append("file", file);

        let res = await axios.post(`/api/admin/companies/${company.id}/upload-logo`, formData)
        if (res.data) {
            toastr.success("Cập nhật logo thành công");
            logoEl.src = res.data;
        }
    } catch (e) {
        alert(e.response.data.message);
    }
})


// API lấy danh sách tỉnh, thành phố
const getProvince = async () => {
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
const renderProvince = arr => {
    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        cityEl.innerHTML += `<option value="${p.name}">${p.name}</option>`;
    }
}

const init = async () => {
    await getProvince();
    cityEl.value = company.city;
}

init();


