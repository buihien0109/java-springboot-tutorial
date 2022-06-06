const API_URL = "http://localhost:8080/api/v1";

// Đọc thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Khai báo biến
let company;
let jobs = [];


// Định nghĩa các API
const getDetailCompanyAPI = (id) => {
    return axios.get(`${API_URL}/companies/${id}`);
}

const getJobsOfCompanyAPI = (id) => {
    return axios.get(`${API_URL}/companies/${id}/jobs`);
}

// Các xử lý
// Lấy thông tin của công ty
const getDetailCompany = async (id) => {
    try {
        let res = await getDetailCompanyAPI(id);

        company = res.data;
        displayCompanyDetail(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Lấy các công việc của công ty đó
const getJobsOfCompany = async (id) => {
    try {
        let res = await getJobsOfCompanyAPI(id);

        jobs = res.data
        displayListJob(res.data)
    } catch (error) {
        console.log(error);
    }
}

// Truy cập vào các thành phần
const listJobEl = document.querySelector(".list-job");
const logoEl = document.querySelector(".company-logo img");
const nameEl = document.querySelector(".company-name");
const addressEl = document.querySelector(".company-address");
const cityEl = document.querySelector(".company-city");
const websiteEl = document.querySelector(".company-website a");
const emailEl = document.querySelector(".company-email a");
const descriptionEl = document.querySelector(".company-description");

// Hiển thị thông tin của công ty
const displayCompanyDetail = (obj) => {
    logoEl.src = obj.logoPath ? obj.logoPath : "https://via.placeholder.com/180x80";
    nameEl.innerText = obj.name;
    addressEl.innerText = obj.address;
    cityEl.innerText = obj.city;
    websiteEl.innerText = obj.website;
    emailEl.innerText = obj.email;
    descriptionEl.innerText = obj.description;
}


// Hiển thị danh sách job của công ty đó
const displayListJob = (arr) => {
    listJobEl.innerHTML = "";
    for (let i = 0; i < arr.length; i++) {
        const j = arr[i];
        listJobEl.innerHTML += `
            <div class="col-md-3">
                <a href="./job-detail.html?company_id=${id}&job_id=${j.id}">
                    <div class="job-item bg-white p-4 mb-4">
                        <div class="image mt-4 mb-4">
                            <img
                                src="${j.image ? j.image : "https://via.placeholder.com/180x80"}"
                                alt="${j.title}"
                            />
                        </div>
                        <div class="info">
                            <h2 class="fs-5 mb-4 text-center">${j.title}</h2>
                            <p>
                                <span class="me-1"><i class="fa-solid fa-sack-dollar"></i></span>
                                ${formatMoney(j.salary)}
                            </p>
                            <p>
                                <span class="me-1"><i class="fa-solid fa-location-dot"></i></span>
                                ${j.city}
                            </p>
                            <p>
                                <span class="me-1"><i class="fa-solid fa-screwdriver-wrench"></i></span>
                                ${ j.skills.map(skill => `<span class="me-1 badge bg-warning text-dark">${skill}</span>`).join("")}
                            </p>
                            <p>
                                <span class="me-1"><i class="fa-solid fa-clock"></i></span>
                                ${j.startDate}
                            </p>
                        </div>
                    </div>
                </a>
            </div>
        `
    }
}

const formatMoney = number => {
    return number.toLocaleString('it-IT', {style : 'currency', currency : 'VND'});
}


const init = async () => {
    await getDetailCompany(id);
    await getJobsOfCompany(id);
}

init();


