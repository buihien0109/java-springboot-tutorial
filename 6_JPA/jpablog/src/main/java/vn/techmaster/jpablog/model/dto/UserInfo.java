package vn.techmaster.jpablog.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserInfo implements Serializable {
    private Integer id;
    private String name;
    private String email;
    private String avatar;
    private LocalDateTime identityCardExpried;
    private LocalDateTime identityCardIssued;
}
