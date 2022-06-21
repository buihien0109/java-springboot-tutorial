// Truy cập vào các thành phần
const titleEl = document.getElementById("job-title");
const descriptionEl = document.getElementById("job-description");
const skillEl = $("#job-skill");
const salaryEl = document.getElementById("job-salary");
const btnCreateJob = document.querySelector(".btn-create-job");

$(document).ready(function() {
    skillEl.select2();
});

// API tạo công ty mới
btnCreateJob.addEventListener("click", async function () {
    try {
        let res = await axios.post(`/api/admin/companies/${company.id}/jobs`, {
            title: titleEl.value,
            description : descriptionEl.value,
            skills : skillEl.val(),
            salary : salaryEl.value
        });

        if(res.data) {
            titleEl.value = "";
            descriptionEl.value = "";
            salaryEl.value = "";
            skillEl.val([]);
            skillEl.trigger("change")

            alert("Tạo công việc thành công");
        }
    } catch (error) {
        console.log(error);
    }
});