package vn.techmaster.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationInfo<T> {
    private List<T> data;
    private int totalItems;
    private int currentPage;
}
