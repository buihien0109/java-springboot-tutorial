const API_URL = "http://localhost:8080/api/v1";

const getCompaniesAPI = () => {
    return axios.get(`${API_URL}/companies`);
}

const getCompanies = async () => {
    try {
        let res = await getCompaniesAPI();
        console.log(res);

        renderTopCompany(res.data);
    } catch (error) {
        console.log(error);
    }
}

const listCompanyEl = document.querySelector(".list-company");

const renderTopCompany = (arr) => {
    listCompanyEl.innerHTML = "";

    for (let i = 0; i < arr.length; i++) {
        const c = arr[i];
        listCompanyEl.innerHTML += `
            <div class="col-md-3">
                <a href="./company-detail.html?id=${c.id}">
                    <div class="company bg-white p-4 mb-4">
                        <div class="image mt-5 mb-5">
                            <img src="${c.logoPath ? c.logoPath : "https://via.placeholder.com/180x80"}"
                                alt="${c.name}" />
                        </div>
                        <div class="info">
                            <h2 class="fs-5 mb-5 text-center">
                            ${c.name}
                            </h2>
                            <p class="text-center">
                                <span class="text-danger">${c.numberOfJobs} việc làm</span>
                                -
                                <span>${c.city}</span>
                            </p>
                        </div>
                    </div>
                </a>
            </div>
        `
    }
}

getCompanies();