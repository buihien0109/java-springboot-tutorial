// Truy cập vào các thành phần
let tableContentEl = document.querySelector(".list-user tbody");
let inputEl = document.querySelector(".seach-form-input");
let btnSearch = document.querySelector(".seach-form-button");

// Khai báo biến
let users = [];


// Gọi API lấy danh sách user
const getUsers = async () => {
    try {
        let res = await axios.get("https://api.github.com/users");

        users = res.data;
        render(users);
    } catch (error) {
        
    }
}

// Tìm kiếm user theo tên
const seachUser = term => {
    let userSearch = users.filter(user => user.login.toLowerCase().includes(term.toLowerCase()));
    render(userSearch);
}

// Tìm kiếm user bằng click
inputEl.addEventListener("keydown", function(e){
    if(e.keyCode == 13) {
        let term = inputEl.value;
        if(term == "") {
            alert("Từ khóa không được để trống");
            return;
        }
        seachUser(term);
        inputEl.value = "";
    }
})

// Tìm kiếm user bằng button
btnSearch.addEventListener("click", function() {
    let term = inputEl.value;
        if(term == "") {
            alert("Từ khóa không được để trống");
            return;
        }
        seachUser(term);
        inputEl.value = "";
})


// Hiển thị danh sách user + phân trang
const render = arr => {
    $('.pagination-container').pagination({
        dataSource: arr,
        pageSize: 5,
        callback: function(data) {
            renderUser(data);
        }
    })
}

// Hiển thị danh sách user
const renderUser = arr => {
    tableContentEl.innerHTML = "";

    let html = "";
    arr.forEach((u, i) => {
        html += `
            <tr>
                <td>${u.id}</td>
                <td>
                    <img src="${u.avatar_url}" alt="${u.login}" />
                </td>
                <td>${u.login}</td>
                <td>${u.html_url}</td>
                <td>${u.repos_url}</td>
            </tr>
        `
    });

    tableContentEl.innerHTML = html;
}

getUsers();
