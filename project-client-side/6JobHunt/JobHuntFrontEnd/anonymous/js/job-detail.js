const API_URL = "http://localhost:8080/api/v1";

// Đọc thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let companyId = params.get("company_id");
let jobId = params.get("job_id");

// Khai báo biến
let company;
let job;


// Định nghĩa các API
const getCompanyAPI = (companyId) => {
    return axios.get(`${API_URL}/companies/${companyId}`);
}

const getJobAPI = (companyId, jobId) => {
    return axios.get(`${API_URL}/companies/${companyId}/jobs/${jobId}`);
}

// Các xử lý
// Lấy thông tin của công ty
const getCompany = async (companyId) => {
    try {
        let res = await getCompanyAPI(companyId);
        console.log(res.data);

        company = res.data;
        displayCompany(res.data);
    } catch (error) {
        console.log(error);
    }
}

// Lấy các công việc của công ty đó
const getJob = async (companyId, jobId) => {
    try {
        let res = await getJobAPI(companyId, jobId);
        console.log(res.data);

        job = res.data
        displayJob(res.data)
    } catch (error) {
        console.log(error);
    }
}

// Truy cập vào các thành phần
let companDetailEl = document.querySelector(".company-detail");
let jobDetailEl = document.querySelector(".job-detail-inner");


// Hiển thị thông tin của công ty
const displayCompany = (obj) => {
    companDetailEl.innerHTML = `
        <a class="company-link" href="./company-detail.html?id=${obj.id}">
            <div class="company bg-white p-3 mb-3">
                <div class="image mt-5 mb-5">
                    <img src="${obj.logoPath ? obj.logoPath : "https://via.placeholder.com/180x80"}" alt="${obj.name}" />
                </div>
                <div class="info">
                    <h2 class="fs-5 mb-2 text-center">${obj.name}</h2>
                    <p>${obj.description}</p>
                </div>
            </div>
        </a>
    `
}


// Hiển thị danh sách job của công ty đó
const displayJob = (obj) => {
    jobDetailEl.innerHTML = `
        <h2 class="fw-normal job-title">${obj.title}</h2>
        <a href="./apply.html?company_id=${companyId}&job_id=${jobId}" class="btn-apply btn d-block text-white w-100 mt-5 mb-5">Ứng Tuyển</a>

        <hr>

        <div class="mt-4 mb-4">
            <div class="job-skills">
                ${obj.skills.map(skill => {
                    return `<span class="skill-item">${skill}</span>`
                }).join("")}
            </div>

            <div class="job-info mt-4">
                <div class="job-info-item d-flex">
                    <span><i class="fa-solid fa-location-dot"></i></span>
                    <span class="d-block job-address">${obj.address}</span>
                </div>
                <div class="job-info-item d-flex">
                    <span><i class="fa-solid fa-city"></i></span>
                    <span class="d-block job-city">${obj.city}</span>
                </div>
                <div class="job-info-item d-flex">
                    <span class="d-block"><i class="fa-solid fa-sack-dollar"></i></i></span>
                    <span class="job-salary">${formatMoney(obj.salary)}</span>
                </div>
                <div class="job-info-item d-flex">
                    <span class="d-block"><i class="fa-solid fa-calendar-days"></i></span>
                    <span class="job-end-date">Thời hạn cuối : ${obj.endDate}</span>
                </div>
            </div>
        </div>

        <hr>

        <div class="mt-5 mb-5">
            <h3 class="fw-normal">Mô Tả Công Việc</h3>
            <div class="job-description">${obj.description}</div>
        </div>
    `

}

const formatMoney = number => {
    return number.toLocaleString('it-IT', { style: 'currency', currency: 'VND' });
}


const init = async () => {
    await getCompany(companyId);
    await getJob(companyId, jobId);
}

init();


