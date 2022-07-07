package vn.techmaster.blog.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PageUtil {
    public int limit;
    public int page;

    public PageUtil(int limit, int page) {
        this.limit = limit;
        this.page = Math.max(page, 1);
    }

    public int calculateOffset() {
        return (page - 1) * limit;
    }

    public int calculateTotalPage(int totalItems) {
        int totalPages;
        if (totalItems % limit == 0) {
            totalPages = totalItems / limit;
        } else {
            totalPages = totalItems / limit + 1;
        }

        return totalPages;
    }
}
