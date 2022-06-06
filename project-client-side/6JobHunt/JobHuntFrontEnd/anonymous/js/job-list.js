const API_URL = "http://localhost:8080/api/v1";

// Khai báo biến
let jobs = [];

const getJobsAPI = () => {
    return axios.get(`${API_URL}/jobs`);
}

// Các xử lý
// Lấy các công việc của công ty đó
const getJobs = async () => {
    try {
        let res = await getJobsAPI();
        console.log(res.data);

        jobs = res.data
        displayListJob(res.data)
    } catch (error) {
        console.log(error);
    }
}

// Truy cập vào các thành phần
const listJobEl = document.querySelector(".list-job");

// Hiển thị danh sách job của công ty đó
const displayListJob = (arr) => {
    listJobEl.innerHTML = "";
    for (let i = 0; i < arr.length; i++) {
        const j = arr[i];
        listJobEl.innerHTML += `
            <div class="col-md-3">
                <a href="./job-detail.html?company_id=${j.companyId}&job_id=${j.id}">
                    <div class="job-item bg-white p-4 mb-4">
                        <div class="image mt-4 mb-4">
                            <img src="${j.image ? j.image : "https://via.placeholder.com/180x80"}" alt="${j.title}" />
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


getJobs();


