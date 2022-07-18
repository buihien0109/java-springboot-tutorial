const nameEl = document.getElementById("input-name");
const linkEl = document.getElementById("input-cv");
const descriptionEl = document.getElementById("input-description");
const btnApply = document.getElementById("btn-apply");

let file = null;
linkEl.addEventListener("change", function(e) {
    file = e.target.files[0];
})

btnApply.addEventListener("click", function() {
    try {
        let name = nameEl.value;
        let description = descriptionEl.value;

        if(!name || !description || !file) {
            alert("Nội dung không được để trống");
            return;
        }

        console.log(file)

        let formData = new FormData();
        formData.append("applicant", JSON.stringify({
            name : name,
            description : description
        }))
        formData.append("file", file)

        axios.post(`/api/viec-lam/${job.id}/ung-tuyen`, formData)

        alert("Gửi đơn ứng tuyển thành công");
    } catch (e) {
        console.log(e);
    }
})