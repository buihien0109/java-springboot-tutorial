// Truy cập vào các thành phần
const titleEl = document.getElementById("job-title");
const descriptionEl = document.getElementById("job-description");
const skillEl = $("#job-skill");
const salaryEl = document.getElementById("job-salary");
const btnUpdateJob = document.querySelector(".btn-update-job");

const imagePreviewEl = document.getElementById("job-logo-preview");
const imageInputEl = document.getElementById("job-logo-input");

$(document).ready(function() {
    skillEl.select2();

    skillEl.val(job.skills);
    skillEl.trigger("change")
});

// Cập nhật thông tin công ty
btnUpdateJob.addEventListener("click", async () => {
    try {
        let res = await axios.put(`/api/admin/companies/${company.id}/jobs/${job.id}`, {
            title : titleEl.value,
            description : descriptionEl.value,
            salary : salaryEl.value,
            skills : skillEl.val()
        });
        if(res.data) {
            alert("Cập nhật thông tin thành công");
        }
    } catch (e) {
        alert(e.response.data.message);
    }
})

// Cập nhật ảnh
// Đổi logo
imageInputEl.addEventListener("change", async (e) => {
    try {
        let file = e.target.files[0];
        let formData = new FormData();
        formData.append("file", file);

        let res = await axios.post(`/api/admin/companies/${company.id}/jobs/${job.id}/upload-file`, formData)
        imagePreviewEl.src = res.data;
    } catch (e) {
        alert(e.response.data.message);
    }
})