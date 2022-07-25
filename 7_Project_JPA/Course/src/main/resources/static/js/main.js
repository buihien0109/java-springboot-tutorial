// Truy cập vào các thành phần
const topicEls = document.querySelectorAll(".topic-item input");
const nameInputEl = document.querySelector(".seach-form-input");

// Phân tích URL
const parseQuery = () => {
    const params = new URLSearchParams(window.location.search);
    let topic = params.get("topic") || "";
    let name = params.get("name") || "";

    // Check topic nếu tồn tại
    topicEls.forEach(e => {
        if(e.value === topic) {
            e.checked = true;
        }
    })

    // Insert input
    nameInputEl.value = name;

    return {topic, name}
}

// Tạo URL để chuyển hướng
const generateQuery = (filter) => {
    let query = [];
    for (const key in filter) {
        if(filter[key]) {
            query.push(`${key}=${filter[key]}`)
        }
    }

    let pathName = window.location.pathname

    return `${pathName}?${query.join("&")}`
}

nameInputEl.addEventListener("keydown", (e) => {
    if(e.keyCode === 13) {
        filter.name = nameInputEl.value;
        window.location.href = generateQuery(filter);
    }
})

topicEls.forEach(ele => {
    ele.addEventListener("change", () => {
        filter.topic = ele.value;
        window.location.href = generateQuery(filter);
    })
})

let filter = parseQuery();