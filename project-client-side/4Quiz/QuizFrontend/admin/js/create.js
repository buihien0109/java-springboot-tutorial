// Truy cập vào các thành phần
const quizAnswerContainerEl = document.querySelector(".quiz-answer");
const btnAddAnswer = document.getElementById("btn-add-answer");
const btnSave = document.getElementById("btn-save");
const quizTitleEl = document.getElementById("title");

// Xử lý quay lại trang index
const btnBack = document.querySelector(".btn-back");
btnBack.addEventListener("click", function () {
    window.location.href = "/";
});

// Xử lý khi thêm câu trả lời trên giao diện
btnAddAnswer.addEventListener("click", function () {
    quizAnswerContainerEl.insertAdjacentHTML(
        "beforeend",
        `
        <div class="d-flex align-items-center mb-3 mt-3">
            <input type="radio" name="quiz-answer">
            <input type="text" class="form-control mx-3 quiz-answer-item">
            <button class="btn btn-danger btn-remove-answer" onclick="removeAnswer(this)">X</button>
        </div>
    `
    );
});

// Xử lý khi xóa câu trả lời
function removeAnswer(btn) {
    let answerItem = btn.parentElement;
    answerItem.parentElement.removeChild(answerItem);
}

// Xử lý khi tạo câu hỏi mới
btnSave.addEventListener("click", async function () {
    try {
        // Lấy danh sách câu trả lời
        let quizAnswers = [];

        // Truy cập vào các phần tử ".quiz-answer-item"
        // để lấy ra thông tin về các câu trả lời & xem câu trả lời đó có chính xác hay không
        const quizAnswersEl = document.querySelectorAll(".quiz-answer-item");
        quizAnswersEl.forEach(q => {
            quizAnswers.push({
                title : q.value,
                correct : q.previousElementSibling.checked
            })
        })

        // Kiểm tra xem câu trả lời có đáp án đúng hay không
        let haveAnswerCorrect = quizAnswers.some(q => q.correct)
        if(!haveAnswerCorrect) {
            alert("Câu hỏi phải có đáp án đúng")
            return
        }
        
        // Gửi API
        let res = await axios.post("http://localhost:8080/api/v1/quizzes", {
            title : quizTitleEl.value,
            quizAnswers : quizAnswers
        })

        // Tạo quiz thành công -> trở về trang chủ
        if(res.data) {
            window.location.href = "/";
        }

    } catch (error) {
        console.log(error.response.data.message);
    }
})
