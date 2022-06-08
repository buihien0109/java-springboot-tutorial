const API_URL = "http://localhost:8080/admin/api/v1";

// ***************** Truy cập vào các thành phần *****************
const cNameEl = document.querySelector("#company-name");
const cWebsiteEl = document.querySelector("#company-website");
const cEmailEl = document.querySelector("#company-email");
const cDescriptionEl = document.querySelector("#company-description");
const cCityEl = document.querySelector("#company-city");
const cAddressEl = document.querySelector("#company-address");
const cLogoPreviewEl = document.querySelector("#company-logo-preview");

const breadcrumbEl = document.querySelector(".breadcrumb-item.active");

// ***************** Hiển thị Company ***************** 
// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// API lấy thông tin user
const getCompany = async (id) => {
    try {
        let res = await axios.get(`${API_URL}/companies/${id}`);

        renderCompany(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
};

// Hiển thị thông tin user lên trên giao diện
const renderCompany = (company) => {
    cNameEl.value = company.name;
    cWebsiteEl.value = company.website;
    cEmailEl.value = company.email;
    cDescriptionEl.value = company.description;
    cCityEl.value = company.city;
    cAddressEl.value = company.address;

    // Nếu company không có logo thì lấy logo mặc định
    if(company.logoPath) {
        cLogoPreviewEl.src = company.logoPath;
    } else {
        cLogoPreviewEl.src = "https://via.placeholder.com/200"
    }

    // Cập nhật breadcrum
    breadcrumbEl.innerText = company.name;
    
    // Cập nhật tiêu đề trang web
    document.title = company.name;
};

// ***************** Cập nhật thông tin công ty *****************
const btnUpdateCompany = document.querySelector(".btn-update-company");
btnUpdateCompany.addEventListener("click", async function() {
    try {
        let res = await axios.put(`${API_URL}/companies/${id}`, {
            name : cNameEl.value,
            website : cWebsiteEl.value,
            email : cEmailEl.value,
            description : cDescriptionEl.value,
            city : cCityEl.value,
            address : cAddressEl.value
        })
        console.log(res);
    } catch (error) {
        console.log(error);
    }
})

// ***************** Xóa công ty *****************
const btnDeleteCompany = document.querySelector(".btn-delete-company");
btnDeleteCompany.addEventListener("click", async function() {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
        if(isConfirm) {
            await axios.delete(`${API_URL}/companies/${id}`);
            
            window.location.href = "./company-list.html";
        }
    } catch (error) {
        console.log(error);
    }
})


// ***************** Cập nhật logo *****************
// Đổi avatar
function uploadFileAPI(file) {
    let formData = new FormData();
    formData.append("file", file);

    return axios({
        method : "POST",
        url : `${API_URL}/companies/${id}/upload-logo`,
        data : formData,
    })
}

const fileUploadEl = document.querySelector("#company-logo-input");
fileUploadEl.addEventListener("change", async (event) => {
    try {
        const file = event.target.files[0];
        let res = await uploadFileAPI(file);
        
        cLogoPreviewEl.src = "http://localhost:8080" + res.data
    } catch (error) {
        console.log(error);
    }
})

// ***************** API lấy danh sách công việc *****************
const jobTableEl = document.querySelector(".job-table tbody");

const getJobs = async (companyId) => {
    try {
        let res = await axios.get(`${API_URL}/companies/${companyId}/jobs`);
        console.log(res);

        renderJob(res.data);
    } catch (error) {
        console.log(error);
    }
}

const renderJob = arr => {
    jobTableEl.innerHTML = "";
    for (let i = 0; i < arr.length; i++) {
        const j = arr[i];
        jobTableEl.innerHTML += `
            <tr>
                <td>${i+1}</td>
                <td>
                    <a href="./job-edit.html?company_id=${id}&job_id=${j.id}">${j.title}</a>
                </td>
                <td>
                    ${j.skills.map(skill => `<span class="me-1 badge bg-warning text-dark">${skill}</span>`).join("")}
                    
                </td>
                <td>${formatMoney(j.salary)}</td>
                <td>${j.numberOfApplicant ? `${j.numberOfApplicant} ứng viên` : ""}</td>
            </tr>
        `
    }
}

const formatMoney = number => {
    return number.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
}


// ***************** API lấy danh sách tỉnh, thành phố *****************
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
        cCityEl.innerHTML += `<option value="${p.name}">${p.name}</option>`;
    }
}

async function init() {
    await getProvince();
    await getCompany(id);
    await getJobs(id);
}

init()