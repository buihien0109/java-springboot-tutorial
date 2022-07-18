package vn.techmaster.entitytodto.dto;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserDto implements Serializable {
    private Integer id;
    private String name;
    private String email;
}
