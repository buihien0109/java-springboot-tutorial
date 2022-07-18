const API_URL = "http://localhost:8080/api/v1";

const blogRepo = {
    getBlogs(page) {
        return axios.get(`${API_URL}/blogs?page=${page}`);
    },
    getBlog(id) {
        return axios.get(`${API_URL}/blogs/${id}`);
    }
}

export default blogRepo;