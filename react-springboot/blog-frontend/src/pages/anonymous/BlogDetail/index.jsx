import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import blogApi from "../../../app/api/blogApi";

function BlogDetail() {
    const { blogId, slug } = useParams();
    const [blog, setBlog] = useState({});

    useEffect(() => {
        const fetchBlog = async () => {
            const res = await blogApi.getBlogById(blogId, slug);
            setBlog(res.data);
        };
        fetchBlog();
    }, []);
    return (
        <div className="course-container mt-5">
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-10">
                        <div className="mb-4">
                            <nav
                                style={{ "--bs-breadcrumb-divider": ">" }}
                                aria-label="breadcrumb"
                            >
                                <ol className="breadcrumb">
                                    <li className="breadcrumb-item">
                                        <Link to={"/"}>Bài viết</Link>
                                    </li>
                                    <li
                                        className="breadcrumb-item active"
                                        aria-current="page"
                                    >
                                        {blog.title}
                                    </li>
                                </ol>
                            </nav>
                        </div>

                        <div className="main p-4 shadow-sm">
                            <h2 className="course-title fs-5">{blog.title}</h2>

                            <hr />

                            <div className="course-description">
                                <p>{blog.content}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default BlogDetail;
