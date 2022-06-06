import blogService from "../service/blogService.js";
import { currentPage } from "../util/blogUtil.js"

blogService.getBlogs(currentPage)

