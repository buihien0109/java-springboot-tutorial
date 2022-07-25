// API lấy danh sách tỉnh - thành phố
async function getProvince(ele) {
    try {
        // Gọi API lấy danh sách tỉnh thành phố
        let res = await axios.get("https://provinces.open-api.vn/api/p/");

        // Render tỉnh thành phố
        renderProvince(res.data, ele);
    } catch (error) {
        console.log(error);
    }
}

function renderProvince(arr, ele) {
    for (let i = 0; i < arr.length; i++) {
        const p = arr[i];
        ele.innerHTML += `<option value="${p.name}">${p.name}</option>`
    }
}