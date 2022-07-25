package vn.techmaster.course.util;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageUtil<T> {
    private List<T> content;
    private int pageNumber;
    private int pageSize;
    private List<Integer> totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;
}
