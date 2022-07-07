// Truy cập vào các thành phần
const titleEl = document.getElementById("job-title");
const descriptionEl = document.getElementById("job-description");
const skillEl = $("#job-skill");
const salaryEl = document.getElementById("job-salary");

const btnUpdateJob = document.querySelector(".btn-update-job");
const btnDeleteJob = document.querySelector(".btn-delete-job");

$(document).ready(function() {
    skillEl.select2();

    skillEl.val(job.skills).trigger("change")
});

// Cập nhật thông tin công việc
btnUpdateJob.addEventListener("click", async () => {
    try {
        let res = await axios.put(`/api/admin/jobs/${job.id}`, {
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

// Xóa công việc
btnDeleteJob.addEventListener("click", async () => {
    try {
        let isConfirm = confirm("Bạn có muốn xóa không?");
        if (isConfirm) {
            await axios.delete(`/api/admin/jobs/${job.id}`);

            alert("Xóa công việc thành công");
            window.location.href = "/admin/jobs";
        }
    } catch (e) {
        alert(e.response.data.message);
    }
})