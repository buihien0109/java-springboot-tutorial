// Tạo biến để lưu danh sách quiz thì API
let quizzes = [];

// Lấy danh sách quiz từ API
async function getQuiz() {
    try { 
        // Gọi API  
        let res = await axios.get("http://localhost:8080/api/v1/quizzes")

        // Lưu lại danh sách quiz từ API
        quizzes = res.data

        // Hiển thị danh sách quiz lên giao diện
        renderQuiz(quizzes);
    } catch (error) {
        alert(error.response.data.message);
    }
}

// Truy cập vào các thành phần
const tableBodyEl = document.querySelector("tbody")

// Function để hiển thị quiz lên trên giao diện
function renderQuiz(arr) {
    // Xóa toàn bộ dữ liệu đang có sẵn
    tableBodyEl.innerHTML = "";

    // Duyệt vòng lặp để hiển thị
    for (let i = 0; i < arr.length; i++) {
        const q = arr[i];
        tableBodyEl.innerHTML += `
            <tr data-id="${q.id}">
                <td>${i + 1}</td>
                <td>${q.title}</td>
                <td>
                    <a href="./detail.html?id=${q.id}" class="btn btn-success">Xem chi tiết</a>
                    <button class="btn btn-danger" onclick="deleteQuiz(${q.id})">Xóa</button>
                </td>
            </tr>
        `
    }
}

// Xử lý phần xóa quiz
const deleteQuiz = async (id) => {
    try {
        // Xác nhận xem có xóa hay không
        isConfirm = confirm("Bạn có muốn xóa không");

        // Nếu đồng ý thì tiến hành xóa
        if(isConfirm) {
            // Gọi API xóa
            await axios.delete(`http://localhost:8080/api/v1/quizzes/${id}`)

            // Nếu xóa thành công trên server thì xóa quiz đó trong mảng ban đầu
            quizzes.forEach((quiz, index) => {
                if(quiz.id == id) {
                    quizzes.splice(index, 1)
                }
            })

            // Hiển thị lại danh sách quiz lên giao diện sau khi xóa
            renderQuiz(quizzes)
        }
    } catch (error) {
        alert(error.response.data.message);
    }
}

getQuiz();