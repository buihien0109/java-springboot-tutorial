import axiosClient from "./axiosClient";

const blogApi = {
    getTodos(queryString) {
        const url = `/blogs?${queryString}`;
        return axiosClient.get(url);
    },
    getBlogById(id, slug) {
        const url = `/blogs/${id}/${slug}`;
        return axiosClient.get(url);
    }
}

export default blogApi;