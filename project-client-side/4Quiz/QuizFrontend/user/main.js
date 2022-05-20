// Truy cập vào các thành phần
const quiz_question = document.querySelector(".quiz-question p");
const quiz_answer = document.querySelector(".quiz-answer");
const btnNext = document.querySelector(".next-btn");
const btnFinish = document.querySelector(".finish-btn");
const btnBack = document.querySelector(".back-btn");

const progressBarEl = document.querySelector(".quiz-progress-bar")
const resultsEl = document.querySelector(".quiz-results")
const resultsScoreEl = document.querySelector(".quiz-results-score")
const quizBottomEl = document.querySelector(".quiz-bottom")

// Khai báo API URL
const API_URL = "http://localhost:8080/user/api/v1/quizzes"
// Khai báo biến
let questions; // lưu trữ danh sách các câu hỏi sau khi gọi API

let current_question = 0; // Câu hỏi hiện tại có chỉ số bao nhiêu trong mảng
let score = 0; // Số điểm hiện tại
let answerId; // Đáp án mà người dùng chọn với câu hỏi hiện tại

// Hiển thị câu hỏi theo index
function showQuestion(count) {
    // lấy ra câu hỏi hiện tại
    let question = questions[count];

    // Hiển thị câu hỏi
    quiz_question.innerText = `Câu ${count + 1} : ${question.title}`;

    // Hiển thị câu trả lời
    quiz_answer.innerHTML = "";
    for (let i = 0; i < question.quizAnswers.length; i++) {
        let q = question.quizAnswers[i];
        quiz_answer.innerHTML += `
            <div class="quiz-item">
                <input 
                    type="radio" 
                    id="answer-${i + 1}" 
                    name="question-${count + 1}"
                    data-id=${q.id}
                />
                <label for="answer-${i + 1}">
                    <span></span>
                    <p>${q.title}</p>
                </label>
            </div>
        `;
    }

    // Update progress bar
    updateProgressBar();

    // Disable nút next đến khi chọn đáp án thì thôi
    btnNext.classList.add("disable");

    // Show nút submit
    if (count == questions.length - 1) {
        btnFinish.style.display = "inline-block";
        btnNext.style.display = "none";
    }

    // Lắng nghe sự kiện khi người dùng chọn đáp án
    let inputs = document.querySelectorAll(".quiz-item input");
    Array.from(inputs).forEach((input) => {
        input.addEventListener("change", function () {
            if(current_question != questions.length - 1) {
                btnNext.classList.remove('disable');
            } else {
                btnFinish.classList.remove('disable');
            }
            
            answerId = input.dataset.id;
        });
    });
}

btnNext.addEventListener("click", nextQuestion);
btnFinish.addEventListener("click", showResult);
btnBack.addEventListener("click", function () {
    window.location.reload();
});

async function nextQuestion() {
    try {
        let res = await axios.post(`${API_URL}/${questions[current_question].id}/check`, {
            id : answerId
        })
        if(res.data) {
            score++;
        }

        current_question++;
        if (current_question > questions.length - 1) {
            current_question = questions.length - 1;
        }
        showQuestion(current_question);
    
    } catch (error) {
        console.log(error);
    }
}

async function showResult() {
    try {
        let res = await axios.post(`${API_URL}/${questions[current_question].id}/check`, {
            id : answerId
        })
        if(res.data) {
            score++;
        }

        quizBottomEl.style.display = "none";
        progressBarEl.style.display = "none";
        resultsEl.style.display = "block";
        resultsScoreEl.innerText = `Bạn đã trả lời đúng ${score}/${questions.length} câu hỏi`;
    } catch (error) {
        console.log(error);
    }
}

// Cập nhật thanh tiến trình
function updateProgressBar() {
    progressBarEl.style.width = `${((current_question + 1) * 100) / questions.length}%`;
}

// Lấy danh sách quiz
async function getQuiz() {
    try {
        let res = await axios.get(API_URL);
        questions = res.data;
        showQuestion(current_question);
    } catch (error) {
        console.log(error);
    }
}

getQuiz();
