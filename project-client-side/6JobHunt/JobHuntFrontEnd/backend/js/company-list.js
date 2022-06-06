const API_URL = "http://localhost:8080/admin/api/v1";

// Truy cập vào các thành phần
const companyTableEl = document.querySelector(".company-table tbody");

// Gọi API lấy danh sách công ty và hiển thị
const getCompanies = async () => {
    try {
        let res = await axios.get(`${API_URL}/companies`);

        renderCompanies(res.data);
    } catch (error) {
        
    }
}

// Hiển thị danh sách công ty ra ngoài giao diện
const renderCompanies = arr => {
    companyTableEl.innerHTML = "";
    arr.forEach((c, i) => {
        companyTableEl.innerHTML += `
            <tr>
                <td>${i + 1}</td>
                <td><img src="${c.logoPath}" alt="${c.name}"></td>
                <td>
                    <a href="./company-edit.html?id=${c.id}">${c.name}</a>
                </td>
                <td>${c.city}</td>
                <td>${c.numberOfJobs}</td>
            </tr>
        `
    });
}

getCompanies();