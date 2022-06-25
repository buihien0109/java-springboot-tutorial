package vn.techmaster.jpatutorial.id.id_identity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user_identity")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserIdentity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    private String name;
}

/*
* Cách tiếp cận này có một nhược điểm rất lớn trong Hibernate vì nó luôn yêu cầu primary key của mỗi entity phải có giá trị để quản lý, nhưng để lấy được giá trị tiếp theo của Identity column chỉ có cách thực thi câu SQL Insert thì mới có thể biết được.
* Điều này dẫn đến các câu lệnh SQL Insert được thực thi ngay lập tức để lấy giá trị của Identity column, khiến Hibernate phải vô hiệu hoá tính năng Batch Insert JDBC.
* */