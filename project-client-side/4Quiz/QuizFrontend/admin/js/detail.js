const quizAnswerContainerEl = document.querySelector(".quiz-answer");
const btnAddAnswer = document.getElementById("btn-add-answer");
const btnSave = document.getElementById("btn-save");
const quizTitleEl = document.getElementById("title");

const API_URL = "http://localhost:8080/api/v1/quizzes";

// Xử lý quay lại trang index
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Lấy ID trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// Gọi API lấy chi tiết quiz
const getQuiz = async (id) => {
    try {
        // Gọi API lấy chi tiết quiz
        let res = await axios.get(`${API_URL}/${id}`);

        // Hiển thị quiz lên trên giao diện
        renderQuiz(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
};

function renderQuiz(quiz) {
    // Render tiêu đề quiz
    quizTitleEl.value = quiz.title;

    // Render các câu trả lời
    quizAnswerContainerEl.innerHTML = "";
    for (let i = 0; i < quiz.quizAnswers.length; i++) {
        const qa = quiz.quizAnswers[i];
        quizAnswerContainerEl.innerHTML += `
            <div class="d-flex align-items-center mb-3 mt-3">
                <input type="radio" name="quiz-answer" ${qa.correct ? "checked" : ""}>
                <input type="text" class="form-control mx-3 quiz-answer-item" value="${qa.title}" data-id=${qa.id}>
                <button class="btn btn-danger btn-remove-answer" onclick="removeAnswer(this, ${qa.id})">X</button>
            </div>
        `
    }
}

btnAddAnswer.addEventListener("click", async function () {
    try {
        // Gọi API tạo câu trả lời trên server
        let res = await axios.post(`${API_URL}/${id}/quiz-answers`)

        // Tạo câu trả lời trên giao diện
        quizAnswerContainerEl.insertAdjacentHTML(
            "beforeend",
            `
                <div class="d-flex align-items-center mb-3 mt-3">
                    <input type="radio" name="quiz-answer">
                    <input type="text" class="form-control mx-3 quiz-answer-item" data-id="${res.data.id}">
                    <button class="btn btn-danger btn-remove-answer" onclick="removeAnswer(this, ${res.data.id})">X</button>
                </div>
            `
        );
    } catch (error) {
        alert(error.response.data.message);
    }
});

async function removeAnswer(btn, quizAnswerId) {
    try {
        // Gọi API xóa trên server
        await axios.delete(`${API_URL}/${id}/quiz-answers/${quizAnswerId}`)

        // Xóa câu trả lời trên giao diện
        let answerItem = btn.parentElement;
        answerItem.parentElement.removeChild(answerItem);
    } catch (error) {
        alert(error.response.data.message);
    }
}

btnSave.addEventListener("click", async function () {
    try {
        // Lấy danh sách câu trả lời
        let quizAnswers = [];

        const quizAnswersEl = document.querySelectorAll(".quiz-answer-item");
        quizAnswersEl.forEach(q => {
            quizAnswers.push({
                id: q.dataset.id,
                title: q.value,
                correct: q.previousElementSibling.checked
            })
        })

        // Kiểm tra xem câu trả lời có đáp án đúng hay không
        let haveAnswerCorrect = quizAnswers.some(q => q.correct)
        if (!haveAnswerCorrect) {
            alert("Câu hỏi phải có đáp án đúng")
            return
        }


        // Gửi API
        let res = await axios.put(`${API_URL}/${id}`, {
            title: quizTitleEl.value,
            quizAnswers: quizAnswers
        })

        // Tạo quiz thành công -> trở về trang chủ
        if (res.data) {
            window.location.href = "/";
        }

    } catch (error) {
        let errorData = error.response.data.message
        if (typeof errorData == "object") {
            let message = ""
            for (const key in errorData) {
                message += `${key} - ${errorData[key]}
`
            }
            alert(message);
        } else {
            alert(errorData);
        }

    }
})

getQuiz(id)
