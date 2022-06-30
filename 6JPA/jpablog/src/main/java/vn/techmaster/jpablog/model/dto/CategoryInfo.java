package vn.techmaster.jpablog.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryInfo {
    private String name;
    private Integer blogCount;
}
