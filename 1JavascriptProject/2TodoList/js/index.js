// Truy cập vào các thành phần
let todoListEl = document.querySelector(".todo-list");
let inputEl = document.getElementById("todo-input");
let btnAdd = document.getElementById("btn-add");
let todoOptionItemsEl = document.querySelectorAll(".todo-option-item input");

// Khai báo biến
let isUpdate = false; // Kiểm tra xem khi nào công việc update
let idUpdate = ""; // ID của công việc đang thực hiện update là gì?
let todos = []; // Lưu trữ danh sách công việc

// Tự động random ID của công việc ngẫu nhiên không trùng nhau
function randomId() {
    return Math.floor(Math.random() * 1000);
}

// Thêm công việc
btnAdd.addEventListener("click", function () {
    let title = inputEl.value;

    if (title == "") {
        alert("Công việc không được để trống!");
        return;
    }

    if (isUpdate) {
        // Update công việc
        for (let i = 0; i < todos.length; i++) {
            if (todos[i].id == idUpdate) {
                todos[i].title = title;
            }
        }

        btnAdd.innerText = "Thêm";
        isUpdate = false;
        idUpdate = "";
    } else {
        // Thêm công viêc mới
        let newTodo = {
            id: randomId(),
            title: title,
            status: false,
        };
        todos.push(newTodo);
    }

    inputEl.value = "";
    addToLocalStorage(todos);
});

function renderTodo(arr) {
    todoListEl.innerHTML = "";

    // Kiểm tra nếu người dùng lựa chọn
    // 1 - Xem tất cả các công việc
    // 2 - Những công việc chưa hoàn thành
    // 3 - Những công việc đã hoàn thành
    let optionSelected = getOptionSelected();
    let newTodos;
    switch (optionSelected) {
        case 1:
            newTodos = [...arr];
            break;
        case 2:
            newTodos = arr.filter((todo) => todo.status === false);
            break;
        case 3:
            newTodos = arr.filter((todo) => todo.status === true);
            break;
        default:
            newTodos = [...arr];
            break;
    }

    // Kiểm tra khi trong danh sách không còn công việc nào cả
    if (newTodos.length == 0) {
        todoListEl.innerHTML = `<p class="todos-empty">Không có công việc nào trong danh sách</p>`;
        return;
    }

    // Duyệt danh sách công việc cần làm và render ra ngoài màn hình
    for (let i = 0; i < newTodos.length; i++) {
        const t = newTodos[i];
        todoListEl.innerHTML += `
            <div class="todo-item ${t.status ? "active-todo" : ""}">
                <div class="todo-item-title">
                    <input 
                        type="checkbox"
                        ${t.status ? "checked" : ""}
                        onclick="toggleStatus(${t.id})"
                    >
                    <p>${t.title}</p>
                </div>
                <div class="option">
                    <button class="btn btn-update" onclick="updateTodo(${t.id})">
                        <img src="./img/pencil.svg" alt="icon">
                    </button>
                    <button class="btn btn-delete" onclick="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon">
                    </button>
                </div>
            </div>
        `;
    }
}

// Kiểm tra khi có sự thay đổi trong nút xem công việc thì render giao diện
todoOptionItemsEl.forEach((btn) => {
    btn.addEventListener("change", function () {
        addToLocalStorage(todos);
    });
});

// Lấy giá trị trong 1 ô radio
function getOptionSelected() {
    for (let i = 0; i < todoOptionItemsEl.length; i++) {
        if (todoOptionItemsEl[i].checked) {
            return parseInt(todoOptionItemsEl[i].value);
        }
    }
}

// Thay đổi status của 1 công việc
function toggleStatus(id) {
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            todos[i].status = !todos[i].status;
        }
    }
    addToLocalStorage(todos);
}

// Xóa công việc
function deleteTodo(id) {
    let isConfirm = confirm("Bạn có muốn xóa không?");
    if (isConfirm) {
        for (let i = 0; i < todos.length; i++) {
            if (todos[i].id == id) {
                todos.splice(i, 1);
            }
        }
        addToLocalStorage(todos);
    }
}

// Update công việc
function updateTodo(id) {
    let title = "";
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            title = todos[i].title;
        }
    }

    btnAdd.innerText = "Cập nhật";
    inputEl.value = title;
    inputEl.focus();

    isUpdate = true;
    idUpdate = id;
}

// Lưu dữ liệu vào LocalStorage
function addToLocalStorage(todos) {
    localStorage.setItem("todos", JSON.stringify(todos));
    renderTodo(todos);
}

// Lấy dữ liệu từ LocalStorage và hiển thị
function getFromLocalStorage() {
    const todosLocalStorage = localStorage.getItem("todos");
    if (todosLocalStorage) {
        todos = JSON.parse(todosLocalStorage);
        renderTodo(todos);
    }
}

// Function được gọi khi load trang
function init() {
    getFromLocalStorage();
}

window.onload = init;
