package com.example.relationship.model.one_to_many.unidrection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    // @OneToMany liên kết thực thể mẹ với một hoặc nhiều thực thể con.
    // Nếu @OneToMany không có liên kết @ManyToOne phản chiếu ở phía con, thì liên kết @OneToMany là liên kết 1 chiều (unidirection).
    // Khi sử dụng liên kết @OneToMany đơn hướng, Hibernate sử dụng bảng liên kết giữa hai thực thể tham gia.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Phone> phones = new LinkedHashSet<>();
}