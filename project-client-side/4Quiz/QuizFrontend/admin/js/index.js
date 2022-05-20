let quizzes = [];

async function getQuiz() {
    try {   
        let res = await axios.get("http://localhost:8080/api/v1/quizzes")

        quizzes = res.data
        renderQuiz(quizzes);
    } catch (error) {
        alert(error.response.data.message);
    }
}

const tableBodyEl = document.querySelector("tbody")

function renderQuiz(arr) {
    tableBodyEl.innerHTML = "";
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

const deleteQuiz = async (id) => {
    try {
        isConfirm = confirm("Bạn có muốn xóa không");
        if(isConfirm) {
            await axios.delete(`http://localhost:8080/api/v1/quizzes/${id}`)

            quizzes.forEach((quiz, index) => {
                if(quiz.id == id) {
                    quizzes.splice(index, 1)
                }
            })

            renderQuiz(quizzes)
        }
    } catch (error) {
        alert(error.response.data.message);
    }
}

getQuiz();