const userMenuEl = document.getElementById("user-menu");

const logout = async () => {
    try {
        await axios.post("http://localhost:8080/api/v1/logout");
        removeFromLocalStorage("userLogin");
        
        window.location.href = "./index.html";
    } catch (error) {
        console.log(error);
    }
}


// Kiểm tra user đã đăng nhập chưa để chuyển avatar
const checkUserLogin = () => {
    let user = getFromLocalStorage("userLogin");
    
    if(user) {
        userMenuEl.innerHTML = `
            ${
                user.roles.some(role => role.name == "USER" || role.name == "ADMIN")
                ? `
                    <li class="nav-item">
                        <a class="nav-link" href="./user.html">Trang User</a>
                    </li>
                `
                : ""
            }
            
            ${
                user.roles.some(role => role.name == "ADMIN")
                ? `
                    <li class="nav-item">
                        <a class="nav-link" href="./admin.html">Trang Admin</a>
                    </li>
                `
                : ""
            }
            <li class="nav-item">
                <button class="nav-link bg-transparent border-0" onclick="logout()">Đăng xuất</button>
            </li>
        `;
    } else {
        userMenuEl.innerHTML = `
            <li class="nav-item">
                <button type="button" class="nav-link bg-transparent border-0" data-bs-toggle="modal"
                    data-bs-target="#modal-login">
                    Đăng nhập
                </button>
            </li>
        `;
    }
}

// Lưu dữ liệu vào LocalStorage
const addToLocalStorage = (key, value) => {
    localStorage.setItem(key, JSON.stringify(value));
}

// Lấy dữ liệu từ LocalStorage và hiển thị
const getFromLocalStorage = (key) => {
    const valueLocalStorage = localStorage.getItem(key);
    if (valueLocalStorage) {
        return JSON.parse(valueLocalStorage);
    }
    return null;
}

// Xóa dữ liệu từ LocalStorage
const removeFromLocalStorage = (key) => {
    localStorage.removeItem(key);
}

checkUserLogin();