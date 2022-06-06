import blogRepo from '../repo/blogRepo.js';
import { renderBlogs, renderPagination, renderBlog } from '../render/blogRender.js'

const blogService = {
    async getBlogs(page) {
        try {
            let res = await blogRepo.getBlogs(page);

            // Hiển thị danh sách bài viết
            renderBlogs(res.data.blogs)

            // Hiển thị phân trang
            renderPagination(res.data.totalPage)
        } catch (error) {
            console.log(error);
        }
    },
    async getBlog(id) {
        try {
            let res = await blogRepo.getBlog(id);
    
            renderBlog(res.data);
        } catch (error) {
            alert(error.response.data.message);
        }
    }
}

export default blogService;