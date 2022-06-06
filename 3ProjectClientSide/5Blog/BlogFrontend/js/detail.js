// URL kết nối API
const API_URL = "http://localhost:8080/api/v1/blogs";

// Truy cập vào các thành phần
const breadcrumbEl = document.querySelector(".breadcrumb-item.active");
const titleEl = document.querySelector(".blog-title");
const contentEl = document.querySelector(".blog-content p");

// Lấy thông tin id trên URL
let params = new URLSearchParams(window.location.search);
let id = params.get("id");

// API lấy thông tin blog
const getBlog = async (id) => {
    try {
        let res = await axios.get(`${API_URL}/${id}`);

        renderBlog(res.data);
    } catch (error) {
        alert(error.response.data.message);
    }
};

// Hiển thị thông tin user lên trên giao diện
const renderBlog = (blog) => {
    titleEl.innerHTML = blog.title;
    contentEl.innerHTML = blog.content;
    breadcrumbEl.innerHTML = blog.title;
};

getBlog(id);