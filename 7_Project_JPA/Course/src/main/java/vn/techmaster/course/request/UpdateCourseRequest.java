package vn.techmaster.course.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCourseRequest {
    private String name;
    private String type;
    private String description;
    private List<Integer> topics;
    private Long supporterId;
}
