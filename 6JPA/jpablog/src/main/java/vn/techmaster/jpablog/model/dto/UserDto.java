package vn.techmaster.jpablog.model.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String avatar;
}
