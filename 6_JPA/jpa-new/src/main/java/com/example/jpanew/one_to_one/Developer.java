package com.example.jpanew.one_to_one;

import jakarta.persistence.*;
import lombok.*;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "developer")
public class Developer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    // Nếu để CascadeType = ALL => Làm hành động gì trên cha sẽ ảnh hưởng đến con
    // orphanRemoval = true : Thao tác với con sẽ thao tác với chính database
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "identity_id")
    private Identity identity;

//    @PreRemove
//    public void preRemove() {
//        this.identity.setDeveloper(null);
//    }
}