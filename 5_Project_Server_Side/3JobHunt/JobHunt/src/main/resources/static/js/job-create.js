// Truy cập vào các thành phần
const titleEl = document.getElementById("job-title");
const descriptionEl = document.getElementById("job-description");
const skillEl = $("#job-skill");
const companyEl = document.getElementById("job-company");
const salaryEl = document.getElementById("job-salary");
const btnCreateJob = document.querySelector(".btn-create-job");

$(document).ready(function() {
    skillEl.select2();
});

// API tạo công ty mới
btnCreateJob.addEventListener("click", async function () {
    try {
        let res = await axios.post(`/api/admin/jobs`, {
            title: titleEl.value,
            description : descriptionEl.value,
            skills : skillEl.val(),
            companyId : companyEl.value,
            salary : salaryEl.value
        });

        if(res.data) {
            toastr.success("Tạo việc làm thành công");

            setTimeout(() => {
                window.location.href = `/admin/jobs/${res.data.id}`;
            }, 1500)
        }
    } catch (error) {
        console.log(error);
    }
});