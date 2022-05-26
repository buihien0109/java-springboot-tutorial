import { currentPage } from "../util/blogUtil.js";

// Truy cập vào các thành phần
const blogContainerEl = document.querySelector(".blog-container");
const paginationContainerEl = document.querySelector(".pagination-container");

// Hiển thị blog ra ngoài giao diện
const renderBlogs = (arr) => {
    blogContainerEl.innerHTML = "";

    for (let i = 0; i < arr.length; i++) {
        const b = arr[i];
        blogContainerEl.innerHTML += `
            <div class="col-lg-4 col-md-6">
                <a class="blog-link rounded-3 mb-4 d-block" href="./detail.html?id=${b.id}">
                    <div class="blog-item shadow-sm">
                        <div class="image">
                            <img src="./image/${b.image}"
                                alt="${b.title}">
                        </div>
                        <div class="content p-3">
                            <h4 class="mt-3 mb-3">${b.title}</h4>
                            <p class="text-muted">
                                <span>${b.authorName}</span> |
                                <span>${b.publishedAt}</span> |
                                <span>12 comments</span>
                            </p>
                            <p>${b.description}</p>
                        </div>
                    </div>
                </a>
            </div>
        `;
    }
}

const renderPagination = (totalPage) => {
    let pages = Array.from(Array(totalPage).keys())
    paginationContainerEl.innerHTML = `
        <ul class="pagination justify-content-center">
            <li class="page-item ${currentPage === 1 ? "disabled" : ""}">
                <a 
                    class="page-link" 
                    href="?page=${currentPage - 1}"
                >
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            ${
                pages.map(page => {
                    return `
                        <li class="page-item ${page + 1 === currentPage ? "active" : ""} ${(page + 1 === currentPage) ? "disabled" : ""}">
                            <a 
                                class="page-link" 
                                href="?page=${page + 1}"
                            >${page + 1}</a>
                        </li>`
                }).join("")
            }
            <li class="page-item ${currentPage === totalPage ? "disabled" : ""}">
                <a 
                    class="page-link" 
                    href="?page=${currentPage + 1}"
                >
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    `
}

// Truy cập vào các thành phần
const breadcrumbEl = document.querySelector(".breadcrumb-item.active");
const titleEl = document.querySelector(".blog-title");
const contentEl = document.querySelector(".blog-content p");

const renderBlog = (blog) => {
    titleEl.innerHTML = blog.title;
    contentEl.innerHTML = blog.content;
    breadcrumbEl.innerHTML = blog.title;
};

export { renderBlogs, renderPagination, renderBlog}
