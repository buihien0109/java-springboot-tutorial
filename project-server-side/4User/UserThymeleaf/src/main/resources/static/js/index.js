// Định nghĩa API lấy danh sách user
function getUsersAPI(term = "") {
    let url = "/api/v1/users";
    if (term) {
        url = `/api/v1/users/search?name=${term}`;
    }

    return axios.get(url);
}

// Gọi API và hiển thị ra dữ liệu
async function getUsers(term = "") {
    try {
        let res = await getUsersAPI(term);

        users = res.data;
        renderUsers(users);
    } catch (error) {
        alert(error.response.data.message);
    }
}

// Truy cập vào các thành phần
const tableEl = document.querySelector("table");
const messageEl = document.querySelector(".message");
const tableBodyEl = document.querySelector("tbody");

// Hiển thi user ra ngoài giao diện
function renderUsers(arr) {
    // Kiểm tra nếu arr rỗng
    if(arr.length === 0) {
        messageEl.classList.remove("d-none");
        tableEl.classList.add("d-none");

        messageEl.innerHTML = "Danh sách user trống!"
        return;
    }

    // Nếu arr có phần tử
    messageEl.classList.add("d-none");
    tableEl.classList.remove("d-none");

    // Sử dụng vòng lặp để hiển thị dữ liệu
    tableBodyEl.innerHTML = "";
    for (let i = 0; i < arr.length; i++) {
        const u = arr[i];
        tableBodyEl.innerHTML += `
            <tr>
                <td>${i + 1}</td>
                <td>${u.name}</td>
                <td>${u.email}</td>
                <td>${u.phone}</td>
                <td>${u.address}</td>
                <td>
                    <a href="/users/id=${u.id}" class="btn btn-success">Xem chi tiết</a>
                    <button class="btn btn-danger" onclick="deleteUser(${u.id})">Xóa</button>
                </td>
            </tr>
        `;
    }
}

const deleteUser = async (id) => {
    try {
        isConfirm = confirm("Bạn có muốn xóa không");
        if (isConfirm) {
            await axios.delete(`/api/v1/users/${id}`);

            // Xóa user ứng với id trong mảng users ban đầu
            users = users.filter(user => user.id !== id);

            // Render lại giao diện
            renderUsers(users)
        }
    } catch (error) {
        alert(error.response.data.message);
    }
};

const searchEl = document.getElementById("search");
searchEl.addEventListener("keydown", function (e) {
    if (e.keyCode === 13) {
        let term = e.target.value;

        getUsers(term);
    }
});